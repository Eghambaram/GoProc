package mobile;


/*
002 * Licensed to the Apache Software Foundation (ASF) under one or more
003 * contributor license agreements.  See the NOTICE file distributed with
004 * this work for additional information regarding copyright ownership.
005 * The ASF licenses this file to You under the Apache License, Version 2.0
006 * (the "License"); you may not use this file except in compliance with
007 * the License.  You may obtain a copy of the License at
008 *
009 *      http://www.apache.org/licenses/LICENSE-2.0
010 *
011 * Unless required by applicable law or agreed to in writing, software
012 * distributed under the License is distributed on an "AS IS" BASIS,
013 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
014 * See the License for the specific language governing permissions and
015 * limitations under the License.
016 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
027 * A ComparatorChain is a Comparator that wraps one or more Comparators in
028 * sequence. The ComparatorChain calls each Comparator in sequence until either
029 * 1) any single Comparator returns a non-zero result (and that result is then
030 * returned), or 2) the ComparatorChain is exhausted (and zero is returned).
031 * This type of sorting is very similar to multi-column sorting in SQL, and this
032 * class allows Java classes to emulate that kind of behaviour when sorting a
033 * List.
034 * <p>
035 * To further facilitate SQL-like sorting, the order of any single Comparator in
036 * the list can be reversed.
037 * <p>
038 * Calling a method that adds new Comparators or changes the ascend/descend sort
039 * <i>after compare(Object, Object) has been called</i> will result in an
040 * UnsupportedOperationException. However, <i>take care</i> to not alter the
041 * underlying List of Comparators or the BitSet that defines the sort order.
042 * <p>
043 * Instances of ComparatorChain are not synchronized. The class is not
044 * thread-safe at construction time, but it <i>is</i> thread-safe to perform
045 * multiple comparisons after all the setup operations are complete.
046 *
047 * @since 2.0
048 * @version $Id: ComparatorChain.html 972397 2015-11-14 15:01:49Z tn $
049 */
public class ComparatorChain<E> implements Comparator<E>, Serializable {

    /** Serialization version from Collections 2.0. */
    private static final long serialVersionUID = -721644942746081630L;

    /** The list of comparators in the chain. */
    private final List<Comparator<E>> comparatorChain;
    /** Order - false (clear) = ascend; true (set) = descend. */
    private BitSet orderingBits = null;
   /** Whether the chain has been "locked". */
    private boolean isLocked = false;

    //-----------------------------------------------------------------------
    /**
064     * Construct a ComparatorChain with no Comparators.
065     * You must add at least one Comparator before calling
066     * the compare(Object,Object) method, or an
067     * UnsupportedOperationException is thrown
068     */
    public ComparatorChain() {
        this(new ArrayList<Comparator<E>>(), new BitSet());
    }

    /**
074     * Construct a ComparatorChain with a single Comparator,
075     * sorting in the forward order
076     *
077     * @param comparator First comparator in the Comparator chain
078     */
    public ComparatorChain(final Comparator<E> comparator) {
        this(comparator, false);
    }

    /**
084     * Construct a Comparator chain with a single Comparator,
085     * sorting in the given order
086     *
087     * @param comparator First Comparator in the ComparatorChain
088     * @param reverse    false = forward sort; true = reverse sort
089     */
    public ComparatorChain(final Comparator<E> comparator, final boolean reverse) {
        comparatorChain = new ArrayList<Comparator<E>>(1);
        comparatorChain.add(comparator);
        orderingBits = new BitSet(1);
        if (reverse == true) {
            orderingBits.set(0);
        }
    }

    /**
100     * Construct a ComparatorChain from the Comparators in the
101     * List.  All Comparators will default to the forward
102     * sort order.
103     *
104     * @param list   List of Comparators
105     * @see #ComparatorChain(List,BitSet)
106     */
    public ComparatorChain(final List<Comparator<E>> list) {
        this(list, new BitSet(list.size()));
    }

    /**
112     * Construct a ComparatorChain from the Comparators in the
113     * given List.  The sort order of each column will be
114     * drawn from the given BitSet.  When determining the sort
115     * order for Comparator at index <i>i</i> in the List,
116     * the ComparatorChain will call BitSet.get(<i>i</i>).
117     * If that method returns <i>false</i>, the forward
118     * sort order is used; a return value of <i>true</i>
119     * indicates reverse sort order.
120     *
121     * @param list   List of Comparators.  NOTE: This constructor does not perform a
122     *               defensive copy of the list
123     * @param bits   Sort order for each Comparator.  Extra bits are ignored,
124     *               unless extra Comparators are added by another method.
125     */
    public ComparatorChain(final List<Comparator<E>> list, final BitSet bits) {
        comparatorChain = list;
        orderingBits = bits;
    }

    //-----------------------------------------------------------------------
    /**
133     * Add a Comparator to the end of the chain using the
134     * forward sort order
135     *
136     * @param comparator Comparator with the forward sort order
137     */
    public void addComparator(final Comparator<E> comparator) {
        addComparator(comparator, false);
    }

    /**
143     * Add a Comparator to the end of the chain using the
144     * given sort order
145     *
146     * @param comparator Comparator to add to the end of the chain
147     * @param reverse    false = forward sort order; true = reverse sort order
148     */
    public void addComparator(final Comparator<E> comparator, final boolean reverse) {
        checkLocked();

        comparatorChain.add(comparator);
        if (reverse == true) {
            orderingBits.set(comparatorChain.size() - 1);
        }
    }

    /**
159     * Replace the Comparator at the given index, maintaining
160     * the existing sort order.
161     *
162     * @param index      index of the Comparator to replace
163     * @param comparator Comparator to place at the given index
164     * @exception IndexOutOfBoundsException
165     *                   if index &lt; 0 or index &gt;= size()
166     */
    public void setComparator(final int index, final Comparator<E> comparator) throws IndexOutOfBoundsException {
        setComparator(index, comparator, false);
    }

    /**
172     * Replace the Comparator at the given index in the
173     * ComparatorChain, using the given sort order
174     *
175     * @param index      index of the Comparator to replace
176     * @param comparator Comparator to set
177     * @param reverse    false = forward sort order; true = reverse sort order
178     */
    public void setComparator(final int index, final Comparator<E> comparator, final boolean reverse) {
        checkLocked();

        comparatorChain.set(index,comparator);
        if (reverse == true) {
            orderingBits.set(index);
        } else {
            orderingBits.clear(index);
        }
    }

    /**
191     * Change the sort order at the given index in the
192     * ComparatorChain to a forward sort.
193     *
194     * @param index  Index of the ComparatorChain
195     */
    public void setForwardSort(final int index) {
        checkLocked();
        orderingBits.clear(index);
    }

    /**
202     * Change the sort order at the given index in the
203     * ComparatorChain to a reverse sort.
204     *
205     * @param index  Index of the ComparatorChain
206     */
    public void setReverseSort(final int index) {
        checkLocked();
        orderingBits.set(index);
    }

    /**
213     * Number of Comparators in the current ComparatorChain.
214     *
215     * @return Comparator count
216     */
    public int size() {
        return comparatorChain.size();
    }

    /**
222     * Determine if modifications can still be made to the
223     * ComparatorChain.  ComparatorChains cannot be modified
224     * once they have performed a comparison.
225     *
226     * @return true = ComparatorChain cannot be modified; false =
227     *         ComparatorChain can still be modified.
228     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
234     * Throws an exception if the {@link ComparatorChain} is locked.
235     *
236     * @throws UnsupportedOperationException if the {@link ComparatorChain} is locked
237     */
    private void checkLocked() {
        if (isLocked == true) {
            throw new UnsupportedOperationException(
                    "Comparator ordering cannot be changed after the first comparison is performed");
        }
    }

    /**
246     * Throws an exception if the {@link ComparatorChain} is empty.
247     *
248     * @throws UnsupportedOperationException if the {@link ComparatorChain} is empty
249     */
    private void checkChainIntegrity() {
        if (comparatorChain.size() == 0) {
            throw new UnsupportedOperationException("ComparatorChains must contain at least one Comparator");
        }
    }

   //-----------------------------------------------------------------------
    /**
258     * Perform comparisons on the Objects as per
259     * Comparator.compare(o1,o2).
260     *
261     * @param o1  the first object to compare
262     * @param o2  the second object to compare
263     * @return -1, 0, or 1
264     * @throws UnsupportedOperationException if the ComparatorChain does not contain at least one Comparator
265     */
    @Override
    public int compare(final E o1, final E o2) throws UnsupportedOperationException {
        if (isLocked == false) {
            checkChainIntegrity();
            isLocked = true;
        }

        // iterate over all comparators in the chain
        final Iterator<Comparator<E>> comparators = comparatorChain.iterator();
        for (int comparatorIndex = 0; comparators.hasNext(); ++comparatorIndex) {

            final Comparator<? super E> comparator = comparators.next();
            int retval = comparator.compare(o1,o2);
            if (retval != 0) {
                // invert the order if it is a reverse sort
                if (orderingBits.get(comparatorIndex) == true) {
                    if (retval > 0) {
                        retval = -1;
                    } else {
                        retval = 1;
                    }
                }
                return retval;
            }
        }

        // if comparators are exhausted, return 0
        return 0;
    }

    //-----------------------------------------------------------------------
    /**
298     * Implement a hash code for this comparator that is consistent with
299     * {@link #equals(Object) equals}.
300     *
301     * @return a suitable hash code
302     * @since 3.0
303     */
    @Override
    public int hashCode() {
        int hash = 0;
        if (null != comparatorChain) {
            hash ^= comparatorChain.hashCode();
        }
        if (null != orderingBits) {
            hash ^= orderingBits.hashCode();
        }
        return hash;
    }

    /**
317     * Returns <code>true</code> iff <i>that</i> Object is
318     * is a {@link Comparator} whose ordering is known to be
319     * equivalent to mine.
320     * <p>
321     * This implementation returns <code>true</code>
322     * iff <code><i>object</i>.{@link Object#getClass() getClass()}</code>
323     * equals <code>this.getClass()</code>, and the underlying
324     * comparators and order bits are equal.
325     * Subclasses may want to override this behavior to remain consistent
326     * with the {@link Comparator#equals(Object)} contract.
327     *
328     * @param object  the object to compare with
329     * @return true if equal
330     * @since 3.0
331     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (null == object) {
            return false;
        }
        if (object.getClass().equals(this.getClass())) {
            final ComparatorChain<?> chain = (ComparatorChain<?>) object;
            return (null == orderingBits ? null == chain.orderingBits : orderingBits.equals(chain.orderingBits)) &&
                   (null == comparatorChain ? null == chain.comparatorChain :
                                              comparatorChain.equals(chain.comparatorChain));
        }
        return false;
    }

}
