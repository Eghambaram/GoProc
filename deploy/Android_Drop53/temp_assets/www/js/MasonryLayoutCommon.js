/* Copyright (c) 2011, 2012, Oracle and/or its affiliates. All rights reserved. */
/* ------------------------------------------------------ */
/* ------------------- MasonryLayoutCommon.js -------------------- */
/* ------------------------------------------------------ */

(function () {

/*
** Copyright (c) 2014, Oracle and/or its affiliates. All rights reserved.
** Important:
** - This file is designed to be shared verbatim among the ADFui products.
** - Do not add framework-specific dependencies in this file (it must be self-contained).
** - Do not change this file without testing it in other ADFui products (ADF Faces, JET, etc.).
*/
if (!window.adf) { /** @expose */ window.adf = {}; };
window.adf.shared                     = window.adf.shared || {};
window.adf.shared.impl                = window.adf.shared.impl || {};
window.adf.shared.impl.masonryLayout  = window.adf.shared.impl.masonryLayout || {};
// must be explicitly assigned to the window.adf.shared.impl object for cross-framework sharing
/**
 * Constructor.
 * @param {Object} elem DOM element associated with the masonry layout
 * @param {boolean} rtl True if the reading direction is right-to-left, otherwise false
 * @param {boolean} automationEnabled True if automation mode is enabled, otherwise false
 * @param {Object} selectors Map of properties for the following selector information:
 *  - tiles: Selector for child tiles.
 * @param {Object} styles Map of properties for the following style classes:
 *  - transitionComponentResizeToStyleClass: Transition for resizing the masonryLayout,
 *  - transitionComponentResizeToFastStyleClass: Transition for resizing the masonryLayout faster,
 *  - transitionMoveToStyleClass: Transition for moving a child tile,
 *  - transitionMoveToFastStyleClass: Transition for moving a child tile faster,
 *  - transitionHideFromStyleClass: Initial state for transition to hide a child tile,
 *  - transitionHideToStyleClass: Transition for hiding a child tile,
 *  - transitionShowFromStyleClass: Initial state for transition to show a child tile,
 *  - transitionShowToStyleClass: Transition for showing a child tile,
 *  - transitionResizeToStyleClass: Transition for resizing a child tile.
 * @param {Object} callbackInfo Map of properties for the following callback functions:
 *  - addStyleClassName: Add a style class to a DOM element,
 *  - removeStyleClassName: Remove a style class from a DOM element,
 *  - getSizeStyleClassName: Get the style class for the tile size,
 *  - getTileSpan: Get the tile span,
 *  - showTileOnEndFunc: Called after a tile is shown,
 *  - hideTileOnEndFunc: Called after a tile is hideden,
 *  - layoutOnEndFunc: Called after layout.
 *  @constructor
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon = function(
  elem, 
  rtl, 
  automationEnabled,
  selectors, 
  styles, 
  callbackInfo)
{
  //this agent initialization concept copied from adf.shared.impl.animationUtils.transition()
  var mlcClass = window.adf.shared.impl.masonryLayout.MasonryLayoutCommon;
  if (mlcClass._agentTypeAndVersion == null)
  {
    // Do a 1-time agent initialization
    mlcClass._agentTypeAndVersion = mlcClass._getAgentTypeAndVersion(navigator.userAgent);
  }
  
  this._elem = elem;
  this._rtl = rtl;
  this._automationEnabled = automationEnabled;
  if (selectors)
  {
    if (selectors.tiles)
      this._tilesSelector = selectors.tiles;
  }
  if (styles)
  {
    if (styles.transitionComponentResizeToStyleClass)
      this._transitionComponentResizeToStyleClass = styles.transitionComponentResizeToStyleClass;
    if (styles.transitionComponentResizeToFastStyleClass)
      this._transitionComponentResizeToFastStyleClass = styles.transitionComponentResizeToFastStyleClass;
    if (styles.transitionMoveToStyleClass)
      this._transitionMoveToStyleClass = styles.transitionMoveToStyleClass;
    if (styles.transitionMoveToFastStyleClass)
      this._transitionMoveToFastStyleClass = styles.transitionMoveToFastStyleClass;
    if (styles.transitionHideFromStyleClass)
      this._transitionHideFromStyleClass = styles.transitionHideFromStyleClass;
    if (styles.transitionHideToStyleClass)
      this._transitionHideToStyleClass = styles.transitionHideToStyleClass;
    if (styles.transitionShowFromStyleClass)
      this._transitionShowFromStyleClass = styles.transitionShowFromStyleClass;
    if (styles.transitionShowToStyleClass)
      this._transitionShowToStyleClass = styles.transitionShowToStyleClass;
    if (styles.transitionResizeToStyleClass)
      this._transitionResizeToStyleClass = styles.transitionResizeToStyleClass;
  }
  if (callbackInfo)
  {
    //need to check for existence of properties on callbackInfo before assigning 
    //them to vars because advanced closure compiler will otherwise complain if 
    //they're not defined
    if (callbackInfo.addStyleClassName)
      this._addStyleClassNameFunc = callbackInfo.addStyleClassName;
    if (callbackInfo.removeStyleClassName)
      this._removeStyleClassNameFunc = callbackInfo.removeStyleClassName;
    if (callbackInfo.getSizeStyleClassName)
      this._getSizeStyleClassNameFunc = callbackInfo.getSizeStyleClassName;
    if (callbackInfo.getTileSpan)
      this._getTileSpanFunc = callbackInfo.getTileSpan;
    if (callbackInfo.showTileOnEndFunc)
      this._showTileOnEndFunc = callbackInfo.showTileOnEndFunc;
    if (callbackInfo.hideTileOnEndFunc)
      this._hideTileOnEndFunc = callbackInfo.hideTileOnEndFunc;
    if (callbackInfo.layoutOnEndFunc)
      this._layoutOnEndFunc = callbackInfo.layoutOnEndFunc;
  }
  
  //create a non-absolutely positioned div to define the size of the 
  //infolet layout, because the absolutely positioned infolets are not 
  //part of the flow and will not define the size of the component div
  var sizeDivWrapper = document.createElement("div");
  var style = sizeDivWrapper.style;
  style.display = "inline-block";
  style.overflow = "hidden";
  style.visibility = "hidden";
  var sizeDiv = document.createElement("div");
  style = sizeDiv.style;
  style.display = "inline-block";
  sizeDivWrapper.appendChild(sizeDiv);
  elem.insertBefore(sizeDivWrapper, elem.firstChild);
  this._sizeDivWrapper = sizeDivWrapper;
  this._sizeDiv = sizeDiv;

  var self = this;
  this._handleTransitionEndFunc = function(event) {
      self._handleTransitionEnd(event);
  };
  this._hideTilesFunc = function() {
      self._hideTiles();
  };
  this._handleHideTransitionEndFunc = function(event) {
      self._handleHideTransitionEnd(event);
  };
  this._handleShowTransitionEndFunc = function(event) {
      self._handleShowTransitionEnd(event);
  };
};

/**
 * Setup the masonry layout.
 * @param {boolean} init True for initialization, false for refresh
 * @param {boolean} reorder True for reordering, false otherwise
 * @return {boolean} True if layout changed, false if not
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype.setup = function(init, reorder)
{
  var ret = false;
  if (init)
  {
    ret = this._layout() ? true : false;
  }
  else
  {
    //if this is a refresh, add the transition class BEFORE doing the next layout
    this._transitionStart(reorder);
    ret = this._transitionLayout();
  }
  return ret;
};

/**
 * Destroy the masonry layout.
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype.destroy = function()
{
  var elem = this._elem;
  
  elem.removeChild(this._sizeDivWrapper);
  this._sizeDivWrapper = null;
  this._sizeDiv = null;
  
  this._handleTransitionEndFunc = null;
  this._hideTilesFunc = null;
  this._handleHideTranitionEndFunc = null;
  this._handleShowTransitionEndFunc = null;
  
  this._arMovedInfolets = null;
  this._arInfoletsToResize = null;
  this._arInfoletsToShow = null;
  this._arInfoletsToHide = null;
  this._arFireHideOnEnd = null;
  
  this._elem = null;
  this._addStyleClassNameFunc = null;
  this._removeStyleClassNameFunc = null;
  this._getSizeStyleClassNameFunc = null;
  this._getTileSpanFunc = null;
  this._showTileOnEndFunc = null;
  this._hideTileOnEndFunc = null;
  this._layoutOnEndFunc = null;
};

/**
 * Resize a child tile.
 * @param {String} selector Selector for the tile to resize
 * @param {String} sizeStyleClass New size style class 
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype.resizeTile = function(selector, sizeStyleClass)
{
  var elem = this._elem;
  var infolet = elem.querySelector(selector);
  if (infolet)
  {
    //add the transition class immediately, but defer the layout call using a 
    //timeout so that the app can set new sizes on the children before the
    //layout call happens so that the size changes will be animated along with
    //the relayout
    if (!this._arInfoletsToResize)
      this._arInfoletsToResize = [];
    var arInfoletsToResize = this._arInfoletsToResize;
    arInfoletsToResize.push(infolet);
    arInfoletsToResize.push(sizeStyleClass);
    this._resizingInfolet = true;
    
    this._queueRelayout();
  }
};

/**
 * Insert a tile into the masonryLayout.
 * @param {Object} tileDomElem Tile to insert
 * @param {Number} index Index at which to insert
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype.insertTileDomElem = function(tileDomElem, index)
{
  var arChildren = this._getTileChildren();
  var currChildAtIndex = null;
  if (index >= 0 && index < arChildren.length)
    currChildAtIndex = arChildren[index];
  var elem = this._elem;
  elem.insertBefore(tileDomElem, currChildAtIndex);
  
  this._queueRelayout();
};

/**
 * Show a hidden tile.
 * @param {String} selector Selector for the tile to show
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype.showTile = function(selector)
{
  var elem = this._elem;
  var infolet = elem.querySelector(selector);
  if (infolet)
  {
    if (!this._arInfoletsToShow)
      this._arInfoletsToShow = [];
    var arInfoletsToShow = this._arInfoletsToShow;
    arInfoletsToShow.push(infolet);
    this._showingInfolets = true;
    
    //don't queue another layout if we're already doing layout and in a phase
    //before the SHOW phase
    var mlcClass = window.adf.shared.impl.masonryLayout.MasonryLayoutCommon;
    if (this._layoutPhase !== mlcClass._PHASE_HIDE &&
        this._layoutPhase !== mlcClass._PHASE_LAYOUT)
    {
      this._queueRelayout();
    }
    else
    {
      //if not queueing a relayout, need to explicitly set this flag to false
      //now so that future component resizes will be processed in resizeNotify()
      this._showingInfolets = false;
    }
  }
};

/**
 * Hide a tile.
 * @param {String} selector Selector for the tile to hide
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype.hideTile = function(selector)
{
  var elem = this._elem;
  var infolet = elem.querySelector(selector);
  if (infolet)
  {
    if (!this._arInfoletsToHide)
      this._arInfoletsToHide = [];
    var arInfoletsToHide = this._arInfoletsToHide;
    arInfoletsToHide.push(infolet);
    this._hidingInfolets = true;
    
    this._queueRelayout();
  }
};

/**
 * Notify the MasonryLayoutCommon that the masonryLayout component has been resized.
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype.resizeNotify = function()
{
  //don't respond to resize events when we're already doing layout
  if (!this._resizingInfolet && 
      !this._hidingInfolets && 
      !this._showingInfolets)
  {
    this._transitionStart(false);
    this._transitionLayout();
  }
};

/**
 * Get the size of a unit cell.
 * @return {Object} object specifying unit cell size with properties w and h
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype.getCellSize = function()
{
  return this._cellSize;
};

/**
 * Determine whether animation is enabled.
 * @return {boolean} true if animation is enabled, false if not
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype.isAnimationEnabled = function()
{
  if (!this._cachedAnimationEnabled)
  {
    if (this._automationEnabled)
    {
      this._animationEnabled = false;
    }
    else
    {
      var mlcClass = window.adf.shared.impl.masonryLayout.MasonryLayoutCommon;
      var agentType = mlcClass._agentTypeAndVersion[0];
      var agentVersion = mlcClass._agentTypeAndVersion[1];
    
      //check whether the agent supports CSS transitions
      this._animationEnabled = mlcClass._isMinimumAgentMet(agentType, agentVersion, 
                                                           "gecko", 16, 
                                                           "trident", 6, 
                                                           "webkit", 533.1);
    }
    this._cachedAnimationEnabled = true;
  }
  return this._animationEnabled;
};

/**
 * Get the size of the given DOM element (including margins).
 * @param {Object} elem DOM element
 * @return {Object} Object specifying size of element with properties w and h
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon._getElemSize = function(elem)
{
  var mlcClass = window.adf.shared.impl.masonryLayout.MasonryLayoutCommon;
  var computedStyle = mlcClass._getComputedStyle(elem);
  var extraWidth = mlcClass._getCSSLengthAsInt(computedStyle.marginLeft) + 
                   mlcClass._getCSSLengthAsInt(computedStyle.marginRight);
  var extraHeight = mlcClass._getCSSLengthAsInt(computedStyle.marginTop) + 
                    mlcClass._getCSSLengthAsInt(computedStyle.marginBottom);
  return {w: elem.offsetWidth + extraWidth, h: elem.offsetHeight + extraHeight};
};

/**
 * Get the insets of a given DOM element (padding and border).
 * @param {Object} elem DOM element
 * @return {Object} Object specifying size of insets with properties:
 *         paddingLeft, paddingRight, paddingTop, paddingBottom,
 *         bodrerLeftWidth, borderRightWidth, borderTopWidth, borderBottomWidth
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon._getElemInsets = function(elem)
{
  var mlcClass = window.adf.shared.impl.masonryLayout.MasonryLayoutCommon;
  var computedStyle = mlcClass._getComputedStyle(elem);
  return {paddingLeft:       mlcClass._getCSSLengthAsInt(computedStyle.paddingLeft), 
          paddingRight:      mlcClass._getCSSLengthAsInt(computedStyle.paddingRight), 
          paddingTop:        mlcClass._getCSSLengthAsInt(computedStyle.paddingTop), 
          paddingBottom:     mlcClass._getCSSLengthAsInt(computedStyle.paddingBottom),
          borderLeftWidth:   mlcClass._getCSSLengthAsInt(computedStyle.borderLeftWidth), 
          borderRightWidth:  mlcClass._getCSSLengthAsInt(computedStyle.borderRightWidth),
          borderTopWidth:    mlcClass._getCSSLengthAsInt(computedStyle.borderTopWidth), 
          borderBottomWidth: mlcClass._getCSSLengthAsInt(computedStyle.borderBottomWidth)};
};

/**
 * Get the computed style of the given DOM element.
 * @param {Object} elem DOM element
 * @return {Object} Computed style for the element
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon._getComputedStyle = function(elem)
{
  var elemOwnerDoc = elem.ownerDocument;
  var defView = elemOwnerDoc.defaultView;
  var computedStyle = null;
  if (defView)
  {
    //this line copied from AdfAgent.getComputedStyle()
    computedStyle = defView.getComputedStyle(elem, null);
  }
  else
  {
    //this line copied from AdfIEAgent.getComputedStyle()
    computedStyle = elem.currentStyle;
  }
  return computedStyle;
};

/**
 * Get the int value of a CSS length.
 * @param {string} cssLength cssLength as a String
 * @return {number} cssLength as an int
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon._getCSSLengthAsInt = function(cssLength)
{
  //this function copied from AdfAgent.getCSSLengthAsInt
  if ((cssLength.length) > 0 && (cssLength != 'auto'))
  {
    var intLength = parseInt(cssLength, 10);

    if (isNaN(intLength))
      intLength = 0;

    return intLength;
  }
  return 0;
};

/**
 * Add a bubble event listener to the given DOM node.
 * @param {Object} node DOM node
 * @param {string} type Event type
 * @param {Function} listener Listener function
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon._addBubbleEventListener = function(node, type, listener)
{
  if (node.addEventListener)
  {
    node.addEventListener(type, listener, false);
  }
  else if (node.attachEvent)
  {
    node.attachEvent("on" + type, listener);
  }
};

/**
 * Remove a bubble event listener from the given DOM node.
 * @param {Object} node DOM node
 * @param {string} type Event type
 * @param {Function} listener Listener function
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon._removeBubbleEventListener = function(node, type, listener)
{
  if (node.removeEventListener)
  {
    node.removeEventListener(type, listener, false);
  }
  else if (node.detachEvent)
  {
    node.detachEvent("on" + type, listener);
  }
};

/**
 * Get the index of an item in an array.
 * @param {Array} array Array to search
 * @param {Object} item Item to search for
 * @return {Number} index of item in array, or -1 if not found
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon._arrayIndexOf = function(array, item)
{
  if (array)
  {
    for (var i = 0; i < array.length; i++)
    {
      if (array[i] == item)
        return i;
    }
  }
  return -1;
};

/**
 * Gets whether the specified agent minimum requirements are met or exceeded.
 * Every 2 arguments past actualAgentType and actualAgentVersion must correspond
 * to a minimum required agent type and floating point version number.
 * @param {String} actualAgentType the actual agent type ("trident", "webkit", "gecko")
 * @param {Float} actualAgentVersion the actual agent version number as a floating point number
 * @return {Boolean} whether the specified agent minimums are met
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon._isMinimumAgentMet = function(actualAgentType, actualAgentVersion)
{
  //This function was copied from adf.shared.impl.animationUtils
  
  var argCount = arguments.length;
  if (argCount % 2 == 0) // even number
  {
    // Loop through each requirement pair to see if we match one
    for (var i = 2; i <= argCount - 2; i += 2)
    {
      var requirementType = arguments[i];
      if (actualAgentType == requirementType)
      {
        // We found an agent type match so now see if the actual version is greater than or equal
        // to the requirement version number:
        var requirementVersion = arguments[1+i];
        if (actualAgentVersion >= requirementVersion)
          return true; // met requirement
        else
          return false; // failed requirement
      }
    }
  }
  return false; // no agent type match found; failed requirement
};

/**
 * Gets the agent type and version.
 * @param {String} givenUserAgentString the navigator's userAgent property
 * @return {Array<Object>} with 2 members, a String for the agent 
 *         type ("trident", "webkit", "gecko") and a Float for the agent version
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon._getAgentTypeAndVersion = function(givenUserAgentString)
{
  //This function was copied from adf.shared.impl.animationUtils
  
  var mlcClass = window.adf.shared.impl.masonryLayout.MasonryLayoutCommon;
  var versionParser = mlcClass._parseFloatVersion;
  var agentType = null;
  var agentVersion = -1;
  var userAgent = givenUserAgentString.toLowerCase();
  if (userAgent.indexOf("msie") != -1 || userAgent.indexOf("trident") != -1)
  {
    agentType = "trident";
    var possibleVersion = versionParser(userAgent, /trident\/(\d+[.]\d+)/);
    if (possibleVersion != -1)
    {
      // 6.0 = IE10
      // 5.0 = IE9
      // 4.0 = IE8
      agentVersion = possibleVersion;
    }
    else
    {
      possibleVersion = versionParser(userAgent, /msie (\d+\.\d+);/);
      if (possibleVersion == -1)
        possibleVersion = versionParser(userAgent, /msie (\d+\.\d+)b;/); // expression for betas
      agentVersion = possibleVersion - 4; // Trident versions are 4 behind IE numbers
    }
    if (document.documentMode != null)
    {
      // If a documentMode is provided, it would be an IE number and Trident versions are 4 behind IE numbers.
      // The actual Trident version in use would be the smaller of the 2 numbers:
      agentVersion = Math.min(agentVersion, document.documentMode - 4);
    }
  }
  else if (userAgent.indexOf("applewebkit") != -1)
  {
    agentType = "webkit";
    // 536.26.17 = Mac Desktop Safari 6.0.2
    // 535.1 = Chrome 13.0.782.1
    // 534.46 = Safari 5.1 or iOS 5
    // 525.18 = Mac/Windows Desktop Safari 3.1.1
    // 420.1 = iOS 3
    agentVersion = versionParser(userAgent, /applewebkit\/(\d+([.]\d+)*)/);
  }
  else if (userAgent.indexOf("gecko/")!=-1)
  {
    agentType = "gecko";
    // rv:5 = Firefox 5
    // rv:2 = Firefox 4
    // rv:1.9 = Firefox 3
    // rv:1.8.1 = Firefox 2
    // rv:1.8 = Firefox 1.5
    agentVersion = versionParser(userAgent, /rv:(\d+[.]\d+)/);
  }
  return [ agentType, agentVersion ];
};

/**
 * Parses the float version out of of the specified agent string using
 * a regular expression to identify the version portion of the string.
 * @param {String} userAgent the lowercase navigator user agent string
 * @param {RegExp} versionNumberPattern the regular expression pattern 
 *        used to extract a number that will be parsed into a float
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon._parseFloatVersion = function(userAgent, versionNumberPattern)
{
  //This function was copied from adf.shared.impl.animationUtils
  
  var matches = userAgent.match(versionNumberPattern);
  if (matches)
  {
    var versionString = matches[1];
    if (versionString)
      return parseFloat(versionString);
  }
  return -1;
};

/**
 * Queue a relayout.
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._queueRelayout = function()
{
  if (!this._hideTilesTimeout)
    this._hideTilesTimeout = setTimeout(this._hideTilesFunc, 0);
};

/**
 * Get the rendered child tiles.
 * @return {Array} Array of rendered child tiles
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._getTileChildren = function()
{
  var elem = this._elem;
  var children = elem.querySelectorAll(this._tilesSelector);
  var arChildren = [];
  for (var i = 0; i < children.length; i++)
  {
    var child = children[i];
    var childStyle = child.style;
    if (child.offsetWidth > 0 && child.offsetHeight > 0 && childStyle.visibility != "hidden")
      arChildren.push(child);
  }
  return arChildren;
};

/**
 * Layout and animate with a transition.  This is the second phase of the
 * <hide, layout, show> sequence.
 * @return {boolean} true if layout changed, otherwise false
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._transitionLayout = function()
{
  var oldMovedInfolets = this._arMovedInfolets;
  var newMovedInfolets = this._layout();
  
  //an infolet should be considered to "move" if it's resized, even if it doesn't change position,
  //because it will still go through a transition
  if (this._arInfoletsToResize)
  {
    var arInfoletsToResize = this._arInfoletsToResize;
    if (!newMovedInfolets)
      newMovedInfolets = [];
    var mlcClass = window.adf.shared.impl.masonryLayout.MasonryLayoutCommon;
    for (var i = 0; i < arInfoletsToResize.length; i += 2)
    {
      var resizedInfolet = arInfoletsToResize[i];
      if (mlcClass._arrayIndexOf(newMovedInfolets, resizedInfolet) < 0)
        newMovedInfolets.push(resizedInfolet);
    }
  }
  
  var calledHandleTransitionEnd = false;
  if (!newMovedInfolets || newMovedInfolets.length < 1)
  {
    if (!oldMovedInfolets || oldMovedInfolets.length < 1)
    {
      this._arMovedInfolets = null;
      this._handleTransitionEnd(null);
      calledHandleTransitionEnd = true;
    }
  }
  else
  {
    this._arMovedInfolets = newMovedInfolets;
  }
  
  var ret = (newMovedInfolets && newMovedInfolets.length > 0);
  
  if (!this.isAnimationEnabled() && !calledHandleTransitionEnd)
    this._handleTransitionEnd(null);
  
  return ret;
};

/**
 * Layout.
 * @return {Array} Array of tiles that were moved
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._layout = function()
{
  var elem = this._elem;
  var children = this._getTileChildren();
  //always recalculate cell size in case the app has specified media queries
  //to change CSS cell sizes based on screen or component size
  this._cellSize = null;
  var cellSize = null;
  this._cols = 0;
  this._rows = 1;
  this._occupancyMap = null;
  var arMovedInfolets = [];
  var arOldPositions = [];
  var arCols = [];
  var rtl = this._rtl;
  //FIX BUG 19790795: need to take container border and padding into account for layout
  var mlcClass = window.adf.shared.impl.masonryLayout.MasonryLayoutCommon;
  var insets = mlcClass._getElemInsets(elem);
  for (var i = 0; i < children.length; i++)
  {
    var child = children[i];

    var childSpan = this._getTileSpanFunc(child);

    //get the old size style class if we've saved it on an infolet
    //being resized, and then delete it from the infolet
    var oldSizeStyleClass = child._afrOldSizeStyleClass;
    if (oldSizeStyleClass)
      delete child._afrOldSizeStyleClass;
    
    if (!this._cellSize)
    {
      var spanForCellSize = childSpan;
      //if we've saved an old size style class on the infolet, we need to
      //use it to calculate cell size because the infolet is being resized and 
      //we've already applied the new size style class to it, but the element
      //size still corresponds to the old size style class
      if (oldSizeStyleClass)
      {
        var tmpDiv = document.createElement("div");
        tmpDiv.className = oldSizeStyleClass;
        spanForCellSize = this._getTileSpanFunc(tmpDiv);
      }
      this._cellSize = this._calcCellSize(child, spanForCellSize);
    }
    cellSize = this._cellSize;

    //keep track of which cells are occupied
    if (!this._occupancyMap)
    {
      //make sure we have at least 1 column to work with
      //FIX BUG 19790795: width available for layout is the offsetWidth minus
      //padding and borders
      var effectiveWidth = elem.offsetWidth - insets.paddingLeft - insets.paddingRight -
                           insets.borderLeftWidth - insets.borderRightWidth;
      this._cols = Math.max(Math.floor(effectiveWidth / cellSize.w), 1);
      this._initOccupancyMap(this._cols, this._rows);
    }

    //handle case where childSpan is bigger than the number of cols by treating
    //childSpan as if it equaled the number of cols (can only modify childSpan
    //AFTER using original value to calculate cellSize above)
    if (childSpan.colSpan > this._cols)
      childSpan.colSpan = this._cols;

    var next = false;
    for (var r = 0; r < this._rows; r++)
    {
      for (var c = 0; c < this._cols; c++)
      {
        if (this._fits(c, r, childSpan))
        {
          //save old position before applying new one so that we can use it below to 
          //figure out which infolets actually moved
          var childStyle = child.style;
          var oldPosition = {top: childStyle.top};
          //FIX BUG 19826013: in RTL, position tiles using "right" instead of "left"
          if (rtl)
            oldPosition.right = childStyle.right;
          else
            oldPosition.left = childStyle.left;
          arOldPositions.push(oldPosition);
          this._position(child, c, r, childSpan, cellSize, insets);
          if (rtl)
            arCols.push(c);
          next = true;
          break;
        }
      }
      if (next)
        break;
      if (r === this._rows - 1)
      {
        this._addRow();
      }
    }
  }

  //only need to adjust sizeDiv and adjust layout for rtl if there are child tiles
  if (cellSize)
  {
    var sizeDiv = this._sizeDiv;
    var style = sizeDiv.style;
    style.width = (this._cols * cellSize.w) + "px";
    style.height = (this._rows * cellSize.h) + "px";
  }
  
  //figure out which infolets actually moved (need to do this AFTER adjusting for RTL above)
  for (var i = 0; i < children.length; i++)
  {
    var child = children[i];
    var childStyle = child.style;
    var oldPosition = arOldPositions[i];
    
    //round to int because fractional value differences in Firefox don't actually result in transition,
    //and it really doesn't make much difference to the user whether they're animated because 
    //they're so small
    //FIX BUG 19826013: in RTL, position tiles using "right" instead of "left"
    if ((rtl && (parseInt(childStyle.right, 10) !== parseInt(oldPosition.right, 10))) ||
        (!rtl && (parseInt(childStyle.left, 10) !== parseInt(oldPosition.left, 10))) || 
        (parseInt(childStyle.top, 10) !== parseInt(oldPosition.top, 10)))
    {
      arMovedInfolets.push(child);
    }
  }

  if (arMovedInfolets.length < 1)
    arMovedInfolets = null;
  return arMovedInfolets;
};

/**
 * Initialize the map of which cells are occupied.
 * @param {Number} cols Number of columns in the layout
 * @param {Number} rows Number of rows in the layout
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._initOccupancyMap = function(cols, rows)
{
  this._occupancyMap = [];
  var occupancyMap = this._occupancyMap;
  for (var row = 0; row < rows; row++)
  {
    var arCols = [];
    occupancyMap.push(arCols);
    for (var col = 0; col < cols; col++)
      arCols[col] = false;
  }
};

/**
 * Add a row to the layout.
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._addRow = function()
{
  this._rows++;

  var arCols = [];
  var occupancyMap = this._occupancyMap;
  occupancyMap.push(arCols);
  for (var col = 0; col < this._cols; col++)
    arCols[col] = false;
};

/**
 * Get whether a tile of the given size fits at the given location.
 * @param {Number} col Column index
 * @param {Number} row Row index
 * @param {Object} childSpan Object specifying tile span with colSpan and rowSpan properties
 * @return {boolean} True if the tile fits, false otherwise
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._fits = function(col, row, childSpan)
{
  var colSpan = childSpan.colSpan;
  var rowSpan = childSpan.rowSpan;
  for (var r = row; r < row + rowSpan; r++)
  {
    if (r >= this._rows)
      this._addRow();
    for (var c = col; c < col + colSpan; c++)
    {
      if (c >= this._cols)
        return false;
      if (this._occupancyMap[r][c])
        return false;
    }
  }
  return true;
};

/**
 * Position a child tile.
 * @param {Object} child Tile to position
 * @param {Number} col Column index
 * @param {Number} row Row index
 * @param {Object} childSpan Object specifying child span with colSpan and rowSpan properties
 * @param {Object} cellSize Object specifying unit cell size with w and h properties
 * @param {Object} insets The insets of the layout container
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._position = function(child, col, row, childSpan, cellSize, insets)
{
  var colSpan = childSpan.colSpan;
  var rowSpan = childSpan.rowSpan;
  var occupancyMap = this._occupancyMap;
  for (var r = row; r < row + rowSpan; r++)
  {
    for (var c = col; c < col + colSpan; c++)
      occupancyMap[r][c] = true;
  }
  //FIX BUG 19790795: take padding into account
  var style = child.style;
  var newTop = insets.paddingTop + (row * cellSize.h);
  style.top = newTop + "px";
  //FIX BUG 19826013: in RTL, position tiles using "right" instead of "left"
  if (this._rtl)
  {
    var newRight = insets.paddingRight + (col * cellSize.w);
    style.right = newRight + "px";
  }
  else
  {
    var newLeft = insets.paddingLeft + (col * cellSize.w);
    style.left = newLeft + "px";
  }
};

/**
 * Add a style class to all of the rendered tile children.
 * @param {String} styleClassName Style class to add
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._addStyleClassToTiles = function(styleClassName)
{
  var children = this._getTileChildren();
  for (var i = 0; i < children.length; i++)
    this._addStyleClassNameFunc(children[i], styleClassName);
};

/**
 * Remove a style class from all of the rendered tile children.
 * @param {String} styleClassName Style class to remove
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._removeStyleClassFromTiles = function(styleClassName)
{
  var children = this._getTileChildren();
  for (var i = 0; i < children.length; i++)
    this._removeStyleClassNameFunc(children[i], styleClassName);
};

/**
 * Start a layout transition.
 * @param {boolean} reorder True if this layout is due to a reorder, false otherwise
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._transitionStart = function(reorder)
{
  if (!this._layoutTransition)
  {
    this._reorderTransitionStarted = reorder;
    if (this.isAnimationEnabled())
    {
      var styleClass = reorder ? this._transitionMoveToFastStyleClass : this._transitionMoveToStyleClass;
      this._addStyleClassToTiles(styleClass);
      var compStyleClass = reorder ? this._transitionComponentResizeToFastStyleClass : this._transitionComponentResizeToStyleClass;
      this._addStyleClassNameFunc(this._sizeDiv, compStyleClass);
      var mlcClass = window.adf.shared.impl.masonryLayout.MasonryLayoutCommon;
      mlcClass._addBubbleEventListener(this._elem, "transitionend", this._handleTransitionEndFunc);
      mlcClass._addBubbleEventListener(this._elem, "webkitTransitionEnd", this._handleTransitionEndFunc);
    }
    this._layoutTransition = true;
  }
};

/**
 * Handle a layout transition end.
 * @param {Object} event Event object
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._handleTransitionEnd = function(event)
{
  var doneTransitioning = true;
  if (this._arMovedInfolets)
  {
    var arMovedInfolets = this._arMovedInfolets;
    if (event)
    {
      var target = event.target;
      for (var i = 0; i < arMovedInfolets.length; i++)
      {
        if (target === arMovedInfolets[i])
        {
          arMovedInfolets.splice(i, 1);
          break;
        }
      }
    }
    else if (!this.isAnimationEnabled())
    {
      //if animation is not enabled, clear all the moved infolets at the same time
      this._arMovedInfolets = [];
      arMovedInfolets = this._arMovedInfolets;
    }
    //if there are still tiles for which we haven't gotten a transition end event yet,
    //then we're not done yet
    if (arMovedInfolets.length > 0)
      doneTransitioning = false;
  }
  if (doneTransitioning)
  {
    if (this._arInfoletsToResize)
    {
      var arInfoletsToResize = this._arInfoletsToResize;
      this._arInfoletsToResize = null;
      
      if (this.isAnimationEnabled())
      {
        for (var i = 0; i < arInfoletsToResize.length; i += 2)
        {
          var infolet = arInfoletsToResize[i];
          this._removeStyleClassNameFunc(infolet, this._transitionResizeToStyleClass);
        }
      }
    }
    
    if (this._reorderTransitionStarted)
    {
      if (this.isAnimationEnabled())
      {
        this._removeStyleClassFromTiles(this._transitionMoveToFastStyleClass);
        this._removeStyleClassNameFunc(this._sizeDiv, this._transitionComponentResizeToFastStyleClass);
      }
      this._reorderTransitionStarted = false;
    }
    else
    {
      if (this.isAnimationEnabled())
      {
        this._removeStyleClassFromTiles(this._transitionMoveToStyleClass);
        this._removeStyleClassNameFunc(this._sizeDiv, this._transitionComponentResizeToStyleClass);
      }
    }
    var mlcClass = window.adf.shared.impl.masonryLayout.MasonryLayoutCommon;
    if (this.isAnimationEnabled())
    {
      mlcClass._removeBubbleEventListener(this._elem, "transitionend", this._handleTransitionEndFunc);
      mlcClass._removeBubbleEventListener(this._elem, "webkitTransitionEnd", this._handleTransitionEndFunc);
    }
    this._layoutTransition = false;
    this._resizingInfolet = false;
    this._hidingInfolets = false;
    this._showingInfolets = false;
    
    if (this._layoutOnEndFunc)
      this._layoutOnEndFunc();
    
    if (this._layoutPhase === mlcClass._PHASE_LAYOUT)
    {
      if (this.isAnimationEnabled())
      {
        //Need to use a timeout here because we don't keep track of all the expected
        //transitionend events from the layout phase.  We listened for the first one
        //and removed the listener and transition style classes.  But, if we 
        //immediately _showTiles, the transitionend listener we add in there
        //may receive some leftover layout transitionend events.  By using the
        //timeout, we don't inadvertently get those leftover events.  
        var self = this;
        setTimeout(function() {
          self._showTiles();
        }, 0);
      }
      else
      {
        this._showTiles();
      }
    }
  }
};

/**
 * Calculate the unit cell size.
 * @param {Object} child Tile to use for calculating cell size
 * @param {Object} childSpan Object specifying child span with colSpan and rowSpan properties
 * @return {Object} Object specifying unit cell size with w and h properties
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._calcCellSize = function(child, childSpan)
{
  var mlcClass = window.adf.shared.impl.masonryLayout.MasonryLayoutCommon;
  var childSize = mlcClass._getElemSize(child);
  return {w: childSize.w / childSpan.colSpan, h: childSize.h / childSpan.rowSpan};
};

/**
 * Hide any tiles waiting to be hidden.  This is the first phase of the
 * <hide, layout, show> sequence.
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._hideTiles = function()
{
  if (this._hideTilesTimeout)
  {
    clearTimeout(this._hideTilesTimeout);
    this._hideTilesTimeout = null;
  }
  
  var mlcClass = window.adf.shared.impl.masonryLayout.MasonryLayoutCommon;
  this._layoutPhase = mlcClass._PHASE_HIDE;
  
  if (this._arInfoletsToHide && this.isAnimationEnabled())
  {
    var arInfoletsToHide = this._arInfoletsToHide;
    for (var i = 0; i < arInfoletsToHide.length; i++)
    {
      var infolet = arInfoletsToHide[i];
      mlcClass._addBubbleEventListener(infolet, "transitionend", this._handleHideTransitionEndFunc);
      mlcClass._addBubbleEventListener(infolet, "webkitTransitionEnd", this._handleHideTransitionEndFunc);
      this._addStyleClassNameFunc(infolet, this._transitionHideFromStyleClass);
    }
    
    //need to do the below in a timeout because it must happen AFTER the 
    //_transitionHideFromStyleClass is applied
    var self = this;
    setTimeout(function() {
      for (var i = 0; i < arInfoletsToHide.length; i++)
      {
        var infolet = arInfoletsToHide[i];
        self._removeStyleClassNameFunc(infolet, self._transitionHideFromStyleClass);
        self._addStyleClassNameFunc(infolet, self._transitionHideToStyleClass);
      }
    }, 0);
  }
  else
  {
    this._handleHideTransitionEnd(null);
  }
};

/**
 * Handle the end of the hide transition.
 * @param {Object} event Event object
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._handleHideTransitionEnd = function(event)
{
  var mlcClass = window.adf.shared.impl.masonryLayout.MasonryLayoutCommon;
  if (event)
  {
    event.preventDefault();
    event.stopPropagation();
    var infolet = event.target;
    this._removeStyleClassNameFunc(infolet, this._transitionHideToStyleClass);
    mlcClass._removeBubbleEventListener(infolet, "transitionend", this._handleHideTransitionEndFunc);
    mlcClass._removeBubbleEventListener(infolet, "webkitTransitionEnd", this._handleHideTransitionEndFunc);
    var arInfoletsToHide = this._arInfoletsToHide;
    if (arInfoletsToHide)
    {
      for (var i = 0; i < arInfoletsToHide.length; i++)
      {
        var tmpInfolet = arInfoletsToHide[i];
        if (tmpInfolet === infolet)
        {
          arInfoletsToHide.splice(i, 1);
          if (!this._arFireHideOnEnd)
            this._arFireHideOnEnd = [];
          var arFireHideOnEnd = this._arFireHideOnEnd;
          arFireHideOnEnd.push(infolet);
          break;
        }
      }
      if (arInfoletsToHide.length < 1)
        this._arInfoletsToHide = null;
    }
  }
  else if (!this.isAnimationEnabled())
  {
    //if animation is not enabled, process all the hides at the same time
    var arInfoletsToHide = this._arInfoletsToHide;
    if (arInfoletsToHide)
    {
      for (var i = 0; i < arInfoletsToHide.length; i++)
      {
        var tmpInfolet = arInfoletsToHide[i];
        if (!this._arFireHideOnEnd)
          this._arFireHideOnEnd = [];
        var arFireHideOnEnd = this._arFireHideOnEnd;
        arFireHideOnEnd.push(tmpInfolet);
      }
      this._arInfoletsToHide = null;
    }
  }
  
  if (!this._arInfoletsToHide)
  {
    if (this._arFireHideOnEnd)
    {
      var arFireHideOnEnd = this._arFireHideOnEnd;
      for (var i = 0; i < arFireHideOnEnd.length; i++)
      {
        var infolet = arFireHideOnEnd[i];
        if (this.isAnimationEnabled())
          this._removeStyleClassNameFunc(infolet, this._transitionHideToStyleClass);
        if (this._hideTileOnEndFunc)
          this._hideTileOnEndFunc(infolet);
      }
      this._arFireHideOnEnd = null;
    }
    
    this._layoutPhase = mlcClass._PHASE_LAYOUT;
    
    this._transitionStart(false);
    
    //update size style classes AFTER starting transition, but BEFORE doing layout
    if (this._arInfoletsToResize)
    {
      var arInfoletsToResize = this._arInfoletsToResize;
      for (var i = 0; i < arInfoletsToResize.length; i += 2)
      {
        var infolet = arInfoletsToResize[i];
        var newSizeStyleClass = arInfoletsToResize[i + 1];
        var oldSizeStyleClass = this._getSizeStyleClassNameFunc(infolet);
        //if we add a _transitionResizeFromStyleClass in the future, we'll have
        //to add it here immediately and then do the below in a timeout because
        //it would need to happen AFTER the _transitionResizeFromStyleClass is 
        //applied
        this._removeStyleClassNameFunc(infolet, oldSizeStyleClass);
        this._addStyleClassNameFunc(infolet, newSizeStyleClass);
        if (this.isAnimationEnabled())
        {
          this._addStyleClassNameFunc(infolet, this._transitionResizeToStyleClass);
          
          //temporarily store the old size style class on the infolet in case
          //_layout() uses this infolet to calculate cell size, because the 
          //element size still corresponds to the old size style class before
          //the transition runs
          infolet._afrOldSizeStyleClass = oldSizeStyleClass;
        }
      }
    }
    
    this._transitionLayout();
  }
};

/**
 * Show any tiles that were inserted.  This is the third phase of the
 * <hide, layout, show> sequence.
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._showTiles = function()
{
  var mlcClass = window.adf.shared.impl.masonryLayout.MasonryLayoutCommon;
  this._layoutPhase = mlcClass._PHASE_SHOW;
  
  if (this._arInfoletsToShow && this.isAnimationEnabled())
  {
    var arInfoletsToShow = this._arInfoletsToShow;
    for (var i = 0; i < arInfoletsToShow.length; i++)
    {
      var infolet = arInfoletsToShow[i];
      mlcClass._addBubbleEventListener(infolet, "transitionend", this._handleShowTransitionEndFunc);
      mlcClass._addBubbleEventListener(infolet, "webkitTransitionEnd", this._handleShowTransitionEndFunc);
      this._addStyleClassNameFunc(infolet, this._transitionShowToStyleClass);
      this._removeStyleClassNameFunc(infolet, this._transitionShowFromStyleClass);
    }
  }
  else
  {
    this._handleShowTransitionEnd(null);
  }
};

/**
 * Handle the end of the show transition.
 * @param {Object} event Event object
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon.prototype._handleShowTransitionEnd = function(event)
{
  if (event)
  {
    event.preventDefault();
    event.stopPropagation();
    var infolet = event.target;
    this._removeStyleClassNameFunc(infolet, this._transitionShowToStyleClass);
    var mlcClass = window.adf.shared.impl.masonryLayout.MasonryLayoutCommon;
    mlcClass._removeBubbleEventListener(infolet, "transitionend", this._handleShowTransitionEndFunc);
    mlcClass._removeBubbleEventListener(infolet, "webkitTransitionEnd", this._handleShowTransitionEndFunc);
    var arInfoletsToShow = this._arInfoletsToShow;
    if (arInfoletsToShow)
    {
      for (var i = 0; i < arInfoletsToShow.length; i++)
      {
        var tmpInfolet = arInfoletsToShow[i];
        if (tmpInfolet === infolet)
        {
          arInfoletsToShow.splice(i, 1);
          if (this._showTileOnEndFunc)
            this._showTileOnEndFunc(infolet);
          break;
        }
      }
      if (arInfoletsToShow.length < 1)
        this._arInfoletsToShow = null;
    }
  }
  else if (!this.isAnimationEnabled())
  {
    //if animation is not enabled, process all the shows at the same time
    var arInfoletsToShow = this._arInfoletsToShow;
    if (arInfoletsToShow)
    {
      for (var i = 0; i < arInfoletsToShow.length; i++)
      {
        var tmpInfolet = arInfoletsToShow[i];
        if (this._showTileOnEndFunc)
          this._showTileOnEndFunc(tmpInfolet);
      }
      this._arInfoletsToShow = null;
    }
  }
  
  //only advance the layout phase if there are no more infolets to show
  //still transitioning
  if (!this._arInfoletsToShow)
    this._layoutPhase = null;
};

/**
 * Layout phase: hide deleted tiles.
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon._PHASE_HIDE = 0;
/**
 * Layout phase: layout tiles.
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon._PHASE_LAYOUT = 1;
/**
 * Layout phase: show inserted tiles.
 */
window.adf.shared.impl.masonryLayout.MasonryLayoutCommon._PHASE_SHOW = 2;

})();
