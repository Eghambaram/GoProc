// Copyright (c) 2008, 2015, Oracle and/or its affiliates. All rights reserved.
/**
 * Axis component.  This class should never be instantiated directly.  Use the
 * newInstance function instead.
 * @class
 * @constructor
 * @extends {DvtBaseComponent}
 * @export
 */
var DvtAxis = function() {};

DvtObj.createSubclass(DvtAxis, DvtBaseComponent, 'DvtAxis');


/**
 * Returns a new instance of DvtAxis.
 * @param {DvtContext} context The rendering context.
 * @param {string} callback The function that should be called to dispatch component events.
 * @param {object} callbackObj The optional object instance on which the callback function is defined.
 * @return {DvtAxis}
 * @export
 */
DvtAxis.newInstance = function(context, callback, callbackObj) {
  var axis = new DvtAxis();
  axis.Init(context, callback, callbackObj);
  return axis;
};


/**
 * Returns a copy of the default options for the specified skin.
 * @param {string} skin The skin whose defaults are being returned.
 * @return {object} The object containing defaults for this component.
 * @export
 */
DvtAxis.getDefaults = function(skin) 
{
  return (new DvtAxisDefaults()).getDefaults(skin);
};


/**
 * @override
 * @protected
 */
DvtAxis.prototype.Init = function(context, callback, callbackObj) {
  DvtAxis.superclass.Init.call(this, context, callback, callbackObj);

  // Create the defaults object
  this.Defaults = new DvtAxisDefaults();

  // Create the event handler and add event listeners
  this._eventHandler = new DvtAxisEventManager(this);
  this._eventHandler.addListeners(this);

  this._bounds = null;
};


/**
 * Minimum buffer for horizontal axis.
 */
DvtAxis.MINIMUM_AXIS_BUFFER = 10;


/**
 * @override
 * @protected
 */
DvtAxis.prototype.SetOptions = function(options) {
  if (options) // Combine the user options with the defaults and store
    this.Options = this.Defaults.calcOptions(options);
  else if (!this.Options) // Create a default options object if none has been specified
    this.Options = this.GetDefaults();
};


/**
 * Returns the preferred dimensions for this component given the maximum available space.
 * @param {object} options The object containing specifications and data for this component.
 * @param {Number} maxWidth The maximum width available.
 * @param {Number} maxHeight The maximum height available.
 * @return {DvtDimension} The preferred dimensions for the object.
 */
DvtAxis.prototype.getPreferredSize = function(options, maxWidth, maxHeight) {
  // Update the options object.
  this.SetOptions(options);

  // Ask the axis to render its context in the max space and find the space used
  return DvtAxisRenderer.getPreferredSize(this, maxWidth, maxHeight);
};


/**
 * Renders the component at the specified size.
 * @param {object} options The object containing specifications and data for this component.
 * @param {number} width The width of the component.
 * @param {number} height The height of the component.
 * @param {number=} x x position of the component.
 * @param {number=} y y position of the component.
 * @export
 */
DvtAxis.prototype.render = function(options, width, height, x, y) 
{
  // Update the options object.
  this.SetOptions(options);

  this.Width = width;
  this.Height = height;

  // Clear any contents rendered previously
  this.removeChildren();

  // Set default values to undefined properties.
  if (!x) {
    x = 0;
  }

  if (!y) {
    y = 0;
  }

  // Render the axis
  var availSpace = new DvtRectangle(x, y, width, height);
  DvtAxisRenderer.render(this, availSpace);
};


/**
 * Processes the specified event.
 * @param {object} event
 * @param {object} source The component that is the source of the event, if available.
 */
DvtAxis.prototype.processEvent = function(event, source) {
  // Dispatch the event to the callback if it originated from within this component.
  if (this === source) {
    this.__dispatchEvent(event);
  }
};

/**
 * @override
 */
DvtAxis.prototype.__getEventManager = function() {
  return this._eventHandler;
};


/**
 * Returns the axisInfo for the axis
 * @return {DvtAxisInfo} the axisInfo
 */
DvtAxis.prototype.__getInfo = function() {
  return this.Info;
};


/**
 * Sets the object containing calculated axis information and support
 * for creating drawables.
 * @param {DvtAxisInfo} axisInfo
 */
DvtAxis.prototype.__setInfo = function(axisInfo) {
  this.Info = axisInfo;
};

/**
 * Returns the axis width
 * @return {number}
 */
DvtAxis.prototype.getWidth = function() {
  return this.Width;
};


/**
 * Returns the axis height
 * @return {number}
 */
DvtAxis.prototype.getHeight = function() {
  return this.Height;
};


/**
 * @override
 */
DvtAxis.prototype.destroy = function() {
  if (this._eventHandler) {
    this._eventHandler.removeListeners(this);
    this._eventHandler.destroy();
    this._eventHandler = null;
  }

  // Call super last during destroy
  DvtAxis.superclass.destroy.call(this);
};

/**
 * Stores the bounds for this axis
 * @param {DvtRectangle} bounds
 */
DvtAxis.prototype.__setBounds = function(bounds) {
  this._bounds = bounds;
};

/**
 * Returns the bounds for this axis
 * @return {DvtRectangle} the object containing the bounds for this axis
 */
DvtAxis.prototype.__getBounds = function() {
  return this._bounds;
};

/**
 * Returns the automation object for this axis
 * @return {DvtAutomation} The automation object
 * @export
 */
DvtAxis.prototype.getAutomation = function() {
  return new DvtAxisAutomation(this);
};
/**
 * Axis Constants
 * @class
 * @export
 */
var DvtAxisConstants = {};

DvtObj.createSubclass(DvtAxisConstants, DvtObj, 'DvtAxisConstants');


/**
 * @const
 * @export
 */
DvtAxisConstants.TICK_LABEL = 'tickLabel';


/**
 * @const
 * @export
 */
DvtAxisConstants.TITLE = 'title';
/**
 * Abstact formatter for an axis label value.
 *
 * @constructor
 */
var DvtAbstractAxisValueFormatter = function() {
};

DvtObj.createSubclass(DvtAbstractAxisValueFormatter, DvtObj, 'DvtAbstractAxisValueFormatter');


/**
 * Abstract method which purpose is to format given numeric value.
 * @param {number} value value to be formatted
 * @return {string} formatted value as string
 */
DvtAbstractAxisValueFormatter.prototype.format = function(value) {
};

/**
 *  Provides automation services for a DVT component.
 *  @class DvtAxisAutomation
 *  @param {DvtAxis} dvtComponent
 *  @implements {DvtAutomation}
 *  @constructor
 *  @export
 */
var DvtAxisAutomation = function(dvtComponent) {
  this._axis = dvtComponent;

  this._options = this._axis.getOptions();
  this._axisInfo = this._axis.__getInfo();
};

DvtObj.createSubclass(DvtAxisAutomation, DvtAutomation, 'DvtAxisAutomation');


/**
 * Valid subIds inlcude:
 * <ul>
 * <li>item[groupIndex0]...[groupIndexN]</li>
 * <li>title</li>
 * </ul>
 * @override
 */
DvtAxisAutomation.prototype.GetSubIdForDomElement = function(displayable) {
  var logicalObj = this._axis.__getEventManager().GetLogicalObject(displayable);
  if (logicalObj && (logicalObj instanceof DvtSimpleObjPeer)) {
    if (logicalObj.getParams()['type'] == DvtAxisConstants.TITLE) // return chart axis title subId
      return 'title';
    else if (this._options['groups']) { // return group axis label subId
      var level = logicalObj.getParams()['level'];
      var labelIndex = this._axisInfo.getStartIndex(logicalObj.getParams()['index'], level);
      var indexList = '';
      // Loop from outermost level to desired level
      for (var levelIdx = 0; levelIdx <= level; levelIdx++) {
        var labels = this._axisInfo.getLabels(this._axis.getCtx(), levelIdx);
        // Find label at each level that belongs in hierarchy for the specified label, and append position to subId index list
        for (var i = 0; i < labels.length; i++) {
          if (this._axisInfo.getStartIndex(i, levelIdx) <= labelIndex && this._axisInfo.getEndIndex(i, levelIdx) >= labelIndex) {
            indexList += '[' + this._axisInfo.getPosition(i, levelIdx) + ']';
          }
        }
      }
      // Return subId
      if (indexList.length > 0)
        return 'item' + indexList;
    }
  }
  return null;
};


/**
 * Valid subIds inlcude:
 * <ul>
 * <li>item[groupIndex0]...[groupIndexN]</li>
 * <li>title</li>
 * </ul>
 * @override
 * @export
 */
DvtAxisAutomation.prototype.getDomElementForSubId = function(subId) {
  if (subId == 'title') { // process chart axis title subId
    var title = this._axisInfo.getTitle();
    if (title)
      return title.getElem();
  }
  else if (this._axisInfo instanceof DvtGroupAxisInfo) { // process group axis label subId
    var numIndices = subId.split('[').length - 1;
    var labelLevel = numIndices - 1;
    var labelIndex = 0;
    var startIndex = 0;
    // Loop from outermost level to specified level
    for (var levelIdx = 0; levelIdx <= labelLevel; levelIdx++) {
      var openParen = subId.indexOf('[');
      var closeParen = subId.indexOf(']');
      var groupIndex = subId.substring(openParen + 1, closeParen);
      subId = subId.substring(closeParen + 1);
      var labels = this._axisInfo.getLabels(this._axis.getCtx(), levelIdx);
      for (var j = 0; j < labels.length; j++) {
        if (this._axisInfo.getStartIndex(j, levelIdx) == startIndex) {
          labelIndex = j;
          break;
        }
      }
      for (var i = labelIndex; i < labels.length; i++) {
        if (this._axisInfo.getPosition(i, levelIdx) == groupIndex) {
          if (subId.length == 0)
            return labels[i].getElem();
          else
            startIndex = this._axisInfo.getStartIndex(i, levelIdx);
          break;
        }
      }
    }

  }
  return null;
};

/**
 * Default values and utility functions for component versioning.
 * @class
 * @constructor
 * @extends {DvtBaseComponentDefaults}
 */
var DvtAxisDefaults = function() {
  this.Init({'skyros': DvtAxisDefaults.VERSION_1, 'alta': DvtAxisDefaults.SKIN_ALTA, 'next': DvtAxisDefaults.SKIN_NEXT});
};

DvtObj.createSubclass(DvtAxisDefaults, DvtBaseComponentDefaults, 'DvtAxisDefaults');

/**
 * Contains overrides for the next generation skin.
 */
DvtAxisDefaults.SKIN_NEXT = {
  'skin': DvtCSSStyle.SKIN_NEXT,
  'layout': {
    'titleGap': 6
  }
};


/**
 * Contains overrides for the 'alta' skin.
 */
DvtAxisDefaults.SKIN_ALTA = {
  'axisLine': {'lineColor': '#9E9E9E'},
  'majorTick': {'lineColor': 'rgba(196,206,215,0.4)', 'baselineColor': 'auto'},
  'minorTick': {'lineColor': 'rgba(196,206,215,0.2)'},
  'tickLabel': {'style': new DvtCSSStyle("font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;")},
  'titleStyle': new DvtCSSStyle("font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 12px;")
};


/**
 * Defaults for version 1.
 */
DvtAxisDefaults.VERSION_1 = {
  'position': null,
  'baselineScaling': 'zero',
  'axisLine': {'lineColor': '#8A8DAC', 'lineWidth': 1, 'rendered': 'on'},
  'majorTick': {'lineColor': 'rgba(138,141,172,0.4)', 'lineWidth': 1, 'rendered': 'auto', 'lineStyle': 'solid'},
  'minorTick': {'lineColor': 'rgba(138,141,172,0.20)', 'lineWidth': 1, 'rendered': 'off', 'lineStyle': 'solid'},
  'tickLabel': {
    'scaling': 'auto',
    'style': new DvtCSSStyle('font-family: tahoma, sans-serif; font-size: 11px; color: #333333;'), 'rotation': 'auto', 'rendered': 'on'
  },
  'titleStyle': new DvtCSSStyle('font-family: tahoma, sans-serif; font-size: 11px; color: #737373;'),

  // For group axis, an optional offset expressed as a factor of the group size.
  'startGroupOffset': 0, 'endGroupOffset': 0,

  //*********** Internal Attributes *************************************************//
  'layout': {'titleGap': 4, 'radialLabelGap': 5, 'insideLabelGapWidth': 4, 'insideLabelGapHeight': 2, 'hierarchicalLabelGapHeight': 8, 'hierarchicalLabelGapWidth': 15},
  '_locale': 'en-us'
};


/**
 * Adjusts the gap size based on the component options.
 * @param {DvtAxis} axis The axis component.
 * @param {Number} defaultSize The default gap size.
 * @return {Number}
 */
DvtAxisDefaults.getGapSize = function(axis, defaultSize) {
  // adjust based on tick label font size
  var scalingFactor = Math.min(DvtTextUtils.getTextStringHeight(axis.getCtx(), axis.getOptions()['tickLabel']['style']) / 14, 1);
  return Math.ceil(defaultSize * scalingFactor);
};
/**
 * Event Manager for DvtAxis.
 * @param {DvtAxis} axis
 * @class
 * @extends {DvtEventManager}
 * @constructor
 */
var DvtAxisEventManager = function(axis) {
  this.Init(axis.getCtx(), axis.processEvent, axis);
  this._axis = axis;
};

DvtObj.createSubclass(DvtAxisEventManager, DvtEventManager, 'DvtAxisEventManager');


/**
 * Returns the parameters for the DvtComponentUIEvent for an object with the specified arguments.
 * @param {string} type The type of object that was the target of the event.
 * @param {object=} id The id of the object, if one exists.
 * @param {number=} index The index of the axis label, in regards to its level, if one is specified.
 * @param {number=} level The level of the axis label, if one is specified.
 * @return {object} the parameters for the DvtComponentUIEvent
 */
DvtAxisEventManager.getUIEventParams = function(type, id, index, level) {
  return {'type': type, 'id': id, 'index': index, 'level': level};
};


/**
 * @override
 */
DvtAxisEventManager.prototype.FireUIEvent = function(type, logicalObj) {
  if (logicalObj instanceof DvtSimpleObjPeer && logicalObj.getParams() != null) {
    this.FireEvent(new DvtComponentUIEvent(type, logicalObj.getParams()), this._axis);
  }
};

/**
 * @override
 */
DvtAxisEventManager.prototype.OnClick = function(event) {
  DvtAxisEventManager.superclass.OnClick.call(this, event);

  var obj = this.GetLogicalObject(event.target);
  if (!obj)
    return;

  var action = this._processActionEvent(obj);

  // If an action occurs, the event should not bubble.
  if (action)
    event.stopPropagation();
};

/**
 * @override
 */
DvtAxisEventManager.prototype.HandleTouchClickInternal = function(evt) {
  var obj = this.GetLogicalObject(evt.target);
  if (!obj)
    return;

  var touchEvent = evt.touchEvent;
  var action = this._processActionEvent(obj);
  if (action && touchEvent)
    touchEvent.preventDefault();
};

/**
 * Processes an action on the specified group label.  Returns true if an action event is fired.
 * @param {DvtGroupAxisObjPeer} obj The group label that was clicked.
 * @return {boolean} True if an event was fired.
 * @private
 */
DvtAxisEventManager.prototype._processActionEvent = function(obj) {
  if (obj && obj.getAction && obj.getAction()) {
    this.FireEvent(new DvtActionEvent(DvtActionEvent.SUBTYPE_ACTION, obj.getAction(), obj.getId()), this._axis);
    return true;
  }

  // Drill Support
  if (obj instanceof DvtAxisObjPeer && obj.isDrillable()) {
    this.FireEvent(new DvtDrillEvent(obj.getId(), null, obj.getGroup()), this._axis);
    return true;
  }

  return false;
};


/**
 * Renderer for DvtAxis.
 * @class
 */
var DvtAxisRenderer = new Object();

DvtObj.createSubclass(DvtAxisRenderer, DvtObj, 'DvtAxisRenderer');

/**
 * Returns the preferred dimensions for this component given the maximum available space. This will never be called for
 * radial axis.
 * @param {DvtAxis} axis
 * @param {number} availWidth
 * @param {number} availHeight
 * @return {DvtDimension} The preferred dimensions for the object.
 */
DvtAxisRenderer.getPreferredSize = function(axis, availWidth, availHeight) {
  // Calculate the axis extents and increments
  var axisInfo = DvtAxisRenderer._createAxisInfo(axis, new DvtRectangle(0, 0, availWidth, availHeight));
  var context = axis.getCtx();
  var options = axis.getOptions();

  // The axis will always return the full length of the dimension along which values are placed, so there's only one
  // size that we need to keep track of.  For example, this is the height on horizontal axes.
  var size = 0;
  var bHoriz = (options['position'] == 'top' || options['position'] == 'bottom');
  var gap = 0; // account for gaps between levels of hierarchical group axis labels

  // No size if not rendered or either dimension is 0
  if (options['rendered'] == 'off' || availWidth <= 0 || availHeight <= 0)
    return bHoriz ? new DvtDimension(availWidth, 0) : new DvtDimension(0, availHeight);

  // Allocate space for the title
  if (options['title']) {
    size = DvtTextUtils.getTextStringHeight(context, options['titleStyle']) + DvtAxisRenderer._getTitleGap(axis);
    if (bHoriz)
      availHeight -= size;
    else
      availWidth -= size;
  }

  // Allocate space for the tick labels
  if (options['tickLabel']['rendered'] == 'on' && options['tickLabel']['position'] != 'inside') {
    if (bHoriz) {
      // Horizontal Axis
      var labelHeight = DvtTextUtils.getTextStringHeight(context, options['tickLabel']['style']);
      if (axisInfo instanceof DvtDataAxisInfo)
        size += labelHeight;
      else if (axisInfo instanceof DvtTimeAxisInfo)
        size += (axisInfo.getLabels(context, 1) != null ? labelHeight * 2 : labelHeight);
      else if (axisInfo instanceof DvtGroupAxisInfo) {
        var numLevels = axisInfo.getNumLevels();
        gap = numLevels > 1 ? DvtAxisDefaults.getGapSize(axis, options['layout']['hierarchicalLabelGapHeight']) : 0;
        for (var level = 0; level < numLevels; level++) { // allocate space outermost to innermost
          var labels = axisInfo.getLabels(context, level);
          var maxDims = DvtTextUtils.getMaxTextDimensions(labels);
          labelHeight = axisInfo.isLabelRotated(level) ? Math.ceil(maxDims.w) : maxDims.h;
          if (size + labelHeight <= availHeight)
            size += labelHeight + gap;
          else {
            if (level == 0 && size == 0) // Outermost level labels were too big, assign all of availHeight
              size = availHeight;
            break;
          }
        }
        size -= gap; // last level rendered doesn't need gap
      }
    }
    else {
      // Vertical Axis
      if (axisInfo instanceof DvtDataAxisInfo)
        size += DvtTextUtils.getMaxTextDimensions(axisInfo.getLabels(context)).w;
      else if (axisInfo instanceof DvtTimeAxisInfo) {
        var innerLabels = axisInfo.getLabels(context);
        var innerLabelWidth = DvtTextUtils.getMaxTextDimensions(innerLabels).w;
        var outerLabels = axisInfo.getLabels(context, 1);
        var outerLabelWidth = outerLabels != null ? DvtTextUtils.getMaxTextDimensions(outerLabels).w : 0;
        size += Math.max(innerLabelWidth, outerLabelWidth);
      }
      else if (axisInfo instanceof DvtGroupAxisInfo) {
        numLevels = axisInfo.getNumLevels();
        gap = numLevels > 1 ? DvtAxisDefaults.getGapSize(axis, options['layout']['hierarchicalLabelGapWidth']) : 0;
        for (level = 0; level < numLevels; level++) { // allocate space outermost to innermost
          labels = axisInfo.getLabels(context, level);
          var labelWidth = DvtTextUtils.getMaxTextDimensions(labels).w;
          if (size + labelWidth <= availWidth)
            size += labelWidth + gap;
          else {
            if (level == 0 && size == 0) // Outermost level labels were too wide, assign all of availWidth
              size = availWidth;
            break;
          }
        }
        size -= gap; // last level rendered doesn't need gap
      }
    }
  }

  if (bHoriz)
    return new DvtDimension(availWidth, Math.min(size, availHeight));
  else
    return new DvtDimension(Math.min(size, availWidth), availHeight);
};

/**
 * Renders the axis and updates the available space.
 * @param {DvtAxis} axis The axis being rendered.
 * @param {DvtRectangle} availSpace The available space.
 */
DvtAxisRenderer.render = function(axis, availSpace) {
  // Calculate the axis extents and increments
  var axisInfo = DvtAxisRenderer._createAxisInfo(axis, availSpace);
  var options = axis.getOptions();

  if (options['rendered'] == 'off')
    return;

  axis.__setBounds(availSpace.clone());

  // Render the title
  DvtAxisRenderer._renderTitle(axis, axisInfo, availSpace);

  // Render the tick labels
  DvtAxisRenderer._renderLabels(axis, axisInfo, availSpace);
};

/**
 * Creates and returns the DvtAxisInfo for the specified axis.
 * @param {DvtAxis} axis The axis being rendered.
 * @param {DvtRectangle} availSpace The available space.
 * @return {DvtAxisInfo}
 * @private
 */
DvtAxisRenderer._createAxisInfo = function(axis, availSpace) {
  var axisInfo = DvtAxisInfo.newInstance(axis.getCtx(), axis.getOptions(), availSpace);
  axis.__setInfo(axisInfo);
  return axisInfo;
};

/**
 * Returns the gap between the title and the tick labels.
 * @param {DvtAxis} axis
 * @return {number}
 * @private
 */
DvtAxisRenderer._getTitleGap = function(axis) {
  return DvtAxisDefaults.getGapSize(axis, axis.getOptions()['layout']['titleGap']);
};

/**
 * Renders the axis title and updates the available space.
 * @param {DvtAxis} axis The axis being rendered.
 * @param {DvtAxisInfo} axisInfo The axis model.
 * @param {DvtRectangle} availSpace The available space.
 * @private
 */
DvtAxisRenderer._renderTitle = function(axis, axisInfo, availSpace) {
  // Note: DvtAxisRenderer.getPreferredSize must be updated for any layout changes to this function.
  var options = axis.getOptions();
  if (!options['title'])
    return;

  // Create the title object and add to axis
  var position = options['position'];

  if (position == 'radial' || position == 'tangential')
    return; // polar chart doesn't have axis titles

  var bHoriz = (options['position'] == 'top' || options['position'] == 'bottom');
  var maxLabelWidth = bHoriz ? availSpace.w : availSpace.h;
  var maxLabelHeight = bHoriz ? availSpace.h : availSpace.w;
  var title = DvtAxisRenderer._createText(axis.__getEventManager(), axis, options['title'], options['titleStyle'],
                                          0, 0, maxLabelWidth, maxLabelHeight,
                                          DvtAxisEventManager.getUIEventParams(DvtAxisConstants.TITLE));

  if (title) {
    // Position the title based on text size and axis position
    var gap = DvtAxisRenderer._getTitleGap(axis);
    var isRTL = DvtAgent.isRightToLeft(axis.getCtx());
    var titleHeight = DvtTextUtils.getTextHeight(title);
    title.alignCenter();

    // Position the label and update the space
    if (position == 'top') {
      title.setX(availSpace.x + availSpace.w / 2);
      title.setY(availSpace.y);
      availSpace.y += (titleHeight + gap);
      availSpace.h -= (titleHeight + gap);
    }
    else if (position == 'bottom') {
      title.setX(availSpace.x + availSpace.w / 2);
      title.setY(availSpace.y + availSpace.h - titleHeight);
      availSpace.h -= (titleHeight + gap);
    }
    else if (position == 'left') {
      title.alignMiddle();
      title.setRotation(isRTL ? Math.PI / 2 : 3 * Math.PI / 2);
      title.setTranslate(availSpace.x + titleHeight / 2, availSpace.y + availSpace.h / 2);
      availSpace.x += (titleHeight + gap);
      availSpace.w -= (titleHeight + gap);
    }
    else if (position == 'right') {
      title.alignMiddle();
      title.setRotation(isRTL ? Math.PI / 2 : 3 * Math.PI / 2);
      title.setTranslate(availSpace.x + availSpace.w - titleHeight / 2, availSpace.y + availSpace.h / 2);
      availSpace.w -= (titleHeight + gap);
    }

    axisInfo.setTitle(title);
  }
};


/**
 * Renders the tick labels and updates the available space.
 * @param {DvtAxis} axis The axis being rendered.
 * @param {DvtAxisInfo} axisInfo The axis model.
 * @param {DvtRectangle} availSpace The available space.
 * @private
 */
DvtAxisRenderer._renderLabels = function(axis, axisInfo, availSpace) {
  // Note: DvtAxisRenderer.getPreferredSize must be updated for any layout changes to this function.
  var options = axis.getOptions();
  if (options['tickLabel']['rendered'] == 'on') {
    // Axis labels are positioned based on the position of the axis.  In layout
    // mode, the labels will be positioned as close to the title as possible to
    // calculate the actual space used.
    var position = options['position'];
    if (position == 'top' || position == 'bottom')
      DvtAxisRenderer._renderLabelsHoriz(axis, axisInfo, availSpace);
    else if (position == 'tangential')
      DvtAxisRenderer._renderLabelsTangent(axis, axisInfo, availSpace);
    else
      DvtAxisRenderer._renderLabelsVert(axis, axisInfo, availSpace);

    // Render the label separators (applicable only to group axis)
    DvtAxisRenderer._renderGroupSeparators(axis, axisInfo, availSpace);
  }
};


/**
 * Renders tick labels for a horizontal axis and updates the available space.
 * @param {DvtAxis} axis The axis being rendered.
 * @param {DvtAxisInfo} axisInfo The axis model.
 * @param {DvtRectangle} availSpace The available space.
 * @private
 */
DvtAxisRenderer._renderLabelsHoriz = function(axis, axisInfo, availSpace) {
  // Note: DvtAxisRenderer.getPreferredSize must be updated for any layout changes to this function.
  // Position and add the axis labels.
  var options = axis.getOptions();
  var position = options['position'];
  var isTickInside = options['tickLabel']['position'] == 'inside';
  var isRTL = DvtAgent.isRightToLeft(axis.getCtx());
  var maxLv1Height = 0;
  var isHierarchical = axisInfo instanceof DvtGroupAxisInfo && axisInfo.getNumLevels() > 1;

  var levelIdx = isHierarchical ? 0 : null;
  var labels = axisInfo.getLabels(axis.getCtx(), levelIdx);

  gap = isHierarchical ? DvtAxisDefaults.getGapSize(axis, options['layout']['hierarchicalLabelGapHeight']) : 0;
  while (labels) {
    var height = 0;

    for (var i = 0; i < labels.length; i++) {
      var label = labels[i];

      if (label == null)
        continue;

      if (axisInfo.isLabelRotated(levelIdx)) {
        // Truncate to fit
        if (!DvtTextUtils.fitText(label, availSpace.h, availSpace.w, axis))
          continue;

        //position and add the axis labels
        if (!isRTL)
          label.alignRight();
        else
          label.alignLeft();

        if (isHierarchical) {
          height = DvtTextUtils.getTextStringWidth(axis.getCtx(), label.getTextString(), label.getCSSStyle());
          label.setTranslateY(availSpace.h - height);
          maxLv1Height = Math.max(maxLv1Height, height);
        }
        else
          label.setTranslateY(availSpace.y);

      }
      else { // not rotated
        if (!isTickInside && DvtTextUtils.guessTextDimensions(label).h - 1 > availSpace.h) // -1 to prevent rounding error (bug 18960904)
          continue;

        if (isHierarchical && position == 'bottom')
          label.setY(availSpace.h);
        else if (position == 'bottom')
          label.setY(availSpace.y);
        else
          label.setY(availSpace.y + availSpace.h);

        if (!isHierarchical && ((position == 'bottom' && !isTickInside) || (position == 'top' && isTickInside)))
          label.alignTop();
        else if (isHierarchical && position == 'top')
          label.alignTop();
        else
          label.alignBottom();

        if (isHierarchical)
          maxLv1Height = Math.max(maxLv1Height, DvtTextUtils.guessTextDimensions(label).h);
        else if (isTickInside) {
          var gap = DvtAxisDefaults.getGapSize(axis, options['layout']['insideLabelGapWidth']);
          isRTL ? label.alignRight() : label.alignLeft();
          label.setX(label.getX() + gap * (isRTL ? -1 : 1));
        }
      }

      // support for categorical axis tooltip
      var datatip = axisInfo.getTooltip(label, i, levelIdx);
      // action support
      var action = axisInfo.getAction(i, levelIdx);
      // drilling support
      var drillable = axisInfo.isDrillable(i, levelIdx);
      var group = axisInfo.getGroup(i, levelIdx);

      // Associate with logical object to support DvtComponentUIEvent and tooltips
      var params = DvtAxisEventManager.getUIEventParams(DvtAxisConstants.TICK_LABEL, label.getTextString(), i, levelIdx);

      axis.__getEventManager().associate(label, new DvtAxisObjPeer(label, group, action, drillable, datatip, params));

      axis.addChild(label);
      if (!isHierarchical)
        maxLv1Height = Math.max(maxLv1Height, DvtTextUtils.guessTextDimensions(label).h);
      else
        axisInfo.setLastRenderedLevel(levelIdx);
    }
    if (isHierarchical) {
      availSpace.y += maxLv1Height + gap;
      availSpace.h -= maxLv1Height + gap;
      levelIdx++;
      labels = axisInfo.getLabels(axis.getCtx(), levelIdx);
    }
    else {
      availSpace.y += maxLv1Height;
      availSpace.h -= maxLv1Height;
      labels = null;
    }
  }

  // Render the nested labels (level 2) for time axis.
  if (axisInfo instanceof DvtTimeAxisInfo) {
    labels = axisInfo.getLabels(axis.getCtx());
    var lv2Labels = axisInfo.getLabels(axis.getCtx(), 1);
    var offset = 0;

    if (lv2Labels != null) {
      for (i = 0; i < lv2Labels.length; i++) {
        label = lv2Labels[i];
        if (label == null)
          continue;
        if (DvtTextUtils.guessTextDimensions(label).h - 1 > availSpace.h) // -1 to prevent rounding error (bug 18960904)
          continue;

        // Associate with logical object to support DvtComponentUIEvent and tooltips
        axis.__getEventManager().associate(label, new DvtSimpleObjPeer(null, null, null, DvtAxisEventManager.getUIEventParams(DvtAxisConstants.TICK_LABEL, label.getTextString())));

        // align with level 1 label
        if (labels[i] != null)
          offset = labels[i].measureDimensions().w / 2;
        if (isRTL) {
          label.setX(label.getX() + offset);
        } else {
          label.setX(label.getX() - offset);
        }

        label.alignTop();
        label.setY(availSpace.y);
        axis.addChild(label);
      }
    }
  }
};


/**
 * Renders tick labels for a vertical axis and updates the available space.
 * @param {DvtAxis} axis The axis being rendered.
 * @param {DvtAxisInfo} axisInfo The axis model.
 * @param {DvtRectangle} availSpace The available space.
 * @private
 */
DvtAxisRenderer._renderLabelsVert = function(axis, axisInfo, availSpace) {
  // Note: DvtAxisRenderer.getPreferredSize must be updated for any layout changes to this function.
  var options = axis.getOptions();
  var position = options['position'];
  var isRTL = DvtAgent.isRightToLeft(axis.getCtx());
  var isNumerical = axisInfo instanceof DvtDataAxisInfo;
  var isTickInside = options['tickLabel']['position'] == 'inside';
  var labels;
  var gap;
  var maxLvlWidth;
  var isHierarchical = axisInfo instanceof DvtGroupAxisInfo && axisInfo.getNumLevels() > 1;

  // Hierarchical group axis labels
  var levelIdx = isHierarchical ? 0 : null;
  labels = axisInfo.getLabels(axis.getCtx(), levelIdx);

  var labelX = 0;
  if (!isHierarchical) {
    // Categorical and time labels are aligned left is position=right, aligned right if position=left.
    // Numerical labels are always aligned right.
    if (position == 'radial') {
      gap = DvtAxisDefaults.getGapSize(axis, options['layout']['radialLabelGap']);
      labelX = availSpace.x + availSpace.w / 2;
      if (isRTL)
        labelX += gap + DvtTextUtils.getMaxTextDimensions(labels).w;
      else
        labelX -= gap;
    }
    else if (position == 'left') {
      labelX = availSpace.x + availSpace.w;
      if (isNumerical && isTickInside)
        labelX += DvtTextUtils.getMaxTextDimensions(labels).w;
    }
    else { // position == 'right'
      labelX = availSpace.x;
      if (isNumerical && !isTickInside)
        labelX += DvtTextUtils.getMaxTextDimensions(labels).w;
    }
  }
  else {
    gap = DvtAxisDefaults.getGapSize(axis, options['layout']['hierarchicalLabelGapWidth']);
    maxLvlWidth = DvtTextUtils.getMaxTextDimensions(labels).w;
  }


  var formatLabelVert = function(label, index) {
    // Truncate to fit
    if (isHierarchical && DvtTextUtils.getMaxTextDimensions(labels).w - 1 > availSpace.w) // -1 to prevent rounding error (bug 18960904)
      return;
    else if (!isHierarchical && !isTickInside && !DvtTextUtils.fitText(label, availSpace.w, availSpace.h, axis))
      return;

    // support for categorical axis tooltip
    var datatip = axisInfo.getTooltip(label, index, levelIdx);
    // action support
    var action = axisInfo.getAction(index, levelIdx);
    // drilling support
    var drillable = axisInfo.isDrillable(index, levelIdx);
    var group = axisInfo.getGroup(index, levelIdx);

    // Associate with logical object to support DvtComponentUIEvent and tooltips
    var params = DvtAxisEventManager.getUIEventParams(DvtAxisConstants.TICK_LABEL, label.getTextString(), index, levelIdx);

    axis.__getEventManager().associate(label, new DvtAxisObjPeer(label, group, action, drillable, datatip, params));

    if (!isHierarchical) {
      label.setX(labelX);
      if (!isNumerical && position == 'right')
        label.alignLeft();
      else
        label.alignRight();

      if (isTickInside) {
        label.alignBottom();
        label.setY(label.getY() - DvtAxisDefaults.getGapSize(axis, options['layout']['insideLabelGapHeight']));
      }

      if (position == 'radial') {
        var labelY = label.getY();
        label.setY(availSpace.y + availSpace.h / 2 - labelY);

        // draw bounding box to improve readability
        var bboxDims = label.getDimensions();
        var padding = bboxDims.h * 0.15;
        var cmd = DvtPathUtils.roundedRectangle(bboxDims.x - padding, bboxDims.y, bboxDims.w + 2 * padding, bboxDims.h, 2, 2, 2, 2);
        var bbox = new DvtPath(axis.getCtx(), cmd);
        var bgColor = label.getCSSStyle().getStyle(DvtCSSStyle.BACKGROUND_COLOR);
        var opacity = labelY + bboxDims.h / 2 > axisInfo.getEndCoord() && axis.getOptions()['polarGridShape'] == 'circle' ? 1 : 0.3;
        if (bgColor)
          bbox.setSolidFill(bgColor);
        else
          bbox.setSolidFill('#FFFFFF', opacity);
        axis.addChild(bbox);
      }
    }
    else {
      label.alignRight();
      label.setX(isRTL ? availSpace.w : availSpace.x + maxLvlWidth);
      axisInfo.setLastRenderedLevel(levelIdx);
    }
    axis.addChild(label);
  };

  while (labels) {
    for (var i = 0; i < labels.length; i++) {
      var label = labels[i];
      if (label != null)
        formatLabelVert(label, i);
    }
    if (isHierarchical) {
      availSpace.x += maxLvlWidth + gap;
      availSpace.w -= maxLvlWidth + gap;
      levelIdx++;
      labels = axisInfo.getLabels(axis.getCtx(), levelIdx);
      maxLvlWidth = labels ? DvtTextUtils.getMaxTextDimensions(labels).w : null;
    }
    else
      break;
  }

  if (axisInfo instanceof DvtTimeAxisInfo) {
    // Render the nested labels (level 2).
    var lv2Labels = axisInfo.getLabels(axis.getCtx(), 1);
    if (lv2Labels != null) {
      for (i = 0; i < lv2Labels.length; i++) {
        label = lv2Labels[i];
        if (label != null)
          formatLabelVert(label, i);
      }
    }
  }
};


/**
 * Renders tick labels for a tangential axis and updates the available space.
 * @param {DvtAxis} axis The axis being rendered.
 * @param {DvtAxisInfo} axisInfo The axis model.
 * @param {DvtRectangle} availSpace The available space.
 * @private
 */
DvtAxisRenderer._renderLabelsTangent = function(axis, axisInfo, availSpace) {
  var labels = axisInfo.getLabels(axis.getCtx());
  for (var i = 0; i < labels.length; i++) {
    var label = labels[i];
    if (label == null)
      continue;
    var maxWidth = availSpace.w / 2 - Math.abs(label.getX());
    var maxHeight = availSpace.h / 2 - Math.abs(label.getY());
    if (DvtTextUtils.fitText(label, maxWidth, maxHeight, axis)) { // truncation

      // support for categorical axis tooltip
      var datatip = axisInfo.getTooltip(label, i);

      // action support
      var action = axisInfo.getAction(i);
      // drilling support
      var drillable = axisInfo.isDrillable(i);
      var group = axisInfo.getGroup(i);

      // Associate with logical object to support DvtComponentUIEvent and tooltips
      var params = DvtAxisEventManager.getUIEventParams(DvtAxisConstants.TICK_LABEL, label.getTextString(), i);
      axis.__getEventManager().associate(label, new DvtAxisObjPeer(label, group, action, drillable, datatip, params));

      label.setTranslateX(availSpace.x + availSpace.w / 2);
      label.setTranslateY(availSpace.y + availSpace.h / 2);
      axis.addChild(label);
    }
  }
};


/**
 * Creates and adds a DvtText object to a container. Will truncate and add tooltip as necessary.
 * @param {DvtEventManager} eventManager
 * @param {DvtContainer} container The container to add the text object to.
 * @param {String} textString The text string of the text object.
 * @param {DvtCSSStyle} cssStyle The css style to apply to the text object.
 * @param {number} x The x coordinate of the text object.
 * @param {number} y The y coordinate of the text object.
 * @param {number} width The width of available text space.
 * @param {number} height The height of the available text space.
 * @param {object} params Additional parameters that will be passed to the logical object.
 * @return {DvtOutputText} The created text object. Can be null if no text object could be created in the given space.
 * @private
 */
DvtAxisRenderer._createText = function(eventManager, container, textString, cssStyle, x, y, width, height, params) {
  var text = new DvtOutputText(container.getCtx(), textString, x, y);
  text.setCSSStyle(cssStyle);
  if (DvtTextUtils.fitText(text, width, height, container)) {
    // Associate with logical object to support DvtComponentUIEvent and truncation
    eventManager.associate(text, new DvtSimpleObjPeer(text.getUntruncatedTextString(), null, null, params));
    return text;
  }
  else
    return null;
};


/**
 * Renders the separators between group labels
 * @param {DvtAxis} axis The axis being rendered.
 * @param {DvtAxisInfo} axisInfo The axis model.
 * @param {DvtRectangle} availSpace The available space.
 * @private
 */
DvtAxisRenderer._renderGroupSeparators = function(axis, axisInfo, availSpace) {
  if (axisInfo instanceof DvtGroupAxisInfo && axisInfo.areSeparatorsRendered()) {
    var numLevels = axisInfo.getNumLevels();
    if (numLevels <= 1 || axisInfo.getLastRenderedLevel() <= 0) // only draw separators when there is more than one level
      return;

    var options = axis.getOptions();
    var position = options['position'];
    var isHoriz = (position == 'top' || position == 'bottom');
    var isRTL = DvtAgent.isRightToLeft(axis.getCtx());

    var color = axisInfo.getSeparatorColor();
    var lineStroke = new DvtSolidStroke(color, 1, 1);
    var prevLevelSize = 0;
    var gap = isHoriz ? DvtAxisDefaults.getGapSize(axis, options['layout']['hierarchicalLabelGapHeight']) : DvtAxisDefaults.getGapSize(axis, options['layout']['hierarchicalLabelGapWidth']);
    var startOffset = options['startGroupOffset'];
    var endOffset = options['endGroupOffset'];

    var x1, y1, x2, y2, x3, x4;
    /*
     * orientation = 'vertical'                     if rotated:
     * (x1, y1)                        (x2, y1)     (x1, y1)                 (x2, y1)
     *    |                               |            |     rotated label      |
     *    ------------- label -------------            --------------------------
     * (x1, y2)    (x3, y2)(x4, y2)    (x2, y2)     (x1, y2)                 (x2, y2)
     *
     *
     * orientation = 'horizontal'
     * (x1, y1) _______ (x2, y1)
     *         |
     *         |
     *         | label
     *         |
     *         |
     * (x1, y2) _______ (x2, y2)
     */

    // process from the innermost level that was rendered
    for (var level = axisInfo.getLastRenderedLevel(); level >= 0; level--) {
      var labels = axisInfo.getLabels(axis.getCtx(), level);
      var maxDims = DvtTextUtils.getMaxTextDimensions(labels);
      var isRotated = axisInfo.isLabelRotated(level);
      var levelSize = isRotated || !isHoriz ? maxDims.w : maxDims.h;

      if (levelSize == 0) { // no labels to draw separators between
        prevLevelSize = levelSize;
        continue;
      }

      // variables to keep track of whether certain edge cases apply
      var prevLabelRendered = false; // previous label exists, does not have blank name, and is within the viewport
      var prevLabelEmpty = null; // previous label exists, but has a blank name (uneven heirarchy)

      // Start drawing separators from second innermost level rendered.
      if (level < axisInfo.getLastRenderedLevel()) {
        for (var i = 0; i < labels.length; i++) {
          var label = labels[i];
          var isEmptyLabel = axisInfo.getLabelAt(i, level).length == 0;  // label exists, but has a blank name (uneven heirarchy)

          if (label == null && isEmptyLabel)
            continue;

          // empty label at first or last position in the outermost level
          var eraseCornerEdge = isEmptyLabel && level == 0 && (i == 0 || i == labels.length - 1);

          var isFirstLabel = label && labels[i - 1] == null;
          var isLastLabel = label && labels[i + 1] == null;

          var start = axisInfo.getStartIndex(i, level);
          var end = axisInfo.getEndIndex(i, level);

          if (isHoriz) { // HORIZONTAL AXIS SEPARATORS

            // draw vertical lines, when necessary, around label
            if (label) {
              x1 = axisInfo.getCoordAt(start - startOffset);
              y1 = !isRotated ? label.getY() - (levelSize / 2) - prevLevelSize - gap : label.getY() + prevLevelSize * .5;
              x2 = axisInfo.getCoordAt(end + endOffset);
              y2 = !isRotated ? label.getY() - (levelSize / 2) : label.getY() + levelSize + prevLevelSize + 2 * gap;

              if ((!isEmptyLabel || !eraseCornerEdge) && prevLabelRendered == false && x1 != null)
                DvtAxisRenderer._addSeparatorLine(axis, lineStroke, x1, y2, x1, y1);

              if (x2 != null && !eraseCornerEdge)
                DvtAxisRenderer._addSeparatorLine(axis, lineStroke, x2, y2, x2, y1);
            }

            // draw horizontal lines, when necessary, around non-empty labels
            if (!isEmptyLabel) {

              if (label)
                var labelWidth = isRotated ? DvtTextUtils.getTextHeight(label) : DvtTextUtils.getTextWidth(label);

              x1 = (isFirstLabel && prevLabelEmpty == false) ? axisInfo.getStartCoord() : axisInfo.getBoundedCoordAt(start - startOffset);
              if (isFirstLabel)
                isFirstLabel = false;
              var nextLabel = axisInfo.getLabelAt(i + 1, level);
              x2 = (isLastLabel && nextLabel && nextLabel.length > 0) ? axisInfo.getEndCoord() : axisInfo.getBoundedCoordAt(end + endOffset);

              x3 = label ? (isRTL ? label.getX() + (labelWidth * .5) : label.getX() - (labelWidth * .5)) : axisInfo.getBoundedCoordAt(end + endOffset);
              x4 = label ? (isRTL ? label.getX() - (labelWidth * .5) : label.getX() + (labelWidth * .5)) : axisInfo.getBoundedCoordAt(start - startOffset);

              if (label) {
                if (isRotated) // draw horizontal line beneath rotated label
                  DvtAxisRenderer._addSeparatorLine(axis, lineStroke, x1, y2, x2, y2);
                else { // draw horizontal lines on either size of rendered label
                  var spacing = isRTL ? -DvtTextUtils.getTextHeight(label) * .5 : DvtTextUtils.getTextHeight(label) * .5; // small space between end of horizontal lines and label
                  var drawRightLine = isRTL ? x1 > x3 - spacing : x1 < x3 - spacing;
                  var drawLeftLine = isRTL ? x4 + spacing > x2 : x4 + spacing < x2;

                  if (drawRightLine)
                    DvtAxisRenderer._addSeparatorLine(axis, lineStroke, x1, y2, x3 - spacing, y2);

                  if (drawLeftLine)
                    DvtAxisRenderer._addSeparatorLine(axis, lineStroke, x4 + spacing, y2, x2, y2);
                }
              }
            }
          }
          else { // VERTICAL AXIS SEPARATORS

            // draw horizontal lines, when necessary, around label
            if (label) {

              x1 = !isRTL ? label.getX() + gap * .5 : label.getX() - levelSize - gap * .5;
              y1 = axisInfo.getCoordAt(start - startOffset);
              x2 = !isRTL ? label.getX() - levelSize - gap * .5 : label.getX() + gap * .5;
              y2 = axisInfo.getCoordAt(end + endOffset);

              if (((!isEmptyLabel && prevLabelRendered == false) || (i == 0 && isEmptyLabel && level != 0)) && y1 != null)
                DvtAxisRenderer._addSeparatorLine(axis, lineStroke, x1, y1, x2, y1);

              if (y2 != null && !eraseCornerEdge)
                DvtAxisRenderer._addSeparatorLine(axis, lineStroke, x2, y2, x1, y2);
            }

            // draw vertical lines, when necessary, around non-empty labels
            if (!isEmptyLabel) {
              y1 = (isFirstLabel && prevLabelEmpty == false) ? 0 : axisInfo.getBoundedCoordAt(start - startOffset);
              if (isFirstLabel)
                isFirstLabel = false;
              nextLabel = axisInfo.getLabelAt(i + 1, level);
              y2 = (isLastLabel && nextLabel && nextLabel.length > 0) ? axisInfo.getEndCoord() : axisInfo.getBoundedCoordAt(end + endOffset);

              if (label) // draw vertical line around label
                DvtAxisRenderer._addSeparatorLine(axis, lineStroke, x2, y1, x2, y2);
            }
          }
          // information about previous label
          prevLabelRendered = (!isEmptyLabel && label != null);
          prevLabelEmpty = label != null || (label == null && isEmptyLabel); //TIOD TAMIKA: IS THIS NECESSARY
        }
      }
      prevLevelSize = levelSize; // save height or width of previous level
    }
  }
  return;
};

/**
 * Renders separator line
 * @param {DvtAxis} axis The axis on which the separators are rendered.
 * @param {DvtSolidStroke} lineStroke The stroke for the line.
 * @param {Number} x1 The first xCoordinate of the line.
 * @param {Number} y1 The first yCoordinate of the line.
 * @param {Number} x2 The second xCoordinate of the line.
 * @param {Number} y2 The second yCoordinate of the line.
 * @private
 */
DvtAxisRenderer._addSeparatorLine = function(axis, lineStroke, x1, y1, x2, y2) {
  var line = new DvtLine(axis.getCtx(), x1, y1, x2, y2);
  line.setStroke(lineStroke);
  line.setPixelHinting(true);
  axis.addChild(line);

  return;
};
/**
 * Calculated axis information and drawable creation.  This class should
 * not be instantiated directly.
 * @class
 * @constructor
 * @extends {DvtObj}
 */
var DvtAxisInfo = function() {};

DvtObj.createSubclass(DvtAxisInfo, DvtObj, 'DvtAxisInfo');


/**
 * Creates an appropriate instance of DvtAxisInfo with the specified parameters.
 * @param {DvtContext} context
 * @param {object} options The object containing specifications and data for this component.
 * @param {DvtRectangle} availSpace The available space.
 * @return {DvtAxisInfo}
 */
DvtAxisInfo.newInstance = function(context, options, availSpace) {
  if (options['timeAxisType'] && options['timeAxisType'] != 'disabled')
    return new DvtTimeAxisInfo(context, options, availSpace);
  else if (isNaN(options['dataMin']) && isNaN(options['dataMax']))
    return new DvtGroupAxisInfo(context, options, availSpace);
  else
    return new DvtDataAxisInfo(context, options, availSpace);
};


/**
 * Calculates and stores the axis information.
 * @param {DvtContext} context
 * @param {object} options The object containing specifications and data for this component.
 * @param {DvtRectangle} availSpace The available space.
 * @protected
 */
DvtAxisInfo.prototype.Init = function(context, options, availSpace) {
  this._context = context;

  // Figure out the start and end coordinate of the axis
  this.Position = options['position'];
  this._radius = options['_radius']; // for polar charts
  this._title = null;

  if (this.Position == 'top' || this.Position == 'bottom') {
    this.StartCoord = availSpace.x;
    this.EndCoord = availSpace.x + availSpace.w;
  }
  else if (this.Position == 'left' || this.Position == 'right') {
    this.StartCoord = availSpace.y;
    this.EndCoord = availSpace.y + availSpace.h;
  }
  else if (this.Position == 'radial') {
    this.StartCoord = 0;
    this.EndCoord = this._radius;
  }
  else if (this.Position == 'tangential') {
    if (DvtAgent.isRightToLeft(context)) {
      this.StartCoord = 2 * Math.PI;
      this.EndCoord = 0;
    }
    else {
      this.StartCoord = 0;
      this.EndCoord = 2 * Math.PI;
    }
  }

  // Axis min and max value. Subclasses should set.
  this.MinValue = null;
  this.MaxValue = null;
  this.GlobalMin = null;
  this.GlobalMax = null;
  this.DataMin = null;
  this.DataMax = null;

  // Set the maximum zoom for this axis
  this.MinViewportExtent = null;

  // The overflows at the two ends of the axis
  this.StartOverflow = 0;
  this.EndOverflow = 0;

  // Sets the buffers (the maximum amount the labels can go over before they overflow)
  if (options['leftBuffer'] == null)
    options['leftBuffer'] = Infinity;
  if (options['rightBuffer'] == null)
    options['rightBuffer'] = Infinity;

  // Store the options object
  this.Options = options;
};


/**
 * Returns the DvtContext associated with this instance.
 * @return {DvtContext}
 */
DvtAxisInfo.prototype.getCtx = function() {
  return this._context;
};


/**
 * Returns the options settings for the axis.
 * @return {object} The options for the axis.
 */
DvtAxisInfo.prototype.getOptions = function() {
  return this.Options;
};


/**
 * Returns an array containing the tick labels for this axis.
 * @param {DvtContext} context
 * @param {Number} levelIdx The level index (optional). 0 indicates the first level, 1 the second, etc. If skipped, 0 (the first level) is assumed.
 * @return {Array} The Array of DvtText objects.
 */
DvtAxisInfo.prototype.getLabels = function(context, levelIdx) {
  return null; // subclasses should override
};


/**
 * Returns the title for this axis.
 * @return {DvtText} The DvtText object, if it exists.
 */
DvtAxisInfo.prototype.getTitle = function() {
  return this._title;
};

/**
 * Sets the title for this axis.
 * @param {DvtText} title The axis title.
 */
DvtAxisInfo.prototype.setTitle = function(title) {
  this._title = title;
};


/**
 * Returns the coordinates of the major ticks.
 * @return {array} Array of coords.
 */
DvtAxisInfo.prototype.getMajorTickCoords = function() {
  return []; // subclasses should override
};

/**
 * Returns the coordinates of the minor ticks.
 * @return {array} Array of coords.
 */
DvtAxisInfo.prototype.getMinorTickCoords = function() {
  return []; // subclasses should override
};


/**
 * Returns the coordinates of the baseline (value = 0). Only applies to numerical axis.
 * @return {number} Baseline coord.
 */
DvtAxisInfo.prototype.getBaselineCoord = function() {
  return null; // subclasses should override
};


/**
 * Returns the value for the specified coordinate along the axis.  Returns null
 * if the coordinate is not within the axis.
 * @param {number} coord The coordinate along the axis.
 * @return {object} The value at that coordinate.
 */
DvtAxisInfo.prototype.getValueAt = function(coord) {
  return null; // subclasses should override
};


/**
 * Returns the coordinate for the specified value.  Returns null if the value is
 * not within the axis.
 * @param {object} value The value to locate.
 * @return {number} The coordinate for the value.
 */
DvtAxisInfo.prototype.getCoordAt = function(value) {
  return null; // subclasses should override
};


/**
 * Returns the value for the specified coordinate along the axis.  If a coordinate
 * is not within the axis, returns the value of the closest coordinate within the axis.
 * @param {number} coord The coordinate along the axis.
 * @return {object} The value at that coordinate.
 */
DvtAxisInfo.prototype.getBoundedValueAt = function(coord) {
  return null; // subclasses should override
};


/**
 * Returns the coordinate for the specified value along the axis.  If a value
 * is not within the axis, returns the coordinate of the closest value within the axis.
 * @param {object} value The value to locate.
 * @return {number} The coordinate for the value.
 */
DvtAxisInfo.prototype.getBoundedCoordAt = function(value) {
  return null; // subclasses should override
};


/**
 * Returns the value for the specified coordinate along the axis.
 * @param {number} coord The coordinate along the axis.
 * @return {object} The value at that coordinate.
 */
DvtAxisInfo.prototype.getUnboundedValueAt = function(coord) {
  return null; // subclasses should override
};


/**
 * Returns the coordinate for the specified value.
 * @param {object} value The value to locate.
 * @return {number} The coordinate for the value.
 */
DvtAxisInfo.prototype.getUnboundedCoordAt = function(value) {
  return null; // subclasses should override
};


/**
 * Returns the tooltip for the specified label.
 * @param {object} label The label.
 * @param {number} index The label index.
 * @return {string} The label tooltip.
 */
DvtAxisInfo.prototype.getTooltip = function(label, index) {
  return label.getUntruncatedTextString(); // subclasses should override
};

/**
 * Returns an object with the label's background labelStyles applied
 * @param {DvtOutputText} label The label.
 * @param {DvtContext} context
 * @return {DvtRect} The object to be rendered behind the label.
 */
DvtAxisInfo.prototype.getLabelBackground = function(label, context) {
  return null; // subclasses should override
};


/**
 * Returns the action for the label with the given index
 * @param {number} index The label index.
 * @return {object} The action
 */
DvtAxisInfo.prototype.getAction = function(index) {
  return null; // subclasses should override
};

/**
 * Returns whether the label at the given index is drillable
 * @param {number} index The label index.
 * @return {boolean} Whether the label is drillable.
 */
DvtAxisInfo.prototype.isDrillable = function(index) {
  return null; // subclasses should override
};


/**
 * Returns the baseline coordinate for the axis, if applicable.
 * @return {number} The baseline coordinate for the axis.
 */
DvtAxisInfo.prototype.getBaselineCoord = function() {
  return null;
};


/**
 * Returns if the labels of the horizontal axis are rotated by 90 degrees.
 * @return {boolean} Whether the labels are rotated.
 */
DvtAxisInfo.prototype.isLabelRotated = function() {
  return false;
};


/**
 * Creates a DvtText instance for the specified text label.
 * @param {DvtContext} context
 * @param {string} label The label string.
 * @param {number} coord The coordinate for the text.
 * @param {DvtCSSStyle} style (optional) The style for the text label.
 * @return {DvtOutputText}
 * @protected
 */
DvtAxisInfo.prototype.CreateLabel = function(context, label, coord, style) {
  var text;

  if (this.Position == 'tangential') {
    var vTol = 16 / 180 * Math.PI; // the mid area (15 degrees) where labels will be middle aligned.
    var hTol = 1 / 180 * Math.PI; // the tolerance (1 degree) where labels will be center aligned.

    var offset = 0.5 * this.getTickLabelFontSize();
    var dist = this._radius + offset;
    if (coord < hTol || coord > 2 * Math.PI - hTol)
      dist += offset; // avoild collision with radial label

    var xcoord = Math.round(dist * Math.sin(coord));
    var ycoord = Math.round(-dist * Math.cos(coord));
    text = style ? new DvtBackgroundOutputText(context, label, xcoord, ycoord, style) : new DvtOutputText(context, label, xcoord, ycoord);

    // Align the label according to the angular position
    if (coord < hTol || Math.abs(coord - Math.PI) < hTol || coord > 2 * Math.PI - hTol)
      text.alignCenter();
    else if (coord < Math.PI)
      text.alignLeft();
    else
      text.alignRight();

    if (Math.abs(coord - Math.PI / 2) < vTol || Math.abs(coord - 3 * Math.PI / 2) < vTol)
      text.alignMiddle();
    else if (coord < Math.PI / 2 || coord > 3 * Math.PI / 2)
      text.alignBottom();
    else
      text.alignTop();
  }
  else {
    text = style ? new DvtBackgroundOutputText(context, label, coord, coord, style) : new DvtOutputText(context, label, coord, coord);

    text.alignMiddle();
    text.alignCenter();
  }
  if (!(text instanceof DvtBackgroundOutputText)) // DvtBackgroundOutputText already created with its CSSStyle
    text.setCSSStyle(this.Options['tickLabel']['style']);
  return text;
};


/**
 * Checks all the labels for the axis and returns whether they overlap.
 * @param {Array} labelDims An array of DvtRectangle objects that describe the x, y, height, width of the axis labels.
 * @param {number} skippedLabels The number of labels to skip. If skippedLabels is 1 then every other label will be skipped.
 * @return {boolean} True if any labels overlap.
 * @protected
 */
DvtAxisInfo.prototype.IsOverlapping = function(labelDims, skippedLabels) {
  // If there are no labels, return
  if (!labelDims || labelDims.length <= 0)
    return false;

  var isVert = (this.Position == 'left' || this.Position == 'right' || this.Position == 'radial');
  var isRTL = DvtAgent.isRightToLeft(this.getCtx());
  var gap = this.GetTickLabelGapSize();

  var pointA1, pointA2, pointB1, pointB2;
  for (var j = 0; j < labelDims.length; j += skippedLabels + 1) {
    if (labelDims[j] == null)
      continue;

    if (pointA1 == null || pointA2 == null) {
      // Set the first points
      if (isVert) {
        pointA1 = labelDims[j].y;
        pointA2 = labelDims[j].y + labelDims[j].h;
      } else {
        pointA1 = labelDims[j].x;
        pointA2 = labelDims[j].x + labelDims[j].w;
      }
      continue;
    }

    if (isVert) {
      pointB1 = labelDims[j].y;
      pointB2 = labelDims[j].y + labelDims[j].h;

      // Broken apart for clarity, next label may be above or below
      if (pointB1 >= pointA1 && pointB1 - gap < pointA2) // next label below
        return true;
      else if (pointB1 < pointA1 && pointB2 + gap > pointA1) // next label above
        return true;
    }
    else {
      pointB1 = labelDims[j].x;
      pointB2 = labelDims[j].x + labelDims[j].w;

      // Broken apart for clarity, next label is on the right for non-BIDI, left for BIDI
      if (!isRTL && (pointB1 - gap < pointA2))
        return true;
      else if (isRTL && (pointB2 + gap > pointA1))
        return true;
    }

    // Otherwise start evaluating from label j
    pointA1 = pointB1;
    pointA2 = pointB2;
  }
  return false;
};

/**
 * Compares two label dimensions and returns whether they overlap.
 * @param {Object} labelDims1 An object that describes the x, y, height, width of the first label.
 * @param {Object} labelDims2 An object that describes the x, y, height, width of the second label.
 * @return {boolean} True if the label dimensions overlap.
 * @protected
 */
DvtAxisInfo.prototype.IsOverlappingDims = function(labelDims1, labelDims2) {
  if (!labelDims1 || !labelDims2)
    return false;

  var pointA1 = labelDims1.y;
  var pointA2 = labelDims1.y + labelDims1.h;
  var pointA3 = labelDims1.x;
  var pointA4 = labelDims1.x + labelDims1.w;

  var pointB1 = labelDims2.y;
  var pointB2 = labelDims2.y + labelDims2.h;
  var pointB3 = labelDims2.x;
  var pointB4 = labelDims2.x + labelDims2.w;

  var widthOverlap = (pointB3 < pointA4 && pointB3 > pointA3) || (pointA3 < pointB4 && pointB4 < pointA4);
  var heightOverlap = (pointB1 >= pointA1 && pointB1 < pointA2) || (pointB1 < pointA1 && pointB2 > pointA1);

  return (widthOverlap && heightOverlap);

};

/**
 * Returns the tick label gap size.
 * @return {number}
 * @protected
 */
DvtAxisInfo.prototype.GetTickLabelGapSize = function() {
  // Get font-size of label and create gap based on font-size
  // GroupAxis and TimeAxis have smaller gaps since these axes become less useable as more labels are dropped
  var fontSize = this.getTickLabelFontSize();
  var gapHoriz = (this instanceof DvtGroupAxisInfo) ? fontSize * 0.3 : fontSize * 1.0;
  var gapVert = (this instanceof DvtGroupAxisInfo) ? fontSize * 0.1 : fontSize * 0.35;

  var isVert = (this.Position == 'left' || this.Position == 'right' || this.Position == 'radial');
  return (isVert || this.isLabelRotated()) ? gapVert : gapHoriz;
};

/**
 * Returns the tick label font size.
 * @return {number}
 */
DvtAxisInfo.prototype.getTickLabelFontSize = function() {
  // Use getTextStringHeight() to account for non-pixel font sizes.
  // The multiplication factor at the end came from the fact that 11px font size has 14px height.
  return DvtTextUtils.getTextStringHeight(this.getCtx(), this.Options['tickLabel']['style']) * (11 / 14);
};


/**
 * Checks the labels for the axis and skips them as necessary.
 * @param {Array} labels An array of DvtText labels for the axis.
 * @param {Array} labelDims An array of DvtRectangle objects that describe the x, y, height, width of the axis labels.
 * @return {Array} The array of DvtText labels for the axis.
 * @protected
 */
DvtAxisInfo.prototype.SkipLabels = function(labels, labelDims) {
  var skippedLabels = 0;
  var bOverlaps = this.IsOverlapping(labelDims, skippedLabels);
  while (bOverlaps) {
    skippedLabels++;
    bOverlaps = this.IsOverlapping(labelDims, skippedLabels);
  }

  if (skippedLabels > 0) {
    var renderedLabels = [];
    for (var j = 0; j < labels.length; j += skippedLabels + 1) {
      renderedLabels.push(labels[j]);
    }
    return renderedLabels;
  } else {
    return labels;
  }
};


/**
 * Checks the labels for the tangential axis and skips them as necessary.
 * @param {Array} labels An array of DvtText labels for the axis.
 * @param {Array} labelDims An array of DvtRectangle objects that describe the x, y, height, width of the axis labels.
 * @return {Array} The array of DvtText labels for the tangential axis.
 * @protected
 */
DvtAxisInfo.prototype.SkipTangentialLabels = function(labels, labelDims) {
  var renderedLabels = [];
  var numLabels = labels.length;

  if (numLabels > 0) {
    // Include first label to start
    renderedLabels.push(labels[0]);
    var prevLabel = labels[0];
    var prevLabelDims = labelDims[0];

    // For labels up to the second to last label, include label if it does not overlap with the previously included label
    for (var j = 1; j < numLabels - 1; j++) {
      if (prevLabelDims && labelDims[j]) {
        if (!this.IsOverlappingDims(prevLabelDims, labelDims[j])) {
          prevLabel = labels[j];
          prevLabelDims = labelDims[j];
          renderedLabels.push(prevLabel);
        }
      }
    }

    // Include last label if it doesn't overlap with the previously included label or first label
    if (!this.IsOverlappingDims(prevLabelDims[j], labelDims[numLabels - 1]) && !this.IsOverlappingDims(labelDims[numLabels - 1], labelDims[0]))
      renderedLabels.push(labels[numLabels - 1]);

    return renderedLabels;
  }
  return labels;
};


/**
 * Returns an array of DvtRectangle objects that describe the x, y, width, height of the axis labels.
 * @param {Array} labels An array of DvtText labels for the axis.
 * @param {DvtContainer} container
 * @return {Array} An array of DvtRectangle objects
 * @protected
 */
DvtAxisInfo.prototype.GetLabelDims = function(labels, container) {
  var labelDims = [];

  // Get the text dimensions
  for (var i = 0; i < labels.length; i++) {
    var text = labels[i];
    if (text == null) {
      labelDims.push(null);
    } else {
      var dims = text.measureDimensions(container);
      if (dims.w && dims.h) // Empty group axis labels with 0 height and width are possible, they should count as null
        labelDims.push(dims);
      else
        labelDims.push(null);
    }
  }

  return labelDims;
};


/**
 * Returns an array of DvtRectangle objects that contains a conservative guess the x, y, width, height of the axis labels.
 * Assumes that the labels are center-middle aligned.
 * @param {Array} labels An array of DvtText labels for the axis.
 * @param {DvtContainer} container
 * @param {Number} fudgeFactor (optional) A factor the would be multiplied to the text width. If not provided, assumed to be 1.
 * @param {Number} level (optional) Used for group axis hierarchical labels
 * @return {Array} An array of DvtRectangle objects
 * @protected
 */
DvtAxisInfo.prototype.GuessLabelDims = function(labels, container, fudgeFactor, level) {
  var labelDims = [];
  if (typeof fudgeFactor == 'undefined')
    fudgeFactor = 1;

  // Get the text dimensions
  for (var i = 0; i < labels.length; i++) {
    var text = labels[i];
    if (text == null) {
      labelDims.push(null);
    } else {
      // get a conservative estimate of the dimensions
      container.addChild(text);
      var estimatedSize = DvtTextUtils.guessTextDimensions(text);
      var estW = estimatedSize.w * fudgeFactor;
      var estH = estimatedSize.h;
      container.removeChild(text);

      var dims;
      if (this.isLabelRotated(level)) {
        dims = new DvtRectangle(text.getTranslateX() - estH / 2, text.getTranslateY() - estW / 2, estH, estW);
      } else {
        dims = new DvtRectangle(text.getX() - estW / 2, text.getY() - estH / 2, estW, estH);
      }
      labelDims.push(dims);
    }
  }

  return labelDims;
};


/**
 * Returns the number of major tick counts for the axis.
 * @return {number} The number of major tick counts.
 */
DvtAxisInfo.prototype.getMajorTickCount = function() {
  return null; // subclasses that allow major gridlines should implement
};


/**
 * Sets the number of major tick counts for the axis.
 * @param {number} count The number of major tick counts.
 */
DvtAxisInfo.prototype.setMajorTickCount = function(count) {
  // subclasses that allow major gridlines should implement
};


/**
 * Returns the number of minor tick counts for the axis.
 * @return {number} The number of minor tick counts.
 */
DvtAxisInfo.prototype.getMinorTickCount = function() {
  return null; // subclasses that allow minor gridlines should implement
};


/**
 * Sets the number of minor tick counts for the axis.
 * @param {number} count The number of minor tick counts.
 */
DvtAxisInfo.prototype.setMinorTickCount = function(count) {
  // subclasses that allow minor gridlines should implement
};


/**
 * Returns the major increment for the axis.
 * @return {number} The major increment.
 */
DvtAxisInfo.prototype.getMajorIncrement = function() {
  return null; // subclasses that allow major gridlines should implement
};


/**
 * Returns the minor increment for the axis.
 * @return {number} The minor increment.
 */
DvtAxisInfo.prototype.getMinorIncrement = function() {
  return null; // subclasses that allow minor gridlines should implement
};


/**
 * Returns the global min value of the axis.
 * @return {number} The global min value.
 */
DvtAxisInfo.prototype.getGlobalMin = function() {
  return this.GlobalMin;
};


/**
 * Returns the global max value of the axis.
 * @return {number} The global max value.
 */
DvtAxisInfo.prototype.getGlobalMax = function() {
  return this.GlobalMax;
};


/**
 * Returns the viewport min value of the axis.
 * @return {number} The viewport min value.
 */
DvtAxisInfo.prototype.getViewportMin = function() {
  return this.MinValue;
};


/**
 * Returns the viewport max value of the axis.
 * @return {number} The viewport max value.
 */
DvtAxisInfo.prototype.getViewportMax = function() {
  return this.MaxValue;
};


/**
 * Returns the data min value of the axis.
 * @return {number} The data min value.
 */
DvtAxisInfo.prototype.getDataMin = function() {
  return this.DataMin;
};


/**
 * Returns the data max value of the axis.
 * @return {number} The data max value.
 */
DvtAxisInfo.prototype.getDataMax = function() {
  return this.DataMax;
};


/**
 * Returns the minimum extent of the axis, i.e. the (minValue-maxValue) during maximum zoom.
 * @return {number} The minimum extent.
 */
DvtAxisInfo.prototype.getMinimumExtent = function() {
  return 0;
};


/**
 * Returns the start coord.
 * @return {number}
 */
DvtAxisInfo.prototype.getStartCoord = function() {
  return this.StartCoord;
};


/**
 * Returns the end coord.
 * @return {number}
 */
DvtAxisInfo.prototype.getEndCoord = function() {
  return this.EndCoord;
};


/**
 * Returns how much the axis labels overflow over the start coord.
 * @return {number}
 */
DvtAxisInfo.prototype.getStartOverflow = function() {
  return this.StartOverflow;
};


/**
 * Returns how much the axis labels overflow over the end coord.
 * @return {number}
 */
DvtAxisInfo.prototype.getEndOverflow = function() {
  return this.EndOverflow;
};

/**
 * Gets the width of a group (for rendering bar chart)
 * @return {Number} the width of a group
 */
DvtAxisInfo.prototype.getGroupWidth = function() {
  return 0;
};

/**
 * Returns a string or an array of groups names/ids of the ancestors of a group label at the given index and level.
 * @param {Number} index The index of the group label within it's level of labels
 * @param {Number=} level The level of the group labels
 * @return {String|Array} The group name/id, or an array of group names/ids.
 * @override
 */
DvtAxisInfo.prototype.getGroup = function(index, level) {
  // only applies to group axis
  return null;
};
/**
 * Calculated axis information and drawable creation for a data axis.
 * @param {DvtContext} context
 * @param {object} options The object containing specifications and data for this component.
 * @param {DvtRectangle} availSpace The available space.
 * @class
 * @constructor
 * @extends {DvtAxisInfo}
 */
var DvtDataAxisInfo = function(context, options, availSpace) {
  this.Init(context, options, availSpace);
};

DvtObj.createSubclass(DvtDataAxisInfo, DvtAxisInfo, 'DvtDataAxisInfo');

/** @private @const */
DvtDataAxisInfo._MAX_NUMBER_OF_GRIDS_AUTO = 10;
/** @private @const */
DvtDataAxisInfo._MINOR_TICK_COUNT = 2;
/** @private @const */
DvtDataAxisInfo._MAX_ZOOM_FACTOR = 64;

/**
 * Bug 19192884 - line charts vertex clipped if dataMax too close to globalMax
 * @private
 */
DvtDataAxisInfo._EXTENT_TOLERANCE = 0.045;


/**
 * @override
 */
DvtDataAxisInfo.prototype.Init = function(context, options, availSpace) {
  DvtDataAxisInfo.superclass.Init.call(this, context, options, availSpace);

  // Figure out the coords for the min/max values
  if (this.Position == 'top' || this.Position == 'bottom') {
    // Provide at least the minimum buffer at each side to accommodate labels
    if (options['tickLabel']['rendered'] != 'off' && options['rendered'] != 'off') {
      this.StartOverflow = Math.max(DvtAxis.MINIMUM_AXIS_BUFFER - options['leftBuffer'], 0);
      this.EndOverflow = Math.max(DvtAxis.MINIMUM_AXIS_BUFFER - options['rightBuffer'], 0);
    }

    // Axis is horizontal, so flip for BIDI if needed
    if (DvtAgent.isRightToLeft(context)) {
      this._minCoord = this.EndCoord - this.EndOverflow;
      this._maxCoord = this.StartCoord + this.StartOverflow;
    }
    else {
      this._minCoord = this.StartCoord + this.StartOverflow;
      this._maxCoord = this.EndCoord - this.EndOverflow;
    }
  }
  else if (this.Position == 'tangential' || this.Position == 'radial') {
    this._minCoord = this.StartCoord;
    this._maxCoord = this.EndCoord;
  }
  else {
    this._minCoord = this.EndCoord;
    this._maxCoord = this.StartCoord;
  }

  this.GlobalMin = options['min'];
  this.GlobalMax = options['max'];
  this.MinValue = options['viewportMin'] == null ? this.GlobalMin : options['viewportMin'];
  this.MaxValue = options['viewportMax'] == null ? this.GlobalMax : options['viewportMax'];

  this._majorIncrement = options['step'];
  this._minorIncrement = options['minorStep'];
  this._minMajorIncrement = options['minStep'];
  this._converter = null;
  if (options['tickLabel'] != null) {
    this._converter = options['tickLabel']['converter'];
  }

  this.DataMin = options['dataMin'];
  this.DataMax = options['dataMax'];
  this._calcAxisExtents();
};


/**
 * Returns the value correspoding to the first tick label (or gridline) of the axis.
 * @return {number} The value of the min label.
 */
DvtDataAxisInfo.prototype.getMinLabel = function() {
  if (this.Options['baselineScaling'] == 'zero' || (this.Options['_continuousExtent'] == 'on' && this.Options['min'] == null)) {
    // the tickLabels and gridlines should be at integer intervals from zero
    return Math.ceil(this.MinValue / this._majorIncrement) * this._majorIncrement;
  } else {
    // the tickLabels and gridlines should be at integer intervals from the globalMin
    return Math.ceil((this.MinValue - this.GlobalMin) / this._majorIncrement) * this._majorIncrement + this.GlobalMin;
  }
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getLabels = function(context, levelIdx) {
  if (levelIdx && levelIdx > 0) // data axis has only one level
    return null;

  var labels = [];
  var labelDims = [];
  var container = context.getStage();

  // when scaling is set then init formatter
  if (this.Options['tickLabel'] && this.Options['tickLabel']['scaling']) {
    var autoPrecision = this.Options['tickLabel']['autoPrecision'] ? this.Options['tickLabel']['autoPrecision'] : 'on';
    this._axisValueFormatter = new DvtLinearScaleAxisValueFormatter(context, this.MinValue, this.MaxValue, this._majorIncrement, this.Options['tickLabel']['scaling'], autoPrecision);
  }

  // Iterate on an integer to reduce rounding error.  We use <= since the first
  // tick is not counted in the tick count.
  for (var i = 0; i <= this._majorTickCount; i++) {
    var value = i * this._majorIncrement + this.getMinLabel();
    var coord = this.getUnboundedCoordAt(value);

    if (this.Options['_skipHighestTick']) {
      if (value == this.MaxValue)
        continue;
      if (this.Position != 'tangential' && Math.abs(coord - this._maxCoord) < this.getTickLabelFontSize())
        continue;
    }

    var label = this._formatValue(value);
    var text = this.CreateLabel(context, label, coord);
    labels.push(text);
  }

  if (this.Position != 'tangential') {
    labelDims = this.GetLabelDims(labels, container);
    labels = this.SkipLabels(labels, labelDims);
  }

  return labels;
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getMajorTickCoords = function() {
  var coords = [];

  // Iterate on an integer to reduce rounding error.  We use <= since the first
  // tick is not counted in the tick count.
  for (var i = 0; i <= this._majorTickCount; i++) {
    var value = i * this._majorIncrement + this.getMinLabel();
    if (this.Options['_skipHighestTick'] && value == this.MaxValue)
      continue;

    var coord = this.getUnboundedCoordAt(value);
    coords.push(coord);
  }

  return coords;
};

/**
 * @override
 */
DvtDataAxisInfo.prototype.getMinorTickCoords = function() {
  var coords = [];

  // Iterate on an integer to reduce rounding error.  We use <= since the first
  // tick is not counted in the tick count.
  // Start from i=-1 so that minorTicks that should get rendered before the first majorTick are evaluated
  for (var i = -1; i <= this._majorTickCount; i++) {
    var value = i * this._majorIncrement + this.getMinLabel();
    for (var j = 1; j < this._minorTickCount; j++) {
      var minorValue = value + (j * this._minorIncrement);
      if (minorValue > this.MaxValue)
        break;
      if (minorValue < this.MinValue)
        continue;

      var coord = this.getUnboundedCoordAt(minorValue);
      coords.push(coord);
    }
  }
  return coords;
};

/**
 * @override
 */
DvtDataAxisInfo.prototype.getBaselineCoord = function() {
  return this.getUnboundedCoordAt(0);
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getValueAt = function(coord) {
  var minCoord = Math.min(this._minCoord, this._maxCoord);
  var maxCoord = Math.max(this._minCoord, this._maxCoord);

  // Return null if the coord is outside of the axis
  if (coord < minCoord || coord > maxCoord)
    return null;

  return this.getUnboundedValueAt(coord);
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getCoordAt = function(value) {
  // Return null if the value is outside of the axis
  if (value < this.MinValue || value > this.MaxValue)
    return null;

  return this.getUnboundedCoordAt(value);
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getBoundedValueAt = function(coord) {
  var minCoord = Math.min(this._minCoord, this._maxCoord);
  var maxCoord = Math.max(this._minCoord, this._maxCoord);

  if (coord < minCoord)
    coord = minCoord;
  else if (coord > maxCoord)
    coord = maxCoord;

  return this.getUnboundedValueAt(coord);
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getBoundedCoordAt = function(value) {
  if (value < this.MinValue)
    value = this.MinValue;
  else if (value > this.MaxValue)
    value = this.MaxValue;

  return this.getUnboundedCoordAt(value);
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getUnboundedValueAt = function(coord) {
  var ratio = (coord - this._minCoord) / (this._maxCoord - this._minCoord);
  return this.MinValue + (ratio * (this.MaxValue - this.MinValue));
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getUnboundedCoordAt = function(value) {
  var ratio = (value - this.MinValue) / (this.MaxValue - this.MinValue);
  return this._minCoord + (ratio * (this._maxCoord - this._minCoord));
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getBaselineCoord = function() {
  // First find the value of the baseline
  var baseline = 0;
  if (this.MaxValue < 0)
    baseline = this.MaxValue;
  else if (this.MinValue > 0)
    baseline = this.MinValue;

  // Return its coordinate
  return this.getCoordAt(baseline);
};


/**
 * @param {number} value
 * @return {string} Formatted value.
 * @private
 */
DvtDataAxisInfo.prototype._formatValue = function(value) {

  if (this._converter && (this._converter['getAsString'] || this._converter['format'])) {
    if (this._axisValueFormatter)
      return this._axisValueFormatter.format(value, this._converter);
    else if (this._converter['getAsString'])
      return this._converter['getAsString'](value);
    else if (this._converter['format'])
      return this._converter['format'](value);
  }

  else if (this._axisValueFormatter)
    return this._axisValueFormatter.format(value);

  else {
    // set the # of decimals of the value to the # of decimals of the major increment
    var t = Math.log(this._majorIncrement) / Math.log(10);
    var decimals = Math.max(Math.ceil(-t), 0);
    return value.toFixed(decimals);
  }
};


/**
 * Determines the number of major and minor tick counts and increments for the axis if values were not given.
 * The default minor tick count is 2.
 * @param {number} scaleUnit The scale unit of the axis.
 * @private
 */
DvtDataAxisInfo.prototype._calcMajorMinorIncr = function(scaleUnit) {
  this._majorIncrement = this._majorIncrement ? this._majorIncrement : scaleUnit;

  // Bug 17221659 - y and y2 aligntickmarks doesn't work if min/max is set
  if (this.Options['alignTickMarks'] == 'on' && this.Options['_majorTickCount']) {
    this._majorTickCount = this.Options['_majorTickCount'];
    // Re-calculate majorIncrement while adhering to the tick count saved when axis was aligned
    this._majorIncrement = (this.MaxValue - this.getMinLabel()) / this._majorTickCount;

    // Bug 17568354 - y2 axis should show better labels when tick marks are aligned
    var testVal = Math.pow(10, Math.ceil((Math.log(this._majorIncrement) / Math.log(10)) - 1));
    var firstDigit = this._majorIncrement / testVal;
    if (firstDigit > 1 && firstDigit <= 1.5)
      firstDigit = 1.5;
    else if (firstDigit > 5)
      firstDigit = 10;
    else
      firstDigit = Math.ceil(firstDigit);

    this._majorIncrement = firstDigit * testVal;
    this.MaxValue = (this._majorIncrement * this._majorTickCount) + this.getMinLabel();
  }
  else {
    if (this._minMajorIncrement != null && this._majorIncrement < this._minMajorIncrement)
      this._majorIncrement = this._minMajorIncrement;

    this._majorTickCount = (this.MaxValue - this.getMinLabel()) / this._majorIncrement;
  }

  if (this._minorIncrement != null && this._majorIncrement / this._minorIncrement >= 2)
    this._minorTickCount = this.Options['_minorTickCount'] ? this.Options['_minorTickCount'] : this._majorIncrement / this._minorIncrement;
  else
    this._minorTickCount = DvtDataAxisInfo._MINOR_TICK_COUNT;
  this._minorIncrement = this._majorIncrement / this._minorTickCount;
};


/**
 * Determines the axis extents based on given start and end value
 * or calculated from the min and max data values of the chart.
 * @private
 */
DvtDataAxisInfo.prototype._calcAxisExtents = function() {
  var zeroBaseline = this.Options['baselineScaling'] == 'zero';
  var continuousExtent = this.Options['_continuousExtent'] == 'on';

  // Include 0 in the axis if we're scaling from the baseline
  if (zeroBaseline) {
    this.DataMin = Math.min(0, this.DataMin);
    this.DataMax = Math.max(0, this.DataMax);
  }

  var scaleUnit = this._calcAxisScale((this.GlobalMin != null ? this.GlobalMin : this.DataMin),
                                      (this.GlobalMax != null ? this.GlobalMax : this.DataMax));

  // If there's only a single value on the axis, we need to adjust the
  // this.DataMin and this.DataMax to produce a nice looking axis with around 6 ticks.
  if (this.DataMin == this.DataMax) {
    if (this.DataMin == 0)
      this.DataMax += 5 * scaleUnit;
    else {
      this.DataMin -= 2 * scaleUnit;
      this.DataMax += 2 * scaleUnit;
    }
  }

  // Set the default global min
  if (this.GlobalMin == null) {
    if (zeroBaseline && this.DataMin >= 0) {
      this.GlobalMin = 0;
    }
    else if (continuousExtent) { // allow smooth pan/zoom transition
      this.GlobalMin = this.DataMin - (this.DataMax - this.DataMin) * 0.1;
    }
    else if (!zeroBaseline && this.GlobalMax != null) {
      this.GlobalMin = this.GlobalMax;
      while (this.GlobalMin >= this.DataMin)
        this.GlobalMin -= scaleUnit;
    }
    else {
      this.GlobalMin = (Math.ceil(this.DataMin / scaleUnit - DvtDataAxisInfo._EXTENT_TOLERANCE) - 1) * scaleUnit;
    }

    // If all data points are positive, the axis min shouldn't be less than zero
    if (this.DataMin >= 0)
      this.GlobalMin = Math.max(this.GlobalMin, 0);
  }

  // Set the default global max
  if (this.GlobalMax == null) {
    if (zeroBaseline && this.DataMax <= 0) {
      this.GlobalMax = 0;
    }
    else if (continuousExtent) { // allow smooth pan/zoom transition
      this.GlobalMax = this.DataMax + (this.DataMax - this.DataMin) * 0.1;
    }
    else if (!zeroBaseline) {
      this.GlobalMax = this.GlobalMin;
      while (this.GlobalMax <= this.DataMax)
        this.GlobalMax += scaleUnit;
    }
    else {
      this.GlobalMax = (Math.floor(this.DataMax / scaleUnit + DvtDataAxisInfo._EXTENT_TOLERANCE) + 1) * scaleUnit;
    }

    // If all data points are negative, the axis max shouldn't be more that zero
    if (this.DataMax <= 0)
      this.GlobalMax = Math.min(this.GlobalMax, 0);
  }

  if (this.GlobalMax == this.GlobalMin) { // happens if this.DataMin == this.DataMax == 0
    this.GlobalMax = 100;
    this.GlobalMin = 0;
    scaleUnit = (this.GlobalMax - this.GlobalMin) / DvtDataAxisInfo._MAX_NUMBER_OF_GRIDS_AUTO;
  }

  if (this.MinValue == null)
    this.MinValue = this.GlobalMin;
  if (this.MaxValue == null)
    this.MaxValue = this.GlobalMax;

  // Recalc the scale unit if the axis viewport is limited
  if (this.MinValue != this.GlobalMin || this.MaxValue != this.GlobalMax)
    scaleUnit = this._calcAxisScale(this.MinValue, this.MaxValue);

  if (this.GlobalMin > this.MinValue)
    this.GlobalMin = this.MinValue;
  if (this.GlobalMax < this.MaxValue)
    this.GlobalMax = this.MaxValue;

  // Calculate major and minor gridlines
  this._calcMajorMinorIncr(scaleUnit);
};


/**
 * Determines the scale unit of the axis based on a given start and end axis extent.
 * @param {number} min The start data value for the axis.
 * @param {number} max The end data value for the axis.
 * @return {number} The scale unit of the axis.
 * @private
 */
DvtDataAxisInfo.prototype._calcAxisScale = function(min, max) {
  if (this._majorIncrement)
    return this._majorIncrement;

  var spread = max - min;
  if (spread == 0) {
    if (min == 0)
      return 10;
    else
      return Math.pow(10, Math.floor(Math.log(min) / Math.LN10) - 1);
  }

  var t = Math.log(spread) / Math.log(10);
  var testVal = Math.pow(10, Math.ceil(t) - 2);
  var first2Digits = Math.round(spread / testVal);

  // Aesthetically choose a scaling factor limiting to a max number of steps
  var scaleFactor = 1;
  if (first2Digits >= 10 && first2Digits <= 14)
    scaleFactor = 2;
  else if (first2Digits >= 15 && first2Digits <= 19)
    scaleFactor = 3;
  else if (first2Digits >= 20 && first2Digits <= 24)
    scaleFactor = 4;
  else if (first2Digits >= 25 && first2Digits <= 45)
    scaleFactor = 5;
  else if (first2Digits >= 46 && first2Digits <= 80)
    scaleFactor = 10;
  else
    scaleFactor = 20;

  return scaleFactor * testVal;
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getMajorTickCount = function() {
  return this._majorTickCount;
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.setMajorTickCount = function(count) {
  this._majorTickCount = count;
  this._majorIncrement = (this.MaxValue - this.MinValue) / this._majorTickCount;
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getMinorTickCount = function() {
  return this._minorTickCount;
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.setMinorTickCount = function(count) {
  this._minorTickCount = count;
  this._minorIncrement = this._majorIncrement / this._minorTickCount;
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getMajorIncrement = function() {
  return this._majorIncrement;
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getMinorIncrement = function() {
  return this._minorIncrement;
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getMinimumExtent = function() {
  return (this.GlobalMax - this.GlobalMin) / DvtDataAxisInfo._MAX_ZOOM_FACTOR;
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getStartOverflow = function() {
  if ((this.Position == 'top' || this.Position == 'bottom') && DvtAgent.isRightToLeft(this.getCtx()))
    return this.EndOverflow;
  else
    return this.StartOverflow;
};


/**
 * @override
 */
DvtDataAxisInfo.prototype.getEndOverflow = function() {
  if ((this.Position == 'top' || this.Position == 'bottom') && DvtAgent.isRightToLeft(this.getCtx()))
    return this.StartOverflow;
  else
    return this.EndOverflow;
};
/**
 * Calculated axis information and drawable creation for a group axis.
 * @param {DvtContext} context
 * @param {object} options The object containing specifications and data for this component.
 * @param {DvtRectangle} availSpace The available space.
 * @class
 * @constructor
 * @extends {DvtAxisInfo}
 */
var DvtGroupAxisInfo = function(context, options, availSpace) {
  this.Init(context, options, availSpace);
};

DvtObj.createSubclass(DvtGroupAxisInfo, DvtAxisInfo, 'DvtGroupAxisInfo');


/**
 * @override
 */
DvtGroupAxisInfo.prototype.Init = function(context, options, availSpace) {
  DvtGroupAxisInfo.superclass.Init.call(this, context, options, availSpace);

  // Flip horizontal axes for BIDI
  var isRTL = DvtAgent.isRightToLeft(context);
  if ((this.Position == 'top' || this.Position == 'bottom') && isRTL) {
    var temp = this.StartCoord;
    this.StartCoord = this.EndCoord;
    this.EndCoord = temp;
  }

  this._levelsArray = [];
  this._groupCount = this._generateLevelsArray(options['groups'], 0, this._levelsArray, 0); // populates this._levelsArray and returns groupCount
  this._numLevels = this._levelsArray.length;
  this._areSeparatorsRendered = options['groupSeparators']['rendered'] == 'on';
  this._separatorColor = options['groupSeparators']['color'];
  this._lastRenderedLevel = null;
  this._drilling = options['drilling'];

  // Calculate the increment and add offsets if specified
  var endOffset = (options['endGroupOffset'] > 0) ? Number(options['endGroupOffset']) : 0;
  var startOffset = (options['startGroupOffset'] > 0) ? Number(options['startGroupOffset']) : 0;

  // Set the axis min/max
  this.DataMin = 0;
  this.DataMax = this._groupCount - 1;

  this.GlobalMin = options['min'] == null ? this.DataMin - startOffset : options['min'];
  this.GlobalMax = options['max'] == null ? this.DataMax + endOffset : options['max'];

  // Set min/max by start/endGroup
  var startIndex = this.getGroupIndex(options['viewportStartGroup']);
  var endIndex = this.getGroupIndex(options['viewportEndGroup']);
  if (startIndex != -1)
    this.MinValue = startIndex - startOffset;
  if (endIndex != -1)
    this.MaxValue = endIndex + endOffset;

  // Set min/max by viewport min/max
  if (options['viewportMin'] != null)
    this.MinValue = options['viewportMin'];
  if (options['viewportMax'] != null)
    this.MaxValue = options['viewportMax'];

  // If min/max is still undefined, fall back to global min/max
  if (this.MinValue == null)
    this.MinValue = this.GlobalMin;
  if (this.MaxValue == null)
    this.MaxValue = this.GlobalMax;

  if (this.GlobalMin > this.MinValue)
    this.GlobalMin = this.MinValue;
  if (this.GlobalMax < this.MaxValue)
    this.GlobalMax = this.MaxValue;

  this._groupWidthRatios = options['_groupWidthRatios'];
  this._processGroupWidthRatios();

  this._startBuffer = isRTL ? options['rightBuffer'] : options['leftBuffer'];
  this._endBuffer = isRTL ? options['leftBuffer'] : options['rightBuffer'];

  this._isLabelRotated = [];
  for (var i = 0; i < this._numLevels; i++)
    this._isLabelRotated.push(false);

  this._renderGridAtLabels = options['_renderGridAtLabels'];

  this._labels = null;
};


/**
 * Processes group width ratios to support bar chart with varying widths.
 * @private
 */
DvtGroupAxisInfo.prototype._processGroupWidthRatios = function() {
  // Edge case: less than two groups
  if (!this._groupWidthRatios || this._groupWidthRatios.length < 2) {
    this._groupWidthRatios = null;
    return;
  }

  // Compute the sums of the group widths that are contained within the viewport
  var sum = 0;
  var groupMin, groupMax;
  for (var g = 0; g < this._groupCount; g++) {
    groupMin = (g == 0) ? this.MinValue : Math.max(g - 0.5, this.MinValue);
    groupMax = (g == this._groupCount - 1) ? this.MaxValue : Math.min(g + 0.5, this.MaxValue);
    if (groupMax > groupMin)
      sum += (groupMax - groupMin) * this._groupWidthRatios[g];
  }

  // Divide the total viewport length (in pixels) proportionally based on the group width ratios.
  var totalWidth = this.EndCoord - this.StartCoord;
  this._groupWidths = this._groupWidthRatios.map(function(ratio) {return ratio * totalWidth / sum;});

  // Construct borderValues array which stores the the value location of the group boundaries.
  this._borderValues = [];
  for (var g = 0; g < this._groupWidthRatios.length - 1; g++) {
    this._borderValues.push(g + 0.5);
  }

  // Construct borderCoords array which stores the coord location of the group boundaries.
  this._borderCoords = [];
  var anchor = Math.min(Math.max(Math.round(this.MinValue), 0), this._borderValues.length - 1);
  this._borderCoords[anchor] = this.StartCoord + (this._borderValues[anchor] - this.MinValue) * this._groupWidths[anchor];
  for (var g = anchor + 1; g < this._borderValues.length; g++) // compute borderCoords after the anchor
    this._borderCoords[g] = this._borderCoords[g - 1] + this._groupWidths[g];
  for (var g = anchor - 1; g >= 0; g--) // compute borderCoords before the anchor
    this._borderCoords[g] = this._borderCoords[g + 1] - this._groupWidths[g + 1];
};


/**
 * Rotates the labels of the horizontal axis by 90 degrees and skips the labels if necessary.
 * @param {Array} labels An array of DvtText labels for the axis.
 * @param {DvtContainer} container
 * @param {number} overflow How much overflow the rotated labels will have.
 * @param {number} level The level the labels array corresponds to
 * @return {Array} The array of DvtText labels for the axis.
 * @private
 */
DvtGroupAxisInfo.prototype._rotateLabels = function(labels, container, overflow, level) {
  var text;
  var x;
  var isRTL = DvtAgent.isRightToLeft(this.getCtx());

  if (level == null)
    level = this._numLevels - 1;

  this._isLabelRotated[level] = true;
  this._setOverflow(overflow, overflow, labels);

  for (var i = 0; i < labels.length; i++) {
    text = labels[i];
    if (text == null)
      continue;
    x = text.getX();
    text.setX(0);
    text.setY(0);
    if (isRTL)
      text.setRotation(Math.PI / 2);
    else
      text.setRotation(3 * Math.PI / 2);
    text.setTranslateX(x);
  }

  var labelDims = this.GuessLabelDims(labels, container, null, level); // the guess returns the exact heights

  return this.SkipLabels(labels, labelDims);
};



/**
 * @override
 */
DvtGroupAxisInfo.prototype.isLabelRotated = function(level) {
  if (level == null)
    level = this._numLevels - 1;
  return this._isLabelRotated[level];
};


/**
 * Sets the start/end overflow of the axis.
 * @param {number} startOverflow How much the first label overflows beyond the start coord.
 * @param {number} endOverflow How much the last label overflows beyonod the end coord.
 * @param {array} labels An array of DvtText labels for a specific level. The x coordinated of the labels will be recalculated.
 * @private
 */
DvtGroupAxisInfo.prototype._setOverflow = function(startOverflow, endOverflow, labels) {
  startOverflow = Math.max(startOverflow - this._startBuffer, 0);
  endOverflow = Math.max(endOverflow - this._endBuffer, 0);

  // Revert the start/endCoord to the original positions before applying the new overflow values
  var isRTL = DvtAgent.isRightToLeft(this.getCtx());
  this.StartCoord += (startOverflow - this.StartOverflow) * (isRTL ? -1 : 1);
  this.EndCoord -= (endOverflow - this.EndOverflow) * (isRTL ? -1 : 1);

  // Reprocess since startCoord and endCoord have changed
  this._processGroupWidthRatios();

  // Recalculate coords for all levels.
  for (var j = 0; j < this._numLevels; j++) {
    labels = this._labels[j];
    // Adjust the label coords
    for (var i = 0; i < labels.length; i++) {
      var text = labels[i];
      if (text) {
        var coord = this._getLabelCoord(j, i);
        text.setX(coord);
      }
    }
  }

  this.StartOverflow = startOverflow;
  this.EndOverflow = endOverflow;
};

/**
 * @override
 */
DvtGroupAxisInfo.prototype.getLabels = function(context, level) {
  if (level == null) // Default to returning inner most labels
    level = this._numLevels - 1;

  if (!this._labels)
    this._generateLabels(context);

  return this._labels[level];

};

/**
 * Gets the coordinate of a group label based on it's position in the hierarchy
 * @param {number} level
 * @param {number} index
 * @private
 * @return {number} The label coord
 */
DvtGroupAxisInfo.prototype._getLabelCoord = function(level, index) {
  var startValue = this.getStartIndex(index, level);
  var endValue = this.getEndIndex(index, level);
  if (startValue == null || endValue == null)
    return null;

  if (startValue < this.MinValue && endValue > this.MinValue)
    startValue = this.MinValue;
  if (endValue > this.MaxValue && startValue < this.MaxValue)
    endValue = this.MaxValue;
  var center = endValue ? startValue + (endValue - startValue) / 2 : startValue;
  return this.getCoordAt(center);
};


/**
 * Generates the labels
 * @param {DvtContext} context
 * @private
 */
DvtGroupAxisInfo.prototype._generateLabels = function(context) {
  var labels = [];
  this._labels = [];
  var container = context.getStage();
  var isHoriz = this.Position == 'top' || this.Position == 'bottom';
  var isRTL = DvtAgent.isRightToLeft(context);

  // Iterate and create the labels
  var label, firstLabel, lastLabel;
  var cssStyle;
  var text;
  for (var level = 0; level < this._numLevels; level++) {
    var levels = this._levelsArray[level];
    for (var i = 0; i < levels.length; i++) {
      if (levels[i]) {
        label = this.getLabelAt(i, level);

        var coord = this._getLabelCoord(level, i);
        if (coord != null) {
          // get categorical axis label style, if it exists
          cssStyle = this.getLabelStyleAt(i, level);
          text = this.CreateLabel(context, label, coord, cssStyle);
          labels.push(text);

          // Store first and last label
          if (!firstLabel && level == 0)
            firstLabel = text;
          if (level == 0)
            lastLabel = text;
        }
        else
          labels.push(null);
      }
      else
        labels.push(null);
    }
    this._labels.push(labels);
    labels = [];
  }

  labels = this._labels[this._numLevels - 1];
  var labelDims = [];

  if (this.Position == 'tangential') {
    labelDims = this.GetLabelDims(labels, container); // actual dims
    this._labels[0] = this.SkipTangentialLabels(labels, labelDims);
    return;
  }

  var firstLabelDim = firstLabel.measureDimensions();

  if (isHoriz) {
    var startOverflow, endOverflow;
    if (this.Options['_startOverflow'] != null && this.Options['_endOverflow'] != null) {
      // Use the preset value if available (during z&s animation)
      startOverflow = this.Options['_startOverflow'];
      endOverflow = this.Options['_endOverflow'];
    }
    else {
      // Set the overflow depending on how much the first and the last label go over the bounds
      var lastLabelDim = lastLabel.measureDimensions();
      startOverflow = isRTL ? firstLabelDim.w + firstLabelDim.x - this.StartCoord : this.StartCoord - firstLabelDim.x;
      endOverflow = isRTL ? this.EndCoord - lastLabelDim.x : lastLabelDim.w + lastLabelDim.x - this.EndCoord;
    }

    if (startOverflow > this._startBuffer || endOverflow > this._endBuffer)
      this._setOverflow(startOverflow, endOverflow, labels);
  }

  for (level = this._numLevels - 1; level >= 0; level--) {
    labels = this._labels[level];
    var minLabelDims = this.GuessLabelDims(labels, container, 0.3, level); // minimum estimate
    var maxLabelDims = this.GuessLabelDims(labels, container, null, level);      // maximum estimate

    if (!this.IsOverlapping(maxLabelDims, 0))
      this._labels[level] = labels; // all labels can fit

    // Rotate and skip the labels if necessary
    if (isHoriz) { // horizontal axis
      if (this.Options['tickLabel']['rotation'] == 'auto') {
        if (this.IsOverlapping(minLabelDims, 0)) {
          this._labels[level] = this._rotateLabels(labels, container, firstLabelDim.h / 2, level);
        } else {
          labelDims = this.GetLabelDims(labels, container); // actual dims
          if (this.IsOverlapping(labelDims, 0))
            this._labels[level] = this._rotateLabels(labels, container, firstLabelDim.h / 2, level);
          else
            this._labels[level] = labels;  // all labels can fit
        }
      } else { // no rotation
        labelDims = this.GetLabelDims(labels, container); // get actual dims for skipping
        this._labels[level] = this.SkipLabels(labels, labelDims);
      }
    } else { // vertical axis
      this._labels[level] = this.SkipLabels(labels, maxLabelDims); // maxLabelDims contain the actual heights
    }
  }
};


/**
 * @override
 */
DvtGroupAxisInfo.prototype.getMajorTickCoords = function() {
  var coords = [], coord;

  // when drawing lines between labels, polar charts need gridline drawn after last label, cartesian charts do not.
  var maxIndex = (this.Position == 'tangential') ? this.getGroupCount() : this.getGroupCount() - 1;

  for (var i = 0; i < this._levelsArray[0].length; i++) {
    if (this._levelsArray[0][i]) {
      var start = this.getStartIndex(i, 0);
      var end = this.getEndIndex(i, 0);
      /* If placing gridlines at labels, use the coordinates at the labels
       * Else if placing gridlines in between labels, use the value halfway between two consecutive coordinates */
      if (this._renderGridAtLabels)
        coord = this.getCoordAt(start + (end - start) * .5); // start == end for non-hierarchical labels
      else
        coord = (end + .5 < maxIndex) ? this.getCoordAt(end + .5) : null;

      if (coord != null)
        coords.push(coord);
    }
  }
  return coords;
};

/**
 * @override
 */
DvtGroupAxisInfo.prototype.getMinorTickCoords = function() {
  var coords = [], coord;
  if (!this._levelsArray[1]) // minor ticks only rendered if two levels exist
    return coords;

  for (var i = 0; i < this._levelsArray[1].length; i++) {
    if (this._levelsArray[1][i]) {
      var start = this.getStartIndex(i, 1);
      var end = this.getEndIndex(i, 1);
      /* If placing gridlines at labels, use the coordinates at the labels
       * Else if placing gridlines in between labels, use the value halfway between two consecutive coordinates */
      if (this._renderGridAtLabels)
        coord = this.getCoordAt(start + (end - start) * .5);
      else
        coord = (end + .5 < this.getGroupCount() - 1) ? this.getCoordAt(end + .5) : null;

      if (coord != null)
        coords.push(coord);
    }
  }
  return coords;
};


/**
 * @return {number} The group index.
 * @override
 */
DvtGroupAxisInfo.prototype.getValueAt = function(coord) {
  var minCoord = Math.min(this.StartCoord, this.EndCoord);
  var maxCoord = Math.max(this.StartCoord, this.EndCoord);

  // Return null if the coord is outside of the axis
  if (coord < minCoord || coord > maxCoord)
    return null;

  return this.getUnboundedValueAt(coord);
};


/**
 * @param {number} value The group index.
 * @override
 */
DvtGroupAxisInfo.prototype.getCoordAt = function(value) {
  if (value < this.MinValue || value > this.MaxValue)
    return null;
  else
    return this.getUnboundedCoordAt(value);
};


/**
 * @return {number} The group index.
 * @override
 */
DvtGroupAxisInfo.prototype.getBoundedValueAt = function(coord) {
  var minCoord = Math.min(this.StartCoord, this.EndCoord);
  var maxCoord = Math.max(this.StartCoord, this.EndCoord);

  if (coord < minCoord)
    coord = minCoord;
  else if (coord > maxCoord)
    coord = maxCoord;

  return this.getUnboundedValueAt(coord);
};


/**
 * @param {number} value The group index.
 * @override
 */
DvtGroupAxisInfo.prototype.getBoundedCoordAt = function(value) {
  if (value < this.MinValue)
    value = this.MinValue;
  else if (value >= this.MaxValue)
    value = this.MaxValue;

  return this.getUnboundedCoordAt(value);
};


/**
 * @return {number} The group index.
 * @override
 */
DvtGroupAxisInfo.prototype.getUnboundedValueAt = function(coord) {
  if (this._groupWidthRatios) {
    // Find the anchor, i.e. the group boundary closest to the coord.
    var anchor = this._borderCoords.length;
    for (var g = 0; g < this._borderCoords.length; g++) {
      if (coord <= this._borderCoords[g]) {
        anchor = g;
        break;
      }
    }
    // Compute the value based on the group width at the anchor.
    if (anchor == 0)
      return this._borderValues[0] - (this._borderCoords[0] - coord) / this._groupWidths[0];
    else
      return this._borderValues[anchor - 1] + (coord - this._borderCoords[anchor - 1]) / this._groupWidths[anchor];
  }
  else {
    // Even group widths
    var incr = (this.EndCoord - this.StartCoord) / (this.MaxValue - this.MinValue);
    return this.MinValue + (coord - this.StartCoord) / incr;
  }
};


/**
 * @param {number} value The group index.
 * @override
 */
DvtGroupAxisInfo.prototype.getUnboundedCoordAt = function(value) {
  if (this._groupWidthRatios) {
    // Find the anchor, i.e. the group boundary closest to the value.
    var anchor = this._borderValues.length;
    for (var g = 0; g < this._borderValues.length; g++) {
      if (value <= this._borderValues[g]) {
        anchor = g;
        break;
      }
    }
    // Compute the coord based on the group width at the anchor.
    if (anchor == 0)
      return this._borderCoords[0] - this._groupWidths[0] * (this._borderValues[0] - value);
    else
      return this._borderCoords[anchor - 1] + this._groupWidths[anchor] * (value - this._borderValues[anchor - 1]);
  }
  else {
    // Even group widths
    var incr = (this.EndCoord - this.StartCoord) / (this.MaxValue - this.MinValue);
    return this.StartCoord + (value - this.MinValue) * incr;
  }
};


/**
 * Returns the group label for the specified group.
 * @param {number} index The index of the group label within it's level.
 * @param {number} level (optional) The level of the group label.
 * @return {string} The group label.
 */
DvtGroupAxisInfo.prototype.getLabelAt = function(index, level) {
  if (level == null)
    level = this._numLevels - 1;

  index = Math.round(index);
  if (index < 0)
    return null;

  var label = this._levelsArray[level] && this._levelsArray[level][index] ? this._levelsArray[level][index]['item'] : null;

  if (label) {
    if (label['name'])
      label = label['name'];
    else if (label['id'] != null) // Empty or null group name allowed if id is specified
      label = '';
  }
  return label;
};

/**
 * Returns the group name or id for the specified group.
 * @param {number} index The index of the group within it's level.
 * @param {number} level (optional) The level of the group label.
 * @return {string} The group name or id.
 */
DvtGroupAxisInfo.prototype.getGroupAt = function(index, level) {
  if (level == null)
    level = this._numLevels - 1;

  index = Math.round(index);
  if (index < 0)
    return null;

  var label = this._levelsArray[level] && this._levelsArray[level][index] ? this._levelsArray[level][index]['item'] : null;

  if (label) {
    if (label['id'])
      return label['id'];
    else if (label['name'] || label['name'] == '')
      return label['name'];
  }

  return label;
};

/**
 * Returns the style for the group label at the specified index and level.
 * @param {number} index The group index.
 * @param {number} level (optional) The level of the group label.
 * @return {DvtCSSStyle}
 */
DvtGroupAxisInfo.prototype.getLabelStyleAt = function(index, level) {
  var labelStyle = this._getGroupAttribute(index, level, 'labelStyle');

  if (labelStyle) {
    var cssStyle = new DvtCSSStyle(labelStyle);
    if (!cssStyle.getStyle('font-size')) // DvtBackgroundOutputText needs font-size for adjusting select browser mis-alignment cases
      cssStyle.setStyle('font-size', this.Options['tickLabel']['style'].getStyle('font-size'));
    return cssStyle;
  }
  return null;
};


/**
 * @override
 */
DvtGroupAxisInfo.prototype.getTooltip = function(label, index, level) {
  // categorical label tooltip is the given shortDesc if it exists, else it is the untruncated text string
  var shortDesc = this._getGroupAttribute(index, level, 'shortDesc');
  return shortDesc ? shortDesc : label.getUntruncatedTextString();
};

/**
 * @override
 */
DvtGroupAxisInfo.prototype.getAction = function(index, level) {
  return this._getGroupAttribute(index, level, 'action');
};

/**
 * @override
 */
DvtGroupAxisInfo.prototype.isDrillable = function(index, level) {
  var drilling = this._getGroupAttribute(index, level, 'drilling');

  if (drilling == 'on')
    return true;
  else if (drilling == 'off')
    return false;
  else
    return this._drilling == 'on' || this._drilling == 'groupsOnly';
};

/**
 * Returns a string or an array of groups names/ids of the ancestors of a group label at the given index and level.
 * @param {Number} index The index of the group label within it's level of labels
 * @param {Number=} level The level of the group labels
 * @return {String|Array} The group name/id, or an array of group names/ids.
 * @override
 */
DvtGroupAxisInfo.prototype.getGroup = function(index, level) {
  if (index < 0 || index > this.getGroupCount() - 1)
    return null;

  var groupLabels = [];
  if (level == null)
    level = this._numLevels - 1;
  var startIndex = this.getStartIndex(index, level);
  for (var levelIndex = 0; levelIndex <= level; levelIndex++) {
    var levelArray = this._levelsArray[levelIndex];
    for (var i = 0; i < levelArray.length; i++) {
      if (this.getStartIndex(i, levelIndex) <= startIndex && this.getEndIndex(i, levelIndex) >= startIndex) {
        groupLabels.push(this.getGroupAt(i, levelIndex));
        continue;
      }
    }
  }
  if (groupLabels.length > 0)
    return groupLabels.length == 1 ? groupLabels[0] : groupLabels;
  return null;
};

/**
 * @override
 */
DvtGroupAxisInfo.prototype.getLabelBackground = function(label, context, level) {
  if (level == null)
    level = this._numLevels - 1;
  var style = label.getCSSStyle();
  if (style) {
    var bgColor = style.getStyle(DvtCSSStyle.BACKGROUND_COLOR);
    var borderColor = style.getStyle(DvtCSSStyle.BORDER_COLOR);
    var borderWidth = style.getStyle(DvtCSSStyle.BORDER_WIDTH);
    var borderRadius = style.getStyle(DvtCSSStyle.BORDER_RADIUS);

    // Create element for label background if group labelStyle has the background-related attributes that we support
    if (bgColor != null || borderColor != null || borderWidth != null || borderRadius != null) {
      var bboxDims = label.getDimensions();
      var padding = bboxDims.h * 0.15;

      // Chrome & IE handle 'vAlign = bottom' in a way that label and the background are misaligned, this corrects the DvtRect
      if ((DvtAgent.isBrowserChrome() || DvtAgent.isPlatformIE()) && label.getVertAlignment() === DvtOutputText.V_ALIGN_BOTTOM)
        bboxDims.y += bboxDims.h / 2;

      var bbox = new DvtRect(context, bboxDims.x - padding, bboxDims.y, bboxDims.w + 2 * padding, bboxDims.h);

      var bgStyle = new DvtCSSStyle();
      if (bgColor != null)
        bgStyle.setStyle(DvtCSSStyle.BACKGROUND_COLOR, bgColor);
      else
        bbox.setInvisibleFill();
      bgStyle.setStyle(DvtCSSStyle.BORDER_COLOR, borderColor);
      bgStyle.setStyle(DvtCSSStyle.BORDER_WIDTH, borderWidth);
      bgStyle.setStyle(DvtCSSStyle.BORDER_RADIUS, borderRadius);
      bbox.setCSSStyle(bgStyle);

      if (this._isLabelRotated[level])
        bbox.setMatrix(label.getMatrix());
      bbox.setMouseEnabled(false);
      return bbox;
    }
    return null;
  }
  else
    return null;
};

/**
 * Returns the index for the specified group.
 * @param {string} group The group.
 * @return {number} The group index. -1 if the group doesn't exist.
 */
DvtGroupAxisInfo.prototype.getGroupIndex = function(group) {
  if (group == null)
    return -1;

  var index = -1;
  for (var i = 0; i < this._groupCount; i++) {
    var curGroup = this.getGroup(i);
    var matches = (group instanceof Array && curGroup instanceof Array) ? DvtArrayUtils.equals(group, curGroup) : group == curGroup;
    if (matches) {
      index = i;
      break;
    }
  }
  return index;
};


/**
 * @override
 */
DvtGroupAxisInfo.prototype.getMinimumExtent = function() {
  return 1;
};


/**
 * @override
 */
DvtGroupAxisInfo.prototype.getGroupWidth = function() {
  // returns the average group width
  return Math.abs(this.EndCoord - this.StartCoord) / Math.abs(this.MaxValue - this.MinValue);
};

/**
 * Returns the number of groups in the specified chart.
 * @return {number}
 */
DvtGroupAxisInfo.prototype.getGroupCount = function() {
  return this._groupCount;
};


/**
 * Returns the number of label levels
 * @return {number}
 */
DvtGroupAxisInfo.prototype.getNumLevels = function() {
  return this._numLevels;
};


/**
 * Conducts a DFS on a hierarchical group object to update the levelsArray
 * @param {object} groupsArray An array of chart groups
 * @param {number} level The level in the hierarchy
 * @param {object} levelsArray A structure of hierarchical group labels by level
 * @param {number} groupIndex The index of the current group
 * @return {groupIndex} A running count of the number of leaf groups
 * @private
 */
DvtGroupAxisInfo.prototype._generateLevelsArray = function(groupsArray, level, levelsArray, groupIndex) {
  for (var i = 0; i < groupsArray.length; i++) {

    // Add new array if at first group in a new level
    if (!levelsArray[level])
      levelsArray[level] = [];

    // Store object for group
    levelsArray[level].push({'item': groupsArray[i], 'start': groupIndex, 'end': groupIndex, 'position': i});

    if (groupsArray[i] && groupsArray[i]['groups']) {
      var lastIndex = levelsArray[level].length - 1;
      var numChildren = this._generateLevelsArray(groupsArray[i]['groups'], level + 1, levelsArray, levelsArray[level][lastIndex]['start']);
      levelsArray[level][lastIndex]['end'] = numChildren - 1; // start and end index used for centering group labels
      groupIndex = numChildren;
    }
    else
      groupIndex++;
  }
  return groupIndex;
};


/**
 * Returns the value for the given attribute for the group item specified by index and level
 * @param {number} index
 * @param {number} level
 * @param {string} attribute The desired atribute
 * @return {string} The value of the desires attribute
 * @private
 */
DvtGroupAxisInfo.prototype._getGroupAttribute = function(index, level, attribute) {
  if (level == null)
    level = this._numLevels - 1;
  var groupItem = (this._levelsArray[level] && this._levelsArray[level][index]) ? this._levelsArray[level][index]['item'] : null;
  return groupItem ? groupItem[attribute] : null;
};

/**
 * Returns whether or not to render group separators
 * @return {boolean}
 */
DvtGroupAxisInfo.prototype.areSeparatorsRendered = function() {
  return this._areSeparatorsRendered;
};

/**
 * Returns the color of the group separators
 * @return {boolean}
 */
DvtGroupAxisInfo.prototype.getSeparatorColor = function() {
  return this._separatorColor;
};

/**
 * Returns the start index for the group item specified by index and level
 * @param {number} index
 * @param {number} level
 * @return {number} The start index
 */
DvtGroupAxisInfo.prototype.getStartIndex = function(index, level) {
  if (level == null)
    level = this._numLevels - 1;
  var startIndex = (this._levelsArray[level] && this._levelsArray[level][index]) ? this._levelsArray[level][index]['start'] : null;
  return startIndex;
};

/**
 * Returns the end index for the group item specified by index and level
 * @param {number} index
 * @param {number} level
 * @return {number} The end index
 */
DvtGroupAxisInfo.prototype.getEndIndex = function(index, level) {
  if (level == null)
    level = this._numLevels - 1;
  var endIndex = (this._levelsArray[level] && this._levelsArray[level][index]) ? this._levelsArray[level][index]['end'] : null;
  return endIndex;
};

/**
 * Returns the position for the group item specified by index and level, in reference to it's parent
 * @param {number} index
 * @param {number} level
 * @return {number} The position of the group item in it's parent's array of children
 */
DvtGroupAxisInfo.prototype.getPosition = function(index, level) {
  if (level == null)
    level = this._numLevels - 1;
  var endIndex = (this._levelsArray[level] && this._levelsArray[level][index]) ? this._levelsArray[level][index]['position'] : null;
  return endIndex;
};

/**
 * Returns whether or not the axis is shifted
 * @return {boolean}
 */
DvtGroupAxisInfo.prototype.isRenderGridAtLabels = function() {
  return this._renderGridAtLabels;
};

/**
 * Store the index of the innermost level that was able to be rendered
 * @param {number} level The innermost level rendered
 */
DvtGroupAxisInfo.prototype.setLastRenderedLevel = function(level) {
  this._lastRenderedLevel = level;
};


/**
 * Returns the index of the innermost level that was able to be rendered
 * @return {number} The innermost level rendered
 */
DvtGroupAxisInfo.prototype.getLastRenderedLevel = function() {
  return this._lastRenderedLevel;
};
/**
 * Simple logical object for tooltip support.
 * @param {DvtOutputText} label The owning text instance.
 * @param {string|Array} group A string or an array of groups names/ids of the label and the ancestors.
 * @param {object} action The action associated with the label.
 * @param {object} drillable Whether the label is drillable.
 * @param {string} datatip The datatip to display.
 * @param {object=} params Optional object containing additional parameters for use by component.
 * @class DvtAxisObjPeer
 * @constructor
 * @implements {DvtSimpleObjPeer}
 */
var DvtAxisObjPeer = function(label, group, action, drillable, datatip, params) {
  this.Init(label, group, action, drillable, datatip, params);
};

DvtObj.createSubclass(DvtAxisObjPeer, DvtSimpleObjPeer, 'DvtAxisObjPeer');


/**
 * @param {DvtOutputText} label The owning text instance.
 * @param {string|Array} group A string or an array of groups names/ids of the label and the ancestors.
 * @param {object} action The action associated with the label.
 * @param {object} drillable Whether the label is drillable.
 * @param {string} datatip The datatip to display.
 * @param {object=} params Optional object containing additional parameters for use by component.
 */
DvtAxisObjPeer.prototype.Init = function(label, group, action, drillable, datatip, params) {
  DvtAxisObjPeer.superclass.Init.call(this, null, datatip, null, params);

  this._label = label;
  this._group = group;
  this._action = action;
  this._drillable = drillable;

  // Apply the cursor for the action if specified
  if (this._action || this._drillable)
    label.setCursor(DvtSelectionEffectUtils.getSelectingCursor());
};


/**
 * Returns the label for this object.
 * @return {DvtOutputText}
 */
DvtAxisObjPeer.prototype.getLabel = function() {
  return this._label;
};


/**
 * Returns the id for this object.
 * @return {object} The id for this label.
 */
DvtAxisObjPeer.prototype.getId = function() {
  return this._group;
};


/**
 * Return the action string for the label, if any exists.
 * @return {string} the action outcome for the label.
 */
DvtAxisObjPeer.prototype.getAction = function() {
  return this._action;
};


/**
 * Returns whether the label is drillable.
 * @return {boolean}
 */
DvtAxisObjPeer.prototype.isDrillable = function() {
  return this._drillable;
};


/**
 * Returns the group.
 * @return {string|Array}
 */
DvtAxisObjPeer.prototype.getGroup = function() {
  return this._group;
};
/**
 * Formatter for an axis with a linear scale.
 * Following cases can occur:
 * 1. scaling is set to none:
 *    No scaling is used in this case.
 * 2. scaling is set to auto, null or undefined:
 *    Scaling is computed. The nearest (less or equal) known scale is used. Regarding fraction part, if autoPrecision equals "on" then the count of significant decimal places
 *    is based on tickStep otherwise fraction part is not formatted.
 * 3. otherwise
 *    Defined scaling is used.
 *    Examples (autoPrecision = "on"):
 *    minValue = 0, maxValue=10000, tickStep=1000, scale="thousand" -> formatted axis values: 0K , ..., 10K
 *    minValue = 0, maxValue=100, tickStep=10, scale="thousand" -> formatted axis values: 0.00K, 0.01K, ..., 0.10K
 *
 * @param {DvtContext} context
 * @param {number} minValue the minimum value on the axis
 * @param {number} maxValue the maximum value on the axis
 * @param {number} tickStep the tick step between values on the axis
 * @param {string} scale the scale of values on the axis; if null or undefined then auto scaling is used.
 * @param {string} autoPrecision "on" if auto precision should be applied otherwise "off"; if null or undefined then auto precision is applied.
 * @constructor
 */
var DvtLinearScaleAxisValueFormatter = function(context, minValue, maxValue, tickStep, scale, autoPrecision) {
  DvtAbstractAxisValueFormatter.call(this);
  this.Init(context, minValue, maxValue, tickStep, scale, autoPrecision);
};

DvtObj.createSubclass(DvtLinearScaleAxisValueFormatter, DvtAbstractAxisValueFormatter, 'DvtLinearScaleAxisValueFormatter');


/**
 * Allowed scales that can be used as formatter scale param values
 */
DvtLinearScaleAxisValueFormatter.SCALE_NONE = 'none';
DvtLinearScaleAxisValueFormatter.SCALE_AUTO = 'auto';
DvtLinearScaleAxisValueFormatter.SCALE_THOUSAND = 'thousand';
DvtLinearScaleAxisValueFormatter.SCALE_MILLION = 'million';
DvtLinearScaleAxisValueFormatter.SCALE_BILLION = 'billion';
DvtLinearScaleAxisValueFormatter.SCALE_TRILLION = 'trillion';
DvtLinearScaleAxisValueFormatter.SCALE_QUADRILLION = 'quadrillion';


/**
 * The scaling factor difference between successive scale values
 */
DvtLinearScaleAxisValueFormatter.SCALING_FACTOR_DIFFERENCE = 3;


/**
 * Initializes the instance.
 * @param {object} context
 * @param {number} minValue
 * @param {number} maxValue
 * @param {number} tickStep
 * @param {number} scale
 * @param {number} autoPrecision
 */
DvtLinearScaleAxisValueFormatter.prototype.Init = function(context, minValue, maxValue, tickStep, scale, autoPrecision) {
  this._context = context;
  // array of successive scale values
  this._scales = {
  };
  // array of scale values ordered by scale factor asc
  this._scalesOrder = [];
  // mapping of scale factors to corresponding scale objects
  this._factorToScaleMapping = {
  };

  this.InitScales();
  this.InitFormatter(minValue, maxValue, tickStep, scale, autoPrecision);
};


/**
 * Initializes scale objects.
 * @protected
 *
 */
DvtLinearScaleAxisValueFormatter.prototype.InitScales = function() {
  /**
   * Creates scale object and refreshes formatter properties using it.
   * @param {string} scaleName one of allowed scale names (e.g. DvtLinearScaleAxisValueFormatter.SCALE_THOUSAND)
   * @param {number} scaleFactor scale factor of corresponding scale, i.e. 'x' such that 10^x represents corresponding scale (e.g. for scale DvtLinearScaleAxisValueFormatter.SCALE_THOUSAND x = 3)
   * @param {string} scaleKey translation key which value (translated) represents given scale (e.g. for DvtLinearScaleAxisValueFormatter.SCALE_THOUSAND an translated english suffix is 'K')
   */
  var createScale = function(scaleName, scaleFactor, scaleKey) {
    var suffix;
    if (scaleKey) {
      // when bundle and bundle suffix is defined then init suffix
      suffix = DvtBundle.getTranslatedString(DvtBundle.UTIL_PREFIX, scaleKey);
    }

    var scale = {
      scaleFactor: scaleFactor, localizedSuffix: suffix
    };

    // update private properties
    this._scales[scaleName] = scale;
    this._scalesOrder.push(scale);
    this._factorToScaleMapping[scaleFactor] = scale;
  };

  var diff = DvtLinearScaleAxisValueFormatter.SCALING_FACTOR_DIFFERENCE;

  createScale.call(this, DvtLinearScaleAxisValueFormatter.SCALE_NONE, 0 * diff);
  createScale.call(this, DvtLinearScaleAxisValueFormatter.SCALE_THOUSAND, 1 * diff, 'SCALING_SUFFIX_THOUSAND');
  createScale.call(this, DvtLinearScaleAxisValueFormatter.SCALE_MILLION, 2 * diff, 'SCALING_SUFFIX_MILLION');
  createScale.call(this, DvtLinearScaleAxisValueFormatter.SCALE_BILLION, 3 * diff, 'SCALING_SUFFIX_BILLION');
  createScale.call(this, DvtLinearScaleAxisValueFormatter.SCALE_TRILLION, 4 * diff, 'SCALING_SUFFIX_TRILLION');
  createScale.call(this, DvtLinearScaleAxisValueFormatter.SCALE_QUADRILLION, 5 * diff, 'SCALING_SUFFIX_QUADRILLION');

  // sort _scalesOrder array
  this._scalesOrder.sort(function(scale1, scale2) {
    if (scale1.scaleFactor < scale2.scaleFactor) {
      return - 1;
    }
    else if (scale1.scaleFactor > scale2.scaleFactor) {
      return 1;
    }
    else {
      return 0;
    }
  });
};


/**
 * Initializes properties used for values formatting (e.g. scale factor that should be applied etc.).
 *
 * @param {number} minValue the minimum value on the axis
 * @param {number} maxValue the maximum value on the axis
 * @param {number} tickStep the tick step between values on the axis
 * @param {string} scale the scale of values on the axis
 * @param {boolean} autoPrecision true if auto precision should be applied otherwise false
 * @protected
 *
 */
DvtLinearScaleAxisValueFormatter.prototype.InitFormatter = function(minValue, maxValue, tickStep, scale, autoPrecision) {
  var findScale = false, decimalPlaces, scaleFactor, useAutoPrecision = false;

  // if autoPrecision doesn't equal "off" (i.e. is "on", null, undefined) then auto precision should be used.
  if (!(autoPrecision === 'off')) {
    useAutoPrecision = true;
  }
  // try to use scale given by "scale" param and if no scale factor is found find appropriate scale
  scaleFactor = this._getScaleFactor(scale);
  if ((typeof scaleFactor) !== 'number') {
    findScale = true;
  }

  // base a default scale factor calculation on the order of
  // magnitude (power of ten) of the maximum absolute value on the axis
  if (findScale) {
    // get the axis endpoint with the largest absolute value,
    // and find its base 10 exponent
    var absMax = Math.max(Math.abs(minValue), Math.abs(maxValue));

    var power = this._getPowerOfTen(absMax);
    scaleFactor = this._findNearestLEScaleFactor(power);
  }

  if (useAutoPrecision === true) {
    if (tickStep == 0 && minValue == maxValue) {
      // TODO: HZHANG Remove this hack for chart tooltips, which currently passes 0 as the tick step in all cases.
      // Workaround for now will be to add decimal places to show at least 1 and at most 4 significant digits
      var valuePowerOfTen = this._getPowerOfTen(maxValue);
      var scaleFactorDiff = scaleFactor - valuePowerOfTen;
      if (scaleFactorDiff <= 0) // Value is same or larger than the scale factor, ensure 4 significant digits.
        // Make sure that the number of decimal places is at least zero. Bug 18677330
        decimalPlaces = Math.max(scaleFactorDiff + 3, 0);
      else // Value is smaller, ensure enough decimals to show 1 significant digit
        decimalPlaces = Math.max(scaleFactorDiff, 4);
    }
    else {
      // get the number of decimal places in the number by subtracting
      // the order of magnitude of the tick step from the order of magnitude
      // of the scale factor
      // (e.g.: scale to K, tick step of 50 -> 3 - 1 = 2 decimal places)
      var tickStepPowerOfTen = this._getPowerOfTen(tickStep);
      decimalPlaces = Math.max(scaleFactor - tickStepPowerOfTen, 0);
    }
  }

  // init private properties with computed values
  this._useAutoPrecision = useAutoPrecision;
  this._scaleFactor = scaleFactor;
  this._decimalPlaces = decimalPlaces;
};


/**
 * Finds a scale factor 'x' such that x <= value (e.g. if value equals 4 then returned scale factor equals 3)
 * @param {number} value value representing an order of magnitude
 * @return {number} a scale factor 'x' such that x <= value
 * @private
 */
DvtLinearScaleAxisValueFormatter.prototype._findNearestLEScaleFactor = function(value) {
  var scaleFactor = 0;

  if (value <= this._scalesOrder[0].scaleFactor) {
    // if the number is less than 10, don't scale
    scaleFactor = this._scalesOrder[0].scaleFactor;
  }
  else if (value >= this._scalesOrder[this._scalesOrder.length - 1].scaleFactor) {
    // if the data is greater than or equal to 10 quadrillion, scale to quadrillions
    scaleFactor = this._scalesOrder[this._scalesOrder.length - 1].scaleFactor;
  }
  else {
    // else find the nearest scaleFactor such that scaleFactor <= value
    var end = this._scalesOrder.length - 1;
    for (var i = end; i >= 0; i--) {
      if (this._scalesOrder[i].scaleFactor <= value) {
        scaleFactor = this._scalesOrder[i].scaleFactor;
        break;
      }
    }
  }
  return scaleFactor;
};


/**
 * Returns scale factor of scale given by scale name.
 * @return scale factor of scale given by scale name
 * @private
 */
DvtLinearScaleAxisValueFormatter.prototype._getScaleFactor = function(scaleName) {
  // If no scaling factor defined, use auto by default.
  if (!scaleName)
    scaleName = DvtLinearScaleAxisValueFormatter.SCALE_AUTO;

  var scaleFactor, scale = this._scales[scaleName];
  if (scale) {
    scaleFactor = scale.scaleFactor;
  }
  return scaleFactor;
};


/**
 * Formats given value using previously computed scale factor and decimal digits count. In case that parsed value equals NaN an unformatted value is returned.
 * @override
 * @param {object} value to be formatted.
 * @return {string} formatted value as string
 */
DvtLinearScaleAxisValueFormatter.prototype.format = function(value, converter) {
  var parsed = parseFloat(value);
  if (!isNaN(parsed)) {
    // Find the suffix for the scale factor
    var suffix;
    if (this._scaleFactor > 0) {
      for (var i = 0; i < this._scaleFactor; i++) {
        parsed /= 10;
      }
      suffix = this._factorToScaleMapping[this._scaleFactor].localizedSuffix;
    }

    // Convert the number itself
    if (converter && converter['getAsString']) {
      parsed = converter['getAsString'](parsed);
    }
    else if (converter && converter['format'])
      parsed = converter['format'](parsed);
    else {
      var defaultConverter = this._context.getNumberConverter({'minimumFractionDigits': this._decimalPlaces, 'maximumFractionDigits': this._decimalPlaces});
      if (defaultConverter && defaultConverter['format'])
        parsed = defaultConverter['format'](parsed);
      else if (this._useAutoPrecision && !isNaN(parseFloat(parsed))) {
        parsed = parseFloat(new Number(parsed).toFixed(this._decimalPlaces));
        parsed = this._formatFraction(parsed);
      }
    }

    // Add the scale factor suffix
    if (typeof suffix === 'string') {
      parsed += suffix;
    }
    return parsed;
  }
  else {
    return value;
  }
};


/**
 * Formats fraction part of given value (adds zeroes if needed).
 * @param {number} value to be formatted
 * @return {string} number with fraction part formatted as string
 * @private
 */
DvtLinearScaleAxisValueFormatter.prototype._formatFraction = function(value) {
  var formatted = value.toString();
  var decimalSep = '.';
  decimalSep = DvtBundle.getTranslatedString(DvtBundle.UTIL_PREFIX, 'NUMBER_FORMAT_DECIMAL_SEPARATOR');
  if (this._decimalPlaces > 0) {
    if (formatted.indexOf('.') == - 1) {
      formatted += decimalSep;
    } else {
      formatted = formatted.replace('.', decimalSep);
    }

    var existingPlacesCount = formatted.substring(formatted.indexOf(decimalSep) + 1).length;

    while (existingPlacesCount < this._decimalPlaces) {
      formatted += '0';
      existingPlacesCount++;
    }
  }
  return formatted;
};


/**
 * Fro given value it returns its order of magnitude.
 * @param {number} value for which order of magnitude should be found
 * @return {number} order of magnitude for given value
 * @private
 */
DvtLinearScaleAxisValueFormatter.prototype._getPowerOfTen = function(value) {
  // more comprehensive and easier than working with value returned by Math.log(value)/Math.log(10)
  value = (value >= 0) ? value : - value;
  var power = 0;

  // Check for degenerate and zero values
  if (value < 1E-15) {
    return 0;
  }
  else if (value == Infinity) {
    return Number.MAX_VALUE;
  }

  if (value >= 10) {
    // e.g. for 1000 the power should be 3
    while (value >= 10) {
      power += 1;
      value /= 10;
    }
  }
  else if (value < 1) {
    while (value < 1) {
      power -= 1;
      value *= 10;
    }
  }
  return power;
};
/**
 * Calculated axis information and drawable creation for a time axis.
 * @param {DvtContext} context
 * @param {object} options The object containing specifications and data for this component.
 * @param {DvtRectangle} availSpace The available space.
 * @class
 * @constructor
 * @extends {DvtAxisInfo}
 */
var DvtTimeAxisInfo = function(context, options, availSpace) {
  this.Init(context, options, availSpace);
};

DvtObj.createSubclass(DvtTimeAxisInfo, DvtAxisInfo, 'DvtTimeAxisInfo');

// ------------------------
// Constants
//
/** @const */
DvtTimeAxisInfo.TIME_SECOND = 1000;
/** @const */
DvtTimeAxisInfo.TIME_MINUTE = 60 * DvtTimeAxisInfo.TIME_SECOND;
/** @const */
DvtTimeAxisInfo.TIME_HOUR = 60 * DvtTimeAxisInfo.TIME_MINUTE;
/** @const */
DvtTimeAxisInfo.TIME_DAY = 24 * DvtTimeAxisInfo.TIME_HOUR;
/** @const */
DvtTimeAxisInfo.TIME_MONTH_MIN = 28 * DvtTimeAxisInfo.TIME_DAY;
/** @const */
DvtTimeAxisInfo.TIME_MONTH_MAX = 31 * DvtTimeAxisInfo.TIME_DAY;
/** @const */
DvtTimeAxisInfo.TIME_YEAR_MIN = 365 * DvtTimeAxisInfo.TIME_DAY;
/** @const */
DvtTimeAxisInfo.TIME_YEAR_MAX = 366 * DvtTimeAxisInfo.TIME_DAY;

/**
 * @override
 */
DvtTimeAxisInfo.prototype.Init = function(context, options, availSpace) {
  DvtTimeAxisInfo.superclass.Init.call(this, context, options, availSpace);

  // Figure out the coords for the min/max values
  if (this.Position == 'top' || this.Position == 'bottom') {
    // Provide at least the minimum buffer at each side to accommodate labels
    if (!options['_isOverview'] && options['tickLabel']['rendered'] == 'on') {
      this.StartOverflow = Math.max(DvtAxis.MINIMUM_AXIS_BUFFER - options['leftBuffer'], 0);
      this.EndOverflow = Math.max(DvtAxis.MINIMUM_AXIS_BUFFER - options['rightBuffer'], 0);
    }

    // Axis is horizontal, so flip for BIDI if needed
    if (DvtAgent.isRightToLeft(context)) {
      this._startCoord = this.EndCoord - this.EndOverflow;
      this._endCoord = this.StartCoord + this.StartOverflow;
    }
    else {
      this._startCoord = this.StartCoord + this.StartOverflow;
      this._endCoord = this.EndCoord - this.EndOverflow;
    }
  }
  else {
    // Vertical axis should go from top to bottom
    this._startCoord = this.StartCoord;
    this._endCoord = this.EndCoord;
  }

  this._minCoord = Math.min(this._startCoord, this._endCoord);
  this._maxCoord = Math.max(this._startCoord, this._endCoord);

  var converter = options['tickLabel'] != null ? options['tickLabel']['converter'] : null;
  this._label1Converter = (converter && converter[0]) ? converter[0] : converter;
  this._label2Converter = (converter && converter[1]) ? converter[1] : null;
  this._dateToIsoConverter = context.getLocaleHelpers()['dateToIsoConverter'];

  this._groups = options['groups'];
  this._timeAxisType = options['timeAxisType'];

  this.DataMin = options['dataMin'];
  this.DataMax = options['dataMax'];

  if (this._groups.length > 1)
    this._averageInterval = (this.DataMax - this.DataMin) / (this._groups.length - 1);
  else if (this.DataMax - this.DataMin > 0)
    this._averageInterval = this.DataMax - this.DataMin;
  else
    this._averageInterval = 6 * DvtTimeAxisInfo.TIME_MINUTE; // to get the time axis to show YMDHM information
  this._step = options['step'];

  // Calculate the increment and add offsets if specified
  var endOffset = options['endGroupOffset'] > 0 ? options['endGroupOffset'] * this._averageInterval : 0;
  var startOffset = options['startGroupOffset'] > 0 ? options['startGroupOffset'] * this._averageInterval : 0;

  this.GlobalMin = options['min'] != null ? options['min'] : this.DataMin - startOffset;
  this.GlobalMax = options['max'] != null ? options['max'] : this.DataMax + endOffset;

  // Set min/max by start/endGroup
  if (options['viewportStartGroup'] != null)
    this.MinValue = options['viewportStartGroup'] - startOffset;
  if (options['viewportEndGroup'] != null)
    this.MaxValue = options['viewportEndGroup'] + endOffset;

  // Set min/max by viewport min/max
  if (options['viewportMin'] != null)
    this.MinValue = options['viewportMin'];
  if (options['viewportMax'] != null)
    this.MaxValue = options['viewportMax'];

  // If min/max is still undefined, fall back to global min/max
  if (this.MinValue == null)
    this.MinValue = this.GlobalMin;
  if (this.MaxValue == null)
    this.MaxValue = this.GlobalMax;

  if (this.GlobalMin > this.MinValue)
    this.GlobalMin = this.MinValue;
  if (this.GlobalMax < this.MaxValue)
    this.GlobalMax = this.MaxValue;

  this._timeRange = this.MaxValue - this.MinValue;

  this._level1Labels = null;
  this._level2Labels = null;
  // Coordinates of labels need to be stored for gridline rendering
  this._level1Coords = null;
  this._level2Coords = null;
  this._isOneLevel = true;

  this._locale = options['_locale'].toLowerCase();

  this._monthResources = [
    DvtBundle.getTranslatedString(DvtBundle.UTIL_PREFIX, 'MONTH_SHORT_JANUARY'),
    DvtBundle.getTranslatedString(DvtBundle.UTIL_PREFIX, 'MONTH_SHORT_FEBRUARY'),
    DvtBundle.getTranslatedString(DvtBundle.UTIL_PREFIX, 'MONTH_SHORT_MARCH'),
    DvtBundle.getTranslatedString(DvtBundle.UTIL_PREFIX, 'MONTH_SHORT_APRIL'),
    DvtBundle.getTranslatedString(DvtBundle.UTIL_PREFIX, 'MONTH_SHORT_MAY'),
    DvtBundle.getTranslatedString(DvtBundle.UTIL_PREFIX, 'MONTH_SHORT_JUNE'),
    DvtBundle.getTranslatedString(DvtBundle.UTIL_PREFIX, 'MONTH_SHORT_JULY'),
    DvtBundle.getTranslatedString(DvtBundle.UTIL_PREFIX, 'MONTH_SHORT_AUGUST'),
    DvtBundle.getTranslatedString(DvtBundle.UTIL_PREFIX, 'MONTH_SHORT_SEPTEMBER'),
    DvtBundle.getTranslatedString(DvtBundle.UTIL_PREFIX, 'MONTH_SHORT_OCTOBER'),
    DvtBundle.getTranslatedString(DvtBundle.UTIL_PREFIX, 'MONTH_SHORT_NOVEMBER'),
    DvtBundle.getTranslatedString(DvtBundle.UTIL_PREFIX, 'MONTH_SHORT_DECEMBER')
  ];
};

/**
 * Returns the am string for this locale if applicable.
 * @param {String} locale the locale for the axis.
 * @return {String} the string representing "am"
 * @private
 */
DvtTimeAxisInfo._getAMString = function(locale) {
  var language = locale.substring(0, 2);
  if (locale == 'en-au' || locale == 'en-ie' || locale == 'en-ph')
    return 'am';
  else if (locale == 'en-gb')
    return '';
  switch (language) {
    case 'en':
      return 'AM';
    case 'ar':
      return '\u0635';
    case 'el':
      return '\u03c0\u03bc';
    case 'ko':
      return '\uc624\uc804';
    case 'zh':
      return '\u4e0a\u5348';
    default:
      return '';
  }
};

/**
 * Returns the pm string for this locale if applicable.
 * @param {String} locale the locale for the axis.
 * @return {String} the string representing "pm"
 * @private
 */
DvtTimeAxisInfo._getPMString = function(locale) {
  var language = locale.substring(0, 2);
  if (locale == 'en-au' || locale == 'en-ie' || locale == 'en-ph')
    return 'pm';
  else if (locale == 'en-gb')
    return '';
  switch (language) {
    case 'en':
      return 'PM';
    case 'ar':
      return '\u0645';
    case 'el':
      return '\u03bc\u03bc';
    case 'ko':
      return '\uc624\ud6c4';
    case 'zh':
      return '\u4e0b\u5348';
    default:
      return '';
  }
};

/**
 * Returns whether the AM/PM string should be displayed before or after the time string based on locale.
 * @param {String} locale the locale for the axis
 * @return {boolean} whether the AM/PM string before the time.
 * @private
 */
DvtTimeAxisInfo._getAMPMBefore = function(locale) {
  var language = locale.substring(0, 2);
  if (language == 'ko' || language == 'zh')
    return true;
  else
    return false;
};

/**
 * Returns the DMY order based on the locale
 * @param {String} locale the locale for the axis
 * @return {String} the order of date, month and year
 * @private
 */
DvtTimeAxisInfo._getDMYOrder = function(locale) {
  var language = locale.substring(0, 2);
  if (locale == 'en-us' || locale == 'en-ph')
    return 'MDY';
  else if (language == 'fa' || language == 'hu' || language == 'ja' || language == 'ko' || language == 'lt' || language == 'mn' || language == 'zh')
    return 'YMD';
  else
    return 'DMY';
};

/**
 * Returns the trailing characters for the year
 * @param {String} locale the locale for the axis
 * @return {String} the year trailing character by locale
 * @private
 */
DvtTimeAxisInfo._getYearTrailingCharacters = function(locale) {
  if (locale.indexOf('ja') == 0 || locale.indexOf('zh') == 0)
    return '\u5e74';
  else if (locale.indexOf('ko') == 0)
    return'\ub144';
  else
    return '';
};


/**
 * Returns the trailing characters for the day
 * @param {String} locale the locale for the axis
 * @return {String} the day trailing character by locale
 * @private
 */
DvtTimeAxisInfo._getDayTrailingCharacters = function(locale) {
  if (locale.indexOf('ja') == 0 || locale.indexOf('zh') == 0)
    return '\u65e5';
  else if (locale.indexOf('ko') == 0)
    return'\uc77c';
  else
    return '';
};

/**
 * Formats the label given an axis value (used for generating tooltips).
 * @param {Number} axisValue The axis value (in milliseconds)
 * @return {String} A formatted axis label
 */
DvtTimeAxisInfo.prototype.formatLabel = function(axisValue) {
  var date = new Date(axisValue);
  var twoLabels = this._formatAxisLabel(date, null, true);
  if (twoLabels[1] != null) {
    if (DvtTimeAxisInfo._getDMYOrder(this._locale) == 'YMD' || (this._timeRange < DvtTimeAxisInfo.TIME_MONTH_MIN && this._step < DvtTimeAxisInfo.TIME_DAY)) // time showing HH:MM:SS or YMD order
      return twoLabels[1] + ' ' + twoLabels[0];
    else
      return twoLabels[0] + ' ' + twoLabels[1];
  }
  else
    return twoLabels[0];
};

/**
 * Formats the given date with the given converter
 * @param {Date} date The current date
 * @param {Date} prevDate The date of the previous set of labels
 * @param {Object} converter The converter
 * @return {String} An axis label
 * @private
 */
DvtTimeAxisInfo.prototype._formatAxisLabelWithConverter = function(date, prevDate, converter) {
  if (converter) {
    var label = null;
    var prevLabel = null;
    if (converter['getAsString']) {
      label = converter['getAsString'](date);
      prevLabel = converter['getAsString'](prevDate);
    }
    else if (converter['format']) {
      label = converter['format'](this._dateToIsoConverter ? this._dateToIsoConverter(date) : date);
      prevLabel = converter['format'](this._dateToIsoConverter ? this._dateToIsoConverter(prevDate) : prevDate);
    }
    if (prevLabel != label)
      return label;
  }
  return null;
};


/**
 * Formats the level 1 and level 2 axis labels
 * @param {Date} date The current date
 * @param {Date} prevDate The date of the previous set of labels
 * @param {boolean} bOneLabel Whether we want to show only one label. Used for tooltip to get correct order for MDY
 * @return {Array} An array [level1Label, level2Label]
 * @private
 */
DvtTimeAxisInfo.prototype._formatAxisLabel = function(date, prevDate, bOneLabel) {
  var label1 = null;// level 1 label
  var label2 = null;// level 2 label
  var isVert = this.Position == 'left' || this.Position == 'right';

  // If dateTimeFormatter is used, use it
  if (this._label1Converter || this._label2Converter) {
    if (this._label1Converter)
      label1 = this._formatAxisLabelWithConverter(date, prevDate, this._label1Converter);
    if (this._label2Converter)
      label2 = this._formatAxisLabelWithConverter(date, prevDate, this._label2Converter);

    return [label1, label2];
  }

  if (this._step >= DvtTimeAxisInfo.TIME_YEAR_MIN || this._timeRange >= 6 * DvtTimeAxisInfo.TIME_YEAR_MIN) {
    label1 = this._formatDate(date, false, false, true);// Year
  }

  else if (this._step >= DvtTimeAxisInfo.TIME_MONTH_MIN || this._timeRange >= 6 * DvtTimeAxisInfo.TIME_MONTH_MIN) {
    if (prevDate == null || prevDate.getMonth() != date.getMonth())
      label1 = this._formatDate(date, false, true, false);// Month

    if (prevDate == null || prevDate.getYear() != date.getYear())
      label2 = this._formatDate(date, false, false, true);// Year
  }

  else if (this._step >= DvtTimeAxisInfo.TIME_DAY || this._timeRange >= 6 * DvtTimeAxisInfo.TIME_DAY) {
    if (bOneLabel) {
      label1 = this._formatDate(date, true, true, true);// Day, Month, Year
    }
    else {
      if (prevDate == null || prevDate.getDate() != date.getDate())
        label1 = this._formatDate(date, true, false, false);// Day

      if (prevDate == null || prevDate.getYear() != date.getYear())
        label2 = this._formatDate(date, false, true, true);// Year, Month
      else if (prevDate.getMonth() != date.getMonth())
        label2 = this._formatDate(date, false, true, false);// Month
    }
  }

  else {
    if (this._step >= DvtTimeAxisInfo.TIME_HOUR || this._timeRange >= 6 * DvtTimeAxisInfo.TIME_HOUR) {
      if (prevDate == null || (prevDate.getHours() != date.getHours()))
        label1 = this._formatTime(date, false, false);// HH AM/PM or HH:MM
    }
    else if (this._step >= DvtTimeAxisInfo.TIME_MINUTE || this._timeRange >= 6 * DvtTimeAxisInfo.TIME_MINUTE) {
      if (prevDate == null || (prevDate.getMinutes() != date.getMinutes()))
        label1 = this._formatTime(date, true, false);// HH:MM
    }
    else {
      if (prevDate == null || prevDate.getSeconds() != date.getSeconds())
        label1 = this._formatTime(date, true, true);// HH:MM:SS
    }

    if (isVert) {
      if (prevDate == null || prevDate.getDate() != date.getDate())
        label2 = this._formatDate(date, true, true, false);// Month, Day
    }
    else {
      if (prevDate == null || prevDate.getYear() != date.getYear())
        label2 = this._formatDate(date, true, true, true);// Year, Month, Day
      else if (prevDate.getMonth() != date.getMonth())
        label2 = this._formatDate(date, true, true, false);// Month, Day
      else if (prevDate.getDate() != date.getDate())
        label2 = this._formatDate(date, true, false, false);// Day
    }
  }


  return [label1, label2];
};


/**
 * Returns the date as a DMY string
 * @param {Date} date The date
 * @param {boolean} showDay Whether the day is shown
 * @param {boolean} showMonth Whether the month is shown
 * @param {boolean} showYear Whether the year is shown
 * @return {string} The formatted string
 * @private
 */
DvtTimeAxisInfo.prototype._formatDate = function(date, showDay, showMonth, showYear) {
  // Bug 20728667. Manually add 543 years to the Gregorian year if using a Thai locale.
  // Should use date.toLocaleDateString once it's available on Safari
  var yearStr = (this._locale.substring(0, 2) == 'th') ? date.getFullYear() + 543 : date.getFullYear();

  var monthStr;
  if (this._monthResources && this._monthResources.length >= 12)
    monthStr = this._monthResources[date.getMonth()];
  else
    monthStr = date.toString().split(' ')[1];// date.toString() returns "Day Mon Date HH:MM:SS TZD YYYY"
  var dayStr = date.getDate();

  // Add the day and year trailing characters if needed
  // These will be "" if not needed
  yearStr += DvtTimeAxisInfo._getYearTrailingCharacters(this._locale);
  dayStr += DvtTimeAxisInfo._getDayTrailingCharacters(this._locale);

  // Process the DMY Order
  var dmyOrder = DvtTimeAxisInfo._getDMYOrder(this._locale);

  var dateStr = '';

  for (var i = 0; i < dmyOrder.length; i++) {
    if (showDay && dmyOrder[i] == 'D') {
      dateStr += dayStr + ' ';
    }
    else if (showMonth && dmyOrder[i] == 'M') {
      dateStr += monthStr + ' ';
    }
    else if (showYear && dmyOrder[i] == 'Y') {
      dateStr += yearStr + ' ';
    }
  }

  return dateStr.length > 0 ? dateStr.slice(0, dateStr.length - 1) : dateStr;
};


/**
 * Returns the date as an HH:MM:SS string
 * @param {Date} date The date
 * @param {boolean} showMinute Whether the minute is shown
 * @param {boolean} showSecond Whether the second is shown
 * @return {string} The formatted string
 * @private
 */
DvtTimeAxisInfo.prototype._formatTime = function(date, showMinute, showSecond) {
  var hours = date.getHours();
  var mins = date.getMinutes();
  var secs = date.getSeconds();

  var am = DvtTimeAxisInfo._getAMString(this._locale);
  var pm = DvtTimeAxisInfo._getPMString(this._locale);
  var ampmBefore = DvtTimeAxisInfo._getAMPMBefore(this._locale);

  var b12HFormat = (am != '' && pm != '');
  var ampm;
  var timeLabel = '';

  if (DvtAgent.isRightToLeft(this.getCtx()))
    timeLabel = '\u200F';

  if (b12HFormat) {
    if (hours < 12) {
      ampm = am;
      if (hours == 0)
        hours = 12;
    }
    else {
      ampm = pm;
      if (hours > 12)
        hours -= 12;
    }
    timeLabel += hours;

    if (showMinute || mins != 0)
      timeLabel += ':' + this._doubleDigit(mins);
  }
  else
    timeLabel += this._doubleDigit(hours) + ':' + this._doubleDigit(mins);

  if (showSecond) {
    timeLabel += ':' + this._doubleDigit(secs);
  }

  if (b12HFormat) {
    if (ampmBefore)
      return ampm + ' ' + timeLabel;
    else
      return timeLabel + ' ' + ampm;
  }
  else {
    return timeLabel;
  }
};


/**
 * Creates a double-digit number string for the HH:MM:SS format
 * @param {Number} num A number less than 100
 * @return {String} A double-digit number string
 * @private
 */
DvtTimeAxisInfo.prototype._doubleDigit = function(num) {
  if (num < 10) {
    return '0' + num;
  }
  return '' + num;
};


/**
 * Returns the time label interval for mixed frequency data.
 * Makes sure that the interval is a regular time unit.
 * @return {number} The interval.
 * @private
 */
DvtTimeAxisInfo.prototype._getMixedFrequencyStep = function() {
  if (this._timeRange >= 6 * DvtTimeAxisInfo.TIME_YEAR_MIN)
    return DvtTimeAxisInfo.TIME_YEAR_MIN;
  if (this._timeRange >= 6 * DvtTimeAxisInfo.TIME_MONTH_MIN)
    return DvtTimeAxisInfo.TIME_MONTH_MIN;
  if (this._timeRange >= 6 * DvtTimeAxisInfo.TIME_DAY)
    return DvtTimeAxisInfo.TIME_DAY;
  if (this._timeRange >= DvtTimeAxisInfo.TIME_DAY)
    return 3 * DvtTimeAxisInfo.TIME_HOUR;
  if (this._timeRange >= 6 * DvtTimeAxisInfo.TIME_HOUR)
    return DvtTimeAxisInfo.TIME_HOUR;
  if (this._timeRange >= DvtTimeAxisInfo.TIME_HOUR)
    return 15 * DvtTimeAxisInfo.TIME_MINUTE;
  if (this._timeRange >= 30 * DvtTimeAxisInfo.TIME_MINUTE)
    return 5 * DvtTimeAxisInfo.TIME_MINUTE;
  if (this._timeRange >= 6 * DvtTimeAxisInfo.TIME_MINUTE)
    return DvtTimeAxisInfo.TIME_MINUTE;
  if (this._timeRange >= DvtTimeAxisInfo.TIME_MINUTE)
    return 15 * DvtTimeAxisInfo.TIME_SECOND;
  if (this._timeRange >= 30 * DvtTimeAxisInfo.TIME_SECOND)
    return 5 * DvtTimeAxisInfo.TIME_SECOND;
  return DvtTimeAxisInfo.TIME_SECOND;
};


/**
 * Returns the positions of time axis labels, given the start, end, and step
 * @param {number} start The start time of the axis.
 * @param {number} end The end time of the axis.
 * @param {number} step The increment between labels.
 * @return {array} A list of label positions.
 * @private
 */
DvtTimeAxisInfo._getLabelPositions = function(start, end, step) {
  // The time positions has to be at even intervals from the beginning of a year (January 1, 12:00:00 AM), otherwise
  // we may have labels such as [2013, 2014, 2015, ...] that are drawn at [June 8 2013, June 8 2014, June 8 2015, ...],
  // which is data misrepresentation.
  var anchor = new Date(start);
  anchor.setMonth(0, 1); // January 1
  anchor.setHours(0, 0, 0, 0); // 00:00:00
  var time = anchor.getTime();

  var times = [];
  if (step >= DvtTimeAxisInfo.TIME_YEAR_MIN && step <= DvtTimeAxisInfo.TIME_YEAR_MAX) {
    // Assume that the step is one year, which can mean different # of days depending on the year
    while (time < start)
      time = DvtTimeAxisInfo._addOneYear(time);
    while (time <= end) {
      times.push(time);
      time = DvtTimeAxisInfo._addOneYear(time);
    }
  }
  else if (step >= DvtTimeAxisInfo.TIME_MONTH_MIN && step <= DvtTimeAxisInfo.TIME_MONTH_MAX) {
    // Assume that the step is one month, which can mean different # of days depending on the month
    while (time < start)
      time = DvtTimeAxisInfo._addOneMonth(time);
    while (time <= end) {
      times.push(time);
      time = DvtTimeAxisInfo._addOneMonth(time);
    }
  }
  else {
    time += Math.ceil((start - time) / step) * step;
    while (time <= end) {
      times.push(time);
      time += step;
    }
  }
  return times;
};


/**
 * Adds the time by one year, e.g. 2014 January 15 -> 2015 January 15 -> ...
 * @param {number} time The current time
 * @return {number} Next year
 * @private
 */
DvtTimeAxisInfo._addOneYear = function(time) {
  var date = new Date(time);
  date.setFullYear(date.getFullYear() + 1);
  return date.getTime();
};

/**
 * Adds the time by one month, e.g. January 15 -> February 15 -> March 15 -> ...
 * @param {number} time The current time
 * @return {number} Next month
 * @private
 */
DvtTimeAxisInfo._addOneMonth = function(time) {
  var date = new Date(time);
  date.setMonth(date.getMonth() + 1);
  return date.getTime();
};


/**
 * Generates the level 1 and level 2 tick labels
 * @param {DvtContext} context
 * @private
 */
DvtTimeAxisInfo.prototype._generateLabels = function(context) {
  var labels1 = [];
  var labels2 = [];
  var labelInfos1 = [];
  var coords1 = [];
  var coords2 = [];
  var prevDate = null;
  var c1 = 0;// number of level 1 labels
  var c2 = 0;// number of level 2 labels
  var container = context.getStage(context);
  var isRTL = DvtAgent.isRightToLeft(context);
  var isVert = (this.Position == 'left' || this.Position == 'right');
  var scrollable = this.Options['zoomAndScroll'] != 'off';
  var first = true;

  // Bug #17046187 : On Chrome, creating a gap value to be used for spacing level1 labels and level2 labels
  var levelsGap = 0;
  if (isVert && DvtAgent.isBrowserChrome()) {
    levelsGap = this.getTickLabelFontSize() * .2;
  }

  // Find the time positions where labels are located
  var times = [];
  if (this._step != null) {
    times = DvtTimeAxisInfo._getLabelPositions(this.MinValue, this.MaxValue, this._step);
  }
  else if (this._timeAxisType == 'mixedFrequency') {
    this._step = this._getMixedFrequencyStep();
    times = DvtTimeAxisInfo._getLabelPositions(this.MinValue, this.MaxValue, this._step);
  }
  else {
    for (var i = 0; i < this._groups.length; i++) {
      if (this._groups[i] >= this.MinValue && this._groups[i] <= this.MaxValue)
        times.push(this._groups[i]);
    }
    this._step = this._averageInterval;

    if (this._timeAxisType != 'regular') {
      // Check the width of the first level1 label. If we expect that we'll have more group labels than we can fit in the
      // available space, then render the time labels at a regular interval (using mixed freq algorithm).
      var firstLabel = new DvtOutputText(context, this._formatAxisLabel(new Date(times[0]))[0], 0, 0);
      var labelWidth = isVert ? DvtTextUtils.guessTextDimensions(firstLabel).h : firstLabel.measureDimensions().w;
      var totalWidth = (labelWidth + this.GetTickLabelGapSize()) * (times.length - 1);
      var availWidth = this._maxCoord - this._minCoord;
      if (totalWidth > availWidth) {
        this._step = this._getMixedFrequencyStep();
        times = DvtTimeAxisInfo._getLabelPositions(this.MinValue, this.MaxValue, this._step);
      }
    }
  }

  if (times.length == 0)
    times = [this.MinValue]; // render at least one label

  // Create and format the labels
  for (var i = 0; i < times.length; i++) {
    var time = times[i];
    var coord = this.getCoordAt(time);
    if (coord == null)
      continue;

    var date = new Date(time);
    var twoLabels = this._formatAxisLabel(date, prevDate);

    var label1 = twoLabels[0];
    var label2 = twoLabels[1];
    //level 1 label
    if (label1 != null) {
      // If level 2 exists put a levelsGap space between labels. levelsGap is only non-zero on Chrome.
      labelInfos1.push({text: label1, coord: (label2 != null ? coord + levelsGap : coord)});
      coords1.push(coord);
      c1++;
    }
    else {
      labelInfos1.push(null);
      coords1.push(null);
    }
    // Defer label1 creation for now for performance optimization.
    // Only the labels we expect not to skip will be created in skipLabelsUniform().
    labels1.push(null);

    // Make sure that the position of first level2 label is constant if the chart is scrollable to prevent jumping around
    if (scrollable && first)
      coord = this.MinValue ? this.getCoordAt(this.MinValue) : coord;
    first = false;

    //level 2 label
    if (label2 != null) {
      var text = this.CreateLabel(context, label2, label2 != null ? coord - levelsGap : coord);
      coords2.push(coord);
      if (!isVert) //set alignment now in order to determine if the labels will overlap
        isRTL ? text.alignRight() : text.alignLeft();
      labels2.push(text);
      this._isOneLevel = false;
      c2++;
    }
    else {
      labels2.push(null);
      coords2.push(null);
    }

    prevDate = date;
  }

  // skip level 1 labels every uniform interval
  c1 = this._skipLabelsUniform(labelInfos1, labels1, container);

  if (!scrollable && c2 > 1 && c1 <= 1.5 * c2) {
    // too few level 1 labels
    labels1 = labels2;
    labels2 = null;
    // center align the new level1 labels
    for (var j = 0; j < labels1.length; j++) {
      if (labels1[j] != null)
        labels1[j].alignCenter();
    }
    c1 = this._skipLabelsGreedy(labels1, this.GetLabelDims(labels1, container));
  }
  else {
    // skip level 2 labels greedily
    c2 = this._skipLabelsGreedy(labels2, this.GetLabelDims(labels2, container));
    if (c2 == 0)
      labels2 = null; // null it so DvtAxisRenderer.getPreferredSize won't allocate space for it
  }

  if (isVert && labels2 != null)
    this._skipVertLabels(labels1, labels2, container);

  this._level1Labels = labels1;
  this._level2Labels = labels2;

  // Store coordinates of labels for gridline rendering
  this._level1Coords = coords1;
  this._level2Coords = coords2;
};


/**
 * Determines if rectangle A (bounded by pointA1 and pointA2) and rectangle B (bounded by pointB1 and B2) overlap.
 * All the points should lie in one dimension.
 * @param {Number} pointA1
 * @param {Number} pointA2
 * @param {Number} pointB1
 * @param {Number} pointB2
 * @param {Number} gap The minimum gap between the two rectangles
 * @return {Boolean} whether rectangle A and B overlap
 * @private
 */
DvtTimeAxisInfo._isOverlapping = function(pointA1, pointA2, pointB1, pointB2, gap) {
  if (pointB1 >= pointA1 && pointB1 - gap < pointA2)
    return true;
  else if (pointB1 < pointA1 && pointB2 + gap > pointA1)
    return true;
  return false;
};


/**
 * Skip labels greedily. Delete all labels that overlap with the last rendered label.
 * @param {Array} labels An array of DvtText labels for the axis. This array will be modified by the method.
 * @param {Array} labelDims An array of DvtRectangle objects that describe the x, y, height, width of the axis labels.
 * @return {Number} The number of remaining labels after skipping.
 * @private
 */
DvtTimeAxisInfo.prototype._skipLabelsGreedy = function(labels, labelDims) {
  // If there are no labels, return
  if (!labelDims || labelDims.length <= 0)
    return false;

  var isVert = (this.Position == 'left' || this.Position == 'right');
  var fontSize = this.getTickLabelFontSize();
  var gap = isVert ? fontSize * 0.1 : fontSize * 0.3;

  var count = 0;// the number of non-null labels
  var pointA1, pointA2, pointB1, pointB2;

  for (var j = 0; j < labelDims.length; j++) {
    if (labelDims[j] == null)
      continue;

    if (isVert) {
      pointB1 = labelDims[j].y;
      pointB2 = labelDims[j].y + labelDims[j].h;
    }
    else {
      pointB1 = labelDims[j].x;
      pointB2 = labelDims[j].x + labelDims[j].w;
    }

    if (pointA1 != null && pointA2 != null && DvtTimeAxisInfo._isOverlapping(pointA1, pointA2, pointB1, pointB2, gap))
      labels[j] = null;

    if (labels[j] != null) {
      // start evaluating from label j
      pointA1 = pointB1;
      pointA2 = pointB2;
      count++;
    }
  }

  return count;
};


/**
 * Skip labels uniformly (every regular interval).
 * @param {array} labelInfos An array of object containing text (the label text string) and coord (the label coordinate).
 * @param {array} labels An array of DvtOutputText labels for the axis (initially empty). This array will be populated by the method.
 * @param {DvtContainer} container The label container.
 * @return {number} The number of remaining labels after skipping.
 * @private
 */
DvtTimeAxisInfo.prototype._skipLabelsUniform = function(labelInfos, labels, container) {
  var rLabelInfos = []; // contains rendered labels only
  var rLabelDims = [];
  for (var j = 0; j < labelInfos.length; j++) {
    if (labelInfos[j] != null) {
      rLabelInfos.push(labelInfos[j]);
      rLabelDims.push(null);
    }
  }

  // Method that returns the label size. If the label object doesn't exist yet, it will create it and measure the
  // dimensions. Otherwise, it simply returns the stored dimensions.
  var isVert = this.Position == 'left' || this.Position == 'right';
  var _this = this;
  var getDim = function(i) {
    if (rLabelDims[i] == null) {
      rLabelInfos[i].label = _this.CreateLabel(container.getCtx(), rLabelInfos[i].text, rLabelInfos[i].coord);
      rLabelDims[i] = rLabelInfos[i].label.measureDimensions(container);
    }
    return isVert ? rLabelDims[i].h : rLabelDims[i].w;
  };


  // Estimate the minimum amount of skipping by dividing the total label width (estimated) by the
  // available axis width.
  var totalWidth = (getDim(0) + this.GetTickLabelGapSize()) * (rLabelInfos.length - 1);
  var availWidth = this._maxCoord - this._minCoord;
  var skip = availWidth > 0 ? (Math.ceil(totalWidth / availWidth) - 1) : 0;

  // Iterate to find the minimum amount of skipping
  var bOverlaps = true;
  while (bOverlaps) {
    for (var j = 0; j < rLabelInfos.length; j++) {
      if (j % (skip + 1) == 0) {
        getDim(j); // create the label and obtain the dim
        rLabelInfos[j].skipped = false;
      }
      else
        rLabelInfos[j].skipped = true;
    }
    bOverlaps = this.IsOverlapping(rLabelDims, skip);
    skip++;
  }

  // Populate the labels array with non-skipped labels
  var count = 0; // # of rendered labels
  for (var j = 0; j < labelInfos.length; j++) {
    if (labelInfos[j] != null && !labelInfos[j].skipped) {
      labels[j] = labelInfos[j].label;
      count++;
    }
  }
  return count;
};


/**
 * Format the alignments of the vertical axis labels and skip them accordingly so that level1 and level2 don't overlap.
 * @param {Array} labels1 An array of level 1 DvtText labels for the axis. This array will be modified by the method.
 * @param {Array} labels2 An array of level 2 DvtText labels for the axis. This array will be modified by the method.
 * @param {DvtContainer} container
 * @private
 */
DvtTimeAxisInfo.prototype._skipVertLabels = function(labels1, labels2, container) {
  var gap = this.getTickLabelFontSize() * .1;

  // returns if two rectangles (dimsA and dimsB) overlap vertically
  var isOverlapping = function(dimsA, dimsB) {
    return DvtTimeAxisInfo._isOverlapping(dimsA.y, dimsA.y + dimsA.h, dimsB.y, dimsB.y + dimsB.h, gap);
  };

  var lastDims = null;
  var overlapping = false;

  // attempt to render both level 1 and level 2 and see if they fit on the axis
  for (var i = 0; i < labels1.length; i++) {
    if (labels1[i] && labels2[i]) {
      labels1[i].alignTop();
      labels2[i].alignBottom();
      if (lastDims && isOverlapping(lastDims, labels2[i].measureDimensions())) {
        overlapping = true;
        break;
      }
      lastDims = labels1[i].measureDimensions();
    }
    else if (labels1[i] || labels2[i]) {
      var label = labels1[i] ? labels1[i] : labels2[i];
      if (lastDims && isOverlapping(lastDims, label.measureDimensions())) {
        overlapping = true;
        break;
      }
      lastDims = label.measureDimensions();
    }
  }

  if (!overlapping)
    return;// if both levels fit, we're done
  var lastLv1Idx = null;
  var lastLv1Dims = null;
  var lastLv2Dims = null;
  var dims;

  // if they don't fit:
  // - for points that have level 2 labels, don't generate the level 1 (one level nesting)
  // - skip all level 1 labels that overlaps with level 2 labels
  for (i = 0; i < labels1.length; i++) {
    if (labels2[i]) {
      // if level 2 exists
      labels1[i] = null;// delete level 1
      labels2[i].alignMiddle();
      dims = labels2[i].measureDimensions();
      if (lastLv1Dims && isOverlapping(lastLv1Dims, dims)) {
        labels1[lastLv1Idx] = null;
      }
      lastLv2Dims = dims;
    }
    else if (labels1[i]) {
      // if level 1 exists but not level 2
      dims = labels1[i].measureDimensions();
      if (lastLv2Dims && isOverlapping(lastLv2Dims, dims)) {
        labels1[i] = null;
      }
      else {
        lastLv1Dims = dims;
        lastLv1Idx = i;
      }
    }
  }
};


/**
 * @override
 */
DvtTimeAxisInfo.prototype.getLabels = function(context, levelIdx) {
  if (levelIdx && levelIdx > 1)// time axis has no more than two levels
    return null;

  if (!this._level1Labels)
    this._generateLabels(context);

  if (levelIdx == 1) {
    return this._level2Labels;
  }

  return this._level1Labels;
};


/**
 * @override
 */
DvtTimeAxisInfo.prototype.getMajorTickCoords = function() {
  var coords = [];
  if (this._isOneLevel) { // only one level, level1 is majorTick
    for (var i = 0; i < this._level1Coords.length; i++) {
      if (this._level1Coords[i] != null && this._level1Labels[i] != null)
        coords.push(this._level1Coords[i]);
    }
  }
  else { // level1 is minorTick, level2 is majorTick
    // don't draw majorTick for the first level2 label bc it's not the beginning of period
    for (var i = 1; i < this._level2Coords.length; i++) {
      if (this._level2Coords[i] != null)
        coords.push(this._level2Coords[i]); // render gridline even if label is skipped
    }
  }

  return coords;
};


/**
 * @override
 */
DvtTimeAxisInfo.prototype.getMinorTickCoords = function() {
  if (this._isOneLevel) // minorTick only applies on timeAxis if there is more than one level
    return [];

  var coords = [];
  for (var i = 0; i < this._level1Coords.length; i++) {
    if (this._level1Coords[i] != null && this._level1Labels[i] != null)
      coords.push(this._level1Coords[i]);
  }

  return coords;
};


/**
 * @override
 */
DvtTimeAxisInfo.prototype.getValueAt = function(coord) {
  // Return null if the coord is outside of the axis
  if (coord < this._minCoord || coord > this._maxCoord)
    return null;

  return this.getUnboundedValueAt(coord);
};


/**
 * @override
 */
DvtTimeAxisInfo.prototype.getCoordAt = function(value) {
  // Return null if the value is outside of the axis
  if (value < this.MinValue || value > this.MaxValue)
    return null;

  return this.getUnboundedCoordAt(value);
};


/**
 * @override
 */
DvtTimeAxisInfo.prototype.getBoundedValueAt = function(coord) {
  if (coord < this._minCoord)
    coord = this._minCoord;
  else if (coord > this._maxCoord)
    coord = this._maxCoord;

  return this.getUnboundedValueAt(coord);
};


/**
 * @override
 */
DvtTimeAxisInfo.prototype.getBoundedCoordAt = function(value) {
  if (value < this.MinValue)
    value = this.MinValue;
  else if (value > this.MaxValue)
    value = this.MaxValue;

  return this.getUnboundedCoordAt(value);
};


/**
 * @override
 */
DvtTimeAxisInfo.prototype.getUnboundedValueAt = function(coord) {
  var ratio = (coord - this._startCoord) / (this._endCoord - this._startCoord);

  if (this._timeAxisType == 'regular') {
    var minVal = this._timeToIndex(this.MinValue);
    var maxVal = this._timeToIndex(this.MaxValue);
    return this._indexToTime(minVal + ratio * (maxVal - minVal));
  }
  else
    return this.MinValue + ratio * (this.MaxValue - this.MinValue);
};


/**
 * @override
 */
DvtTimeAxisInfo.prototype.getUnboundedCoordAt = function(value) {
  var ratio;
  if (this._timeAxisType == 'regular') {
    var minVal = this._timeToIndex(this.MinValue);
    var maxVal = this._timeToIndex(this.MaxValue);
    var val = this._timeToIndex(value);
    ratio = (val - minVal) / (maxVal - minVal);
  }
  else
    ratio = (value - this.MinValue) / (this.MaxValue - this.MinValue);

  return this._startCoord + ratio * (this._endCoord - this._startCoord);
};


/**
 * Converts time to group index for regular time axis.
 * @param {number} time
 * @return {number} index
 * @private
 */
DvtTimeAxisInfo.prototype._timeToIndex = function(time) {
  var endIndex = this._groups.length;
  for (var i = 0; i < this._groups.length; i++) {
    if (time <= this._groups[i]) {
      endIndex = i;
      break;
    }
  }
  var startIndex = endIndex - 1;

  var startTime = this._groups[startIndex] !== undefined ? this._groups[startIndex] : this._groups[0] - this._averageInterval;
  var endTime = this._groups[endIndex] !== undefined ? this._groups[endIndex] : this._groups[this._groups.length - 1] + this._averageInterval;

  return startIndex + (time - startTime) / (endTime - startTime);
};


/**
 * Converts group index to time for regular time axis.
 * @param {number} index
 * @return {number} time
 * @private
 */
DvtTimeAxisInfo.prototype._indexToTime = function(index) {
  var endIndex = Math.min(Math.max(Math.ceil(index), 0), this._groups.length);
  var startIndex = endIndex - 1;

  var startTime = this._groups[startIndex] !== undefined ? this._groups[startIndex] : this._groups[0] - this._averageInterval;
  var endTime = this._groups[endIndex] !== undefined ? this._groups[endIndex] : this._groups[this._groups.length - 1] + this._averageInterval;

  return startTime + (index - startIndex) * (endTime - startTime);
};


/**
 * @override
 */
DvtTimeAxisInfo.prototype.getGroupWidth = function() {
  if (this._timeAxisType == 'regular')
    return Math.abs(this.getUnboundedCoordAt(this._indexToTime(1)) - this.getUnboundedCoordAt(this._indexToTime(0)));
  else
    return Math.abs(this.getUnboundedCoordAt(this.MinValue + this._averageInterval) - this.getUnboundedCoordAt(this.MinValue));
};


/**
 * @override
 */
DvtTimeAxisInfo.prototype.getMinimumExtent = function() {
  return this._averageInterval;
};


/**
 * @override
 */
DvtTimeAxisInfo.prototype.getStartOverflow = function() {
  if ((this.Position == 'top' || this.Position == 'bottom') && DvtAgent.isRightToLeft(this.getCtx()))
    return this.EndOverflow;
  else
    return this.StartOverflow;
};


/**
 * @override
 */
DvtTimeAxisInfo.prototype.getEndOverflow = function() {
  if ((this.Position == 'top' || this.Position == 'bottom') && DvtAgent.isRightToLeft(this.getCtx()))
    return this.StartOverflow;
  else
    return this.EndOverflow;
};
