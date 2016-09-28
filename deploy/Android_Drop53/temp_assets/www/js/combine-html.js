// @compiled on Fri Jan 29 18:50:42 MST 2016
// Note: this is a generated file all changes will be lost. 


/////////////////////////////////////// start of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/ELErrors.js///////////////////////////////////////

/* Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved. */
/* ------------------- ELErrors.js ---------------------- */

var adf                    = window.adf                 || {};
adf.mf                     = adf.mf                     || {};
adf.mf.api                 = adf.mf.api                 || {};
adf.mf.el                  = adf.mf.el                  || {};
adf.mf.locale              = adf.mf.locale              || {};
adf.mf.log                 = adf.mf.log                 || {};
adf.mf.resource            = adf.mf.resource            || {};
adf.mf.util                = adf.mf.util                || {};

adf.mf.internal            = adf.mf.internal            || {};
adf.mf.internal.api        = adf.mf.internal.api        || {};
adf.mf.internal.el         = adf.mf.internal.el         || {};
adf.mf.internal.el.parser  = adf.mf.internal.el.parser  || {};
adf.mf.internal.locale     = adf.mf.internal.locale     || {};
adf.mf.internal.log        = adf.mf.internal.log        || {};
adf.mf.internal.mb         = adf.mf.internal.mb         || {};
adf.mf.internal.perf       = adf.mf.internal.perf       || {};
adf.mf.internal.perf.story = adf.mf.internal.perf.story || {};
adf.mf.internal.resource   = adf.mf.internal.resource   || {};
adf.mf.internal.util       = adf.mf.internal.util       || {};


/*
 * Represents any of the ADF flavored exceptions
 */
adf.mf.AdfException = function(message) { 
	/* since this is the only exception that is know and sent to both sides of the channel it needs to match the Java type */ 
	this[adf.mf.internal.api.constants.TYPE_PROPERTY]           = "oracle.adfmf.framework.exception.AdfException";
	this[adf.mf.internal.api.constants.EXCEPTION_FLAG_PROPERTY] = true;
	this.name                                                   = 'AdfException';
	this.message                                                = (message || "");
	this.stack													= (new Error()).stack;
};
adf.mf.AdfException.prototype = new Error();


/*
 * Represents any of the exception conditions that can arise during expression evaluation.
 */
adf.mf.ELException = function(message) { 
	this[adf.mf.internal.api.constants.TYPE_PROPERTY]           = "oracle.adfmf.framework.exception.ELException";
	this[adf.mf.internal.api.constants.EXCEPTION_FLAG_PROPERTY] = true;
	this.name                                                   = 'ELException';
	this.message                                                = (message || "");
	this.stack													= (new Error()).stack;
};
adf.mf.ELException.prototype = new Error();


/**
 * Thrown to indicate that a method has been passed an illegal or 
 * inappropriate argument.
 */
adf.mf.IllegalArgumentException = function(message) { 
	this[adf.mf.internal.api.constants.TYPE_PROPERTY]           = "oracle.adfmf.framework.exception.IllegalArgumentException";
	this[adf.mf.internal.api.constants.EXCEPTION_FLAG_PROPERTY] = true;
	this.name                                                   = 'IllegalArgumentException';
	this.message                                                = (message || "");
	this.stack													= (new Error()).stack;
};
adf.mf.IllegalArgumentException.prototype = new Error();

/**
 * Thrown to indicate that an array is being accessed beyond
 * it array boundaries.
 */
adf.mf.IndexOutOfBoundsException = function(message) { 
	this[adf.mf.internal.api.constants.TYPE_PROPERTY]           = "oracle.adfmf.framework.exception.IndexOutOfBoundsException";
	this[adf.mf.internal.api.constants.EXCEPTION_FLAG_PROPERTY] = true;
	this.name                                                   = 'IndexOutOfBoundsException';
	this.message                                                = (message || "");
	this.stack													= (new Error()).stack;
};
adf.mf.IndexOutOfBoundsException.prototype = new Error();

/**
 * Thrown to indicate that the channel is not available.
 */
adf.mf.NoChannelAvailableException = function(message) { 
	this[adf.mf.internal.api.constants.TYPE_PROPERTY]           = "oracle.adfmf.framework.exception.NoChannelAvailableException";
	this[adf.mf.internal.api.constants.EXCEPTION_FLAG_PROPERTY] = true;
	this.name                                                   = 'NoChannelAvailableException';
	this.message                                                = (message || "Operation not supported in the current environment");
	this.stack													= (new Error()).stack;
};     
adf.mf.NoChannelAvailableException.prototype = new Error();


/**
 * Thrown to indicate that a null pointer has been encountered.
 */
adf.mf.NullPointerException = function(message) { 
	this[adf.mf.internal.api.constants.TYPE_PROPERTY]           = "oracle.adfmf.framework.exception.NullPointerException";
	this[adf.mf.internal.api.constants.EXCEPTION_FLAG_PROPERTY] = true;
	this.name                                                   = 'NullPointerException';
	this.message                                                = (message || "");
	this.stack													= (new Error()).stack;
};
adf.mf.NullPointerException.prototype = new Error();


/**
 * Thrown to indicate that a illegal state has been encountered.
 */
adf.mf.IllegalStateException = function(message) { 
	this[adf.mf.internal.api.constants.TYPE_PROPERTY]           = "oracle.adfmf.framework.exception.IllegalStateException";
	this[adf.mf.internal.api.constants.EXCEPTION_FLAG_PROPERTY] = true;
	this.name                                                   = 'IllegalStateException';
	this.message                                                = (message || "");
	this.stack													= (new Error()).stack;
};
adf.mf.IllegalStateException.prototype = new Error();


/**
 * Thrown when a property could not be found while evaluating a {@link adf.mf.el.ValueExpression} or
 * {@link MethodExpression}. For example, this could be triggered by an index out of bounds while
 * setting an array value, or by an unreadable property while getting the value of a JavaBeans
 * property.
 */
adf.mf.PropertyNotFoundException = function(message) { 
	this[adf.mf.internal.api.constants.TYPE_PROPERTY]           = "oracle.adfmf.framework.exception.PropertyNotFoundException";
	this[adf.mf.internal.api.constants.EXCEPTION_FLAG_PROPERTY] = true;
	this.name                                                   = 'PropertyNotFoundException';
	this.message                                                = (message || "");
	// Uncomment this if you want to track where the exception is coming from:
	//this.stack													= (new Error()).stack;
};
adf.mf.PropertyNotFoundException.prototype = new Error();


/**
 * Thrown when a property could not be written to while setting the value on a
 * {@link adf.mf.el.ValueExpression}. For example, this could be triggered by trying to set a map value on an
 * unmodifiable map.
 */
adf.mf.PropertyNotWritableException = function(message) { 
	this[adf.mf.internal.api.constants.TYPE_PROPERTY]           = "oracle.adfmf.framework.exception.PropertyNotWritableException";
	this[adf.mf.internal.api.constants.EXCEPTION_FLAG_PROPERTY] = true;
	this.name                                                   = 'PropertyNotWritableException';
	this.message                                                = (message || "");
	this.stack													= (new Error()).stack;
};
adf.mf.PropertyNotWritableException.prototype = new Error();


/**
 * Thrown to indicate that the requested operation is not supported.
 */
adf.mf.UnsupportedOperationException = function(message) { 
	this[adf.mf.internal.api.constants.TYPE_PROPERTY]           = "oracle.adfmf.framework.exception.UnsupportedOperationException";
	this[adf.mf.internal.api.constants.EXCEPTION_FLAG_PROPERTY] = true;
	this.name                                                   = 'UnsupportedOperationException';
	this.message                                                = (message || "");
	this.stack													= (new Error()).stack;
};
adf.mf.UnsupportedOperationException.prototype = new Error();


adf.mf.DataRangeNotPresentException = function(message) {
	this[adf.mf.internal.api.constants.TYPE_PROPERTY]           = "oracle.adfmf.framework.exception.DataRangeNotPresentException";
	this[adf.mf.internal.api.constants.EXCEPTION_FLAG_PROPERTY] = true;
	this.name                                                   = 'DataRangeNotPresentException';
	this.message                                                = (message || "");
	this.stack													= (new Error()).stack;
};
adf.mf.DataRangeNotPresentException.prototype = new Error();



/////////////////////////////////////// end of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/ELErrors.js///////////////////////////////////////



/////////////////////////////////////// start of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/ManagedBeans.js///////////////////////////////////////

/* Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved. */
/* ------------------- ManagedBeans.js ---------------------- */

var adf                    = window.adf                 || {};
adf.mf                     = adf.mf                     || {};
adf.mf.api                 = adf.mf.api                 || {};
adf.mf.api.bean            = adf.mf.api.bean            || {};
adf.mf.el                  = adf.mf.el                  || {};
adf.mf.locale              = adf.mf.locale              || {};
adf.mf.log                 = adf.mf.log                 || {};
adf.mf.resource            = adf.mf.resource            || {};
adf.mf.util                = adf.mf.util                || {};

adf.mf.internal            = adf.mf.internal            || {};
adf.mf.internal.api        = adf.mf.internal.api        || {};
adf.mf.internal.el         = adf.mf.internal.el         || {};
adf.mf.internal.el.parser  = adf.mf.internal.el.parser  || {};
adf.mf.internal.locale     = adf.mf.internal.locale     || {};
adf.mf.internal.log        = adf.mf.internal.log        || {};
adf.mf.internal.mb         = adf.mf.internal.mb         || {};
adf.mf.internal.perf       = adf.mf.internal.perf       || {};
adf.mf.internal.perf.story = adf.mf.internal.perf.story || {};
adf.mf.internal.resource   = adf.mf.internal.resource   || {};
adf.mf.internal.util       = adf.mf.internal.util       || {};

adf.mf.internal.mb.ManagedBeanDefinition = adf.mf.internal.mb.ManagedBeanDefinition || {
  "APPLICATION": "applicationScope",
  "PAGE_FLOW":   "pageFlowScope",
  "VIEW":        "viewScope"
};

/**
 * Set a collection of managed bean definitions.  The collection of bean
 * definitions supplied here _REPLACES_ any existing definitions, it does
 * not add to the existing set.
 *
 * @param {Array.<adf.mf.internal.mb.ManagedBeanDefinition>} beanDefs an array of
 *        ManagedBeanDefinition objects.
 * @param {Array.<function(Object,Object):void>|function(Object,Object)} success invoked when
 *        the method is successful invoked. Functions accept a request and response object as
 *        arguments.
 * @param {Array.<function(Object,Object):void>|function(Object,Object)} failed invoked when
 *        an error is encountered. Functions accept a request and response object as arguments.
 */
adf.mf.internal.mb.setBeanDefinitions = function(beanDefs, success, failed)
{
  // ensure defs is an array
  var defs = (adf.mf.internal.util.is_array(beanDefs))? beanDefs : [beanDefs];
  var scb  = (adf.mf.internal.util.is_array(success))?  success  : [success];

  if (!adf.mf.internal.isJavaAvailable())
  {
    if(defs.length == 0)
    {
      /* if there are no beans being defined, this command is a NOOP */
      for(var i = 0; i < scb.length; ++i)
      {
        try
        {
          scb[i](null, null);
        }
        catch(e) { /* ignore */ }
      }
      return;  /* do not actually make the java call since it will error out */
    }
    else
    {
      /* since there were beans defined, log a message and then let it error out in the invoke */
      adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
        "adf.mf.internal.mb.setBeanDefinitions", "ERROR_MNGD_BEANS_NOT_SUPPORTED");
    }
  }

  adf.mf.api.invokeMethod("oracle.adfmf.framework.api.Model", "setBeanDefinitions",
    defs, success, failed);
};

/**
 * Add a managed bean definition
 *
 * @param {adf.mf.api.bean.ManagedBeanDefinition} beanDefinition the managed bean definition to add
 * @param {Array.<function(Object,Object):void>|function(Object,Object)} success invoked when
 *        the method is successful invoked. Functions accept a request and response object as
 *        arguments.
 * @param {Array.<function(Object,Object):void>|function(Object,Object)} failed invoked when
 *        an error is encountered. Functions accept a request and response object as arguments.
 */
adf.mf.api.bean.addBeanDefinition = function(beanDefinition, success, failed)
{
  if (!adf.mf.internal.isJavaAvailable())
  {
   var scb = (adf.mf.internal.util.is_array(success)) ? success : [success];
   for (var i = 0, size = scb.length; i < size; ++i)
   {
      try
      {
        scb[i](null, null);
      }
      catch(e) { /* ignore */ }
    }
    return;
  }

  adf.mf.api.invokeMethod("oracle.adfmf.framework.api.Model", "addBeanDefinition",
    beanDefinition, success, failed);
};

/**
 * Remove a bean definition from the current context
 *
 * @param {adf.mf.api.bean.ManagedBeanDefinition} beanDefinition the definition of the bean to remove.
 *        The managed properties array does not need to be populated, all other properties are required.
 * @param {Array.<function(Object,Object):void>|function(Object,Object)} success invoked when
 *        the method is successful invoked. Functions accept a request and response object as
 *        arguments.
 * @param {Array.<function(Object,Object):void>|function(Object,Object)} failed invoked when
 *        an error is encountered. Functions accept a request and response object as arguments.
 */
adf.mf.api.bean.removeBeanDefinition = function(beanDefinition, success, failed)
{
  if (!adf.mf.internal.isJavaAvailable())
  {
    var scb = (adf.mf.internal.util.is_array(success)) ? success : [success];
    for (var i = 0, size = scb.length; i < size; ++i)
    {
      try
      {
        scb[i](null, null);
      }
      catch(e) { /* ignore */ }
    }
    return;
  }

  adf.mf.api.invokeMethod("oracle.adfmf.framework.api.Model", "removeBeanDefinition",
    beanDefinition, success, failed);
};

/**
 * Managed Bean definition
 *
 * @param {string} name managed bean's name
 * @param {string} type managed bean's type
 * @param {string} scope managed bean's scope
 * @param {Array.<adf.mf.internal.mb.ManagedPropertyDefinition>} props managed bean's managed
 *        properties
 */
adf.mf.api.bean.ManagedBeanDefinition = function(name, type, scope, props)
{
  this.beanName     = name;  /* managed bean's name  */
  this.fqnClassname = type;  /* managed bean's type  */
  this.scope        = scope; /* managed bean's scope */
  this.props        = props; /* managed bean's props */
};

/**
 * @deprecated Use adf.mf.api.bean.ManagedBeanDefinition instead
 */
adf.mf.internal.mb.ManagedBeanDefinition = adf.mf.api.bean.ManagedBeanDefinition;

/**
 * @return {string} the managed bean's name
 */
adf.mf.api.bean.ManagedBeanDefinition.prototype.getBeanName = function()
{
  return this.beanName;
};

/**
 * @return {string} the managed bean's fully qualified Java class name
 */
adf.mf.api.bean.ManagedBeanDefinition.prototype.getBeanClass = function()
{
  return this.fqnClassname;
};

/**
 * @return {string} the managed bean's associated scope
 */
adf.mf.api.bean.ManagedBeanDefinition.prototype.getScope = function()
{
  return this.scope;
};

/**
 * @return {Array.<adf.mf.internal.mb.ManagedPropertyDefinition>} the associated managed bean's
 *         properties
 */
adf.mf.api.bean.ManagedBeanDefinition.prototype.getManagedProperties = function()
{
  return this.props;
};

/**
 * Managed property definition used in the managed bean definition
 * that should be set by the controller layer on creation
 *
 * @param {string} name managed bean property
 * @param {string} type managed bean property's fully qualified Java classname
 * @param {Object} value managed bean property's value
 */
adf.mf.api.bean.ManagedPropertyDefinition = function(name, type, value)
{
  this.name  = name;  /* managed property's name  */
  this.type  = type;  /* managed property's type  */
  this.value = value; /* managed property's value */
};

/**
 * @deprecated Use adf.mf.api.bean.ManagedPropertyDefinition instead
 */
adf.mf.internal.mb.ManagedPropertyDefinition = adf.mf.api.bean.ManagedPropertyDefinition;

/**
 * @return {string} the managed property's name
 */
adf.mf.api.bean.ManagedPropertyDefinition.prototype.getName = function()
{
  return this.name;
};

/**
 * @return {string} the managed property's fully qualified class name
 */
adf.mf.api.bean.ManagedPropertyDefinition.prototype.getType = function()
{
  return this.type;
};

/**
 * @return {Object} the managed property's value
 */
adf.mf.api.bean.ManagedPropertyDefinition.prototype.getValue = function()
{
  return this.value;
};


/////////////////////////////////////// end of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/ManagedBeans.js///////////////////////////////////////



/////////////////////////////////////// start of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/ContainerIntegration.js///////////////////////////////////////

/* Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved. */
/* ------------------- ContainerIntegration.js ---------------------- */

var adf                    = window.adf                 || {};
adf.mf                     = adf.mf                     || {};
adf.mf.api                 = adf.mf.api                 || {};
adf.mf.el                  = adf.mf.el                  || {};
adf.mf.locale              = adf.mf.locale              || {};
adf.mf.log                 = adf.mf.log                 || {};
adf.mf.resource            = adf.mf.resource            || {};
adf.mf.util                = adf.mf.util                || {};

adf.mf.internal            = adf.mf.internal            || {};
adf.mf.internal.api        = adf.mf.internal.api        || {};
adf.mf.internal.el         = adf.mf.internal.el         || {};
adf.mf.internal.el.parser  = adf.mf.internal.el.parser  || {};
adf.mf.internal.locale     = adf.mf.internal.locale     || {};
adf.mf.internal.log        = adf.mf.internal.log        || {};
adf.mf.internal.mb         = adf.mf.internal.mb         || {};
adf.mf.internal.perf       = adf.mf.internal.perf       || {};
adf.mf.internal.perf.story = adf.mf.internal.perf.story || {};
adf.mf.internal.resource   = adf.mf.internal.resource   || {};
adf.mf.internal.util       = adf.mf.internal.util       || {};



(function() {
  var ADFMF_CONTAINER_UTILITIES          = "oracle.adfmf.framework.api.AdfmfContainerUtilities"
  var ADFMF_CONTAINER_UTILITIES_INTERNAL = "oracle.adfmf.framework.internal.AdfmfContainerUtilitiesInternal";
  var ASYNC_SUCCESS_CALLBACK             = "asyncContainerJavaScriptFunctionResponseSuccess";
  var ASYNC_FAILED_CALLBACK              = "asyncContainerJavaScriptFunctionResponseFailed";
  
   /**
    * Checks and obtains new configuration if available.
    * <br/>
    * The associated AdfmfContainerUtilites method that is invoked
    * is checkForNewConfiguration, which is defined
    * as:
    * public static void checkForNewConfiguration() throws AdfException
    * <br/>
    * so the associated JavaScript  function will be defined as
    * <br/>
    * adf.mf.api.checkForNewConfiguration = function(success, failed)
    * <br/>
    * The success and failed callbacks were added so the return value
    * and exception could be passed back to the JavaScript  calling code.
    * <br/>
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>void
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error (AdfException).
    * <br/>
    * <b>Example</b>
    * <pre>
   adf.mf.api.checkForNewConfiguration(
          function(req, res) &#123; alert("checkForNewConfiguration complete"); },
          function(req, res) &#123; alert("checkForNewConfiguration failed with " + adf.mf.util.stringify(res); }
   );
    * </pre>
    */
   adf.mf.api.checkForNewConfiguration = function(success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "checkForNewConfiguration", success, failed);
   };

   /**
    * Gets an array of <code>FeatureInformation</code> objects that provide
    * information about the features that are available in this session of the
    * ADF Mobile application and should be displayed on a custom springboard.
    * These features have already been filtered by the evaluation of constraints.
    * These are the features that would normally be displayed on the default
    * springboard.
    * <br/>
    * The associated AdfmfContainerUtilites method that is invoked
    * is getFeatures, which is defined
    * as:
    * public static FeatureInformation[] getFeatures() throws AdfException
    * <br/>
    * so the associated JavaScript  function will be defined as
    * <br/>
    * adf.mf.api.getFeatures = function(success, failed)
    * <br/>
    * The success and failed callbacks were added so the return value
    * and exception could be passed back to the JavaScript  calling code.
    * <br/>
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>
    * 
    *         An array of <code>FeatureInformation</code> objects each
    *         representing a feature that is available. This will include the
    *         feature id, the feature name, a path to the feature icon and a path
    *         to the feature image. Normally a springboard will display the name
    *         of the feature and the image for that feature.
    *         
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error (AdfException).
    * <br/>
    * <b>Example</b>
    * <pre>
   adf.mf.api.getFeatures(
          function(req, res) &#123; alert("getFeatures complete"); },
          function(req, res) &#123; alert("getFeatures failed with " + adf.mf.util.stringify(res); }
   );
    * </pre>
    */
   adf.mf.api.getFeatures = function(success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "getFeatures", success, failed);
   };

   /**
    * Gets <code>ApplicatiaonInformation</code> object containing the information
    * about the application. This can be used to get the application name for a
    * custom springboard. Additional information such as vendor, version and
    * application id are provided as well.
    * <br/>
    * The associated AdfmfContainerUtilites method that is invoked
    * is getApplicationInformation, which is defined
    * as:
    * public static ApplicatiaonInformation getApplicationInformation() throws AdfException
    * <br/>
    * so the associated JavaScript  function will be defined as
    * <br/>
    * adf.mf.api.getApplicationInformation = function(success, failed)
    * <br/>
    * The success and failed callbacks were added so the return value
    * and exception could be passed back to the JavaScript  calling code.
    * <br/>
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>
    * 
    *         A <code>ApplicatiaonInformation</code> object containing
    *         application level metadata. This includes application name, vendor,
    *         version and application id.
    * 
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error (AdfException).
    * <br/>
    * <b>Example</b>
    * <pre>
   adf.mf.api.getApplicationInformation(
          function(req, res) &#123; alert("getApplicationInformation complete"); },
          function(req, res) &#123; alert("getApplicationInformation failed with " + adf.mf.util.stringify(res); }
   );
    * </pre>
    */
   adf.mf.api.getApplicationInformation = function(success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "getApplicationInformation", success, failed);
   };

   /**
    * Activates the feature with the given ID.
    * <br/>
    * The associated AdfmfContainerUtilites method that is invoked
    * is gotoFeature, which is defined
    * as:
    * public static void gotoFeature(String featureId) throws AdfException
    * <br/>
    * so the associated JavaScript  function will be defined as
    * <br/>
    * adf.mf.api.gotoFeature = function(featureId, success, failed)
    * <br/>
    * The success and failed callbacks were added so the return value
    * and exception could be passed back to the JavaScript  calling code.
    * <br/>
    * @param featureId
    *          ID of feature to activate
    *          
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>void
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error (AdfException).
    * <br/>
    * <b>Example</b>
    * <pre>
    * adf.mf.api.gotoFeature("feature0",
    *   function(req, res) &#123; alert("gotoFeature complete"); },
    *   function(req, res) &#123; alert("gotoFeature failed with " + adf.mf.util.stringify(res); }
    * );
    * </pre>
    */
   adf.mf.api.gotoFeature = function(/* String */ featureId, success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "gotoFeature", featureId, success, failed);
   };


   /**
    * Activates the springboard.
    * <br/>
    * The associated AdfmfContainerUtilites method that is invoked
    * is gotoSpringboard, which is defined
    * as:
    * public static void gotoSpringboard() throws AdfException
    * <br/>
    * so the associated JavaScript  function will be defined as
    * <br/>
    * adf.mf.api.gotoSpringboard = function(success, failed)
    * <br/>
    * The success and failed callbacks were added so the return value
    * and exception could be passed back to the JavaScript  calling code.
    * <br/>
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>void
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error (AdfException).
    * <br/>
    * <b>Example</b>
    * <pre>
   adf.mf.api.gotoSpringboard(
          function(req, res) &#123; alert("gotoSpringboard complete"); },
          function(req, res) &#123; alert("gotoSpringboard failed with " + adf.mf.util.stringify(res); }
   );
    * </pre>
    */
   adf.mf.api.gotoSpringboard = function(success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "gotoSpringboard", success, failed);
   };

   /**
    * Hides the springboard.
    * <br/>
    * The associated AdfmfContainerUtilites method that is invoked
    * is hideSpringboard, which is defined
    * as:
    * public static void hideSpringboard() throws AdfException
    * <br/>
    * so the associated JavaScript  function will be defined as
    * <br/>
    * adf.mf.api.hideSpringboard = function(success, failed)
    * <br/>
    * The success and failed callbacks were added so the return value
    * and exception could be passed back to the JavaScript  calling code.
    * <br/>
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>void
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error (AdfException).
    * <br/>
    * <b>Example</b>
    * <pre>
   adf.mf.api.hideSpringboard(
          function(req, res) &#123; alert("hideSpringboard complete"); },
          function(req, res) &#123; alert("hideSpringboard failed with " + adf.mf.util.stringify(res); }
   );
    * </pre>
    */
   adf.mf.api.hideSpringboard = function(success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "hideSpringboard", success, failed);
   };

   /**
    * Shows the springboard.
    * <br/>
    * The associated AdfmfContainerUtilites method that is invoked
    * is showSpringboard, which is defined
    * as:
    * public static void showSpringboard() throws AdfException
    * <br/>
    * so the associated JavaScript  function will be defined as
    * <br/>
    * adf.mf.api.showSpringboard = function(success, failed)
    * <br/>
    * The success and failed callbacks were added so the return value
    * and exception could be passed back to the JavaScript  calling code.
    * <br/>
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>void
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error (AdfException).
    * <br/>
    * <b>Example</b>
    * <pre>
   adf.mf.api.showSpringboard(
          function(req, res) &#123; alert("showSpringboard complete"); },
          function(req, res) &#123; alert("showSpringboard failed with " + adf.mf.util.stringify(res); }
   );
    * </pre>
    */
   adf.mf.api.showSpringboard = function(success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "showSpringboard", success, failed);
   };

   /**
    * Activates the default feature.
    * <br/>
    * The associated AdfmfContainerUtilites method that is invoked
    * is gotoDefaultFeature, which is defined
    * as:
    * public static void gotoDefaultFeature() throws AdfException
    * <br/>
    * so the associated JavaScript  function will be defined as
    * <br/>
    * adf.mf.api.gotoDefaultFeature = function(success, failed)
    * <br/>
    * The success and failed callbacks were added so the return value
    * and exception could be passed back to the JavaScript  calling code.
    * <br/>
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>void
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error (AdfException).
    * <br/>
    * <b>Example</b>
    * <pre>
   adf.mf.api.gotoDefaultFeature(
          function(req, res) &#123; alert("gotoDefaultFeature complete"); },
          function(req, res) &#123; alert("gotoDefaultFeature failed with " + adf.mf.util.stringify(res); }
   );
    * </pre>
    */
   adf.mf.api.gotoDefaultFeature = function(success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "gotoDefaultFeature", success, failed);
   };


   /**
    * Resets the feature with the given ID.
    * <br/>
    * The associated AdfmfContainerUtilites method that is invoked
    * is resetFeature, which is defined
    * as:
    * public static void resetFeature(String featureId) throws AdfException
    * <br/>
    * so the associated JavaScript  function will be defined as
    * <br/>
    * adf.mf.api.resetFeature = function(featureId, success, failed)
    * <br/>
    * The success and failed callbacks were added so the return value
    * and exception could be passed back to the JavaScript  calling code.
    * <br/>
    * @param featureId
    *          ID of feature to reset
    *          
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>void
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error.
    *         
    * @throws AdfException
    * <br/>
    * <b>Example</b>
    * <pre>
   adf.mf.api.resetFeature("feature0",
          function(req, res) &#123; alert("resetFeature complete"); },
          function(req, res) &#123; alert("resetFeature failed with " + adf.mf.util.stringify(res); }
   );
    * </pre>
    */
   adf.mf.api.resetFeature = function(/* String */ featureId, success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "resetFeature", featureId, success, failed);
   };


   /**
    * Hides navigation bar.
    * <br/>
    * The associated AdfmfContainerUtilites method that is invoked
    * is hideNavigationBar, which is defined
    * as:
    * public static void hideNavigationBar() throws AdfException
    * <br/>
    * so the associated JavaScript  function will be defined as
    * <br/>
    * adf.mf.api.hideNavigationBar = function(success, failed)
    * <br/>
    * The success and failed callbacks were added so the return value
    * and exception could be passed back to the JavaScript  calling code.
    * <br/>
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>void
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error.
    *         
    * @throws AdfException
    * <br/>
    * <b>Example</b>
    * <pre>
   adf.mf.api.hideNavigationBar(
          function(req, res) &#123; alert("hideNavigationBar complete"); },
          function(req, res) &#123; alert("hideNavigationBar failed with " + adf.mf.util.stringify(res); }
   );
    * </pre>
    */
   adf.mf.api.hideNavigationBar = function(success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "hideNavigationBar", success, failed);
   };

   /**
    * Shows navigation bar.
    * <br/>
    * The associated AdfmfContainerUtilites method that is invoked
    * is showNavigationBar, which is defined
    * as:
    * public static void showNavigationBar() throws AdfException
    * <br/>
    * so the associated JavaScript  function will be defined as
    * <br/>
    * adf.mf.api.showNavigationBar = function(success, failed)
    * <br/>
    * The success and failed callbacks were added so the return value
    * and exception could be passed back to the JavaScript  calling code.
    * <br/>
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>void
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error.
    *         
    * @throws AdfException
    * <br/>
    * <b>Example</b>
    * <pre>
   adf.mf.api.showNavigationBar(
          function(req, res) &#123; alert("showNavigationBar complete"); },
          function(req, res) &#123; alert("showNavigationBar failed with " + adf.mf.util.stringify(res); }
   );
    * </pre>
    */
   adf.mf.api.showNavigationBar = function(success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "showNavigationBar", success, failed);
   };
   
   /**
    * Shows the preferences screen.
    * <br/>
    * The associated AdfmfContainerUtilites method that is invoked
    * is showPreferences, which is defined
    * as:
    * public static void showPreferences() throws AdfException
    * <br/>
    * so the associated JavaScript  function will be defined as
    * <br/>
    * adf.mf.api.showPreferences = function(success, failed)
    * <br/>
    * The success and failed callbacks were added so the return value
    * and exception could be passed back to the JavaScript  calling code.
    * <br/>
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>void
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error.
    *         
    * @throws AdfException
    * <br/>
    * <b>Example</b>
    * <pre>
   adf.mf.api.showPreferences(
          function(req, res) &#123; alert("showPreferences complete"); },
          function(req, res) &#123; alert("showPreferences failed with " + adf.mf.util.stringify(res); }
   );
    * </pre>
    */
   adf.mf.api.showPreferences = function(success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "showPreferences", success, failed);
   };


   /**
    * Invokes a Javascript method with the given arguments on the specified
    * feature. Returns the result of the method execution.
    * <br/>
    * The associated AdfmfContainerUtilites method that is invoked
    * is invokeContainerJavaScriptFunction, which is defined
    * as:
    * public static Object invokeContainerJavaScriptFunction(String featureId, String methodName, Object[] args) throws AdfException
    * <br/>
    * so the associated JavaScript  function will be defined as
    * <br/>
    * adf.mf.api.showNavigationBar = function(success, failed)
    * <br/>
    * The success and failed callbacks were added so the return value
    * and exception could be passed back to the JavaScript  calling code.
    * <br/>
    * @param featureId
    *          ID of feature on which to invoke the method
    * @param methodName
    *          method name
    * @param args
    *          array of arguments to be passed to method
    *          
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>Object
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error.
    *         
    * @throws AdfException
    * <br/>
    * <b>Example</b>
    *  <ul>
    *  <li>included a simple appFunction.js file to feature1 (by adding it to feature1 content's include in JDeveloper).</li>
    *  <li>added calls to adf.mf.api.invokeContainerJavaScriptFunction to your code</li>
    *  </ul>
    * <br/>
    * <b>appFunctions.js</b>
    * <pre>
    (function() 
    &#123;
       if (!window.application) window.application = &#123;};

       application.testFunction = function()
       &#123;
          var args = arguments;

          alert("APP ALERT " + args.length + " ");
          return "application.testFunction - passed";
       };
    })();
    * </pre>
    * <br/>
    * <pre>
   adf.mf.api.invokeContainerJavaScriptFunction("feature1",
          function(req, res) &#123; alert("invokeContainerJavaScriptFunction complete"); },
          function(req, res) &#123; alert("invokeContainerJavaScriptFunction failed with " + adf.mf.util.stringify(res); }
   or
   adf.mf.api.invokeContainerJavaScriptFunction("feature1", [ "P1" ], 
          function(req, res) &#123; alert("invokeContainerJavaScriptFunction complete"); },
          function(req, res) &#123; alert("invokeContainerJavaScriptFunction failed with " + adf.mf.util.stringify(res); }
   );
   or
   adf.mf.api.invokeContainerJavaScriptFunction("feature1", [ "P1", "P2" ], 
          function(req, res) &#123; alert("invokeContainerJavaScriptFunction complete"); },
          function(req, res) &#123; alert("invokeContainerJavaScriptFunction failed with " + adf.mf.util.stringify(res); }
   );
    * </pre>
    * <br/>
    * Now when the user presses the button they will see three alerts (from the appFunctions.js):
    * <pre>
        APP ALERT 0
        APP ALERT 1
        APP ALERT 2
    * </pre>
    */
   adf.mf.api.invokeContainerJavaScriptFunction = function(/* String */ featureId, /* String */ methodName, /* Object[] */ args, success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "invokeContainerJavaScriptFunction", featureId, methodName, args, success, failed);
   };


   /**
    * Invokes a native method on the specified class with the given arguments.
    * Returns the result of method execution.
    * 
    * @param className
    *          class name
    * @param methodName
    *          method name
    * @param args
    *          array of arguments to be passed to method
    * 
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>Object
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error.
    *         
    * @throws AdfException
    */
   adf.mf.api.invokeContainerMethod = function(/* String */ classname, /* String */ methodName, /* Object[] */ args, success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "invokeContainerMethod", classname, methodName, args, success, failed);
   };

   /**
    * Returns the feature information for the passed in feature id.
    * <br/>
    * The associated AdfmfContainerUtilites method that is invoked
    * is getFeatureById, which is defined
    * as:
    * public static FeatureInformation getFeatureById(String featureId) throws AdfException
    * <br/>
    * so the associated JavaScript  function will be defined as
    * <br/>
    * adf.mf.api.getFeatureById = function(featureId, success, failed)
    * <br/>
    * The success and failed callbacks were added so the return value
    * and exception could be passed back to the JavaScript  calling code.
    * <br/>
    * @param featureId
    *          ID of the feature to retrieve
    * 
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>Feature
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error.
    *         
    * @throws AdfException
    * <br/>
    * <b>Example</b>
    * <pre>
   adf.mf.api.getFeatureById("feature.id",
          function(req, res) &#123; alert("getFeatureById complete"); },
          function(req, res) &#123; alert("getFeatureById failed with " + adf.mf.util.stringify(res); }
   );
    * </pre>
    */
   adf.mf.api.getFeatureById = function(/* String */ featureId, success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "getFeatureById", featureId, success, failed);
   };

   /**
    * Returns the feature information for the passed in feature name.
    * <br/>
    * The associated AdfmfContainerUtilites method that is invoked
    * is getFeatureByName, which is defined
    * as:
    * public static FeatureInformation getFeatureByName(String featureName) throws AdfException
    * <br/>
    * so the associated JavaScript  function will be defined as
    * <br/>
    * adf.mf.api.getFeatureByName = function(featureName, success, failed)
    * <br/>
    * The success and failed callbacks were added so the return value
    * and exception could be passed back to the JavaScript  calling code.
    * <br/>
    * @param featureName
    *          Name of the feature to retrieve
    * 
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>void
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error.
    *         
    * @throws AdfException
    * <br/>
    * <b>Example</b>
    * <pre>
   adf.mf.api.getFeatureByName("feature.name",
          function(req, res) &#123; alert("getFeatureByName complete"); },
          function(req, res) &#123; alert("getFeatureByName failed with " + adf.mf.util.stringify(res); }
   );
    * </pre>
    */
   adf.mf.api.getFeatureByName = function(/* String */ featureName, success, failed) 
   {
      adf.mf.api.invokeMethod(ADFMF_CONTAINER_UTILITIES, "getFeatureByName", featureName, success, failed);
   };

   /**
    * internal api for invoke Java 
    * e.g. adf.mf.internal.api.invokeMethod(commId, classname, methodname, param1, param2, ... , paramN ,successCallback, failedCallback);
    */

   adf.mf.internal.api.invokeMethod = function()
   {
      argc   = arguments.length;
      params = new Array();

      for (var i=3; i < argc-2; i++) 
      {
         params[i-3] = arguments[i];
      }

      var request = { "classname"  : arguments[1], /* clazz  */
            "method"     : arguments[2], /* method */
            "params"     : params };

      adf.mf.internal.context.invokeJavaMethod(/* CommId */ arguments[0], request, arguments[argc-2], arguments[argc-1]);
   };

   /**
    * The framework enables you to display the device's e-mail interface, and optionally pre-populate certain fields:
    * 
    *  @param options
    *     is a JSON object with the following optional properties:
    *     <ul>
    *     <li>to: recipients (comma-separated)</li>
    *     <li>cc: CC recipients (comma-separated)</li>
    *     <li>subject: message subject</li>
    *     <li>body: message body</li>
    *     <li>bcc: BCC recipients (comma-separated)</li>
    *     <li>attachments: list of filenames to attach to the e-mail (comma-separated)</li>
    *     <li>mimeTypes:
    *     <ul><li><b>iOS</b>: List of MIME types to use for the attachments (comma-separated). 
    *     Specify null to let the framework automatically determine the MIME types. 
    *     It is also possible to only specify the MIME types for selected attachments; see examples below.</li>
    *     <li><b>Android</b>: MIME type in Android isn't true MIME type but just a way for Android to filter 
    *     applications to be shown in the application chooser dialog. But empty MIME type doesn't work 
    *     and throws exception. So if no MIME type is passed in, we use "plain/text" by default. Also, if 
    *     there are multiple attachment types, user doesn't need to provide multiple MIME types, but can 
    *     provide just most suitable MIME type (as per Android documentation). That being said, if the user 
    *     has an application which is being deployed to both iOS and Android, they can pass in the comma-separated 
    *     list of mime types and Android will still work fine.</li></ul>
    *     </li>
    *     </ul>
    *  <br/>
    *  After this interface is displayed, the user can choose to either send the e-mail or discard it. Note that it 
    *  is not possible to automatically send the e-mail due to device/carrier restrictions; only the end user can actually 
    *  send the e-mail. The device must also have at least one e-mail account configured to send e-mail; otherwise, an 
    *  error will be displayed indicating that no e-mail accounts could be found.
    *  <br/>
    *  Examples:
    *
    * Populate an e-mail to 'john.doe@foo.com', copy 'jane.doe@bar.com', with the subject 'Test message', 
    * and the body 'This is a test message'
    * <br/>
    * <pre>
   adf.mf.api.sendEmail(&#123;to: "john.doe@foo.com",
                         cc: "jane.doe@bar.com",
                         subject: "Test message",
                         body: "This is a test message"},
                        success, failed);
    * </pre>
    * <br/>
    * Taking the same example, but now adding a BCC to 'mary.may@another.com' and 'lary.day@another.com'
    * and attaching two files.<br/>
    * <b>NOTE:</b> By not specifying a value for the mimeTypes parameter, you are telling the framework to automatically 
    * determine the MIME type for each of the attachments
    * <br/>
    * <pre>
   adf.mf.api.sendEmail(&#123;to: "john.doe@foo.com",
                         cc: "jane.doe@bar.com",
                         bcc: "mary.may@another.com,lary.day@another.com"
                         subject: "Test message",
                         attachments: "path/to/file1.txt,path/to/file2.png"},
                        success, failed);
    * </pre>
    * 
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>void
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error.
    *         
    * @throws AdfException
    */
   adf.mf.api.sendEmail = function(/* JSON */ options, success, failed)
   {
      if (options)
      {
         var attachments = options["attachments"];
         var mimeTypes = options["mimeTypes"];
         if (attachments || mimeTypes)
         {
            var attachmentsArray = attachments ? attachments.split(",") : [];
            var mimeTypesArray = mimeTypes ? mimeTypes.split(",") : [];
            var numMimeTypes = mimeTypesArray.length;
            var numAttachments = attachmentsArray.length;
            if (numMimeTypes > 0 && numAttachments != numMimeTypes)
            {
               // When attachments and MIME types are both specified, the number of each
               // must match.
               if (failed != null)
               {
                  var rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_MIMETYPES_NOTEQUAL_ATTACHMENTS", [numMimeTypes, numAttachments]);
                  failed(rmsg);
                  return;
               }
            }
         }
      }
      Cordova.exec( success, failed, "AdfmfEmail", "sendEmail", [options]);
   };

   // This variant of the sendEmail API is ONLY used when invoking sendEmail from Embedded Java code
  adf.mf.internal.api.sendEmail = function (commId, requestId, emailOptions) {  
    try {
      adf.mf.api.sendEmail(emailOptions, /* Options to pre-populate e-mail dialog */

        function(result) /* Success callback */
        {
          var responseData = result || {};
          try 
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK, requestId, responseData, function() {}, function() {});
          }
          catch (se)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.sendEmail", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
          }
        },

        function(result) /* Error callback */
        {
          var responseData = result || {};
          try 
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, responseData, function() {}, function() {});
          }
          catch (se)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.sendEmail", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
          }
        });
     }
     catch(se)
     {
        adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
          "adf.mf.api.sendEmail", "ERROR_EXCEPTION", se);;
        adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, msg, function() {}, function() {});
    }
  };



   /**
    * The framework enables you to display the device's text messaging (SMS) interface, and optionally pre-populate certain fields:
    * 
    *  @param options
    *     is a JSON object with the following optional properties:
    *     <ul>
    *     <li>to: recipients (comma-separated)</li>
    *     <li>body: message body</li>
    *     </ul>
    *  <br/>
    *  After this interface is displayed, the user can choose to either send the 
    *  SMS or discard it. Note that it is not possible to automatically send the 
    *  SMS due to device/carrier restrictions; only the end user can actually send the SMS.
    *  <br/>
    *  Examples:
    *
    * Populate an SMS message to '1234567890' with the body 'This is a test message'
    * <br/>
    * <pre>
    adf.mf.api.sendSMS(&#123;to: "1234567890",
                        body: "This is a test message"},
                       success, failed);
    * </pre>
    * 
    * <b>success callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the associated AdfmfContainerUtilities
    *    method's return value.
    *  <br/>i.e.<br/>void
    *  <br/>
    *  <b>failed callback</b> must be in the form of 
    *    function(request, response) where the request 
    *    contains the original request and the response
    *    contains the error.
    *         
    * @throws AdfException
    */
   adf.mf.api.sendSMS = function(/* JSON */ options, success, failed)
   {
     if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
      {
        adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api.sendSMS",
              "CORDOVA_DEBUG", "Inside the ContainterIntegration.js invoking AdfmfSMS.sendSMS Cordova plugin");
      }
      Cordova.exec( success, failed, "AdfmfSMS", "sendSMS", [options]);
   };	

   // This variant of the sendSMS API is ONLY used when invoking sendSMS from Embedded Java code
   adf.mf.internal.api.sendSMS = function (commId, requestId, smsOptions) {
     try {
      adf.mf.api.sendSMS(smsOptions, /* Options to pre-populate SMS dialog */

        function(result) /* Success callback */
        {
          var responseData = result || {};
          try
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK, requestId, responseData, function() {}, function() {});
          }
          catch (se)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.sendSMS", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
          }
        },

        function(result) /* Error callback */
        {
          var responseData = result || {};
          try
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, responseData, function() {}, function() {});
          }
          catch (se)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.sendSMS", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
          }
        });
    }
    catch(se)
    {
      adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
        "adf.mf.api.sendSMS", "ERROR_EXCEPTION", se);
      adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, msg, function() {}, function() {});
    }
  };

  adf.mf.api.getDeviceProperties = function(success, failed)
  {
    if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
    {
      adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api.getDeviceProperties",
            "CORDOVA_DEBUG", "Inside the ContainterIntegration.js invoking ADFMobileDeviceProperties.getDeviceProperties Cordova plugin");
    }
    Cordova.exec( success, failed, "ADFMobileDeviceProperties", "getDeviceProperties", []);
  };

  adf.mf.internal.api.getCurrentPosition = function (commId, requestId) {
    try {
      navigator.geolocation.getCurrentPosition(
      
        function (data) {
          var position = data || {};
          try
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK,
               requestId, position, function () {}, function () {});
          }
          catch (se)
          {
             adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.getCurrentPosition", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
          }
        },

        function (data) {
          var message = ((data != null) && (data.message != null)) ? data.message : "unknown error";
          try
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK,
               requestId, message, function () {}, function () {});
          }
          catch (se)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.getCurrentPosition", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
          }
      });
    }
    catch(se)
    {
      adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
        "navigator.geolocation.getCurrentPosition", "ERROR_EXCEPTION", se);
      adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, msg, function() {}, function() {});
    }
  };

  adf.mf.internal.api.watchPosition = function (commId, requestId, userWatchId, geolocationOptions) { // success callback
    var firstTime = true;
    try
    {
      var watchId = navigator.geolocation.watchPosition(
      
        function (data) {
          // 'data' is a complex object with prototypical inheritance and is not guaranteed to return expected results when serialized using JSON.stringify.  
          // Therefore, convert it into a POJO - 'position'- and pass-on 'position' instead.
          var position = {
            timestamp: null,
          	coords: {
          	  speed: null,
          	  longitude: null,
          	  latitude: null,
          	  heading: null,
          	  altitudeAccuracy: null,
          	  altitude: null,
          	  accuracy: null
          	}
          };
    	  if (data != null)
    	  {
    		// This pattern is used so that if the value that is being assigned is undefined, the default value of the LHS variable is preserved.
			position.timestamp = data.timestamp || position.timestamp;
			if (data.coords != null)
			{
			  position.coords.accuracy = data.coords.accuracy || position.coords.accuracy;
			  position.coords.altitude = data.coords.altitude || position.coords.altitude;
			  position.coords.altitudeAccuracy = data.coords.altitudeAccuracy || position.coords.altitudeAccuracy;
			  position.coords.heading = data.coords.heading || position.coords.heading;
			  position.coords.latitude = data.coords.latitude || position.coords.latitude;
			  position.coords.longitude = data.coords.longitude || position.coords.longitude;
			  position.coords.speed = data.coords.speed || position.coords.speed;
			}
    	  }
          if (firstTime) 
          {  // unblock the calling thread only on the first callback.
            try
            {
              adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK,
                    requestId, String(watchId), function () {}, function () {});
            }
            catch (se)
            {
              adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.watchPosition", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
            }
            firstTime = false;
          }
          try
          {
            adf.mf.api.invokeMethod("oracle.adf.model.datacontrols.device.GeolocationProxy",
               "invokeEmbeddedCallback", userWatchId, position, function () {}, function () {});
          }
          catch (se)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.watchPosition", "ERROR_IN_REQUEST", "invokeEmbeddedCallback", se);
          }
        },
        
        function (data) {
          navigator.geolocation.clearWatch(String(watchId));  // if error, need to clear watchId so there is no dangling callbacks
          var message = ((data != null) && (data.message != null)) ? data.message : "unknown error";
          try
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK,
               requestId, message, function () {}, function () {});
          }
          catch (se)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.watchPosition", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
          }
        }, 
        geolocationOptions);
    }
    catch(se)
    {
      adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
        "navigator.geolocation.watchPosition", "ERROR_EXCEPTION", se);
      adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, msg, function() {}, function() {});
    }        
  };


  adf.mf.internal.api.clearWatch = function (watchID) {
    if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
    {
      adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.clearWatch",
            "CORDOVA_DEBUG", watchID);
    }
    navigator.geolocation.clearWatch(watchID); // no callbacks
  };

  adf.mf.internal.api.createContact = function (commId, requestId, properties)
  {
    if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
    {
      adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.createContact",
            "CORDOVA_DEBUG", adf.mf.util.stringify(properties));
    }

    try
    {
      // Fix for Bug 16433413: If displayName is passed as null, then Cordova returns "null" for displayName.
      // If empty string is passed in, either valid displayName is returned or null is returned when there is
      // no displayName.
      if (properties !== undefined && properties !== null)
      {
        // Remove all dot properties from javascript object (JSON) to be sent to Cordova layer
        adf.mf.internal.removeDotProperties(properties);

        if (properties.displayName == null)
        {
          properties.displayName = "";
        }
      }

      if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
      {
        adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.createContact",
          "CORDOVA_DEBUG_JUST_BEFORE_CREATE_CONTACT_CALL_TO_CORDOVA", adf.mf.util.stringify(properties));
      }

      var contact = navigator.contacts.create(properties);
      if (contact.note == null)
      {
        contact.note = '';
      }
      if (contact.nickname == null)
      {
        contact.nickname = '';
      }

      contact.save(
      
        function (data) {
          var contact = data || {};
  
          // In cordova-2.2.0.js, createIn method adds "Invalid Object" value for null Birthday
          // so we need to put back null there to serialize in to Java layer properly.
          var bDay = contact.birthday;
          if (isNaN(bDay) || ("undefined" == typeof bDay.getDate))
          {
            contact.birthday = null;
          }
          try
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK,
                 requestId, contact, function () {}, function () {});
          }
          catch (se)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.createContact", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
          }
        },
  
        function (data) {
          var errorCode = ((data != null) && (data.code != null)) ? data.code : ContactError.UNKNOWN_ERROR;
          try
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK,
                 requestId, errorCode, function () {}, function () {});
          }
          catch (se)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.createContact", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
          }
        });
    }
    catch(se)
    {
      adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
        "navigator.contacts.save", "ERROR_EXCEPTION", se);
      adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, msg, function() {}, function() {});
    }   
  };

  adf.mf.internal.api.findContacts = function (commId, requestId, fields, options) {
    if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
    {
      adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.findContacts", "CORDOVA_DEBUG - fields",
            fields);
      adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.findContacts", "CORDOVA_DEBUG - options",
            adf.mf.util.stringify(options));
    }

	try
	{
		navigator.contacts.find(fields, 
			function (data)
			{
				var contacts = data || {};
				if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
				{
					adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.find success",
						"CORDOVA_FIND_CONTACTS_BEFORE_CONVERTING", adf.mf.util.stringify(contacts));
				}

				try
				{
					// cordova-2.2.0.js doesn't invoke createIn() method and thus does not convert birthday from
					// long to String for findContact call and thus the contact(s) returned have birthday as
					// long in stringified way. eg. "505003627119". Whereas saveContact call correctly invokes
					// createIn() method and thus returns valid string. eg. "1986-01-01T22:47:07.119Z".
					// As ADFMF supports this string type or just the long (without stringified long) we need to
					// invoke createIn() method for each contact to convert date to supported format before
					// invoking the success callback method.
					for (var i = 0; i < contacts.length; i++)
					{
						var contact = contacts[i];
						
						// Copied this method body from cordova-2.2.0.js and:
						// 1) Added if condition to safeguard it
						// 2) Removed console.log and added our framework logging.
						var bDay = contact.birthday;
						try
						{
							if (null != bDay && !isNaN(bDay))
							{
								contact.birthday = new Date(parseFloat(bDay));
							}
						}
						catch (exception)
						{
							if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
							{
								adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.find success",
									"CORDOVA_FIND_CONTACTS_DURING_CONVERT_BDAY", "Exception converting date from long to String");
							}

							adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
								"adf.mf.internal.util.convertContact", "ERROR_EXCEPTION", exception);
						}
					}

					if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
					{
						adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.find success",
							"CORDOVA_FIND_CONTACTS_AFTER_CONVERTING", adf.mf.util.stringify(contacts));
					}

					adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK,
						requestId, contacts, function () {}, function () {});
				}
				catch (se)
				{
					adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
						"adf.mf.internal.api.findContacts", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
				}
			},

        function (data) {
          var errorCode = ((data != null) && (data.code != null)) ? data.code : ContactError.UNKNOWN_ERROR;
          
          try
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK,
               requestId, errorCode, function () {}, function () {});
          }
          catch (se)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.findContacts", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
          }
        }, options);
    }
    catch(se)
    {
      adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
        "navigator.contacts.find", "ERROR_EXCEPTION", se);
      adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, msg, function() {}, function() {});
    } 
  };

  adf.mf.internal.api.removeContact = function (commId, requestId, properties) {
    if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
    {
      adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.removeContact",
            "CORDOVA_DEBUG",
            adf.mf.util.stringify(properties));
    }

    try
    {
      var contact = navigator.contacts.create(properties);

      contact.remove(
      
        function (data) {
          var contact = data || {};
          try
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK,
                 requestId, contact, function () {}, function () {});
          }
          catch (se)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.removeContact", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
          }
        },

        function (data) {
          var errorCode = ((data != null) && (data.code != null)) ? data.code : ContactError.UNKNOWN_ERROR;
          try
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK,
               requestId, errorCode, function () {}, function () {});
          }
          catch (se)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.removeContact", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
          }          
        });
    }
    catch(se)
    {
      adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
        "navigator.contacts.remove", "ERROR_EXCEPTION", se);
      adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, msg, function() {}, function() {});
    } 
  };

  adf.mf.internal.api.getPicture = function (commId, requestId, cameraOptions) {
    if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
    {
      adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.getPicture",
            "CORDOVA_DEBUG", adf.mf.util.stringify(cameraOptions));
    }
    
    try
    {
      navigator.camera.getPicture(
        
        function (data) {
          var imageData = data || {};
          if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
          {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.getPicture success",
               "CORDOVA_DEBUG", "");
          }
          try
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK,
               requestId, imageData, function () {}, function () {});
          }
          catch (se)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.getPicture", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
          }
        },

        function (data) {
          var message = data || {};
          if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
          {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.getPicture", "CORDOVA_DEBUG", message);
          }
    
         // Return empty string as image data and call success callback instead of calling failure callback
          var imageData = "";
          try
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK,
               requestId, imageData, function () {}, function () {});
          }
          catch (se)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.getPicture", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
          }
        }, cameraOptions);
    }
    catch(se)
    {
      adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
        "navigator.camera.getPicture", "ERROR_EXCEPTION", se);
      adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, msg, function() {}, function() {});
    } 
  };


  adf.mf.internal.api.getDeviceProperties = function(commId, requestId) {
    if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
    {
      adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "adf.mf.internal.api.getDeviceProperties", "CORDOVA_DEBUG", "");
    }
    
    try
    {
      adf.mf.api.getDeviceProperties(
      
        function (data) {
          var deviceProperties = data || {};
          if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
          {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.getDeviceProperties success",
                "CORDOVA_DEBUG", adf.mf.util.stringify(deviceProperties));
          }
          
          // Replace the phonegap version with the value from device.cordova
          // deviceProperties.device.phonegap = window.device.cordova;
          
          try
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK,
                                             requestId, deviceProperties, function () {}, function () {});
          }
          catch (se)
          {;
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.getDeviceProperties", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
          }                              
        },
        
        function (data) {
          var message = data || {};
          if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
          {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.getDeviceProperties failed",
                "CORDOVA_DEBUG", "");
          }
          try
          {
            adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK,
                                             requestId, message, function () {}, function () {});
          }
          catch (se)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.getDeviceProperties", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
          }
        });
    }
    catch(se)
    {
      adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
        "adf.mf.api.getDeviceProperties", "ERROR_EXCEPTION", se);
      adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, msg, function() {}, function() {});
    }  
  };

  /**
   * INTERNAL FUNCTION used to to remove dot properties from JSON
   */
  adf.mf.internal.removeDotProperties = function(dat)
  {
	  if (dat !== undefined && dat !== null)
	  {
		  if(dat instanceof Array)
		  {
			  for(var i = 0; i < dat.length; i++)
			  {
				  var retValue = this.removeDotProperties(dat[i]);
				  if (retValue === null)
				  {
					  dat.splice (i, 1);
					  i--;
				  }
			  }
		  }
		  else if(typeof dat === 'object')
		  {
			  for(var property in dat)
			  {
				  if(property !== undefined && property !== null)
				  {
					  if (property == '.null')
					  {
						  if (dat[property] == true)
						  {
							  return null;
						  }
					  }
					  else if (property.indexOf (".") == 0)
					  {
						  // If the property is a dot property, delete it, as those shouldn't be sent to Cordova layer.
						  delete dat[property];
					  }
					  else if((typeof dat[property]) != 'function')
					  {
						  var retValue = this.removeDotProperties(dat[property]);
						  if (retValue === null)
						  {
							  delete dat[property];
						  }
					  }
				  }
			  }
		  }
	  }

	  return dat;
  };

  /**
   * INTERNAL API used for Push Notification 
   * 
   * namespace : adf.mf.internal.api.pushnotifications
   * 
   * methods :
   * 	public :
   * 		register(params)
   * 			Register with APNs/GCM. Calls container.internal.device.integration.PushNotifications(params, tokenHandler, errorHandler)
   * 			@params : JSON formatted registration parameters. Ex : {"badge":"true", "sound":"true"}
   * 	private :
   * 		tokenHandler(request, token)
   * 			Success callback for registration on iOS. Here we use adf.mf.api.invokeMethod to pass on the token to embedded's eventing mechanism.
   * 			@token : Device token received from APNs
   * 		successHandler(request, response)
   * 			Success callback for registration on Android. 
   *            This is merely a success callback for a successful attempt to register - no token is received from GCM at this point.
   * 		errorHandler(request, error)
   * 			Failure callback for registration. Right now not utilized but will be hooked to error handling callbacks in Embedded when they are available.
   * 			@error Error
   * 		onNotificationAPN(notification)
   * 			Called by PushPlugin upon a notification from APNs. Here we use adf.mf.api.invokeMethod to pass on the notification to Embedded's eventing mechanism 
   * 			@notification  JSON object representing notification payload
   * 		onNotificationGCM(notification)
   * 			General event callback used by PushPlugin on Android to report :
   * 				- token upon successful registration with GCM
   * 				- incoming notification event from GCM
   * 				- errors if any
   * 			Calls into tokenHandler/onNotificationAPN in order to pass on data to embedded's eventing mechanism depending on the event type.
   * 			@notification JSON notification event with following keys
   * 				event   : If "registered", its a successful registration callback, if "message" its a notification event
   * 				payload : Value is JSON of notification payload if event is "message"
   * 				regid   : Value is a string representing registration ID issued by GCM in response to a registration request 
   */
  window.PushNotifications = function()
  {
    /* PUBLIC - Register for Push Notifications */
    this.register = function(params)
	{
	  if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
	  {
		adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "PushNotifications", "register", "invoking push notification command.");				
	  }
	  try
	  {
		var isAndroid = (navigator.userAgent.toLowerCase().indexOf("android") != -1);
		if (isAndroid)
		{
		  params['ecb'] = "adf.mf.internal.api.pushnotifications.onNotificationGCM";
		  container.internal.device.integration.PushNotifications.register(params, successHandler, errorHandler);
		} 
		else
		{
		  params['ecb'] = "adf.mf.internal.api.pushnotifications.onNotificationAPN";
		  container.internal.device.integration.PushNotifications.register(params, tokenHandler, errorHandler);
		}
	  }
	  catch(e)
	  {
		adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "adf.mf.internal.api.pushnotifications", "register","PushNotifications.register was not invoked (error="+adf.mf.util.stringify(e)+")");
	  }
	};

	/* PRIVATE - Gets called by PushPlugin on iOS after successful registration */
	var tokenHandler = function(request, token)
	{
	  adf.mf.api.invokeMethod("oracle.adfmf.framework.event.pushnotification.NativePushNotificationEventInterceptor", "receivedToken", token,
		function(req,res)
		{
		  //success
		  if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
          {
             adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.pushnotifications", "tokenHandler",
            		 "Inside the success callback for request " + adf.mf.util.stringify(req) + " with response "+adf.mf.util.stringify(res));
          }
		},
		function(req,res)
		{
		  //failure
		  adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "adf.mf.internal.api.pushnotifications", "tokenHandler",
				  "Inside the failure callback for request " + adf.mf.util.stringify(req) + " with response "+adf.mf.util.stringify(res));
		}
	  );
	};

	/* PRIVATE - Gets called by PushPlugin if there was an error trying to register*/
	var errorHandler = function(request,error)
	{
	  adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "adf.mf.internal.api.pushnotifications", "errorHandler",
			  "There was an error in processing this request : " + adf.mf.util.stringify(request) + ". Error : "+adf.mf.util.stringify(error));
	  adf.mf.api.invokeMethod("oracle.adfmf.framework.event.pushnotification.NativePushNotificationEventInterceptor", "receivedError", adf.mf.util.stringify(error),
		  		function(req,res)
		  		{
		  		  //success
		  		  if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
		  		  {
		  		    adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.pushnotifications", "errorHandler", 
		  		    		"Inside the success callback for request " + adf.mf.util.stringify(req) + " with response "+adf.mf.util.stringify(res));
		  		  }
		  		},
		  		function(req,res)
		  		{
		  		  //failure
		  		  adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "adf.mf.internal.api.pushnotifications", "errorHandler", 
		  				  "Inside the failure callback for request " + adf.mf.util.stringify(req) + " with response "+adf.mf.util.stringify(res));
		  		}
	  );
	};

	/* PRIVATE - Gets called by PushPlugin if the call to register on Android succeeded */
	var successHandler = function(request,response)
	{
	  if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
      {
        adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.pushnotifications", "successHandler",
        		"Request: " + adf.mf.util.stringify(request) + " Response: "+adf.mf.util.stringify(response));
      }
	};

	/* PRIVATE Gets called by PushPlugin on iOS when notification arrives */
	this.onNotificationAPN = function(notification)
	{
	  //appState
      var appState = 0;
      if(notification.triggersStartup === "true")
      {
    	appState = 1;  
      }
      else if(notification.foreground === "0")
	  {
	    appState = 2;
      }
      else if(notification.foreground === "1")
	  {
		appState = 3;
	  } 
      
      //token
      var token = "";
      if(notification.hasOwnProperty('deviceToken'))
      {
        token = notification.deviceToken;
      }
      
	  adf.mf.api.invokeMethod("oracle.adfmf.framework.event.pushnotification.NativePushNotificationEventInterceptor", "receivedRemoteNotification", JSON.stringify(notification), appState, token,
		function(req,res)
		{
		  //success
		  if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
          {
             adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.pushnotifications", "onNotificationAPN",
            		 "Inside the success callback for request " + adf.mf.util.stringify(req) + " with response "+adf.mf.util.stringify(res));
          }
		},
		function(req,res)
		{
	      //failure
		  adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "adf.mf.internal.api.pushnotifications", "onNotificationAPN", 
				  "Inside the failure callback for request " + adf.mf.util.stringify(req) + " with response "+adf.mf.util.stringify(res));
		}
	  );
	};

	/* PRIVATE Gets called by PushPlugin on Android when a notification event (registration success, registration failure, incoming notification) occurs */
	this.onNotificationGCM = function(notification)
	{
	  switch( notification.event )
	  {
	    case 'registered':
	      if (notification.regid.length > 0)
		  {
		    tokenHandler(null,notification.regid);
		  }
		  break;
		  
        case 'message':
          //appState
          var appState = 0;
		  if(notification.coldstart)
		  {
		    appState = 1;    
		  }
		  else if(notification.foreground)
		  {
		    appState = 3;
		  } 
		  else
		  {
		    appState = 2;  
		  }
		  
	      //token
	      var token = "";
	      if(notification.payload.hasOwnProperty('deviceToken'))
	      {
            token = notification.payload.deviceToken;
          }
	      
		  adf.mf.api.invokeMethod("oracle.adfmf.framework.event.pushnotification.NativePushNotificationEventInterceptor", "receivedRemoteNotification", JSON.stringify(notification.payload), appState, token,
		    function(req,res)
		    {
			   //success
			   if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
			   {
			      adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api.pushnotifications", "onNotificationGCM",
			    		  "Inside the success callback for request " + adf.mf.util.stringify(req) + " with response "+adf.mf.util.stringify(res));
			    }
		    },
			function(req,res)
			{
			  //failure
			  adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "adf.mf.internal.api.pushnotifications", "onNotificationGCM",
					  "Inside the failure callback for request " + adf.mf.util.stringify(req) + " with response "+adf.mf.util.stringify(res));
			}
		  );
		  break;

        case 'error':
          adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "adf.mf.internal.api.pushnotifications", "onNotificationGCM", 
  				  "Error Received : "+JSON.stringify(notification));
          errorHandler(null, notification.error);
		  break;

		default:
		  // Unknown, an event was received and we do not know what it is
		  break;
	  }
    };
  };
  adf.mf.internal.api.pushnotifications = new PushNotifications();
  
  /**
   * Local Notifications
   * 
   * @namespace
   */
  adf.mf.api.localnotification = 
  {
    /**
     * Schedule a local notification
     *
     * @param {Object} options - notification options
     * @param {string} options.title - notification title
     * @param {string} options.alert - notification alert
     * @param {Date} options.date - date at which notification needs to be triggered
     * @param {Number} options.badge - application icon will be badged by this number when notification is triggered
     * @param {string} options.sound - set it to 'SYSTEM_DEFAULT' to play the default system sound upon a notification
     * @param {string} options.vibration - set it to 'SYSTEM_DEFAULT' for default system vibration upon a notification
     * @param {Object} options.payload - custom payload to be sent via notification
     * @param {successCallback} scb - success callback
     * @param {errorCallback} ecb  - error callback 
     */
    add: function(options, scb, ecb)
    {
  	  var successHandler = function(request, response) 
  	  {
  	    if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
  		{
  		  adf.mf.log.Framework.logp(adf.mf.log.level.FINE,
  				  "adf.mf.api.localnotification.add",
  				  "successHandler", "Request: "
  				  + adf.mf.util.stringify(request)
  				  + " Response: "
  				  + adf.mf.util.stringify(response));
  	    }
  	    if (typeof(scb) == typeof(Function))
  	      scb(request,response);
  	  };

      var errorHandler = function(request,error)
      {
        adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "adf.mf.api.localnotification.add", "errorHandler",
        		"There was an error in processing this request : " + adf.mf.util.stringify(request) + ". Error : "+adf.mf.util.stringify(error));
        if (typeof(ecb) == typeof(Function))
          ecb(request, error);
      };
          
  	  if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
  	  {
  		adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "LocalNotification", "add", "invoking local notification command.");
  	  }
  	  
  	  try
  	  {
          container.internal.device.integration.LocalNotification.add(options, successHandler, errorHandler);
  	  }
  	  catch(e)
  	  {
  		adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "adf.mf.api.localnotification", "add","LocalNotification.add was not invoked (error="+adf.mf.util.stringify(e)+")");
  	  }
  	},
  	
    /**
     * Cancel a scheduled local notification
     *
     * @param {string} notificationId - id of the scheduled notification that needs to be cancelled
     * @param {successCallback} scb - success callback
     * @param {errorCallback} ecb - error callback 
     */
    cancel: function(notificationId, scb, ecb)
    {
      var successHandler = function(request, response) 
  	  {
  	    if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE)) 
  		{
  		  adf.mf.log.Framework.logp(adf.mf.log.level.FINE,
  				  "adf.mf.api.localnotification.cancel",
  				  "successHandler", "Request: "
  				  + adf.mf.util.stringify(request)
  				  + " Response: "
  				  + adf.mf.util.stringify(response));
  	    }
  	    if (typeof(scb) == typeof(Function))
  	      scb(request,response);
  	  };

      var errorHandler = function(request,error)
      {
        adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "adf.mf.api.localnotification.cancel", "errorHandler",
        		"There was an error in processing this request : " + adf.mf.util.stringify(request) + ". Error : "+adf.mf.util.stringify(error));
        if (typeof(ecb) == typeof(Function))        
          ecb(request, error);
      };
          
  	  if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
  	  {
  		adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "LocalNotification", "cancel", "invoking local notification command.");
  	  }
  	  try
  	  {
        container.internal.device.integration.LocalNotification.cancel(notificationId, successHandler, errorHandler);
  	  }
  	  catch(e)
  	  {
  		adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "adf.mf.api.localnotification", "cancel","LocalNotification.cancel was not invoked (error="+adf.mf.util.stringify(e)+")");
  	  }
    }
    
    /**
     * Success Callback
     *
     * @callback successCallback
     * @param {Object} request - request
     * @param {Object} response - response
     * @param {string} response.id - id of the notification
     */
    
    /**
     * Error Callback
     *
     * @callback errorCallback
     * @param {Object} request - request
     * @param {Object} response - response
     */    
  };
  
  /**
   * Internal API used for local notifications
   *
   * These methods are to be used only by the embedded java code
   */
  adf.mf.internal.api.localnotification = adf.mf.internal.api.localnotification || function()
  {
  };

  adf.mf.internal.api.localnotification.add = adf.mf.internal.api.localnotification.add || function(commId, requestId, params)
  { 
	  container.internal.device.integration.LocalNotification.add(params,
			  function(request,result) /* Success callback */
			  {
		  		var responseData = result.id || {};
		  		try
		  		{
		  			adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK, requestId, responseData, function() {}, function() {});
		  		}
		  		catch (se)
		  		{
		  			adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
		  					"adf.mf.internal.api.localnotification.add", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
		  		}
			  },
			  function(request,result) /* Error callback */
			  {
				  var responseData = result || {};
				  try 
				  {
					  adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, responseData, function() {}, function() {});
				  }
				  catch (se)
				  {
					  adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
							  "adf.mf.internal.api.localnotification.add", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
				  }
			  });
  };
  
  adf.mf.internal.api.localnotification.cancel = adf.mf.internal.api.localnotification.cancel || function(commId, requestId, params)
  { 
	  container.internal.device.integration.LocalNotification.cancel(params,
			  function(request,result) /* Success callback */
			  {
		  		var responseData = result.id || {};
		  		try
		  		{
		  			adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK, requestId, responseData, function() {}, function() {});
		  		}
		  		catch (se)
		  		{
		  			adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
		  					"adf.mf.internal.api.localnotification.cancel", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
		  		}
			  },
			  function(request,result) /* Error callback */
			  {
				  var responseData = result || {};
				  try 
				  {
					  adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, responseData, function() {}, function() {});
				  }
				  catch (se)
				  {
					  adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
							  "adf.mf.internal.api.localnotification.cancel", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
				  }
			  });
  };
  
  /**
   * Internal API used for sliding window plugins
   *
   * These methods are to be used only by the embedded java code
   */
 adf.mf.internal.api.slidingwindow = adf.mf.internal.api.slidingwindow || function()
 {
 };
 
 adf.mf.internal.api.slidingwindow.create =  adf.mf.internal.api.slidingwindow.create || function(commId, requestId, featureId)
 {
    container.internal.device.integration.SlidingWindow.create(featureId,
        function(request,result) /* Success callback */
        {
            var responseData = result || {};
             try
               {
                   adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK, requestId, responseData, function() {}, function() {});
               }
               catch (se)
               {
                   adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                          "adf.mf.internal.api.slidingwindow.create", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
               }
        },
       function(request,result) /* Error callback */
       {
           var responseData = result || {};
       try 
       {
           adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, responseData, function() {}, function() {});
       }
       catch (se)
       {
           adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                  "adf.mf.internal.api.slidingwindow.create", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
        }
    });
 };

 adf.mf.internal.api.slidingwindow.show =  adf.mf.internal.api.slidingwindow.show || function(commId, requestId, windowId, options)
 {
    container.internal.device.integration.SlidingWindow.show(windowId, options,
        function(request,result) /* Success callback */
        {
            var responseData = result || {};
             try
               {
                   adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK, requestId, "true", function() {}, function() {});
               }
               catch (se)
               {
                   adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                          " adf.mf.internal.api.slidingwindow.show", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
               }
        },
       function(request,result) /* Error callback */
       {
           var responseData = result || {};
       try 
       {
           adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, "false", function() {}, function() {});
       }
       catch (se)
       {
           adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                  " adf.mf.internal.api.slidingwindow.show", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
        }
    });
 };
 
  adf.mf.internal.api.slidingwindow.hide =  adf.mf.internal.api.slidingwindow.hide || function(commId, requestId, windowId)
 {
    container.internal.device.integration.SlidingWindow.hide(windowId, 
        function(request,result) /* Success callback */
        {
            var responseData = result || {};
             try
               {
                   adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK, requestId, "true", function() {}, function() {});
               }
               catch (se)
               {
                   adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                          " adf.mf.internal.api.slidingwindow.hide", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
               }
        },
       function(request,result) /* Error callback */
       {
           var responseData = result || {};
       try 
       {
           adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, "false", function() {}, function() {});
       }
       catch (se)
       {
           adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                  " adf.mf.internal.api.slidingwindow.hide", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
        }
    });
 };
 
  adf.mf.internal.api.slidingwindow.destroy =  adf.mf.internal.api.slidingwindow.destroy || function(commId, requestId, windowId)
 {
    container.internal.device.integration.SlidingWindow.destroy(windowId, 
        function(request,result) /* Success callback */
        {
            var responseData = result || {};
             try
               {
                   adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK, requestId, "true", function() {}, function() {});
               }
               catch (se)
               {
                   adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                          " adf.mf.internal.api.slidingwindow.destroy", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
               }
        },
       function(request,result) /* Error callback */
       {
           var responseData = result || {};
       try 
       {
           adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, "false", function() {}, function() {});
       }
       catch (se)
       {
           adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                  " adf.mf.internal.api.slidingwindow.destroy", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
        }
    });
 };
 
 adf.mf.internal.api.slidingwindow.getWindowInfo =  adf.mf.internal.api.slidingwindow.getWindowInfo || function(commId, requestId, windowId)
 {
    container.internal.device.integration.SlidingWindow.getWindowInfo(windowId, 
        function(request,result) /* Success callback */
        {
            var responseData = result || {};
             try
               {
                   adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK, requestId, responseData, function() {}, function() {});
               }
               catch (se)
               {
                   adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                          " adf.mf.internal.api.slidingwindow.destroy", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
               }
        },
       function(request,result) /* Error callback */
       {
           var responseData = result || {};
       try 
       {
           adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, responseData, function() {}, function() {});
       }
       catch (se)
       {
           adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                  " adf.mf.internal.api.slidingwindow.destroy", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
        }
    });
 };
 
 adf.mf.internal.api.slidingwindow.getWindowInfo =  adf.mf.internal.api.slidingwindow.getWindowInfo || function(commId, requestId, windowId)
 {
    container.internal.device.integration.SlidingWindow.getWindowInfo(windowId, 
        function(request,result) /* Success callback */
        {
            var responseData = result || {};
             try
               {
                   adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK, requestId, responseData, function() {}, function() {});
               }
               catch (se)
               {
                   adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                          " adf.mf.internal.api.slidingwindow.destroy", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
               }
        },
       function(request,result) /* Error callback */
       {
           var responseData = result || {};
       try 
       {
           adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, responseData, function() {}, function() {});
       }
       catch (se)
       {
           adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                  " adf.mf.internal.api.slidingwindow.destroy", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
        }
    });
 };
 
 adf.mf.internal.api.slidingwindow.getTopWindowId =  adf.mf.internal.api.slidingwindow.getTopWindowId || function(commId, requestId)
 {
    container.internal.device.integration.SlidingWindow.getTopWindowId( 
        function(request,result) /* Success callback */
        {
            var responseData = result || {};
             try
               {
                   adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK, requestId, responseData, function() {}, function() {});
               }
               catch (se)
               {
                   adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                          " adf.mf.internal.api.slidingwindow.getTopWindowId", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
               }
        },
       function(request,result) /* Error callback */
       {
           var responseData = result || {};
       try 
       {
           adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, responseData, function() {}, function() {});
       }
       catch (se)
       {
           adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                  " adf.mf.internal.api.slidingwindow.getTopWindowId", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
        }
    });
 };
 
 adf.mf.internal.api.slidingwindow.getWindowIds =  adf.mf.internal.api.slidingwindow.getWindowIds || function(commId, requestId)
 {
    container.internal.device.integration.SlidingWindow.getWindowIds( 
        function(request,result) /* Success callback */
        {
            var responseData = result || {};
             try
               {
                   adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK, requestId, responseData, function() {}, function() {});
               }
               catch (se)
               {
                   adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                          " adf.mf.internal.api.slidingwindow.getWindowIds", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
               }
        },
       function(request,result) /* Error callback */
       {
           var responseData = result || {};
       try 
       {
           adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, responseData, function() {}, function() {});
       }
       catch (se)
       {
           adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                  " adf.mf.internal.api.slidingwindow.getWindowIds", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
        }
    });
 };   
 
 adf.mf.internal.api.slidingwindow.getCurrentWindowId =  adf.mf.internal.api.slidingwindow.getCurrentWindowId || function(commId, requestId)
 {
    container.internal.device.integration.SlidingWindow.getCurrentWindowId( 
        function(request,result) /* Success callback */
        {
            var responseData = result || {};
             try
               {
                   adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_SUCCESS_CALLBACK, requestId, responseData, function() {}, function() {});
               }
               catch (se)
               {
                   adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                          " adf.mf.internal.api.slidingwindow.getCurrentWindowId", "ERROR_IN_REQUEST", ASYNC_SUCCESS_CALLBACK, se);
               }
        },
       function(request,result) /* Error callback */
       {
           var responseData = result || {};
       try 
       {
           adf.mf.internal.api.invokeMethod(commId, ADFMF_CONTAINER_UTILITIES_INTERNAL, ASYNC_FAILED_CALLBACK, requestId, responseData, function() {}, function() {});
       }
       catch (se)
       {
           adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                                  " adf.mf.internal.api.slidingwindow.getCurrentWindowId", "ERROR_IN_REQUEST", ASYNC_FAILED_CALLBACK, se);
        }
    });
 };   
 
  /**
  * PUBLIC FUNCTION used to set the status bar style on iOS
  *
  * e.g. adf.mf.api.setStatusBarStyle(style, callback)
  *
  * The style may be one of the following values
  *    "dark"   : A dark status bar, intended for use on light backgrounds.
  *    "light"  : A light status bar, intended for use on dark backgrounds.
  *
  * @param {string} style     the style to set.  either "dark" or "light"
  * @param {function(string)} callback - function is called back with the current style as the argument
  *                                      can be null
  *
  * Example:
  *      adf.mf.api.setStatusBarStyle("dark", function(style) { console.log("new style: " + style); });
  */
  adf.mf.api.setStatusBarStyle = function(style, callback)
  {
   Cordova.exec( function(result) { if (callback) { callback(result);} } , function() { }, "ADFMobileShell", "setStatusBarStyle", [style]);
  }

  /**
  * PUBLIC FUNCTION used to get the status bar style on iOS
  *
  * e.g. adf.mf.api.getStatusBarStyle(callback)
  *
  * The callback function will be invoked with a single string argument which will be one of
  *    "dark"   : A dark status bar, intended for use on light backgrounds.
  *    "light"  : A light status bar, intended for use on dark backgrounds.
  *
  * @param {function(string)} callback - function is called back with the current style as the argument
  *                                      can be null
  *
  * Example:
  *      adf.mf.api.getStatusBarStyle(function(style) { console.log("current style: " + style); });
  */
  adf.mf.api.getStatusBarStyle = function(callback)
  {
   Cordova.exec( function(result) { if (callback) { callback(result); } } , function() { }, "ADFMobileShell", "getStatusBarStyle", []);
  }
 
})();


/////////////////////////////////////// end of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/ContainerIntegration.js///////////////////////////////////////



/////////////////////////////////////// start of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/Adflog.js///////////////////////////////////////

/* Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved. */
/* ------------------- Adflog.js ---------------------- */
// moved to base.js
// TODO need to:
// - look for the @-requires messages in all of Bruces code to remove references to "Adflog" (don't think this particular one is required anywhere... maybe just bootstrap)
// - purge this file altogether but be careful to check that Ant doesn't try to reference it

/////////////////////////////////////// end of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/Adflog.js///////////////////////////////////////



/////////////////////////////////////// start of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/JavaScriptContext.js///////////////////////////////////////

/* Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved. */
/* ------------------- JavaScriptContext.js ---------------------- */
//@requires ELErrors
//@requires AdfPerfTiming


var adf                   = window.adf || {};
adf.mf                    = adf.mf || {};
adf.mf.api                = adf.mf.api || {};
adf.mf.el                 = adf.mf.el || {};
adf.mf.locale             = adf.mf.locale || {};
adf.mf.log                = adf.mf.log || {};
adf.mf.resource           = adf.mf.resource || {};
adf.mf.util               = adf.mf.util || {};

adf.mf.internal           = adf.mf.internal || {};
adf.mf.internal.api       = adf.mf.internal.api || {};
adf.mf.internal.el        = adf.mf.internal.el || {};
adf.mf.internal.el.parser = adf.mf.internal.el.parser || {};
adf.mf.internal.locale    = adf.mf.internal.locale || {};
adf.mf.internal.log       = adf.mf.internal.log || {};
adf.mf.internal.mb        = adf.mf.internal.mb || {};
adf.mf.internal.perf      = adf.mf.internal.perf || {};
adf.mf.internal.resource  = adf.mf.internal.resource || {};
adf.mf.internal.util      = adf.mf.internal.util || {};

/**
 * JavaScriptContext is defined here.  It is an internal object but depends on
 * a couple helper objects (JavaScriptFunctions and JavaScriptVariables) that
 * are defined here as well.  Since only the JavaScriptContext must be exposed
 * to other internal objects, it will have an adf.mf.internal.el namespace.  The
 * others are never exposed and are only local to this initialization function.
 */
(function() {
  /* private to the JavaScriptContext object */
  function JavaScriptFunctions() { /* implements FunctionMapper */
    /* map from function name to function implementation */
    this.map   = null;

    /* function */
    this.resolveFunction = function(/* String */ prefix, /* String */ localName) {
      if (this.map === null) {
        this.map = {};
      }
      return this.map[prefix + ":" + localName];
    };

    /* void */
    this.setFunction = function(/* String */ prefix, /* String */ localName, /* Function */ func) {
      if (this.map === null) {
        this.map = {};
      }
      this.map[prefix + ":" + localName] = func;
    };
  };

  /* private to the JavaScriptContext object */
  function JavaScriptVariables()  { /* implements VariableMapper */
    /* map from variable name to variable's value */
    this.map         = null;
    this.nextModLZWCode  = 0;

    /* adf.mf.el.ValueExpression */
    this.resolveVariable = function(/* String */ variable) {
      if (this.map === null) {
        this.map = {};
      }
      return this.map[variable];
    };

    /* adf.mf.el.ValueExpression */
    this.setVariable = function(/* String */ variable, /* value */ value) {
      if (this.map === null) {
        this.map = {};
      }

      this.map[variable] = value;

      return this.map[variable];
    };

    /* void */
    this.removeVariable = function(/* String */ variable) {
      if(this.map !== null) {
        if(this.map[variable] !== undefined) {
          delete this.map[variable];
        }
      }
    };

    /* void */
    this.clearWeakReferences = function() {
      if(this.map !== null) {
        for(var v in this.map) {
          if(this.map[v][adf.mf.internal.api.constants.WEAK_REFERENCE_PROPERTY] !== undefined) {
            if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINEST))
            {
             adf.mf.log.Framework.logp(adf.mf.log.level.FINEST,
              "JavaScriptVariables","clearWeakReferences",
              "removing weak ref - " + v);
            }
            delete this.map[v];
          }
        }
        this.nextModLZWCode = 0;
      }
    };

    /* String */
    this.resolveWeakReference = function(/* String */ variable) {
      var v = this.resolveVariable(variable);

      if(v != undefined) {
        return v[adf.mf.internal.api.constants.WEAK_REFERENCE_PROPERTY];
      }
      return undefined;
    };

    /* String */
    this.getWeakReference = function(/* string */ name) {
      if(this.map !== null) {
        for(var v in this.map) {
          var fqn = this.map[v][adf.mf.internal.api.constants.WEAK_REFERENCE_PROPERTY];

          if((fqn !== undefined) && (name.indexOf(fqn) == 0))
          {
            var wrn = v + name.substring(fqn.length);

            if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINEST))
            {
              adf.mf.log.Framework.logp(adf.mf.log.level.FINEST,
               "JavaScriptVariables", "getWeakReference",
               "just found weak ref - " + wrn);
            }

            return wrn;
          }
        }
      }
      return undefined;
    };

    /* String */
    this.findMatchingWeakReference = function(/* string */ name) {
      if(this.map !== null) {
        for(var v in this.map) {
          var fqn = this.map[v][adf.mf.internal.api.constants.WEAK_REFERENCE_PROPERTY];

          if((fqn !== undefined) && (name == fqn))
          {
            return v;
          }
        }
      }
      return undefined;
    };

    /* String */
    this.addCompressedReference = function(/* string */ reference)
    {
      var lzwk = this.findMatchingWeakReference(reference);

      if(lzwk == undefined) {
        var key  = adf.mf.internal.api.constants.WEAK_REFERENCE_PROPERTY;
        var wr  = {};

        wr[key] = reference;
        lzwk   = "_" + (this.nextModLZWCode++);

        if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINEST))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINEST,
           "JavaScriptVariables", "addCompressedReference",
           "just added a weak ref - " + wr);
        }
        this.setVariable(lzwk, wr);
      }

      return lzwk;
    };
  };

  /* Still internal but exposed to the other internal objects */
  adf.mf.internal.el.JavaScriptContext = function(/* ELResolver */ elResolver) { /* implements ELContext */
    this.context        = this;
    this.functions      = null;
    this.resolver       = (elResolver || null);
    this.variables      = null;
    this.queue         = null;
    this.vmchannel      = null;
    this.security       = null;
    this.modid         = (new Date()).getTime();

    /**
     * Get the modification id for the context.
     */
    /* int */
    this.getModId = function() {
      return this.modid;
    };

    /**
     * Update the modification id for the context.
     */
    /* void */
    this.updateModId = function() {
      this.modid = (new Date()).getTime();
    };

    /**
     * Returns the context object associated with the given key. The ELContext maintains a
     * collection of context objects relevant to the evaluation of an expression. These context
     * objects are used by ELResolvers. This method is used to retrieve the context with the given
     * key from the collection. By convention, the object returned will be of the type specified by
     * the key. However, this is not required and the key is used strictly as a unique identifier.
     *
     * @param key
     *        The unique identifier that was used to associate the context object with this
     *        ELContext.
     * @return The context object associated with the given key, or null if no such context was
     *      found.
     * @throws NullPointerException
     *         if key is null.
     */
    /* ELContext */
    this.getContext = function(/* Class */ key) {
      /* first check to make sure we have a context to give back */
      if((this.context === undefined) || (this.context === null)) {
        return null;
      }

      /* next check that they passed a key and if not return the root context */
      if(key === undefined)  {
        return this.context;
      }

      /* looks like we have everything so return the key'ed context */
      return this.context[key];
    };


    /**
     * Retrieves the ELResolver associated with this context. The ELContext maintains a reference to
     * the ELResolver that will be consulted to resolve variables and properties during an
     * expression evaluation. This method retrieves the reference to the resolver. Once an ELContext
     * is constructed, the reference to the ELResolver associated with the context cannot be
     * changed.
     *
     * @return The resolver to be consulted for variable and property resolution during expression
     *      evaluation.
     */
    /* ELResolver */
    this.getELResolver = function() {
      if (this.resolver === null) {
    this.resolver = new VariableResolver();

        /* add some scopes to the context */
        this.getVariableMapper().setVariable("viewScope",      {});
        this.getVariableMapper().setVariable("pageFlowScope",   {});
        this.getVariableMapper().setVariable("applicationScope", {});
        /*
        * Represents #{feature} implicit object that allows developers to access runtime state of the feature.
        * The name intentionally does not end with "Scope" as it is more of a context.
        * This constant is defined in Java in oracle.adfmf.Constants, and both names need to be kept in sync.
        */
        this.getVariableMapper().setVariable("feature",       {});
        this.getVariableMapper().setVariable("preferenceScope",  {});
        this.getVariableMapper().setVariable("validationScope",  {});
        this.getVariableMapper().setVariable("deviceScope", {"device": {}, "hardware": {"screen": {}}});
      }
      return this.resolver;
    };


    /**
     * Retrieves the FunctionMapper associated with this ELContext.
     *
     * @return The function mapper to be consulted for the resolution of EL functions.
     */
    /* FunctionMapper */
    this.getFunctionMapper = function() {
      if (this.functions === null) {
        this.functions = new JavaScriptFunctions();
      }
      return this.functions;
    };


    /**
     * Retrieves the VariableMapper associated with this ELContext.
     *
     * @return The variable mapper to be consulted for the resolution of EL variables.
     */
    /* VariableMapper */
    this.getVariableMapper = function() {
      if (this.variables === null) {
        this.variables = new JavaScriptVariables();
      }
      return this.variables;
    };


    /**
     * Returns whether an {@link ELResolver} has successfully resolved a given (base, property)
     * pair. The {@link CompositeELResolver} checks this property to determine whether it should
     * consider or skip other component resolvers.
     *
     * @return The variable mapper to be consulted for the resolution of EL variables.
     * @see CompositeELResolver
     */
    /* boolean */
    this.isPropertyResolved = function() {
      return this.resolved;
    };


    /**
     * Associates a context object with this ELContext. The ELContext maintains a collection of
     * context objects relevant to the evaluation of an expression. These context objects are used
     * by ELResolvers. This method is used to add a context object to that collection. By
     * convention, the contextObject will be of the type specified by the key. However, this is not
     * required and the key is used strictly as a unique identifier.
     *
     * @param key
     *        The key used by an {@link ELResolver} to identify this context object.
     * @param contextObject
     *        The context object to add to the collection.
     * @throws NullPointerException
     *         if key is null or contextObject is null.
     */
    /* void */
    this.putContext = function(/* Class */ key, /* object */ contextObject) {
      if((this.context === undefined) || (this.context === null)){
        this.context = {};
      }
      if((key === undefined) || (key === null)) {
        throw Error("invalid key");
      }

      if((contextObject === undefined) || (contextObject === null)) {
        throw Error("invalid context object");
      }

      this.context[key] = contextObject;
    };


    /* void */
    this.setELResolver = function(/* ELResolver */ resolver) {
      this.resolver = resolver;
    };


    /* void */
    this.setFunction = function(/* String */ prefix, /* String */ localName, /* Function */ func) {
      if (this.functions === null) {
        this.functions = new JavaScriptFunctions();
      }
      this.functions.setFunction(prefix, localName, func);
    };


    /**
     * Called to indicate that a ELResolver has successfully resolved a given (base, property) pair.
     * The {@link CompositeELResolver} checks this property to determine whether it should consider
     * or skip other component resolvers.
     *
     * @param resolved
     *        true if the property has been resolved, or false if not.
     * @see CompositeELResolver
     */
    /* void */
    this.setPropertyResolved = function(/* boolean */ resolved) {
      this.resolved = resolved;
    };



    /* void */
    this.clearWeakReferences = function() {
      if (this.variables === null) {
        this.variables = new JavaScriptVariables();
      }
      return this.variables.clearWeakReferences();
    };

    /* String */
    this.getWeakReference = function(/* String */ name) {
      if (this.variables === null) {
        this.variables = new JavaScriptVariables();
      }

      return this.variables.getWeakReference(name);
    };


    /* String */
    this.addCompressedReference = function(/* String */ name) {
      if (this.variables === null) {
        this.variables = new JavaScriptVariables();
      }

      return this.variables.addCompressedReference(name);
    };

    this.uncompressReference = function(/* ELExpression */ elExpression)
    {
      try
      {
        if (this.variables == null)
        {
          return elExpression;
        }

        var token = elExpression.tokens [0];
        var result = this.variables.resolveWeakReference(token.index);

        if (!result)
        {
          return elExpression;
        }

        if (result == token.index)
        {
          return elExpression;
        }

        var replacement = {};
        replacement[token.index] = result;

        return elExpression.stripLocalValues(false, replacement, false);
      }
      catch(err)
      {
        return elExpression;
      }
    };

    /* object */
    this.getVariable = function(/* String */ name) {
      if (this.variables === null) {
        this.variables = new JavaScriptVariables();
      }

      return this.variables.resolveVariable(name);
    };


    /* void */
    this.setVariable = function(/* String */ name, /* adf.mf.el.ValueExpression */ expression) {
      if (this.variables === null) {
        this.variables = new JavaScriptVariables();
      }
      // adf.mf.internal.perf.trace("JavaScriptContext.setVariable", name);
      return this.variables.setVariable(name, expression);
    };


    /* void */
    this.removeVariable = function(/* String */ name) {
      if (this.variables === null) {
        this.variables = new JavaScriptVariables();
      }
      // adf.mf.internal.perf.trace("JavaScriptContext.removeVariable", name);
      return this.variables.removeVariable(name);
    };


       /* void */
   this.pushVariable = function(/* String */name, /* adf.mf.el.ValueExpression */expression) {
    var prevValue = undefined;

    if (this.variables === null) {
      this.variables = new JavaScriptVariables();
    }

    prevValue = this.variables.resolveVariable(name);

    if (this.queue === null) {
      this.queue = [];
    }

    if (prevValue != null) {
      this.queue[name] = this.queue[name] || [];

      this.queue[name].push(prevValue);
    }

    return this.variables.setVariable(name, expression);
   };

   /* void */
   this.popVariable = function(/* String */name) {
    if (this.variables === null) {
      this.variables = new JavaScriptVariables();
    }

    if (this.queue === null) {
      this.queue = [];
    }

    if ((q = this.queue[name]) != null) {
      var v;

      if((v = q.pop()) != null)
      {
       this.setVariable(name, v);
      }
      else
      {
       if (q.length == 0) {
        delete this.queue[name];
        this.variables.removeVariable(name);
       }
      }
    }
   };



    /* void */
    this.invokeJavaMethod = function(/* Communication channel Id */ commId, request, success, failed) {
      if(this.vmchannel === null) {
        this.vmchannel  = new adf.mf.internal.VMChannel(this);
      }

      if (commId != undefined)
      {
        request.featureId = commId;
      }
      this.vmchannel.nonBlockingCall(request, success, failed);
    };

    /* void */
    this.invokeSecurityMethod = function(command, username, password, tenantname, success, failed) {
      if(this.security === null) {
        //this.security  = new Security(this);
        this.security  = new adf.mf.security(this);
      }
      var fm = this.getFunctionMapper();
      var f  = fm.resolveFunction("Security", command);
      return f.call(undefined, username, password, tenantname, success, failed);
    };

    /* String */
    this.toString = function() {
      return "[ ELContext: " + this.context + " ]";
    };
  };


  function VariableResolver() {
  }

  adf.mf.internal.el.VariableResolver = VariableResolver;

  /**
  * Attempts to resolve the given property object on the given base object.
  *
  * If the base object is null or undefined, delegates to
  * context.getVariableMapper().resolveVariable(property).
  *
  * If the base object is a Java language array, returns the value at the given index. The index
  * is specified by the property argument, and coerced into an integer. If the coercion could not
  * be performed, an IllegalArgumentException is thrown. If the index is out of bounds, null is
  * returned.
  *
  * If the base object is a map, returns the value associated with the given key, as specified by
  * the property argument. If the key was not found, null is returned. Just as in
  * java.util.Map.get(Object), just because null is returned doesn't mean there
  * is no mapping for the key; it's also possible that the Map explicitly maps
  * the key to null.
  *
  *
  * @param context
  *        The context of this evaluation.
  * @param base
  *        The base object to return the most general property type for, or null to enumerate
  *        the set of top-level variables that this resolver can evaluate.
  * @param property
  *        The property or variable to return the acceptable type for.
  * @return If the propertyResolved property of ELContext was set to true, then the result of the
  *      variable or property resolution; otherwise undefined.
  * @throws NullPointerException
  *         if context is null
  * @throws PropertyNotFoundException
  *         if base is not null and the specified property does not exist or is not readable.
  * @throws ELException
  *         if an exception was thrown while performing the property or variable resolution.
  *         The thrown exception must be included as the cause property of this exception, if
  *         available.
  */
  /* object */
  VariableResolver.prototype.getValue = function(/* ELContext */ context, /* object */ base, /* object */ property) {
   var result = undefined;
   if ((base === undefined) || (base === null)) {
    // Root Property EL Resolver
    var variables = context.getVariableMapper();

    try {
      result = variables.resolveVariable(property);

      if ((result instanceof Object) &&
       (result[adf.mf.internal.api.constants.WEAK_REFERENCE_PROPERTY] !== undefined))
      {
       var ref = result[adf.mf.internal.api.constants.WEAK_REFERENCE_PROPERTY];
       var p = adf.mf.internal.el.parser.parse("#{" + ref + "}");
       try {
        result = p.evaluate(context);
       } catch (e) {
        result = {};
        p.setValue(context, result);
       }
      }
    } catch (e) {
    }
   } else
   if (base instanceof Array) {
    // Array EL Resolver
    result = base[property];
   } else
   if ((typeof base) === 'object') {
    var baseType = base['.type'];
    if (baseType === 'Attribute') {
      // Attribute EL Resolver
      if (property == "bindings")
      {
       result = new adf.mf.internal.el.AttributeBinding(base);
      }
      else if (property == 'inputValue')
      {
       /* getInputValue */
       result = base.getPropertyInputValue(property);
      }
      else
      {
       result = base.getProperty(property);
      }
    } else
    if (baseType === 'AttributeBinding') {
      // Attribute Binding EL Resolver
      result = new adf.mf.internal.el.Attribute(base, property);
    } else
    if (baseType === 'TreeBindings') {
      // Tree Binding EL Resolver
      /* special cases:
      *   inputValue: need to read to the provider's object and not here
      */
      if (property == 'iterator')
      {
       try
       {
        result = new adf.mf.el.TreeNodeIterator(base, 0);

        if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINEST))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINEST,
           "adf.mf.internal.el", "TreeBindingsELResolver",
           "resolved iterator with " + result.getCachedRowCount(0) + " rows cached.");
        }
       }
       catch (ie)
       {
        if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINEST))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINEST,
           "adf.mf.internal.el", "TreeBindingsELResolver",
           "resolving the iterator resulted in an exception: " + ie);
        }
       }
      }
      else if (base[property] !== undefined)
      {
       result = base[property];
      }
      else
      {
       var ab = base.columnAttributes() || {};
       result = ab[property];
      }
    } else
    if (baseType === 'oracle.adfmf.bindings.dbf.TreeNode') {
      // TreeNode EL Resolver
      if (property == "bindings")
      {
       result = new adf.mf.internal.el.AttributeBinding(base);
      }
      else if (property.toLowerCase() == "rowkey")
      {
       result = base.rowKey();
      }
      else if (property == "dataProvider")
      {
       result = base.getProvider();
      }
      else
      {
       var dp = base.getProvider() || {};
       result = dp[property];
                           // if it is a row, attempt to resolve row.bindings.property.inputValue
                          if (result == undefined && dp[".type"] === 'row')
                          {
                           result = VariableResolver.getValueFromRow(dp, property);
                          }
      }
    } else
    if (baseType === 'OptionalFragmentArgument') {
      // Optional Fragment Argument EL Resolver
      result = base;
    } else {
      // Map EL Resolver
      /* first attempt to get the property */
      result = base[property];

                      // if it is a row, attempt to resolve row.bindings.property.inputValue
                      if (result == undefined && baseType === 'row')
                      {
                       result = VariableResolver.getValueFromRow(base,property);
                      }
    }
   } // (typeof base) == 'object'
   if (result === undefined) {
    throw new adf.mf.PropertyNotFoundException();
   }
   return result;
  };

      /**
      * Resolves the EL #{row.property} to #{row.bindings.property.inputValue}
      */
      VariableResolver.getValueFromRow = function(row, property)
      {
       var bindings = row["bindings"];
       if (bindings && typeof bindings == 'object')
       {
        var attr = bindings[property];
        if (attr && typeof attr == 'object')
        {
          return attr["inputValue"];
        }
       }
       return undefined;
      }

      /**
      * Set on the EL #{row.property} will be set to #{row.bindings.property.inputValue}
      */
      VariableResolver.setValueInRow = function(row, property, value)
      {
       var bindings = row["bindings"];
       if (bindings && typeof bindings == 'object')
       {
        var attr = bindings[property];
        if (attr && typeof attr == 'object')
        {
          attr["inputValue"] = value;
        }
       }
      }

  /**
  * Attempts to set the value of the given property object on the given base object.
  *
  * If the base object is null or undefined, delegates to
  * context.getVariableMapper().setVariable(property, value).
  *
  * If the base object is a Java language array, attempts to set the value at the given index
  * with the given value. The index is specified by the property argument, and coerced into an
  * integer. If the coercion could not be performed, an IllegalArgumentException is thrown. If
  * the index is out of bounds, a PropertyNotFoundException is thrown.
  *
  * If the base object is a map, attempts to set the value associated with the given key, as
  * specified by the property argument.
  *
  * @param context
  *        The context of this evaluation.
  * @param base
  *        The base object to return the most general property type for, or null to enumerate
  *        the set of top-level variables that this resolver can evaluate.
  * @param property
  *        The property or variable to return the acceptable type for.
  * @param value
  *        The value to set the property or variable to.
  * @throws NullPointerException
  *         if context is null
  * @throws PropertyNotFoundException
  *         if base is not null and the specified property does not exist or is not readable.
  * @throws PropertyNotWritableException
  *         if the given (base, property) pair is handled by this ELResolver but the
  *         specified variable or property is not writable.
  * @throws ELException
  *         if an exception was thrown while attempting to set the property or variable. The
  *         thrown exception must be included as the cause property of this exception, if
  *         available.
  */
  /* void */
  VariableResolver.prototype.setValue = function(/* ELContext */ context, /* object */ base, /* object */ property, /* object */ value) {
   if ((base === undefined) || (base === null)) {
    // Root Property EL Resolver
    context.getVariableMapper().setVariable(property, value);
   } else
   if (base instanceof Array) {
    // Array EL Resolver
    base[property] = value;
   } else
   if ((typeof base) == 'object') {
    var baseType = base['.type'];
    if (baseType == 'Attribute') {
      // Attribute EL Resolver
      if (property == 'inputValue')
      {
       base.setPropertyInputValue(value);
      }
      else
      {
       base.setProperty(property, value);
      }
    } else
    if (baseType == 'AttributeBinding') {
      // Attribute Binding EL Resolver
      base[property] = value;
    } else
    if (baseType == 'TreeBindings') {
      // Tree Binding EL Resolver
      /* special cases:
      *   inputValue: need to written to the provider's object and not here
      */
      if (property == 'iterator')
      {
       throw new adf.mf.PropertyNotWritableException("resolver is read-only");
      }
      else if (base[property] !== undefined)
      {
       base[property] = value;
      }
      else
      {
       if ((typeof value) !== 'AttributeBindings')
       {
        throw new adf.mf.IllegalArgumentException("value is not a AttributeBindings object");
       }

       var ab = base.columnAttributes() || {};
       ab[property] = value;
      }
    } else
    if (baseType == 'oracle.adfmf.bindings.dbf.TreeNode') {
      // TreeNode EL Resolver
      if (property == "bindings")
      {
       base.bindings = value;
      }
      else if (property == "dataProvider")
      {
       base.dataProvider = value;
      }
      else if(property == "rowKey")
      {
       throw new adf.mf.PropertyNotWritableException("rowKey is read-only");
      }
      else
      {
       var dp = base.getProvider() || {};
                           if (dp[".type"] == "row")
                           {
                            VariableResolver.setValueInRow(dp, property, value);
                           }
                           else
                           {
                            dp[property] = value;
                           }
      }
    } else
    if (baseType == 'OptionalFragmentArgument') {
      // Optional Fragment Argument EL Resolver
    } else
                if (baseType == "row")
                {
                  VariableResolver.setValueInRow(base, property, value);
                }else {
      // Map EL Resolver
      base[property] = value;
    }
   }
  };


  adf.mf.internal.el.Attribute = function(/* AttributeBinding */ ab, /* string */ name)
  {
   this['ab'] = ab;
   this['.type'] = 'Attribute';
   this['.name'] = name;

   this.toString = function()
   {
    return "Attribute[" + adf.mf.util.stringify(this.getPropertyInputValue()) + "]";
   };

   this.getProperty = function(/* string */ name)
   {
    var bindings = undefined;
    var property = undefined;

    try
    {
      bindings = this.ab.getBindings();
      property = bindings[this['.name']];
    }
    catch (e)
    {
      throw new adf.mf.PropertyNotFoundException('unknown property ' + this['.name']);
    }

    return property[name];
   };

   this.getPropertyInputValue = function()
   {
    if ((ab !== undefined) && (ab.tn !== undefined))
    {
      return '' + adf.mf.util.stringify(this.ab.tn.getProvider()[this['.name']]);
    }
    else
    {
      throw new adf.mf.PropertyNotFoundException('unknown property ' + this['.name']);
    }
   };

   this.setProperty = function(/* string */ name, /* object */ value)
   {
    var bindings = this.ab.getBindings();
    var property = bindings[this['.name']];

    property[name] = value;
   };

   this.setPropertyInputValue = function(/* object */ value)
   {
    if ((ab !== undefined) && (ab.tn !== undefined))
    {
      var p = this.ab.tn.getProvider();

      p[this['.name']] = value;
    }
    else
    {
      throw new adf.mf.PropertyNotFoundException('unknown property ' + this['_name']);
    }
   };
  };

  adf.mf.internal.el.AttributeBinding = function(/* TreeNode */ tn)
  {
   this['.type'] = 'AttributeBinding';
   this.tn = tn;

   this.getBindings = function()
   {
    return tn.getBindings();
   };

   this.getProvider = function()
   {
    return tn.getProvider();
   };

   this.toString = function()
   {
    return "Attribute Bindings";
   };
  };

  adf.mf.internal.el.OptionalFragmentArgument = function()
  {
   this['.type']  = 'OptionalFragmentArgument';

    this.toString   = function()
    {
      return "OptionalFragmentArgument";
    };

    this.getProperty = function(/* string */ name)
   {
      return this;
   };

    this.setProperty = function(/* string */ name, /* object */ value)
   {
      /* ignore */
   };
  };
  adf.mf.api.OptionalFragmentArgument = adf.mf.api.OptionalFragmentArgument || new adf.mf.internal.el.OptionalFragmentArgument;

  adf.mf.internal.VMChannel = function(/* Context */ context) {
    /**
     * blockingCall(java-class-name, java-class-method-name, success-callback, failed-callback, arguments);
     */
    this.blockingCall = function(request, success, failed) {
      throw adf.mf.UnsupportedOperationException("blocking calls are not supported in this version");
    };


    this.nonBlockingCall= function(request, success, failed) {
      var scb  = [];
      var fcb  = [];
      var op   = request.classname + ":" + request.method;

      if(adf.mf.internal.batchRequest !== undefined)
      {
        var deferedObject = {};

        /* configure up the success and failed callback vectors */
        scb = scb.concat(adf.mf.internal.util.is_array(success)? success : [success]);
        fcb = fcb.concat(adf.mf.internal.util.is_array(failed)?  failed  : [failed ]);

        adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "adf.mf.internal.VMChannel", "nonBlockingCall",
        "appending request on the batch request - actual request is being defered.");
        adf.mf.internal.batchRequest.push(request);

        deferedObject[adf.mf.internal.api.constants.DEFERRED_PROPERTY];

        for(var i = 0; i < scb.length; ++i)
        {
          try
          {
            scb[i](request, deferedObject);
          }
          catch(se)
          {
            /* nothing we can do */
          }
        }
      }
      else
      {
        var perf = adf.mf.internal.perf.start("adf.mf.internal.VMChannel.nonBlockingCall", op);

        /* configure up the success and failed callback vectors */
        scb = scb.concat([function() { perf.stop(); }]);
        scb = scb.concat(adf.mf.internal.util.is_array(success)? success : [success]);

        fcb = fcb.concat([function() { perf.stop(); }]);
        fcb = fcb.concat(adf.mf.internal.errorHandlers);
        fcb = fcb.concat(adf.mf.internal.util.is_array(failed)?  failed  : [failed ]);

        try
        {

          container.internal.device.integration.vmchannel.invoke(10000, request, scb, fcb);
        }
        catch(e)
        {
          if((! adf.mf.internal.isJavaAvailable()) || (e.name == "TypeError") || (e.name == "ReferenceError"))
          {  // this is when navigator, container.internal.device.integration, or container.internal.device.integration.vmchannel is missing.
            e = new adf.mf.NoChannelAvailableException();
          }

          for(var i = 0; i < fcb.length; ++i)
          {
            try
            {
              fcb[i](request, e);
            }
            catch(fe)
            {
              /* nothing we can do */
            }
          }
        }
      }
    };

    context.setFunction("VMChannel", "blockingCall",   this.blockingCall);
    context.setFunction("VMChannel", "nonBlockingCall", this.nonBlockingCall);
  };

  adf.mf.security = function(/* Context */ context) {
    this.login = function(username, password, tenantname) {
      if((container.internal.device.integration !== undefined) && (container.internal.device.integration.Security !== undefined))
      {
        container.internal.device.integration.Security.featureLogin(username, password, tenantname);
      }
      else
      {
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "Security", "login",
              ("adfmf - invoking the Security command "));
        }
      }
    };

    this.logout= function() {
      if((container.internal.device.integration !== undefined) && (container.internal.device.integration.Security !== undefined))
      {
        container.internal.device.integration.Security.featureLogout();
      }
      else
      {
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "Security", "logout",
              ("adfmf - invoking the Security command "));
        }
      }
    };

    this.isAuthenticated = function() {
      if((container.internal.device.integration !== undefined) && (container.internal.device.integration.Security !== undefined))
      {
        container.internal.device.integration.Security.featureIsAuthenticated();
      }
      else
      {
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "Security", "isAuthenticated",
              ("adfmf - invoking the Security command "));
        }
      }
    };

    this.cancelLogin = function() {
      if((container.internal.device.integration !== undefined) && (container.internal.device.integration.Security !== undefined))
      {
        container.internal.device.integration.Security.cancelLogin();
      }
      else
      {
        console.log("adfmf - invoking the Security command ");
      }
    };

    this.isConnectionMultiTenantAware = function(username, password, tenantname, callback) {
      if((container.internal.device.integration !== undefined) && (container.internal.device.integration.Security !== undefined))
      {
        container.internal.device.integration.Security.featureIsConnectionMultiTenantAware(callback);
      }
      else
      {
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "Security", "isConnectionMultiTenantAware",
              ("adfmf - invoking the Security command "));
        }
      }
    };

    this.getMultiTenantUsername = function(username, password, tenantname, callback) {
      if((container.internal.device.integration !== undefined) && (container.internal.device.integration.Security !== undefined))
      {
        container.internal.device.integration.Security.featureGetMultiTenantUsername(callback);
      }
      else
      {
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "Security", "getMultiTenantUsername",
              ("adfmf - invoking the Security command "));
        }
      }
    };

    this.getLoginViewInitData = function(username, password, tenantname, success, failed) {
      try
      {
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "Security", "getLoginViewInitData", "invoking security command.");
        }

        container.internal.device.integration.Security.getLoginViewInitData(success, failed);
      }
      catch(e)
      {
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.WARNING))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.WARNING, "Security", "getLoginViewInitData",
                            ("Security.getLoginViewInitData was not invoked (error="+e+")"));
        }
      }
    };

    context.setFunction("Security", "cancelLogin",            this.cancelLogin);
    context.setFunction("Security", "login",                this.login);
    context.setFunction("Security", "logout",                this.logout);
    context.setFunction("Security", "isAuthenticated",          this.isAuthenticated);
    context.setFunction("Security", "isConnectionMultiTenantAware", this.isConnectionMultiTenantAware);
    context.setFunction("Security", "getMultiTenantUsername",     this.getMultiTenantUsername);
    context.setFunction("Security", "getLoginViewInitData",      this.getLoginViewInitData);
  };
})();


/////////////////////////////////////// end of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/JavaScriptContext.js///////////////////////////////////////



/////////////////////////////////////// start of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/AdfLocale.js///////////////////////////////////////

/* Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved. */
/* ------------------- AdfLocale.js ---------------------- */
// moved to base.js
// TODO need to:
// - look for the @-requires messages in all of Bruces code to remove references to "AdfLocale"
// - purge this file altogether but be careful to check that Ant doesn't try to reference it

/////////////////////////////////////// end of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/AdfLocale.js///////////////////////////////////////



/////////////////////////////////////// start of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/AdfResource.js///////////////////////////////////////

/* Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved. */
/* ------------------- AdfResource.js ---------------------- */
// moved to base.js
// TODO need to:
// - look for the @-requires messages in all of Bruces code to remove references to "AdfResource"
// - purge this file altogether but be careful to check that Ant doesn't try to reference it

/////////////////////////////////////// end of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/AdfResource.js///////////////////////////////////////



/////////////////////////////////////// start of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/AdfPerfTiming.js///////////////////////////////////////

/* Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved. */
/* ------------------- AdfPerfTiming.js ---------------------- */
// @requires Adflog
// @requires ELErrors

var adf                    = window.adf                 || {};
adf.mf                     = adf.mf                     || {};
adf.mf.api                 = adf.mf.api                 || {};
adf.mf.el                  = adf.mf.el                  || {};
adf.mf.locale              = adf.mf.locale              || {};
adf.mf.log                 = adf.mf.log                 || {};
adf.mf.resource            = adf.mf.resource            || {};
adf.mf.util                = adf.mf.util                || {};

adf.mf.internal            = adf.mf.internal            || {};
adf.mf.internal.api        = adf.mf.internal.api        || {};
adf.mf.internal.el         = adf.mf.internal.el         || {};
adf.mf.internal.el.parser  = adf.mf.internal.el.parser  || {};
adf.mf.internal.locale     = adf.mf.internal.locale     || {};
adf.mf.internal.log        = adf.mf.internal.log        || {};
adf.mf.internal.mb         = adf.mf.internal.mb         || {};
adf.mf.internal.perf       = adf.mf.internal.perf       || {};
adf.mf.internal.resource   = adf.mf.internal.resource   || {};
adf.mf.internal.util       = adf.mf.internal.util       || {};

/**
 * adf.mf.internal.perf consists of a set of javascript functions
 * to instrument the adf.mf javascript sub-systems
 *
 * HOW-TO USE THIS:
 * 1. Declare the performance logger
 *
 *    #configure the performance logger to only use the adfmf ConsoleHandler
 *    oracle.adfmf.performance.level=FINER
 *    oracle.adfmf.performance.useParentHandlers=false
 *    oracle.adfmf.performance.handlers=oracle.adfmf.util.logging.ConsoleHandler
 */
(function()
{
  // create a performance logger that can be used in the javascript
  var perfLogger = adf.mf.log.Performance = adf.mf.log.Performance ||
    new adf.mf.log.logger("oracle.adfmf.performance");

  // ============================================================================================
  // Private methods and variables

  // Uncomment the following lines, in order to obtain performance numbers for start up.
  //perfLogger.init(adf.mf.log.level.FINE,
  //  "[%LEVEL% - %LOGGER% - %CLASS% - %METHOD%] %MESSAGE%");

  var FINEST = adf.mf.log.level.FINEST;
  var FINER = adf.mf.log.level.FINER;
  var FINE = adf.mf.log.level.FINE;

  var noop = function() {};
  var Assert = adf.mf.api.AdfAssert;

  /////////////////////////////////////////////////////////////////////////////////////////////////
  // Task object (base class)
  function Task(name)
  {
    this.Init(name);
  }

  adf.mf.api.AdfObject.createSubclass(Task, adf.mf.api.AdfObject, "Task");

  Task.InitClass = function()
  {
    // Used to keep a unique ID per task to identify it
    this._nextId = 1;
    // Tasks that are currently running
    this._activeTasks = [];
    this.VisitResult = { "ACCEPT": 0, "REJECT": 1, "COMPLETE": 2 };
  };

  /**
   * Get the most recent task that was started
   */
  Task.getActiveTask = function()
  {
    return (Task._activeTasks.length == 0) ?
      null : Task._activeTasks[Task._activeTasks.length - 1];
  };

  Task.prototype.Init = function(name)
  {
    this._id = Task._nextId++;
    this._name = name;
    this._parent = null;
    this._tasks = [];
    this._start = null;
    this._stop = null;
    this._tasksCompletedAt = null;
    this._activeTaskCount = 0;
  };

  Task.prototype.getIdentifier = function()
  {
    // Lazily create it for performance as we won't always need it
    if (this._identifier == null)
    {
      this._identifier = this.constructor.name + "[" + this._id + "/" + this._name + "]";
    }

    return this._identifier;
  };

  /**
   * Get the count of the tasks that are still running
   * @return {Number} the active count
   */
  Task.prototype.getActiveTaskCount = function()
  {
    return this._activeTaskCount;
  };

  /**
   * @return {Number} the unique ID of the task
   */
  Task.prototype.getId = function()
  {
    return this._id;
  };

  /**
   * @return {string} the friendly name of the task
   */
  Task.prototype.getName = function()
  {
    return this._name;
  };

  /**
   * @return {Task} the parent or null if a top level task
   */
  Task.prototype.getParent = function()
  {
    return this._parent;
  };

  /**
   * @return {Operation} the closest parent operation or null if none
   */
  Task.prototype.getParentOperation = function()
  {
    for (var p = this._parent; p != null; p = p.getParent())
    {
      if (p instanceof Operation)
      {
        return p;
      }
    }

    return null;
  };

  /**
   * Add a child
   * @param {Task} task the child
   */
  Task.prototype.addChildTask = function(task)
  {
    if (this._stop != null)
    {
      Assert.assert(false,
        "addChildTask called on a stopped task: " + this.getIdentifier() +
        ", attempting to add " + task.getIdentifier());
    }
    this._tasks.push(task);
    ++this._activeTaskCount;
  };

  /**
   * Start the task running
   */
  Task.prototype.start = function()
  {
    if (this._start != null)
    {
      Assert.assert(
        false,
        "start called on a task that was already started: " + this.getIdentifier());
    }

    this._start = (new Date()).getTime();
    this._parent = Task.getActiveTask();

    if (this._parent != null)
    {
      this._parent.addChildTask(this);
    }

    Task._activeTasks.push(this);

    if (perfLogger.isLoggable(FINEST))
    {
      perfLogger.logp(FINEST,
        "adf.mf.internal.perf.Task", "start",
        "Task started. " + this.getIdentifier() + " Parent: " +
        (this._parent == null ? "None" : this._parent.getIdentifier()) +
        " at " + this._start);
    }

    return this;
  };

  /**
   * Stop the task (may not be yet completed if there are children tasks still running)
   */
  Task.prototype.stop = function()
  {
    try
    {
      if (this._stop != null)
      {
        Assert.assert(
          false,
          "stop called on a task that was already stopped: " + this.getIdentifier());
      }

      this._stop = (new Date()).getTime();
      if (perfLogger.isLoggable(FINEST))
      {
        perfLogger.logp(FINEST,
          "adf.mf.internal.perf.Task", "stop",
          "Task stopped. " + this.getIdentifier() + " at " + this._stop +
          ". Elapsed: " + this.getElapsed());
      }

      // Notify the sub-class
      this.Stopped();

      // See if this task is now complete
      this._checkComplete();
    }
    catch (e)
    {
      // Eat any exceptions in the performance code to prevent issues loading the page
      if (perfLogger.isLoggable(FINE))
      {
        perfLogger.logp(FINEST,
          "adf.mf.internal.perf.Task", "stop",
          "Exception thrown " + e.message);
      }
      return { "stop": noop };
    }
  };

  /**
   * @return {Number} the start time (ms) of the task or null if not started
   */
  Task.prototype.getStart = function()
  {
    return this._start;
  };

  /**
   * @return {Number} the stop time (ms) of the task or null if not yet stopped.
   */
  Task.prototype.getStop = function()
  {
    return this._stop;
  };

  /**
   * @return {Number} the time (ms) between the start and stop times (may be null if not yet stopped)
   */
  Task.prototype.getElapsed = function()
  {
    return (this._start == null || this._stop == null) ?
      null : (this._stop - this._start);
  };

  /**
   * @return {Number} the time (ms) between the start and when the task was completed (all children
   * completed)
   */
  Task.prototype.getTotalElapsed = function()
  {
    return (this._start == null || this._tasksCompletedAt == null) ?
      null : (this._tasksCompletedAt - this._start);
  };

  /**
   * @return {Number} the time (ms) of the task completion or null if not yet stopped or children
   * tasks are still running
   */
  Task.prototype.getTasksCompletedAt = function()
  {
    return this._tasksCompletedAt;
  };

  /**
   * Visit interface. The callback accepts the task and returns a Task.VisitResult object.
   * @return {bool} true if visiting should continue
   */
  Task.prototype.visit = function(callback)
  {
    var result = callback(this);
    switch (result)
    {
      case Task.VisitResult["ACCEPT"]:
        for (var t = 0, numTasks = this._tasks.length; t < numTasks; ++t)
        {
          var task = this._tasks[t];
          if (!task.visit(callback))
          {
            return false;
          }
        }
        return true;
      case Task.VisitResult["REJECT"]:
        return true;
      case Task.VisitResult["COMPLETE"]:
      default:
        return false;
    }
  };

  /**
   * Check if the task was forced to complete (took too long)
   */
  Task.prototype.wasForcedToComplete = function()
  {
    return this._forceCompleted === true;
  };

  /**
   * Force a long running task to complete
   */
  Task.prototype.forceComplete = function()
  {
    if (this._tasksCompletedAt == null)
    {
      this._forceCompleted = true;
      for (var t = 0, numTasks = this._tasks.length; t < numTasks; ++t)
      {
        var task = this._tasks[t];

        if (task._tasksCompletedAt == null)
        {
          // Forcing the child to complete should result in the _childTaskComplete call
          // from the child and therefore the _activeTaskCount should be decremented
          // by the end of this function and the _checkComplete will run
          task.forceComplete();
        }

        if (this._tasksCompletedAt != null)
        {
          break;
        }
      }

      if (this._stop == null)
      {
        this.stop();
      }
    }

    Assert.assert(this._tasksCompletedAt != null,
      "Task was forced to complete, but is still active");
  };

  /**
   * Gets the monitor ID. This ID includes the path to indicate the correct nesting level.
   *
   * @return {string} the monitor ID of this task
   */
  Task.prototype.getMonitorId = function()
  {
    var monitorId = this.getName();
    var parentTask = this.getParent();

    if (parentTask != null)
    {
      // If not finer, the calls won't be included, so only use the operations to build the monitor
      // ID
      if (!perfLogger.isLoggable(FINER))
      {
        while (!(parentTask instanceof Operation))
        {
          parentTask = parentTask.getParent();
        }
      }

      if (parentTask != null)
      {
        monitorId = parentTask.getMonitorId() + " > " + monitorId;
      }
    }

    return monitorId;
  };

  /**
   * Function for sub-classes
   */
  Task.prototype.Completed = function() {}

  /**
   * Function for sub-classes
   */
  Task.prototype.Stopped = function() {}

  Task.prototype._checkComplete = function()
  {
    if (this._stop != null && this._tasksCompletedAt == null)
    {
      if (this._activeTaskCount == 0)
      {
        this._tasksCompletedAt = (new Date()).getTime();
        if (perfLogger.isLoggable(FINEST))
        {
          var logMsg = "Task completed. " + this.getIdentifier() + " at " + this._tasksCompletedAt +
            ". Elapsed: " + this.getTotalElapsed();

          if (this.wasForcedToComplete())
          {
            logMsg += ". (Forced)";
          }

          perfLogger.logp(FINEST, "adf.mf.internal.perf.Task", "Completed", logMsg);
        }

        var activeTask = Task.getActiveTask();
        if (activeTask != this)
        {
          Assert.assert(false,
            "Task that was not the active one was completed. Completed task: " +
            this.getIdentifier() + ". Active task: " + activeTask.getIdentifier());
        }

        Task._activeTasks.pop();
        if (perfLogger.isLoggable(FINEST))
        {
          var active = Task.getActiveTask();
          perfLogger.logp(FINEST,
            "adf.mf.internal.perf.Task", "_checkComplete",
            "Currently active task is now: " + (active == null ? "null" : active.getIdentifier()));
        }

        // Notify the sub-class
        this.Completed();

        if (this._parent != null)
        {
          // Notify the parent
          this._parent._childTaskComplete(this);
        }
      }
      else if (perfLogger.isLoggable(FINEST))
      {
        perfLogger.logp(FINEST,
          "adf.mf.internal.perf.Task", "_checkComplete",
          "Task waiting on tasks to complete. Task " + this.getIdentifier() + " is waiting on " +
          this._activeTaskCount + " tasks");
      }
    }
  };

  Task.prototype._childTaskComplete = function(task)
  {
    --this._activeTaskCount;
    this._checkComplete();
  };

  // Since Task has static methods, ensure that it is initialized before the first instance has
  // been created
  adf.mf.api.AdfObject.ensureClassInitialization(Task);

  /////////////////////////////////////////////////////////////////////////////////////////////////
  // Operation object

  /**
   * Function representing an operation to track performance. All method calls during the operation
   * are tracked as part of the operation.
   */
  function Operation(name, description)
  {
    this.Init(name, description);
  }

  adf.mf.api.AdfObject.createSubclass(Operation, Task, "Operation");

  Operation.InitClass = function()
  {
    this._activeOperation = null;
    this._completedOperations = [];
  };

  Operation.prototype.Init = function(name, description)
  {
    Operation.superclass.Init.call(this, name);
    this._description = description === undefined ? name : description;
  };

  // Static members

  /**
   * Checks if there is a top level operation currently running
   * @return {boolean} true if there is an active operation
   */
  Operation.isOperationActive = function()
  {
    return Operation._activeOperation != null;
  };

  /**
   * Get the description of this operation
   * @return {string} the description to use
   */
  Operation.prototype.getDescription = function()
  {
    return this._description;
  };

  /**
   * Start the operation. Tracks the start time and either sets the operation as the top active
   * operation or adds it as a child to the last operation started.
   */
  Operation.prototype.start = function()
  {
    Operation.superclass.start.call(this);

    if (this.getParent() == null)
    {
      Operation._activeOperation = this;
    }
  };

  /**
   * Called when an operation is done. For the top level operation this will start the process
   * of sending the data to the embedded side for logging as a monitor observation.
   */
  Operation.prototype.Stopped = function()
  {
    var isFinestLoggingEnabled = perfLogger.isLoggable(FINEST);
    if (Operation._activeOperation == this && this.getActiveTaskCount() > 0)
    {
      // This is the top level operation, but tasks are still active.
      // Wait up to 10 seconds for any active calls to complete
      if (this._waitingOnCompletionTimeout == null)
      {
        this._waitingOnTaskCompletion = window.setTimeout(
          this._activeTaskTimeout.bind(this), 10000);

        if (isFinestLoggingEnabled)
        {
          var logStr = "Top level operation completed, but tasks are still running. " +
            this.getIdentifier();

          perfLogger.logp(FINEST, "adf.mf.internal.perf.Operation", "Stopped", logStr);
        }
      }
    }
  };

  Operation.prototype.Completed = function()
  {
    if (Operation._activeOperation == this)
    {
      Operation._activeOperation = null;
    }

    if (this.getParent() == null)
    {
      this._sendToEmbedded();
    }
  };

  /**
   * Called from the timeout set in the stop method if not all the children calls and operations
   * were complete at the time. If this method is called, the code will stop waiting for those to
   * complete, assuming them to be broken in some way.
   */
  Operation.prototype._activeTaskTimeout = function()
  {
    delete this._waitingOnTaskCompletion;

    if (this.getTasksCompletedAt() == null)
    {
      if (perfLogger.isLoggable(FINEST))
      {
        perfLogger.logp(FINEST,
          "adf.mf.internal.perf.Operation", "_activeTaskTimeout",
          "Tasks did not complete. Forcing timeout. " + this.getIdentifier());
      }

      this.forceComplete();
    }
  };

  /**
   * Appends the monitor observations for this operation to the passed in array.
   * @param {Array.<Object>} monitorObservations the array to append to
   * @param {Array.<String>} logs if finer, this will be a non-null array to collect the
   *        logs to be able to parse the results via the js-perf.py tool.
   */
  Operation.prototype._appendMonitorObservations = function(
    monitorObservations,
    logs)
  {
    var isFiner = logs != null;
    var isFinest = isFiner && perfLogger.isLoggable(FINEST);

    this.visit(
      function (task)
      {
        var isCall = task instanceof Call;

        // Only include call observations at the individual level with finest logging
        if (isCall && !isFinest)
        {
          // Keep looking in case there are nested operations
          return Task.VisitResult["ACCEPT"];
        }

        var appendDescStr = null;
        var desc = isCall ? null : task.getDescription();

        var taskData =
        {
          "id": task.getMonitorId(),
          "duration": task.getTotalElapsed(),
          "start": task.getStart()
        };

        if (task.wasForcedToComplete())
        {
          var forceCompletedStr = "(Task was forced to complete)";
          appendDescStr = appendDescStr == null ?
            forceCompletedStr :
            appendDescStr + " " + forceCompletedStr;
        }

        if (appendDescStr != null)
        {
          desc = !desc ? appendDescStr : desc + " " + appendDescStr;
        }

        // Send the description if non-null and not an empty string
        if (desc)
        {
          taskData["description"] = desc;
        }

        monitorObservations.push(taskData);

        if (!isCall && logs != null)
        {
          // Since calls are not logged, find the parent operation, if there is one
          var parentTask = task.getParent();
          while (parentTask != null && parentTask instanceof Call)
          {
            parentTask = parentTask.getParent();
          }

          // For the FINER level logs, send the output to the console now, while the
          // web view is not busy (for FINEST this is printed as they come in)
          var opLog = task.getIdentifier() + "\n Started: " + task.getStart() + "\n Elapsed: " +
            task.getElapsed() + "\n Total: " + task.getTotalElapsed() + "\n Child of: " +
            (parentTask == null ? "None" : parentTask.getIdentifier());
          logs.push(opLog);
        }

        // At a finer level send the calls to the server as observations as well
        if (isFiner && !isFinest)
        {
          var callData = {};

          task.visit(
            function (nestedTask)
            {
              if (nestedTask == task)
              {
                // Skip over the current task
                return Task.VisitResult["ACCEPT"];
              }
              else if (nestedTask instanceof Operation)
              {
                // Only get the calls directly under the current operation. We have encountered a
                // nested operation, so do not visit under this sub-tree
                return Task.VisitResult["REJECT"];
              }
              else
              {
                // Group all of the calls under one average at the finer level
                var callMonitorId = taskData["id"] + " > " + nestedTask.getName();
                var callTypeData = callData[callMonitorId];

                // See if this call has been seen for this operation yet
                if (callTypeData == null)
                {
                  callTypeData =
                  {
                    "id": callMonitorId,
                    "duration": 0,
                    "description": "",
                    "numberOfCalls": 0
                  };
                  callData[callMonitorId] = callTypeData;
                }

                callTypeData["duration"] += nestedTask.getTotalElapsed();
                ++callTypeData["numberOfCalls"];
              }
            });

          for (var monitorId in callData)
          {
            var callTypeData = callData[monitorId];
            monitorObservations.push(callTypeData);

            // Do not include calls for FINER level in the console log
          }
        }

        return Task.VisitResult["ACCEPT"];
      });
  };

  /**
   * Sends the information to the embedded side to be logged as a monitor observation
   */
  Operation.prototype._sendToEmbedded = function()
  {
    this._active = null;

    // See if there is an active operation. If so, delay the sending of the data until no
    // operations are currently running. This allows the code to try to find a time that the UI is
    // not busy to send the data, reducing the hit on performance
    if (Operation._activeOperation != null)
    {
      Operation._completedOperations.push(this);

      if (perfLogger.isLoggable(FINEST))
      {
        perfLogger.logp(FINEST, "adf.mf.internal.perf.Operation", "_sendToEmbedded",
          "Another operation is currently running, waiting to send the data to the embedded side " +
          "until that operation completes");
      }

      return;
    }

    var isFiner = perfLogger.isLoggable(FINER);
    var logs = isFiner && !perfLogger.isLoggable(FINEST) ? [] : null;
    var numCompletedOperations = Operation._completedOperations.length;
    var monitorObservations = [];

    // If there are any operations that were delayed send their observations as well
    if (numCompletedOperations > 0)
    {
      for (var i = 0; i < numCompletedOperations; ++i)
      {
        var op = Operation._completedOperations[i];
        op._appendMonitorObservations(monitorObservations, logs);
      }

      Operation._completedOperations = [];
    }

    this._appendMonitorObservations(monitorObservations, logs);

    if (logs != null)
    {
      var logStr = "";
      for (var l = 0, numLogs = logs.length; l < numLogs; ++l)
      {
        if (l > 0)
        {
          logStr += "\n";
        }

        logStr += logs[l];
      }
      perfLogger.logp(FINER, "adf.mf.internal.perf.Task", "finerLogs", logStr);
    }

    if (this._forceCompleted != null)
    {
      // Add an additional monitor to show that there were tasks that had to be force completed
      monitorObservations.push(
        {
          "id": this.getName() + ":ForceCompleted",
          "description": "Tasks for this operation were forced to be completed",
          "duration": (this._tasksCompletedAt - this._stop)
        });
    }

    if (perfLogger.isLoggable(FINEST))
    {
      var logStr = "Sending data to the embedded side. Number of observations: " +
        monitorObservations.length + ". Observations:";

      for (var o = 0, numObservations = monitorObservations.length; o < numObservations; ++o)
      {
        var mo = monitorObservations[o];
        var desc = mo["description"] || "";

        logStr += "\n  ID: " + mo["id"] +
          "\n    Duration: " + mo["duration"] +
          "\n    Details:" +
          "\n      " + desc.replace(/\n/g, "\n      ");
      }
      perfLogger.logp(FINEST, "adf.mf.internal.perf.Operation", "_sendToEmbedded", logStr);
    }

    adf.mf.api.invokeMethod(
      "oracle.adfmf.framework.api.Model",
      "addMonitorObservations",
      monitorObservations, noop, noop);
  };

  // Since Operation has static methods, ensure that it is initialized before the first instance has
  // been created
  adf.mf.api.AdfObject.ensureClassInitialization(Operation);

  /////////////////////////////////////////////////////////////////////////////////////////////////
  // Call object

  /**
   * Call object. Used for tracking start and stop calls on code functions.
   * @param {String} name the name of the call being made. Typically the fully qualified path
   *        to the function being executed
   */
  function Call(name)
  {
    this.Init(name);
  }

  adf.mf.api.AdfObject.createSubclass(Call, Task, "Call");

  Call.prototype.toString = function()
  {
    return this.getIdentifier();
  };

  // ============================================================================================
  // API methods

  /**
   * Notifies the framework of the start of an operation. An operation is a long running process to
   * be tracked for performance. Calls during the operation may be tracked to determine the
   * breakdown of the time spent in an operation. Operations may be nested but should only be used
   * to track major events (load page, navigation, handle data change event, etc.).
   *
   * @param {string} name the name of the operation
   * @param {string} description a description of the operation
   * @return {{stop: function()}} an object with a stop function that must be called when the
   *         operation has completed
   */
  adf.mf.internal.perf.startOperation = function(name, description)
  {
    // Track operations at a "FINE" level
    if (perfLogger.isLoggable(FINE))
    {
      try
      {
        var op = new Operation(name, description);
        op.start();
        return op;
      }
      catch (e)
      {
        // Eat any exceptions in the performance code to prevent issues loading the page
        if (perfLogger.isLoggable(FINE))
        {
          perfLogger.logp(FINEST,
            "adf.mf.internal.perf", "startOperation",
            "Exception thrown " + e.message);
        }
        return { "stop": noop };
      }
    }
    else
    {
      return { "stop": noop };
    }
  };

  /**
   * Notifies the framework of a method call to be tracked as part of the time breakdown of an
   * operation. This should be used for methods that contribute to the overhead of an operation
   * and should therefore be tracked to determine performance changes.
   *
   * @param {string} name the name of the method
   * @return {{stop: function()}} an object with a stop function that must be called when the
   *         call has completed. The stop function may be called asynchronously.
   */
  adf.mf.internal.perf.start = function(name)
  {
    // Track calls at a "FINER" level and only when an operation is active
    if (Operation.isOperationActive() && perfLogger.isLoggable(FINER))
    {
      try
      {
        var call = new Call(name);
        call.start();
        return call;
      }
      catch (e)
      {
        // Eat any exceptions in the performance code to prevent issues loading the page
        if (perfLogger.isLoggable(FINE))
        {
          perfLogger.logp(FINEST,
            "adf.mf.internal.perf", "start",
            "Exception thrown " + e.message);
        }
        return { "stop": noop };
      }
    }
    else
    {
      return { "stop": noop };
    }
  };

  /**
   * ADFc is still using this function, disable it instead of removing it so that the calling code
   * will not fail.
   * @deprecated Use adf.mf.internal.perf.start for method calls and
   *             adf.mf.internal.perf.startOperation for major operations (page loading, page
   *             navigation, etc.)
   */
  adf.mf.internal.perf.perfTimings = noop;
})();


/////////////////////////////////////// end of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/AdfPerfTiming.js///////////////////////////////////////



/////////////////////////////////////// start of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/ELParser.js///////////////////////////////////////

/* Copyright (c) 2011, 2015, Oracle and/or its affiliates. All rights reserved. */
/* ------------------- ELParser.js ---------------------- */
// @requires ELErrors
// @requires JavaScriptContext


var adf                    = window.adf                 || {};
adf.mf                     = adf.mf                     || {};
adf.mf.api                 = adf.mf.api                 || {};
adf.mf.el                  = adf.mf.el                  || {};
adf.mf.locale              = adf.mf.locale              || {};
adf.mf.log                 = adf.mf.log                 || {};
adf.mf.resource            = adf.mf.resource            || {};
adf.mf.util                = adf.mf.util                || {};

adf.mf.internal            = adf.mf.internal            || {};
adf.mf.internal.api        = adf.mf.internal.api        || {};
adf.mf.internal.el         = adf.mf.internal.el         || {};
adf.mf.internal.el.parser  = adf.mf.internal.el.parser  || {};
adf.mf.internal.el.parser.cache  = adf.mf.internal.el.parser.cache  || {};
adf.mf.internal.el.parser.ops    = adf.mf.internal.el.parser.ops    || {};
adf.mf.internal.locale     = adf.mf.internal.locale     || {};
adf.mf.internal.log        = adf.mf.internal.log        || {};
adf.mf.internal.mb         = adf.mf.internal.mb         || {};
adf.mf.internal.perf       = adf.mf.internal.perf       || {};
adf.mf.internal.perf.story = adf.mf.internal.perf.story || {};
adf.mf.internal.resource   = adf.mf.internal.resource   || {};
adf.mf.internal.util       = adf.mf.internal.util       || {};

/**
 * Literals:
 *    Boolean: true and false
 *    Integer: as in Java
 *    Floating point: as in Java
 *    String: with single and double quotes; " is escaped as \", ' is escaped as \', and \ is escaped as \\.
 *    Null: null
 *
 * Operators:
 *    In addition to the . and [] operators discussed in Variables, there is the additional operators:
 *       Arithmetic: +, - (binary), *, / and div, % and mod, - (unary)
 *       Logical: and, &&, or, ||, not, !
 *       Relational: ==, eq, !=, ne, <, lt, >, gt, <=, ge, >=, le.
 *                  Comparisons can be made against other values, or against boolean,
 *                  string, integer, or floating point literals.
 *       Empty: The empty operator is a prefix operation that can be used to determine whether a value is null or empty.
 *       Conditional: A ? B : C. Evaluate B or C, depending on the result of the evaluation of A.
 *
 *   The precedence of operators highest to lowest, left to right is as follows:
 *      1. [] .
 *      2. () - Used to change the precedence of operators.
 *      3. - (unary) not ! empty
 *      4. * / div % mod
 *      5. + - (binary)
 *      6. < > <= >= lt gt le ge
 *      7. == != eq ne
 *      8. && and
 *      9. || or
 *     10. ? :
 *
 *  Reserved Words:
 *  The following words are reserved for the JSP expression language and should not be used as identifiers.
 *      and   eq   gt   true   instanceof
 *      or    ne   le   false  empty
 *      not   lt   ge   null   div   mod
 *  Note that many of these words are not in the language now, but they may be in the future,
 *  so you should avoid using them.
 *
 *  Examples:
 *  Here are some example EL expressions and the result of evaluating them.
 *
 *  EL Expression            Result
 *  ----------------------------------  --------------------------------------------------------------------
 *  ${1 > (4/2)}            false
 *  ${4.0 >= 3}              true
 *  ${100.0 == 100}            true
 *  ${(10*10) ne 100}          false
 *  ${'a' < 'b'}            true
 *  ${'hip' gt 'hit'}          false
 *  ${4 > 3}              true
 *  ${1.2E4 + 1.4}            12001.4
 *  ${3 div 4}              0.75
 *  ${10 mod 4}              2
 *  ${empty param.Add}          True if the request parameter named Add is null or an empty string
 *  ${pageContext.request.contextPath}  The context path
 *  ${sessionScope.cart.numberOfItems}  The value of the numberOfItems property of the session-scoped attribute
 *                    named cart
 *  ${param['mycom.productId']}      The value of the request parameter named mycom.productId
 *  ${header["host"]}          The host
 *  ${departments[deptName]}      The value of the entry named deptName in the departments map
 *
 *
 *  How to use the ELParser and ELExpression objects:
 *  - If you want to parse and evaluate an expression for a one time evaluation (i.e. will not be cached)
 *    do the following:
 *      adf.mf.internal.el.parser.evaluate(expression, context);
 *    i.e.
 *      adf.mf.internal.el.parser.evaluate("Hello", context);
 *      adf.mf.internal.el.parser.evaluate("${1.23E3}", context);
 *      adf.mf.internal.el.parser.evaluate("${applicationScope.loginRequired}", context);
 *      adf.mf.internal.el.parser.evaluate("${applicationScope.variableA < applicationScope.variableB}", context);
 *
 *  - If you want to parse an expression and use it over and over or simply let the "system" cached the expression
 *    for you, do the following:
 *      var expr      = adf.mf.internal.el.parser.parse(expression);
 *      ...
 *      var value     = expr.evaluate(context);  // can be called multiple times
 *
 *    i.e.
 *      var expr = adf.mf.internal.el.parser.evaluate("Hello");
 *      expr.evaluate(context);  // can be called multiple times
 *
 *      var expr = adf.mf.internal.el.parser.evaluate("${1.23E3}");
 *      expr.evaluate(context);  // can be called multiple times
 *
 *      var expr = adf.mf.internal.el.parser.evaluate(context, "${applicationScope.loginRequired}");
 *      expr.evaluate(context);  // can be called multiple times
 *
 *      var expr = adf.mf.internal.el.parser.evaluate(context, "${applicationScope.variableA < applicationScope.variableB}");
 *      expr.evaluate(context);  // can be called multiple times
 */
(function() {
  adf.mf.internal.el.parser.ops =  {};

  var isStr = function(s) { return typeof(s) === 'string' || s instanceof String; };
  adf.mf.internal.el.parser.ops.isStr = isStr;

  adf.mf.internal.el.parser.ops.concat = function(a, b)             { return "" + a + b;                   };

  // standard EL binary operations implementations
  //function add(a, b)                { return (isStr(a) || isStr(b))? ("" + a + b) : (a + b); }
  adf.mf.internal.el.parser.ops.add = function(a, b)
  {
    // EL should not allow adding strings together, but this code re-writes EL like "#{null}#{null}"
    // to #{null + null}. In order to prevent a radical change at this point, support string
    // concatination despite being against the EL specification.
    if (a === null && b === null)
    {
      // JavaEL will use a blank string for nulls being concatinated
      return "";
    }
    else if (a === null && isStr(b))
    {
      return b;
    }
    else if (isStr(a) && b === null)
    {
      return a;
    }

    return a + b;
  };

  adf.mf.internal.el.parser.ops.subtract = function(a, b)           { return a - b;                        };
  adf.mf.internal.el.parser.ops.multiply = function(a, b)           { return a * b;                        };
  adf.mf.internal.el.parser.ops.divide = function(a, b)             { return a / b;                        };
  adf.mf.internal.el.parser.ops.modulo = function(a, b)             { return a % b;                        };
  adf.mf.internal.el.parser.ops.index = function(a, b)              { return a[b];                         };

  // standard EL unary operation implementations
  adf.mf.internal.el.parser.ops.negate = function(a)                { return -a;                           };
  adf.mf.internal.el.parser.ops.empty = function(a)
  {
    return ((a === null) || (a === '') || (a == []) ||
      (a === adf.mf.api.OptionalFragmentArgument));
  };

  adf.mf.internal.el.parser.ops.not = function(a)                   { return !adf.mf.internal.el.parser.ops.coerce(a);                   };

  // standard EL logical operations implementations
  adf.mf.internal.el.parser.ops.greaterThanOrEqual = function(a, b) { return a >= b;                       };
  adf.mf.internal.el.parser.ops.greaterThan = function(a, b)        { return a > b;                        };
  adf.mf.internal.el.parser.ops.lessThanOrEqual = function(a, b)    { return a <= b;                       };
  adf.mf.internal.el.parser.ops.lessThan = function(a, b)           { return a < b;                        };
  adf.mf.internal.el.parser.ops.equals = function(a, b)             { return adf.mf.internal.el.parser.ops.coerce(a) == adf.mf.internal.el.parser.ops.coerce(b);       };
  adf.mf.internal.el.parser.ops.notEqual = function(a, b)           { return adf.mf.internal.el.parser.ops.coerce(a) != adf.mf.internal.el.parser.ops.coerce(b);       };
  adf.mf.internal.el.parser.ops.or = function(a, b)                 { return adf.mf.internal.el.parser.ops.coerce(a) || adf.mf.internal.el.parser.ops.coerce(b);       };
  adf.mf.internal.el.parser.ops.and = function(a, b)                { return adf.mf.internal.el.parser.ops.coerce(a) && adf.mf.internal.el.parser.ops.coerce(b);       };
  adf.mf.internal.el.parser.ops.ternary = function(a,b,c)           { return (adf.mf.internal.el.parser.ops.coerce(a))? b : c;           };

  // coerce the value to a boolean if so be it
  adf.mf.internal.el.parser.ops.coerce = function(a)                { return (a == "true") ? true : (a == "false") ? false : a; };

  // standard EL function implementations
  adf.mf.internal.el.parser.ops.unknown = function()                {                                      };
  adf.mf.internal.el.parser.ops.block = function()     {};

  adf.mf.internal.el.parser.ops.append = function(a, b) {
        if (Object.prototype.toString.call(a) != "[object Array]") {
          return [a, b];
        }
        a = a.slice();
        a.push(b);
        return a;
      };

      /*
       *  The precedence of operators highest to lowest, left to right is as follows:
       *      1. [] .
       *      2. () - Used to change the precedence of operators.
       *      3. - (unary) not ! empty
       *      4. * / div % mod
       *      5. + - (binary)
       *      6. < > <= >= lt gt le ge
       *      7. == != eq ne
       *      8. && and
       *      9. || or
       *     10. ? :
       */
  adf.mf.internal.el.parser.ops.unaryOperations = {
        /* token : [ token, function_to_perform, increment_position_by, prior_token, precedence, requires_word_boundary ]*/
        "-"     : ["-",     adf.mf.internal.el.parser.ops.negate,          +1, +2, 3, false],
        "!"     : ["!",     adf.mf.internal.el.parser.ops.not,             +1, +2, 3, false],
        "not"   : ["not",   adf.mf.internal.el.parser.ops.not,             +3, +2, 3, true ],
        "empty" : ["empty", adf.mf.internal.el.parser.ops.empty,           +5, +2, 3, true ]
      };

  adf.mf.internal.el.parser.ops.binaryOperations = {
        /* token : [ token, function_to_perform, increment_position_by, prior_token, precedence, requires_word_boundary ]*/
        // ",": [",",   adf.mf.internal.el.parser.ops.append,              +1, -1, -1, false], ---> not really a binary operator
        "#"   : ["#",   adf.mf.internal.el.parser.ops.concat,              +1, +2,  5, false],
        "+"   : ["+",   adf.mf.internal.el.parser.ops.add,                 +1, +2,  5, false],
        "-"   : ["-",   adf.mf.internal.el.parser.ops.subtract,            +1, +2,  5, false],
        "*"   : ["*",   adf.mf.internal.el.parser.ops.multiply,            +1, +1,  4, false],
        "/"   : ["/",   adf.mf.internal.el.parser.ops.divide,              +1, +1,  4, false],
        "div" : ["div", adf.mf.internal.el.parser.ops.divide,              +3, +2,  4, true ],
        "%"   : ["%",   adf.mf.internal.el.parser.ops.modulo,              +1, +2,  4, false],
        "mod" : ["mod", adf.mf.internal.el.parser.ops.modulo,              +3, +2,  4, true ],
        "and" : ["and", adf.mf.internal.el.parser.ops.and,                 +3, +2,  8, true ],
        "&&"  : ["&&",  adf.mf.internal.el.parser.ops.and,                 +2, +2,  8, false],
        "or"  : ["or",  adf.mf.internal.el.parser.ops.or,                  +2, +2,  9, true ],
        "||"  : ["||",  adf.mf.internal.el.parser.ops.or,                  +2, +2,  9, false],
        "<="  : ["<=",  adf.mf.internal.el.parser.ops.lessThanOrEqual,     +2, +2,  6, false],
        "le"  : ["le",  adf.mf.internal.el.parser.ops.lessThanOrEqual,     +2, +2,  6, true ],
        "<"   : ["<",   adf.mf.internal.el.parser.ops.lessThan,            +1, +1,  6, false],
        "lt"  : ["lt",  adf.mf.internal.el.parser.ops.lessThan,            +2, +1,  6, true ],
        ">="  : [">=",  adf.mf.internal.el.parser.ops.greaterThanOrEqual,  +2, +2,  6, false],
        "ge"  : ["ge",  adf.mf.internal.el.parser.ops.greaterThanOrEqual,  +2, +2,  6, true ],
        ">"   : [">",   adf.mf.internal.el.parser.ops.greaterThan,         +1, +2,  6, false],
        "gt"  : ["gt",  adf.mf.internal.el.parser.ops.greaterThan,         +2, +2,  6, true ],
        "=="  : ["==",  adf.mf.internal.el.parser.ops.equals,              +2, +2,  7, false],
        "eq"  : ["eq",  adf.mf.internal.el.parser.ops.equals,              +2, +2,  7, true ],
        "!="  : ["!=",  adf.mf.internal.el.parser.ops.notEqual,            +2, +2,  7, false],
        "ne"  : ["ne",  adf.mf.internal.el.parser.ops.notEqual,            +2, +2,  7, true ]
      };

  adf.mf.internal.el.parser.ops.ternaryOperations = {
        /* token : [ token, function_to_perform, increment_position_by, prior_token, precedence, requires_word_boundary ]*/
        "?"   : ["?",   adf.mf.internal.el.parser.ops.ternary,             +1, +2,  11, false]
      };

  adf.mf.internal.el.parser.ops.constants = {
        "true"  : true,
        "false" : false,
        "null"  : null
      };

  adf.mf.internal.el.parser.ops.functions = {
        "abs"             : Math.abs,
        "sign"            : adf.mf.internal.el.parser.ops.unknown,
        "pow"             : Math.pow,
        "exp"             : Math.exp,
        "ln"              : adf.mf.internal.el.parser.ops.unknown,
        "round"           : Math.round,
        "truncate"        : Math.floor,
        "len"             : adf.mf.internal.el.parser.ops.unknown,
        "strstr"          : adf.mf.internal.el.parser.ops.unknown,
        "leftstr"         : adf.mf.internal.el.parser.ops.unknown,
        "rightstr"        : adf.mf.internal.el.parser.ops.unknown,
        "substr"          : String.substr,
        "lower"           : String.toLowerCase,
        "upper"           : String.toUpperCase,
        "date"            : adf.mf.internal.el.parser.ops.unknown,
        "now"             : adf.mf.internal.el.parser.ops.unknown,
        "lookup"          : adf.mf.internal.el.parser.ops.unknown
      };

  var PRIMARY  = 1 <<  0;
  var OPERATOR = 1 <<  1;
  var FUNCTION = 1 <<  2;
  var LPAREN   = 1 <<  3;
  var RPAREN   = 1 <<  4;
  var COMMA    = 1 <<  5;
  var SIGN     = 1 <<  6;
  var CALL     = 1 <<  7;
  var OPENEXP  = 1 <<  8;
  var CLOSEEXP = 1 <<  9;
  var UNIOP    = 1 << 10;
  var HOOK     = 1 << 11;
  var COLON    = 1 << 12;
  var LBRACE   = 1 << 13;
  var NO_ARGS  = 1 << 14;

  /* types of tokens that will be encountered */
  var TOKEN_CONSTANT         =  0;
  var TOKEN_UNARY_OPERATOR   =  1;
  var TOKEN_BINARY_OPERATOR  =  2;
  var TOKEN_TERNARY_OPERATOR =  3;
  var TOKEN_VARIABLE         =  4;
  var TOKEN_FUNCTION         =  5;
  var TOKEN_INDEX            =  6;
  var TOKEN_DOT_OFFSET       =  7;
  var TOKEN_PROPERTY         =  8;
  var TOKEN_COLON            =  9;
  var TOKEN_COMMA            = 10;
  var TOKEN_NO_ARGS          = 11;


  function Token(type, index, prior, value) {
    this.type  = type;
    this.index = index || 0;
    this.prior = prior || 3;
    this.value = (value !== undefined) ? value : null;

    this.toString = function () {
      switch (this.type) {
        case TOKEN_CONSTANT:         return escape(this.value);
        case TOKEN_UNARY_OPERATOR:   /* or */
        case TOKEN_BINARY_OPERATOR:  /* or */
        case TOKEN_TERNARY_OPERATOR: /* or */
        case TOKEN_NO_ARGS:          /* or */
        case TOKEN_INDEX:            /* or */
        case TOKEN_COLON:            /* or */
        case TOKEN_COMMA:            /* or */
        case TOKEN_DOT_OFFSET:       /* or */
        case TOKEN_VARIABLE:         return this.index;
        case TOKEN_FUNCTION:         return "INVOKE";
        default:                     return "Invalid Token";
      }
    };
  }

  // table used by table driven parser
  var parserRules = {};
  // default rule (function)
  var otherRule;

  /**
   * Adds several new rules to 'parserRules' table.
   *
   * @param {Object.<string, Object>} rules This is table of new rules like {"+": [...], "true": true}.
   *   This format is compatible with "adf.mf.internal.el.parser.ops.xxx" definition
   *   tables.
   * @param {function} f Function that will be called when some of registerred rules
   *   is recognized. This function will be called with two parameters. 'index'
   *   which is index to the ELParser.expression. Second parameter 'param' is 'value'
   *   object from rules map.
   * @param {boolean} caseInsensitive Used for case insensitive tokens, like 'true'
   *   in EL Expression language.
   */
  function addParserRules(rules, f, caseInsensitive) {
    for (var k in rules) {
      addParserRule(k, f, rules [k], caseInsensitive);
    }
  }

  /**
   * Adds one rule to 'parserRules' table.
   *
   * @param {string} text Text specifies content of recognized token, like '==',
   *   'true' or '+'.
   * @param {function} f Function that will be called when registerred rule
   *   is recognized. This function will be called with two parameters. 'index'
   *   which is index to the ELParser.expression. Second parameter 'param' contains
   *   value of 'param'.
   * @param {Object} param Value that will be send to function 'f'.
   * @param {boolean} caseInsensitive Used for case insensitive tokens, like 'true'
   *   in EL Expression language.
   */
  function addParserRule(text, f, param, caseInsensitive) {
    _addParserRulesForCharacter(0, text, parserRules, f, param, caseInsensitive);
  }

  /**
   * This method adds rules for one character from the given text. This method
   * is private.
   *
   * @private
   * @param {number} index Index to the text string. It points to the
   *   current character.
   * @param {string} text Text specifies content of recognized token, like '==',
   *   'true' or '+'.
   * @param {Object.<string, Object>} m Points to the current node in
   *   parserRules table.
   * @param {function} f Function that will be called when registerred rule
   *   is recognized. This function will be called with two parameters. 'index'
   *   which is index to the ELParser.expression. Second parameter 'param' contains
   *   value of 'param'.
   * @param {Object} param Value that will be send to function 'f'.
   * @param {boolean} caseInsensitive Used for case insensitive tokens, like 'true'
   *   in EL Expression language.
   */
  function _addParserRulesForCharacter(index, text, m, f, param, caseInsensitive) {
    var ch = text.charAt(index);
    var last = index + 1 == text.length;
    if(caseInsensitive) {
      ch = ch.toLowerCase();
      _addParserRuleForCharacter(index, text, m, f, param, caseInsensitive, ch, last);
      var ch2 = ch.toUpperCase();
      if (ch !== ch2)
        _addParserRuleForCharacter(index, text, m, f, param, caseInsensitive, ch2, last);
    } else
      _addParserRuleForCharacter(index, text, m, f, param, caseInsensitive, ch, last);
  }

  /**
   * This method adds one rule for one character from the given text. This method
   * is private.
   *
   * @private
   * @param {number} index Index to the text string. It points to the
   *   current character.
   * @param {string} text Text specifies content of recognized token, like '==',
   *   'true' or '+'.
   * @param {Object.<string, Object>} m Points to the current node in
   *   parserRules table.
   * @param {function} f Function that will be called when registerred rule
   *   is recognized. This function will be called with two parameters. 'index'
   *   which is index to the ELParser.expression. Second parameter 'param' contains
   *   value of 'param'.
   * @param {Object} param Value that will be send to function 'f'.
   * @param {boolean} caseInsensitive Used for case insensitive tokens, like 'true'
   *   in EL Expression language.
   * @param {string} ch Character that should be added.
   * @param {boolean} last This parameter should be true for last character from
   *   text string.
   */
  function _addParserRuleForCharacter(index, text, m, f, param, caseInsensitive, ch, last) {
    var nm = m [ch];
    if (!nm) {
      nm = [{}];
      m [ch] = nm;
    }
    if (last) {
      nm [nm.length] = f;
      nm [nm.length] = param;
    } else
      _addParserRulesForCharacter(index + 1, text, nm [0], f, param, caseInsensitive);
  }

  /**
   * This function parses one token from ELParser.expression according to rules
   * stored in 'parserRules', and calls one of registerred callback method.
   *
   * @param {Object} elParser Instance of ELParser.
   * @returns {boolean} True, if some token was recognized.
   */
  function parseToken(elParser) {
    if (_parseTokenCharacter(elParser, elParser.pos, parserRules)) return true;
    if (otherRule) return otherRule.call(elParser, elParser.pos);
    return false;
  }

  /**
   * This function parses one character from the current token according to rules
   * stored in 'parserRules', and calls one of registerred callback method.
   *
   * @param {Object} elParser Instance of ELParser.
   * @param {number} index Index to elParser.expression.
   * @param {Object.<string, Object>} map Points to the current node in
   *   parserRules table.
   * @returns {boolean} True, if some token was recognized.
   */
  function _parseTokenCharacter(elParser, index, map) {
    var ch = elParser.expression.charAt(index);
    var r = map [ch];
    if (r) {
      if (_parseTokenCharacter(elParser, index + 1, r [0])) return true;
      for (var j = 1; j < r.length; j+=2) {
        if (r [j + 1] === 0 ? r [j].call(elParser, index + 1) : r [j].call(elParser, index + 1, r [j + 1]))
          return true;
      }
    }
    return false;
  }

  /*
   * Adds rule for parsing binary operators.
   */
  addParserRules(adf.mf.internal.el.parser.ops.binaryOperations, function(index, param) {
    if (param[5] && !this.isOperatorBoundary(index))
      return false;

    this.finishTerm (this.pos);
    this.token = param[0];
    this.prior = param[4];
    this.pos   += param[2];
    if (this.isSign() && (this.expected & SIGN)) {
      if (this.isNegativeSign()) {
        this.token = adf.mf.internal.el.parser.ops.unaryOperations["-"][0];
        this.prior = adf.mf.internal.el.parser.ops.unaryOperations["-"][4];
        this.nooperands++;
        this.addfunc(TOKEN_UNARY_OPERATOR);
      }
      this.expected = (PRIMARY | LPAREN | FUNCTION | SIGN | CLOSEEXP);
    }
    else if (this.isComment()) {
      /* do nothing */
    }
    else {
      if ((this.expected & OPERATOR) === 0) {
        var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNEXPECTED_OPERATOR_FOUND");

        this.parsingError(this.pos, rmsg);
      }
      this.nooperands += 2;
      this.addfunc(TOKEN_BINARY_OPERATOR);
      this.expected = (PRIMARY | LPAREN | FUNCTION | SIGN | UNIOP | CLOSEEXP);
    }
    return true;
  });

  /*
   * Adds rule for parsing unary operators.
   */
  addParserRules(adf.mf.internal.el.parser.ops.unaryOperations, function(index, param) {
    if (param[5] && !this.isOperatorBoundary(index))
      return false;
    this.token = param[0];
    this.prior = param[4];  /* adding precedence support */
    if ((this.expected & UNIOP) === 0) {
      var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNEXPECTED_UNIARY_OP_FOUND");

      this.parsingError(this.pos, rmsg);
    }
    this.addfunc(TOKEN_UNARY_OPERATOR);
    this.nooperands++;
    this.expected = (PRIMARY | LPAREN | FUNCTION | SIGN | CLOSEEXP);
    this.finishTerm (this.pos);
    this.pos  += param[0].length;
    return true;
  });

  /*
   * Adds rule for parsing '}'.
   */
  addParserRule("}", function() {
    while (this.operatorStack.length > 0) {
      this.tokenStack.push(this.operatorStack.pop());
    }
    this.finishTerm (this.pos);
    this.pos++;
    if ((this.expected & CLOSEEXP) === 0) {
      var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNKNOWN_CHAR_FOUND", ["}"]);

      this.parsingError(this.pos, rmsg);
    }
    if(this.expCount > 1) {
      this.token   = "+";   // should go to # if we should concat
      this.prior   = 30;    // 30 = 3 (for add) * 10 (to ensure it is always the last precedence)
      this.nooperands  += 2;
      this.addfunc(TOKEN_BINARY_OPERATOR);
    }
    this.text        = this.pos;
    this.expected    = (OPENEXP);
    return true;
  });

  /*
   * Adds rule for parsing ','.
   */
  addParserRule(",", function(index) {
    this.pos++;
    this.prior = -1;
    this.token = ",";
    if ((this.expected & COMMA) === 0) {
      var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNKNOWN_CHAR_FOUND", [","]);

      this.parsingError(this.pos, rmsg);
    }
    this.addfunc(TOKEN_COMMA);
    this.nooperands    += 2;
    this.expected = (PRIMARY | LPAREN | FUNCTION | SIGN | UNIOP | CLOSEEXP);
    this.finishTerm (this.pos - 1);
    return true;
  });

  /*
   * Adds rule for parsing constant literals.
   */
  addParserRules(adf.mf.internal.el.parser.ops.constants, function(index, param) {
    // Verify that this is not part of a longer varible name that
    // just starts with a constant
    if (index < this.expression.length) {
      var nextChar = this.expression.charCodeAt(index);
      // Match a word character, [0-9A-Za-z_]
      if ((nextChar >= 48 /* 0 */ && nextChar <= 57 /* 9 */) ||
        (nextChar >= 65 /* A */ && nextChar <= 90 /* Z */) ||
        (nextChar >= 97 /* a */ && nextChar <= 122 /* z */) ||
        nextChar == 95 /* _ */) {
        // The string only begins with a constant, it is not a constant
        return false;
      }
    }
    this.value = param;
    this.pos  = index;

    if ((this.expected & PRIMARY) === 0) {
      var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNEXPECTED_CONSTANT_FOUND");

      this.parsingError(this.pos, rmsg);
    }
    var constant = new Token(TOKEN_CONSTANT, 0, 0, this.value);
    this.tokenStack.push(constant);
    this.expected = (OPERATOR | HOOK | LPAREN | RPAREN | COLON | COMMA | CLOSEEXP);
    return true;
  }, true);

  /*
   * Adds rule for parsing '#'.
   */
  addParserRule("#", function(index) {
    this.directive = this.pos;
    this.pos = index;
    return true;
  });

  /*
   * Adds rule for parsing '$'.
   */
  addParserRule("$", function(index) {
    this.directive = this.pos;
    this.finishTerm (this.pos);
    this.pos = index;
    return true;
  });

  /*
   * Adds rule for parsing '['.
   */
  addParserRule("[", function(index) {
    this.pos++;

    if ((this.expected & LBRACE) === 0) {
      var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNKNOWN_CHAR_FOUND", ["["]);

      this.parsingError(this.pos, rmsg);
    }
    else {
      this.nooperands  +=  2;
      this.prior   =  1;
      this.token   = '[';

      this.addfunc(TOKEN_INDEX);
      this.pmatch += 100;
    }

    this.expected = (PRIMARY | LPAREN | FUNCTION | UNIOP | SIGN);
    return true;
  });

  /*
   * Adds rule for parsing '('.
   */
  addParserRule("(", function(index) {
    this.pos++;
    this.pmatch += 100;

    if ((this.expected & LPAREN) === 0) {
      var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNKNOWN_CHAR_FOUND", ["("]);

      this.parsingError(this.pos, rmsg);
    }

    if (this.expected & CALL) {
      this.nooperands +=  2;
      this.prior  =  11;
      this.token  = '(';
      this.addfunc(TOKEN_FUNCTION);
      this.expected = (PRIMARY | LPAREN | RPAREN | FUNCTION | UNIOP | NO_ARGS | SIGN);
    }
    else
    {
      this.expected = (PRIMARY | LPAREN | RPAREN | UNIOP | SIGN);
    }
    return true;
  });

  /*
   * Adds rule for parsing numeric literals.
   */
  addParserRules({"0":0,"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":0,"8":0,"9":0,"+":0,"-":0,".":0},
  function(index)
  {
    var result   = false;
    var i        = this.pos;

    var exponent = false;
    var dot = false;
    var digit = false;

    while (i < this.expression.length)
    {
      var c = this.expression.charAt(i);
      if (c == '.')
      {
        if (dot || exponent)
        {
          result = false;
          break;
        }

        dot = true;
      }
      else if (c == '+' || c == '-')
      {
        if (i == this.pos) break;
        var previousChar = this.expression.charAt (i - 1);
        if (previousChar != 'e' && previousChar != 'E') break;
      }
      else if (c == 'e' || c == 'E')
      {
        if (!digit)
        {
          // A number must have a digit before the e or E (.e1 is not valid)
          result = false;
          break;
        }
        exponent = true;
      }
      else if (c >= '0' && c <= '9')
      {
        digit = true;
        result = true;
      }
      else
      {
        if ((c.toLowerCase() !== c.toUpperCase()) || (c === '_'))
          result = false;
        break;
      }
      i++;
    }

    if (result)
    {
      var str = this.expression.substr(this.pos, i - this.pos);
      this.value = parseFloat(str);
      if ((this.expected & PRIMARY) === 0)
      {
        var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNEXPECTED_NUMBER_CONSTANT_FOUND");

        this.parsingError(this.pos, rmsg);
      }
      token = new Token(TOKEN_CONSTANT, 0, 0, this.value);
      this.tokenStack.push(token);

      this.expected = (OPERATOR | HOOK | RPAREN | COLON | COMMA | CLOSEEXP);
      this.pos = i;
    }

    return result;
  });

  /*
   * Adds rule for parsing binary operators.
   */
  addParserRules(adf.mf.internal.el.parser.ops.binaryOperations, function(index, param) {
    // This code doesn't seem to be used, instead the block at line 450 is used.
    // Can this be removed?
    if (param[5] && !this.isOperatorBoundary(index))
      return false;
    this.token = this.expression.substr(this.pos, index - this.pos);
    this.prior = param[4];  /* adding precedence support */
    if ((this.expected & FUNCTION) === 0) {
      var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNEXPECTED_BINARY_OP_FOUND");

      this.parsingError(this.pos, rmsg);
    }
    this.addfunc(TOKEN_BINARY_OPERATOR);
    this.nooperands += 2;
    this.expected    = (PRIMARY | LPAREN | UNIOP | SIGN);
    this.finishTerm (this.pos);
    this.pos   = index;
    return true;
  });

  /*
   * Adds rule for parsing ternary operators.
   */
  addParserRules(adf.mf.internal.el.parser.ops.ternaryOperations, function(index, param) {
    this.token = this.expression.substr(this.pos, index - this.pos);
    this.prior = param[4];  /* adding precedence support */
    if ((this.expected & HOOK) === 0) {
      var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNEXPECTED_TERNARY_OP_FOUND");

      this.parsingError(this.pos, rmsg);
    }
    this.addfunc(TOKEN_TERNARY_OPERATOR);
    this.nooperands  += 2;
    this.expected     = (PRIMARY | LPAREN | UNIOP | SIGN);
    this.finishTerm (this.pos);
    this.pos   = index;
    return true;
  });

  /*
   * Adds rule for parsing ')'.
   */
  addParserRule(")", function() {
    this.finishTerm (this.pos);
    this.pos++;
    this.pmatch -= 100;
    if ((this.expected & RPAREN) === 0) {
      var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNKNOWN_CHAR_FOUND", [")"]);

      this.parsingError(this.pos, rmsg);
    }

    if((this.expected & NO_ARGS) === NO_ARGS) {
      var vartoken = new Token(TOKEN_NO_ARGS, this.token, 0, 0);
      this.tokenStack.push(vartoken);
    }

    this.expected = (OPERATOR | HOOK | RPAREN | COMMA | LBRACE | LPAREN | COLON | CALL | CLOSEEXP);
    return true;
  });

  /*
   * Adds rule for parsing ']'.
   */
  addParserRule("]", function() {
    this.finishTerm (this.pos);
    this.pos++;
    this.pmatch -= 100;
    if ((this.expected & RPAREN) === 0) {
      var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNKNOWN_CHAR_FOUND", ["]"]);

      this.parsingError(this.pos, rmsg);
    }

    // Move the operation from the operation stack to the token stack
    if (this.operatorStack.length > 0 &&
      this.operatorStack[this.operatorStack.length - 1].type == TOKEN_INDEX)
    {
      this.tokenStack.push(this.operatorStack.pop());
    }

    this.expected = (OPERATOR | HOOK | LBRACE | RPAREN | CLOSEEXP | COLON);
    return true;
  });

  /*
   * Adds rule for parsing ':'.
   */
  addParserRule(":", function() {
    this.pos++;
    this.prior = 10;
    this.token = ":";
    if ((this.expected & COLON) === 0) {
      var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNKNOWN_CHAR_FOUND", [":"]);

      this.parsingError(this.pos, rmsg);
    }

    this.addfunc(TOKEN_COLON);
    this.nooperands  += 2;
    this.expected     = (PRIMARY | LPAREN | UNIOP | SIGN);
    this.finishTerm (this.pos - 1);
    return true;
  });

  /*
   * Adds rule for parsing '.'.
   */
  addParserRule(".", function() {
    this.nooperands  +=  2;
    this.prior   = 1;
    this.token = '.';
    this.addfunc(TOKEN_DOT_OFFSET);
    this.pos++;
    this.expected = (PRIMARY);
    return true;
  });

  /**
   * This call adds rules for parsing identifiers to parserRules, and
   * registers callback function that is able to parse identifiers.
   *
   * @param {type} f Function that implements parsing identifiers.
   */
  function addIdentifierParserRule(f) {
    for (var ch in parserRules) {
      if ((ch.toLowerCase() !== ch.toUpperCase()) || (ch === '_')) {
        var nm = parserRules [ch];
        nm [nm.length] = f;
        nm [nm.length] = null;
        addIdentifierParserRuleForNode(nm [0], f);
      }
    }
    otherRule = f;
  }

  /**
   * This call adds rules for parsing identifiers to the concrete node of
   * parserRules table, and registers callback function that is able
   * to parse identifiers.
   *
   * @param {type} f Function that implements parsing identifiers.
   */
  function addIdentifierParserRuleForNode(m, f) {
    for (var ch in m) {
      if ((ch.toLowerCase() !== ch.toUpperCase()) || (ch === '_') ||
          ((ch >= '0') && (ch <= '9'))
      ) {
        var nm = m [ch];
        nm [nm.length] = f;
        nm [nm.length] = null;
        addIdentifierParserRuleForNode(nm [0], f);
      }
    }
  }

  /*
   * Adds rule for parsing indentifiers.
   */
  addIdentifierParserRule(function(index) {
    var st = null;
    for (var i = index; i < this.expression.length; i++) {
      var ch = this.expression.charAt(i);
      if ((ch.toLowerCase() != ch.toUpperCase()) || (ch === '_'))
        continue;
      if ((this.pos < i) &&
          ((ch >= '0') && (ch <= '9'))
      )
        continue;
      break;
    } // for i
    if (this.pos === i) return false;
    if ((this.expected & PRIMARY) === 0) {
      var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNEXPECTED_VARIABLE_FOUND");
      this.parsingError(this.pos, rmsg);
    }

    this.token = this.expression.substr(this.pos, i - this.pos);
    var vartoken = null;
    if (!this.currentTermStart [this.pmatch])
    {
      vartoken = new Token(TOKEN_VARIABLE, this.token, 0, 0);
      this.currentTermStart [this.pmatch] = this.pos;
      this.currentELTermStart [this.pmatch] = this.tokenStack.length;
    } else
      vartoken = new Token(TOKEN_PROPERTY, this.token, 0, 0);

    this.prior = 3;
    this.pos = i;
    this.tokenStack.push(vartoken);
    this.expected = (OPERATOR | HOOK | COLON | RPAREN | COMMA | LPAREN | LBRACE | CALL | CLOSEEXP);
    return true;
  });

  /*
   * Adds rule for parsing string literals.
   */
  addParserRules({"'":0,'"':0}, function(index) {
    var delim = this.expression.charAt(this.pos);
    var esc = false;
    for (var i = index; i < this.expression.length; i++) {
      var ch = this.expression.charAt(i);
      if (ch === '\\')
        esc = true;
      else {
        esc = false;
        if ((!esc) && ch === delim) {
          var str = this.expression.substr(this.pos + 1, i - this.pos - 1);
          this.value = this.unescape(str, this.pos);
          this.pos = i + 1;
          if ((this.expected & PRIMARY) === 0) {
            var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNEXPECTED_STRING_FOUND");

            this.parsingError(this.pos, rmsg);
          }
          var token = new Token(TOKEN_CONSTANT, 0, 0, this.value);
          this.tokenStack.push(token);

          this.expected = (OPERATOR | HOOK | COLON | RPAREN | COMMA | CLOSEEXP);
          return true;
        }
      }
    } // for i
    this.pos = i;
    return false;
  });

  /*
   * Adds rule for parsing whitespaces.
   */
  addParserRules({" ":0,'\n':0,'\r':0,'\t':0}, function(index) {
    this.pos++;
    return true;
  });

  adf.mf.internal.el.parser.cache.clear = function() {
    adf.mf.internal.el.parser.cache.map  = {};
    adf.mf.internal.el.parser.cache.hit  = 0;
    adf.mf.internal.el.parser.cache.miss = 0;
  };

  adf.mf.internal.el.parser.cache.count = function() {
    var count = 0;

    if(adf.mf.internal.el.parser.cache.map != null) {
      for(var key in adf.mf.internal.el.parser.cache.map) {
        ++count;
      }
    }
    return count;
  };

  adf.mf.internal.el.parser.cache.purge = function(expr) {
    if(adf.mf.internal.el.parser.cache.map == null)
    {
      return;
    }
    delete adf.mf.internal.el.parser.cache.map[expr];
  };

  adf.mf.internal.el.parser.cache.lookup = function(expr) {
    var pexp  = null;

    if(adf.mf.internal.el.parser.cache.map == null)
    {
      adf.mf.internal.el.parser.cache.map  = {};
      adf.mf.internal.el.parser.cache.hit  = 0;
      adf.mf.internal.el.parser.cache.miss = 0;
    }

    pexp = adf.mf.internal.el.parser.cache.map[expr];

    if((pexp == undefined) || (pexp == null))
    {
      pexp = new ELParser().parse(expr);
      adf.mf.internal.el.parser.cache.map[expr] = pexp;
      ++adf.mf.internal.el.parser.cache.miss;
    }
    else
    {
      pexp.unflatten();
      ++adf.mf.internal.el.parser.cache.hit;
    }

    return pexp;
  };

  var ELParser = function () {

    /**
     * This method calls parser given expression, and returns ELExpression.
     *
     * @param {string} expr
     * @returns {ELExpression}
     */
    adf.mf.internal.el.parser.parse = function (expr) {
      return adf.mf.internal.el.parser.cache.lookup(expr);
    };

    adf.mf.internal.el.parser.evaluate = function (context, expr) {
      var pexp  = adf.mf.internal.el.parser.cache.lookup(expr);
      return pexp.evaluate(context);
    };

    function ELExpression(tokens, expr, terms, elTerms) {
      this.tokens             = tokens;
      this.stringVersion      = null;
      this.readonly           = null;
      this._expr              = expr;
      this.elTerms            = elTerms;
    }

    adf.mf.internal.el.parser.ELParser = ELParser;
    adf.mf.internal.el.parser.ELExpression = ELExpression;

    /**
     * Expressions are also designed to be immutable so that only one instance needs to be created for
     * any given expression String / {@link FunctionMapper}. This allows a container to pre-create
     * expressions and not have to reparse them each time they are evaluated.
     */
    ELExpression.prototype =
    {
      ensureItIsNotTheNullObject: function(t)
      {
        return (t == null || t[".null"] === true) ? null : t;
      },

      evaluate: function (context)
      {
        var n1, n2, n3, fn, item;
        var nstack       = [];
        var tokenLength  = this.tokens.length;
        var val          = null;
        var ind          = null;
        var ignoreNull   = false;

        for (var i = 0; i < tokenLength; i++)
        {
          item = this.tokens[i];

          var type = item.type;
          switch (type)
          {
            case TOKEN_CONSTANT:
              nstack.push(item.value);
              break;

            case TOKEN_TERNARY_OPERATOR:
              n3 = this.ensureItIsNotTheNullObject(nstack.pop());
              n2 = this.ensureItIsNotTheNullObject(nstack.pop());
              n1 = this.ensureItIsNotTheNullObject(nstack.pop());
              fn = (adf.mf.internal.el.parser.ops.ternaryOperations[item.index])[1]; /* function to execute the operation */
              nstack.push(fn(n1, n2, n3));
              break;

            case TOKEN_BINARY_OPERATOR:
              n2 = this.ensureItIsNotTheNullObject(nstack.pop());
              n1 = this.ensureItIsNotTheNullObject(nstack.pop());
              fn = (adf.mf.internal.el.parser.ops.binaryOperations[item.index])[1]; /* function to execute the operation */
              nstack.push(fn(n1, n2));
              break;

            case TOKEN_DOT_OFFSET:
            case TOKEN_INDEX:
              n2 = nstack.pop();
              n1 = nstack.pop();
              var elResolver = context.getELResolver();

              try
              {
                var val = elResolver.getValue(context, n1, n2);
                nstack.push(val);

                if (type == TOKEN_INDEX)
                {
                  // Not sure why this is needed, but without this functionality,
                  // the control tests will hang on Android, going into an apparent
                  // infinite loop.
                  ignoreNull = true;
                }
              }
              catch (ex)
              {
                if (((typeof n1) !== 'object') && (n2 === "inputValue"))
                {
                  nstack.push(n1);
                }
                else
                {
                  if (type == TOKEN_INDEX || (ignoreNull && type == TOKEN_DOT_OFFSET))
                  {
                    // This is the old approach and preserving backwards compatibility. In Java,
                    // only a MapElResolver will not throw property not found exceptions. The issue
                    // is that in JavaScript, there is no knowledge of a java.util.Map. So, for now
                    // the indexed expression will work like a map evaluation, and the dot notation
                    // as a property lookup.
                    val = n1[n2];
                    nstack.push(val);

                    // Flag this expression as not having been resolved so that it can be added
                    // to the batch at the end (used by adf.mf.el.getLocalValue)
                    adf.mf.internal.el.indexedExpressionUnresolved = true;
                  }
                  else
                  {
                    throw new adf.mf.PropertyNotFoundException(this.getExpression());
                  }
                }
              }
              break;

            case TOKEN_COMMA:
            case TOKEN_COLON:
            case TOKEN_NO_ARGS:
              /* ignore */
              break;

            case TOKEN_PROPERTY:
              nstack.push(item.index);
              break;

            case TOKEN_VARIABLE:
              var elResolver = context.getELResolver();
              try
              {
                var value = elResolver.getValue(context, null, item.index);
                nstack.push(value);
                ignoreNull = false;
              }
              catch (ex)
              {
                throw new adf.mf.PropertyNotFoundException(this.getExpression());
              }
              break;

            case TOKEN_UNARY_OPERATOR:
              n1 = this.ensureItIsNotTheNullObject(nstack.pop());
              fn = (adf.mf.internal.el.parser.ops.unaryOperations[item.index])[1];
              nstack.push(fn(n1));
              break;

            case TOKEN_FUNCTION:
              n1 = nstack.pop();
              fn = nstack.pop();
              if (fn.apply && fn.call)
              {
                if (Object.prototype.toString.call(n1) == "[object Array]") {
                    n1.push(context);
                }
                else {
                  var x = this.ensureItIsNotTheNullObject(n1);

                  n1 = [x];
                  n1.push(context);
                }
                nstack.push(fn.apply(undefined, n1));
              }
              else
              {
                throw new adf.mf.ELException(fn + " is not a function");
              }
              break;
            default:
              throw new adf.mf.ELException("invalid ELExpression - " + type);
          }
        }
        if (nstack.length > 1) {
          throw new adf.mf.ELException("invalid ELExpression (mis-match tokens and operations)");
        }

        return nstack[0];
      },

      setValue: function (context, value)
      {
        if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINEST))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINEST, "ValueExpression", "setValue",
            ("adfmf- setValue " + this.toString() + " = " + adf.mf.util.stringify(value)));
        }

        var tokens = this.tokens;
        var stack = [];
        var d = tokens.length;
        for (var i = 0; i < d; i++)
        {
          var token = tokens [i];
          switch (token.type)
          {
            case TOKEN_VARIABLE:
              // If the variable is the only token, use the EL resolver to set the value
              if (i == d - 1)
              {
                context.getELResolver().setValue(context, null, token.index, value);
                return;
              }
              var v = null;
              try {
                v = context.getELResolver().getValue(context, null, token.index);
              } catch (e) {
                adf.mf.el.addVariable(token.index, v = {});
              }
              stack.push(v);
              break;

            case TOKEN_PROPERTY:
              stack.push(token.index);
              break;

            case TOKEN_DOT_OFFSET:
            case TOKEN_INDEX:
              var n = stack.pop();
              var baseObject = stack.pop();
              var v = null;

              // If this is the last property of the object, us the EL resolver to
              // set the value
              if (i == d - 1)
              {
                context.getELResolver().setValue(context, baseObject, n, value);

                // Remove the .null if set
                if (baseObject != null)
                {
                  delete baseObject[".null"];
                }

                return;
              }

              try
              {
                v = context.getELResolver().getValue(context, baseObject, n);
              }
              catch (e)
              {
                v = baseObject[n] = {};

                // Remove the .null if set
                delete baseObject[".null"];
              }

              stack.push(v);
              break;

            case TOKEN_CONSTANT:
              stack.push(token.value);
              break;

            case TOKEN_TERNARY_OPERATOR:
              var n3 = this.ensureItIsNotTheNullObject(stack.pop());
              var n2 = this.ensureItIsNotTheNullObject(stack.pop());
              var n1 = this.ensureItIsNotTheNullObject(stack.pop());
              // function to execute the operation
              var fn = (adf.mf.internal.el.parser.ops.ternaryOperations[token.index])[1];

              stack.push(fn(n1, n2, n3));
              break;

            case TOKEN_BINARY_OPERATOR:
              var n2 = this.ensureItIsNotTheNullObject(stack.pop());
              var n1 = this.ensureItIsNotTheNullObject(stack.pop());
              // function to execute the operation
              var fn = (adf.mf.internal.el.parser.ops.binaryOperations[token.index])[1];
              stack.push(fn(n1, n2));
              break;

            default:
              break;
          } // switch
        } // for

        throw new adf.mf.PropertyNotWritableException(this.toString());
      },

      /**
       * Returns the original String used to create this ELExpression, unmodified. This is used for
       * debugging purposes but also for the purposes of comparison (e.g. to ensure the expression in
       * a configuration file has not changed). This method does not provide sufficient information to
       * re-create an expression. Two different expressions can have exactly the same expression
       * string but different function mappings. Serialization should be used to save and restore the
       * state of an ELExpression.
       *
       * @return The original expression String.
       */
      /* String */
      getExpressionString: function() {
        if (!this._expr)
          this.refresh();
        return this._expr;
      },

      /**
       * Get the expression using the EL indexed expression notation. This may be used to create
       * a common syntax to be able to compare two different EL expressions to see if they reference
       * the same object and property.
       * @return {string} the indexed representation as a string
       */
      getIndexedRepresentation: function()
      {
        if (this._indexedExpr == null)
        {
          var stack = [];

          for (var i = 0; i < this.tokens.length; i++)
          {
            var token = this.tokens[i];

            switch (token.type)
            {
              case TOKEN_CONSTANT:
                stack.push(escape(token.value));
                break;
              case TOKEN_TERNARY_OPERATOR:
                var n3 = stack.pop();
                // There may only be one here. This will occur during a dependencies call
                // for example where the EL expression is split into tokens.
                if (stack.length == 0)
                {
                  stack.push(n3);
                }
                else
                {
                  var n2 = stack.pop();
                  var n1 = stack.pop();
                  stack.push("((" + n1 + ")? " + n2 + " : " + n3 + ")");
                }
                break;
              case TOKEN_COMMA:
                var n2 = stack.pop();
                var n1 = stack.pop();
                stack.push("" + n1 + ", " + n2 + "");
                break;
              case TOKEN_COLON:
                break; // ignore
              case TOKEN_NO_ARGS:
                stack.push("");
                break;
              case TOKEN_BINARY_OPERATOR:
                var n2 = stack.pop();
                // There may only be one here. This will occur during a dependencies call
                // for example where the EL expression is split into tokens.
                if (stack.length == 0)
                {
                  stack.push(n2);
                }
                else
                {
                  var n1 = stack.pop();
                  stack.push("(" + n1 + " " + token.index + " " + n2 + ")");
                }

                break;
              case TOKEN_INDEX:
                var n2 = stack.pop();
                var n1 = stack.pop();
                stack.push(n1 + "[" + n2 + "]");
                break;
              case TOKEN_DOT_OFFSET:
                var n2 = stack.pop();
                var n1 = stack.pop();
                stack.push(n1 + '["' + n2 + '"]');
                break;
              case TOKEN_PROPERTY:
                stack.push(token.index);
                break;
              case TOKEN_VARIABLE:
                stack.push(token.index);
                break;
              case TOKEN_UNARY_OPERATOR:
                var n1 = stack.pop();
                switch (token.index)
                {
                  case "!":
                    stack.push("(!" + n1 + ")");
                    break;
                  case "-":
                    stack.push("(-" + n1 + ")");
                    break;
                  default:
                    stack.push(f + "(" + n1 + ")");
                    break;
                }
                break;
              case TOKEN_FUNCTION:
                var n1 = stack.pop();
                var fn = stack.pop();
                stack.push(fn + "(" + n1 + ")");
                break;
              default:
                throw new adf.mf.ELException("Invalid ELExpression");
            }
          }
          if (stack.length > 1)
          {
            console.log(stack);
            throw new adf.mf.ELException("Invalid ELExpression (incorrect number of operands)");
          }

          this._indexedExpr = stack[0];
        }

        return this._indexedExpr;
      },

      getExpression: function() {
        return "#{" + this.getExpressionString() + "}";
      },

      /**
       * Evaluates the expression as an lvalue and determines if {@link #setValue(ELContext, Object)}
       * will always fail.
       *
       * @param context used to resolve properties (<code>base.property</code> and <code>base[property]</code>)
       * and to determine the result from the last base/property pair
       * @return <code>true</code> if {@link #setValue(ELContext, Object)} always fails.
       * @throws ELException if evaluation fails (e.g. property not found, type conversion failed, ...)
       */
      /* boolean */
      isReadOnly: function() {
        if(this.readonly === null) {
          this.readonly = !((this.tokens.length > 0) && (this.tokens[0].type == TOKEN_VARIABLE));
        }
        return this.readonly;
      },

      /**
       * obtain all the variables this expression is dependent on.
       *
       * @returns {Array}
       */
      dependentObjects: function () {
        var tokenLength = this.tokens.length;
        var vars        = [];
        for (var i = 0; i < tokenLength; i++) {
          var item = this.tokens[i];
          if((item.type === TOKEN_VARIABLE) && (vars.indexOf(item.index) == -1)) {
            vars.push(item.index);
          }
        }
        return vars;
      },

      /**
       * convert the ELExpression to a context free expression.
       */
      dependencies: function () {
        if (!this.terms) {
          this.terms = [];
          var elTerms = this.toContextFreeExpression ().getELTerms();
          for (var i = 0; i < elTerms.length; i++) {
            var t = elTerms [i].getExpressionString ();
            this.terms.push (t);
          }
        }
        return this.terms;
      },

      /**
       * Get an array of context free expressions for each dependency in
       * the indexed EL syntax
       * @returns {Array.<string>} array of dependencies in indexed syntax
       */
      getIndexedDependencies: function()
      {
        if (this._indexedDependencies == null)
        {
          // Get all the dependencies in context free form
          var elTerms = this.toContextFreeExpression().getELTerms();
          var deps = [];

          // Cache the value so it only has to be computed once
          this._indexedDependencies = deps;

          for (var i = 0; i < elTerms.length; ++i)
          {
            deps.push(elTerms[i].getIndexedRepresentation());
          }
        }

        return this._indexedDependencies;
      },

      /**
       * Obtain all the terms this expression is dependent on.
       *
       * @returns {Array} This method returns array of ELExpressions.
       */
      getELTerms: function () {
        if (!this.elTerms)
          this.refresh ();
        return this.elTerms;
      },

      /**
       * Concatenates two ELExpressions.
       *
       * @param {Object} ELExpression to be concatenated to this ELExpression.
       */
      concat: function (elExpression) {
        var tokens = this.tokens.slice();
        var firstToken = elExpression.tokens [0];
        var newToken = new Token (TOKEN_PROPERTY, firstToken.index, firstToken.prior, firstToken.value);
        tokens.push(newToken);
        tokens.push(new Token (TOKEN_DOT_OFFSET, ".", -2, 0));
        adf.mf.internal.util.appendAll(tokens, elExpression.tokens, 1);
        var expression = this.getExpressionString() + '.' + elExpression.getExpressionString();
        return new ELExpression (tokens, expression, [expression], null);
      },

      /**
       * Appends array index access this ELExpression.
       *
       * @param {number} array index
       * @returns {Object} ELExpression.
       */
      appendIndex: function (index) {
        var tokens = this.tokens.slice();
        tokens.push (new Token (TOKEN_CONSTANT, 0, 3, index));
        tokens.push (new Token (TOKEN_INDEX, "[", -99, 0));
        var expression = this.getExpressionString() + (typeof index === "string" ? "['" + index + "']" : "[" + index + "]");
        return new ELExpression (tokens, expression, [expression], null);
      },

      cleanup: function(/* Array */ arr, /* Array */ exclude)
      {
        var dfarr  = adf.mf.util.removeDuplicates(arr);
        var elen   = exclude.length;
        var dlen   = dfarr.length;

        for(var e = 0; e < elen; ++e)
        {
          for(var d = 0; d < dlen; ++d)
          {
           if(dfarr[d] == exclude[e]) {
             dfarr.splice(d, 1);
           }
          }
        }

        // alert("cleanup("+arr.join(",")+" and " + exclude.join(",") + ")  ==> " + dfarr);
        return dfarr;
      },

      /**
       * This method computes this._expr, this.terms and this.elTerms variables
       * based on this.tokens value.
       */
      refresh: function () {
        var stack = [];
        var termStart = [];
        var termsMap = {};
        var terms = [];
        var elTerms = [];

        function finishTerm (el, from, to, term) {
          if (!termsMap[term]) {
            termsMap[term] = true;
            terms.push(term);
            var tokens = el.tokens.slice(from, to);
            elTerms.push(new ELExpression(tokens));
          }
        }

        function finishTerms (el, to) {
          for (var i = 2; i < arguments.length; i++) {
            var from = termStart.pop();
            if (from >= 0)
              finishTerm (el, from - 1, to - 1, arguments [i]);
            else
              from = -from;
            to = from;
          }
          termStart.push(-to);
        }

        for (var i = 0; i < this.tokens.length; i++) {
          var token = this.tokens[i];
          var type = token.type;

          if (type === TOKEN_CONSTANT) {
            stack.push(escape(token.value));
            termStart.push(-i-1);
          }
          else if (type === TOKEN_TERNARY_OPERATOR) {
            var n3 = stack.pop();
            var n2 = stack.pop();
            var n1 = stack.pop();
            stack.push("((" + n1 + ")? " + n2 + " : " + n3 + ")");
            finishTerms(this, i + 1, n3, n2, n1);
          }
          else if (type === TOKEN_COMMA) {
            var n2 = stack.pop();
            var n1 = stack.pop();
            stack.push("" + n1 + ", " + n2 + "");
            finishTerms(this, i + 1, n2, n1);
          }
          else if (type === TOKEN_COLON) {
            /* ignore */
          }
          else if (type === TOKEN_NO_ARGS) {
            stack.push("");
            termStart.push(-i-1);
          }
          else if (type === TOKEN_BINARY_OPERATOR) {
            var n2 = stack.pop();
            var n1 = stack.pop();
            stack.push("(" + n1 + " " + token.index + " " + n2 + ")");
            finishTerms(this, i + 1, n2, n1);
          }
          else if (type === TOKEN_INDEX) {
            var n2 = stack.pop();
            var n1 = stack.pop();
            var term = n1 + "[" + n2 + "]";
            stack.push(term);
            var from1 = termStart.pop();
            if (from1 >= 0)
              finishTerm(this, from1 - 1, i, n2);
          }
          else if (type === TOKEN_DOT_OFFSET) {
            var n2 = stack.pop();
            var n1 = stack.pop();
            var n3 = n1 + "." + n2;
            stack.push(n3);
          }
          else if (type === TOKEN_PROPERTY) {
            stack.push(token.index);
          }
          else if (type === TOKEN_VARIABLE) {
            stack.push(token.index);
            termStart.push(i + 1);
          }
          else if (type === TOKEN_UNARY_OPERATOR) {
            var n1 = stack.pop();
            var f = token.index;
            if (f === "!") {
              stack.push("(!" + n1 + ")");
            }
            else if (f === "-") {
              stack.push("(-" + n1 + ")");
            }
            else {
              stack.push(f + "(" + n1 + ")");
            }
            finishTerms(this, i + 1, n1);
          }
          else if (type === TOKEN_FUNCTION) {
            var n1 = stack.pop();
            var fn = stack.pop();
            var n2 = fn + "(" + n1 + ")";
            stack.push(n2);
            var from1 = termStart.pop();
            if (from1 >= 0)
              finishTerm (this, from1 - 1, i, n1);
          }
          else {
            throw new adf.mf.ELException("Invalid ELExpression");
          }
        }
        if (stack.length > 1) {
          throw new adf.mf.ELException("Invalid ELExpression (incorrect number of operands)");
        }
        if (termStart.length > 0) {
          var from = termStart.pop();
          if(from >= 0)
            finishTerm (this, from - 1, i, stack[0]);
        }
        this._expr = stack[0];
        this.elTerms = elTerms;
      },

      /**
       * This method clears cached values of local variables.
       */
      unflatten: function() {
        for (var i = 0; i < this.tokens.length; i++) {
          var token = this.tokens [i];
          if (token.flattened) {
            token.type = TOKEN_VARIABLE;
            token.value = 0;
            token.flattened = false;
          }
        }
      },

      // returns ELExpression
      toContextFreeExpression: function() {
        // for a context free version but not swapping out local variables.
        return this.stripLocalValues (true, undefined, false);
      },

      /**
       * Strips local values.
       *
       * @param {boolean} contextFree
       * @param {array} tokenReplacement
       * @param {boolean} replaceLocalVariables
       * @returns {ELExpression}
       */
      stripLocalValues: function(contextFree, tokenReplacement, replaceLocalVariables) {
        var result = null;
        for (var i = 0; i < this.tokens.length; i++)
        {
          var item = this.tokens[i];
          if (item.type === TOKEN_VARIABLE)
          {
            if(tokenReplacement != null)
            {
              var replacement = tokenReplacement[item.index];
              if (replacement) {
                if (!replacement.tokens) {
                  if(replacement.match(/[#$][{]/g) != null)
                  {
                    replacement = adf.mf.internal.el.parser.parse(replacement);
                  } else {
                    replacement = adf.mf.internal.el.parser.parse("#{" + replacement + "}");
                  }
                }
                result = replaceEL (result, this, i, replacement);
                continue;
              }
            }
            if(contextFree)
            {
              var elResolver = adf.mf.internal.context.getELResolver();
              try {
                var value = elResolver.getValue(adf.mf.internal.context, null, item.index);
                if((value != null) && (value.getAlias !== undefined))
                {
                  // change the variable to be it's context free (alias) form
                  var expandedEL = value.getAlias();
                  result = replaceEL (result, this, i, expandedEL);
                  continue;
                } else
                if(replaceLocalVariables && (value != undefined) && ((typeof value) !== 'object')) {
                  result = replaceValue (result, this, i, value);
                  continue;
                }
              } catch (ex) {
              }
            }
          }
          if (result)
            result.tokens.push (item);
        } // for i

        if (result)
          return result.getEL ();
        this._expr = null;
        return this;
      },

      toString: function() {
          // for a non-context free version and not swapping out local variables.
          return this.getExpressionString();
      }
    };

    function replaceValue (result, el, index, value) {
      if (!result)
        result = new Result (el, index);
      result.tokens.push (new Token(TOKEN_CONSTANT, 0, 0, value));
      return result;
    }

    function replaceEL (result, el, index, insertedEL) {
      if (!result)
        result = new Result (el, index);
      adf.mf.internal.util.appendAll(result.tokens, insertedEL.tokens);
      return result;
    }

    function Result (el, index) {
      this.tokens = el.tokens.slice (0, index);
    }

    Result.prototype.getEL = function () {
      return new ELExpression (this.tokens, null, null, null);
    };


    function ELParser() {
      this.success     = false;
      this.errormsg    = "";
      this.expression  = "";

      this.pos         = 0;

      this.value       = 0;
      this.prior       = 3;
      this.token       = 0;
      this.pmatch      = 0;

    }

    /*
     * Expression Language BNF - taken from the JavaServer Pages 2.0 Specification (Section JSP.2.9 Collected Syntax)
     *
     * Expression           ::= Expression1 ExpressionRest?
     * ExpressionRest       ::= '?' Expression ':' Expression
     * Expression1          ::= Expression BinaryOp Expression | UnaryExpression
     * BinaryOp             ::= 'and' | '&&' | 'or' | '||' | '+' | '-' | '*' | '/' | 'div' | '%' | 'mod' |
     *                          '<' | 'gt' |'>' | 'lt' | '<=' | 'ge' |'>=' | 'le' | '==' | 'eq' | '=!' | 'ne'
     * UnaryExpression      ::= UnaryOp UnaryExpression |   Value
     * UnaryOp              ::= '-' | '!' | 'not' | 'empty'
     * Value                ::= ValuePrefix | Value ValueSuffix
     * ValuePrefix          ::= Literal | '(' Expression ')' | Identifier except for ImplicitObject |
     *                          ImplicitObject | FunctionInvocation
     * ValueSuffix          ::= '.' Identifier | '[' Expression ']'
     * Identifier           ::= Java language identifierCollected Syntax 1-83
     * ImplicitObject       ::= 'pageContext' | 'pageScope' | 'requestScope' | 'sessionScope' | 'applicationScope' |
     *                          'param' | 'paramValues' | 'header' | 'headerValues' | 'initParam' | 'cookie'
     * FunctionInvocation   ::= (Identifier ':')? Identifier '(' ( Expression ( ',' Expression )* )? ')'
     * Literal              ::= BooleanLiteral | IntegerLiteral | FloatingPointLiteral | StringLiteral | NullLiteral
     * BooleanLiteral       ::= 'true' | 'false'
     * StringLiteral        ::= '([^'\]|\'|\\)*' | "*(\\|"\|[\"^])"
     *                          i.e., a string of any characters enclosed by single or double quotes,
     *                                where \ is used to escape ', ", and \. It is possible to use single
     *                                quotes within double quotes, and vice versa, without escaping.
     * IntegerLiteral       ::= ['0'-'9']+
     * FloatingPointLiteral ::= (['0'-'9'])+ '.' (['0'-'9'])* Exponent? | '.' (['0'-'9'])+ Exponent? | (['0'-'9'])+ Exponent?
     * Exponent             ::= ['e','E'] (['+','-'])? (['0'-'9'])+
     * NullLiteral          ::= 'null'
     *
     * Notes
     * - An identifier is constrained to be a Java identifier - e.g., no -, no /, etc.
     * - A String only recognizes a limited set of escape sequences, and \ may not appear unescaped.
     * - The relational operator for equality is == (double equals).
     * - The value of an IntegerLiteral ranges from Long.MIN_VALUE to Long.MAX_VALUE
     * - The value of a FloatingPointLiteral ranges from Double.MIN_VALUE to Double.MAX_VALUE
     */
    ELParser.prototype = {
      parse: function (expr) {
        var insideExpression    = false;
        this.operatorStack      = [];
        this.tokenStack         = [];
        this.expected           = (OPENEXP);
        this.nooperands         = 0;
        var token               = null;
        this.pmatch             = 0;
        this.errormsg           = "";
        this.success            = true;
        this.expression         = expr;
        this.pos                = 0;
        this.text               = 0;
        this.expCount           = 0;
        this.currentELTermStart = {};
        this.currentTermStart   = {};
        this.elTerms            = [];
        this.terms              = [];
        this.termsMap           = {};

        /* look for nested EL expressions */
        if(expr.match(new RegExp(".*[$#]{[^}]*[$#]{")))  {
          this.parsingError(this.pos, "ERROR_EL_PARSER_NESTED_EL_NOT_SUPPORTED");
        }

        while (this.pos < this.expression.length) {
          if((this.expected & OPENEXP) == OPENEXP) {
            if (this.isExpressionDirective()) {
              /* we have a potential expression */
            }
            else {
              var txt = null;

              if (this.pos + 1 == this.expression.length) {
                txt = this.expression.substring(this.text, this.expression.length);
                if(this.text !== 0){
                  this.expCount++;
                }
              }
              else if (this.isOpenExpression()) {
                this.expCount++;
                txt           = this.expression.substring(this.text, this.pos - 1);
                this.expected = (PRIMARY | LPAREN | RPAREN | FUNCTION | SIGN | UNIOP | CLOSEEXP);
              }

              // in this case have something like: xxx#{...}
              // we want the xxx to be concatenated on the result of #{...}
              if((txt !== null) && (txt.length > 0)) {
                var t   = new Token(TOKEN_CONSTANT, 0, 0, txt);

                if(this.expCount++ > 1) {
                  this.token   = "+";   // should go to # if we should concat
                  this.prior   = 30;    // 30 = 3 (for add) * 10 (to ensure it is always the last precedence)
                  this.nooperands  += 2;
                  this.addfunc(TOKEN_BINARY_OPERATOR);
                  this.text    = this.pos;
                }

                this.tokenStack.push(t);
              }
            }
            this.pos++;
          }
          else
            if (parseToken(this)) {
            }
          else {
            if (this.errormsg === "") {
              var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNKNOWN_CHAR_FOUND", [this.expression.charAt(this.pos)]);

              this.parsingError(this.pos, rmsg);
            }
            else {
              this.parsingError(this.pos, this.errormsg);
            }
          }
        }
        if (insideExpression) {
          var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_MISSING_ENDING");

          this.parsingError(this.pos, rmsg);
        }
        if (this.pmatch != 0) {
          var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_MISMATCH");

          this.parsingError(this.pos, rmsg);
        }
        while (this.operatorStack.length > 0) {
          var tmp = this.operatorStack.pop();
          this.tokenStack.push(tmp);
        }
        if(this.tokenStack.length == 0) {
          token = new Token(TOKEN_CONSTANT, 0, 0, "");
          this.tokenStack.push(token);
        }
        if (this.nooperands + 1 !== this.tokenStack.length) {
          var  msg = "{";
          for(var i=0; i < this.tokenStack.length; ++i) {
            msg += " token["+i+"] = '" + this.tokenStack[i].toString() + "' ";
          }
          msg += "}";

          var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_INCORRECT_OPERANDS",
                                                                    [("[" + (this.nooperands + 1) + ", " + this.tokenStack.length + "]"), msg]);

          this.parsingError(this.pos, rmsg);
        }
        return new ELExpression(this.tokenStack, null, null, this.elTerms);
      },

      finishTerm: function (endPosition)
      {
        this.finishTermTokens();

        var termStart = this.currentTermStart[this.pmatch];
        if (!termStart)
        {
          return;
        }

        var term = this.expression.substr(termStart, endPosition - termStart).trim();
        var elTermStart = this.currentELTermStart[this.pmatch];
        var newELTerm = null;

        if (!this.termsMap[term])
        {
          if (elTermStart === 0 &&
              endPosition === this.expression.length)
          {
            newELTerm = this;
          }
          else
          {
            var termExpression = this.tokenStack.slice(elTermStart);
            newELTerm = new ELExpression(termExpression, term, [term], null);
          }

          this.elTerms.push(newELTerm);
          this.termsMap[term] = true;
        }

        this.currentTermStart[this.pmatch]   = null;
        this.currentELTermStart[this.pmatch] = null;
      },

      evaluate: function (expr, variables) {
        var /* ELExpression */ elExpr = this.parse(expr);
        var /* var array    */ vars   = [];
        var /* return value */ v;

        try
        {
          v = elExpr.evaluate(variables);
        }
        catch(e)
        {
          try
          {
            vars = elExpr.dependencies();
          }
          catch(e2)
          {
            vars = [];
          }
          vars.push(expr);

          var  rmsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_EL_PARSER_UNABLE_TO_RESOLVE", [expr]);

          teval = {"msg": rmsg, "variables": vars};
          throw e;
        }
        return v;
      },

      parsingError: function (column, msg) {
        this.success = false;
        this.errormsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_IN_EL_PARSING", [msg, column, this.expression]);

        /* this is a huge issue, log a message in case the caller is not catching exceptions */
              adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "adf.mf.internal.el.parser", "EL Parser", this.errormsg);

        throw new adf.mf.ELException(this.errormsg);
      },

      finishTermTokens: function ()
      {
        while (this.operatorStack.length > 0)
        {
          if (this.operatorStack[this.operatorStack.length - 1].type == TOKEN_DOT_OFFSET)
          {
            this.tokenStack.push(this.operatorStack.pop());
          }
          else
          {
            break;
          }
        }
      },

      addfunc: function (type) {
        var operator = new Token(type, this.token, this.prior - this.pmatch, 0);
        while (this.operatorStack.length > 0) {
          if (operator.prior >= this.operatorStack[this.operatorStack.length - 1].prior) {
            this.tokenStack.push(this.operatorStack.pop());
          }
          else {
            break;
          }
        }
        this.operatorStack.push(operator);
      },

      /**
       * unescape an input string into a normal string
       *
       * @param input string to unescape
       * @param pos   in the overall expression we are unescaping
       *
       * @returns the unescaped string
       */
      unescape: function(input, pos) {
        var buf    = [];
        var escape = false;

        for (var i = 0; i < input.length; i++) {
          var c = input.charAt(i);

          if (! escape) {
            if (c == '\\') { /* turn on escaping */
              escape = true;
            } else {  /* non-escaped character, just add it to the buffer */
              buf.push(c);
            }
          } else { /* character following the escape character \\ */
            switch (c) {
            case '\\': buf.push('\\'); break;
            case '/':  buf.push('/');  break;
            case 'b':  buf.push('\b'); break;
            case 'f':  buf.push('\f'); break;
            case 'n':  buf.push('\n'); break;
            case 'r':  buf.push('\r'); break;
            case 't':  buf.push('\t'); break;
            case 'u':  /* following 4 chars make up the hex code for the character */
              var unicodeCode = parseInt(input.substring(i+1, i+5), 16);
              buf.push(String.fromCharCode(unicodeCode)); // add the string representation of the unicode char
              i += 4;
              break;
            default:
              throw this.parsingError(pos + i, "Illegal escape sequence: '\\" + c + "'");
            }
            escape = false;
          }
        }

        return buf.join('');  /* convert the array to a single string */
      },

      /**
       * Determine if the next token is a sign token (- or +)
       *
       * @returns {Boolean}
       */
      isSign: function () {
        var code = this.expression.charCodeAt(this.pos - 1);
        return (code === 45 || code === 43); // - or +
      },

      /**
       * Determine if the next token is a negative sign token
       *
       * @returns {Boolean}
       */
      isNegativeSign: function () {
        return (this.expression.charCodeAt(this.pos - 1) === 45); // -
      },

      /**
       * Determine if the next token is a dot token
       *
       * @returns {Boolean}
       */
      isDot: function () {
        var code = this.expression.charCodeAt(this.pos);
        if (code === 46) { // .
          this.pos++;
          this.prior = 0;
          return true;
        }
        return false;
      },

      /**
       * Determine if the next token is the open expression token
       *
       * @returns {Boolean}
       */
      isOpenExpression: function () {
        var code = this.expression.charCodeAt(this.pos);
        if ((code === 123) && (this.directive+1 == this.pos)){ // {
          // this.pos++;
          return true;
        }
        return false;
      },

      /**
       * Determine if this is a expression directive.
       *
       * @returns {Boolean}
       */
      isExpressionDirective: function () {
        var status = false;
        var code   = this.expression.charCodeAt(this.pos);

        if ((code === 35 /* # */) || (code === 36 /* $ */)) {
          this.directive = this.pos;
          status         = true;
        }
        return status;
      },

      /**
       * Determine if the next token is ends the operator
       *
       * @returns {Boolean}
       */
      isOperatorBoundary: function (pos) {
        var code = this.expression.charCodeAt(pos);

        if (code === 40 /* (     */ ||
          code === 32 /* space */ ||
          code ===  9 /* tab   */ ||
          code === 10 /* LF    */ ||
          code === 13 /* CR    */) {
          return true;
        }
        return false;
      },

      /**
       * Determine if the next token is a variable token
       *
       * @returns {Boolean}
       */
      isVariable: function () {
        var str       = "";
        var length    = 0;

        for (var i = this.pos; i < this.expression.length; i++) {
          var c           = this.expression.charAt(i);
          var includeChar = true;

          /* see if this character is not a valid character for a name */
          if(str === "") {
            /* first character must be alpha except for compressed keys
             * which will start with _ (and be in the form of _999)
             */
            if((c.toLowerCase() == c.toUpperCase()) && (c !== '_')) {
              break;
            }
          }

          if(c.toLowerCase() == c.toUpperCase()) {
            if(((c >= '0') && (c <= '9')) ||    /* numbers are validate             */
               ((c == '.') || (c == '_'))) {    /* dot and underscore are also okay */
              }else break;
          }

          length++;
          if(includeChar) {
            str += c;
          }
        }
        if (str.length > 0) {
          this.token  = str;
          this.prior  = 3;
          this.pos   += length; // str.length;
          return true;
        }
        return false;
      },

      /**
       * Determine if the next token is a comment token.
       *
       * @returns {Boolean}
       */
      isComment: function () {
        /*
         * remember we need to look back one character for the slash since it might
         * have been picked up as a unary or binary operation token.
         */
        var code = this.expression.charCodeAt(this.pos - 1);

        if ((code === 47 /* slash */) && (this.expression.charCodeAt(this.pos) === 42 /* start */)) {
          this.pos = this.expression.indexOf("*/", this.pos) + 2; /* eat all those characters */

          if (this.pos === 1) {
            this.pos = this.expression.length;
          }
          return true;
        }
        return false;
      }
    };

    function escape(v) {
      var quote     = "\""; // "'";
      var escapable = /[\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
      var meta      = { '\b': '\\b','\t': '\\t','\n': '\\n','\f': '\\f','\r': '\\r','\\': '\\\\' };
      if ((typeof v) === "string") {
        escapable.lastIndex = 0;
            return escapable.test(v) ?
                quote + v.replace(escapable, function (a) {
                      var c = meta[a];
                      return ((typeof c === 'string')? c :
                          '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4));
                  }) + quote :
                  quote + v + quote;
      }
      return v;
    }

    return ELParser;
  }();
})();


/////////////////////////////////////// end of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/ELParser.js///////////////////////////////////////



/////////////////////////////////////// start of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/Utilities.js///////////////////////////////////////

/* Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved. */
/* ------------------- Utilities.js ---------------------- */
// @requires AdfPerfTiming


var adf                                       = window.adf                                    || {};
adf.mf                                        = adf.mf                                        || {};
adf.mf.api                                    = adf.mf.api                                    || {};
adf.mf.el                                     = adf.mf.el                                     || {};
adf.mf.locale                                 = adf.mf.locale                                 || {};
adf.mf.log                                    = adf.mf.log                                    || {};
adf.mf.resource                               = adf.mf.resource                               || {};
adf.mf.util                                   = adf.mf.util                                   || {};

adf.mf.internal                               = adf.mf.internal                               || {};
adf.mf.internal.api                           = adf.mf.internal.api                           || {};
adf.mf.internal.converters                    = adf.mf.internal.converters                    || {};
adf.mf.internal.converters.dateParser         = adf.mf.internal.converters.dateParser         || {};
adf.mf.internal.converters.dateParser.iso8601 = adf.mf.internal.converters.dateParser.iso8601 || {};
adf.mf.internal.el                            = adf.mf.internal.el                            || {};
adf.mf.internal.el.parser                     = adf.mf.internal.el.parser                     || {};
adf.mf.internal.locale                        = adf.mf.internal.locale                        || {};
adf.mf.internal.log                           = adf.mf.internal.log                           || {};
adf.mf.internal.mb                            = adf.mf.internal.mb                            || {};
adf.mf.internal.perf                          = adf.mf.internal.perf                          || {};
adf.mf.internal.resource                      = adf.mf.internal.resource                      || {};
adf.mf.internal.util                          = adf.mf.internal.util                          || {};


/**
 * startBatchRequest marks the start of the batch request.  Once this function is called
 * all subsequent requests to the java layer will be deferred until the flushBatchRequest.
 * Between the start and flush batch request markers, all requests success callbacks will
 * be called with deferred object ({.deferred:true}) response object.
 *
 * @see adf.mf.util.flushBatchRequest
 */
/* void */
adf.mf.util.startBatchRequest = function()
{
  if(adf.mf.internal.batchRequest != undefined)
  {
    throw new adf.mf.ELException("Batch Request already started.");
  }
  adf.mf.internal.batchRequest = [];
};


/* boolean */
adf.mf.util.isException = function(/* exception object */ obj)
{
  var o       = ((obj != undefined) && ('object' == typeof obj))? obj : {};
  var e       = (o[adf.mf.internal.api.constants.EXCEPTION_FLAG_PROPERTY] === true);

  if(e)  return e;
  else { /* lets see if it ends with Exception */
    return adf.mf.util.isType(o, "Exception");
  }
};


/* boolean */
adf.mf.util.isType = function(/* object */ obj, /* type name */ tname)
{
  var o       = ((obj != undefined) && ('object' == typeof obj))? obj : {};
  var type    = o[adf.mf.internal.api.constants.TYPE_PROPERTY] || "unknown";

  return (type.length == tname.length)?
    (type == tname) :
    (type.indexOf(tname, type.length - tname.length) != -1);
};


/**
 * Where startBatchRequest marks the start of the batch request, flushBatchRequest marks
 * the end of the batch and flushes (processes) the requests.  The caller can determine
 * if the flush should abort of the first error or continue to completion by passing either
 * true or false in the abortOnError parameter.  Regardless, the success callbacks will
 * be called in order if the batch is deemed successful otherwise the failed callbacks
 * will be invoked.  The callbacks parameters will be a vector of requests/responses one
 * for each request that was batched.
 *
 * @see adf.mf.util.startBatchRequest
 */
/* void */
adf.mf.util.flushBatchRequest = function(/* boolean abortOnError, callback success, callback failed, [boolean ignoreErrorMessages]*/)
{
  var argv  = arguments;
  var argc  = arguments.length;
  var scb   = [];
  var fcb   = [];

  if (argc!=4 && argc!=3 && argc!=2)
  {
    throw new adf.mf.ELException("Wrong number of arguments");
  }

  var abortOnError   = argv[0] || false;
  var errorHandler   = ((argc == 4) && (argv[3] == true))?
    adf.mf.internal.api.nvpEatErrors :
    adf.mf.internal.api.arraySimulatedErrors;
  var perf = adf.mf.internal.perf.start("adf.mf.util.flushBatchRequest");
  scb = scb.concat([errorHandler]);
  scb = scb.concat((adf.mf.internal.util.is_array(argv[1]))? argv[1] : [argv[1]]);
  scb = scb.concat([function() { perf.stop(); }]);

  fcb = fcb.concat((adf.mf.internal.util.is_array(argv[2]))? argv[2] : [argv[2]]);
  fcb = fcb.concat([function() { perf.stop(); }]);

  try
  {
    if((adf.mf.internal.batchRequest === undefined) || (adf.mf.internal.batchRequest === null))
    {  /* so we do not have a defined batch request */
      throw new adf.mf.IllegalStateException("batch request is not defined");
    }

    if(adf.mf.internal.batchRequest.length > 0)
    {  /* so we have pending requests */
      if(adf.mf.internal.isJavaAvailable())
      {
        var   requests = adf.mf.internal.batchRequest.slice(0);

        if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINEST))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINEST,
            "adf.mf.util", "flushBatchRequest",
            ("batch request contains " + requests.length + " requests."));
        }

        adf.mf.internal.batchRequest = undefined;
        adf.mf.api.invokeMethod("oracle.adfmf.framework.api.Model",
            "processBatchRequests", abortOnError, requests, scb, fcb);
      }
      else
      {
        throw new adf.mf.IllegalStateException("invalid environment defined for batch request");
      }
    }
    else
    {  /* this is okay, let the called know we are done */
      adf.mf.internal.batchRequest = undefined;
      for(var i = 0; i < scb.length; ++i) { try { scb[i](); }catch(e){}; }
    }
  }
  catch(e)
  {  /* this is not good, let the caller know */
    for(var i = 0; i < fcb.length; ++i) { try { fcb[i](); }catch(e){}; }
  }
};


/**
 * Get the context free version of the passed in EL expression.
 **/
/* String */
adf.mf.util.getContextFreeExpression = function(/* EL Expression */ el)
{
  return adf.mf.internal.el.parser.parse(el).toContextFreeExpression().getExpression();
};


/**
 * remove array entry
 */
/* array */
adf.mf.util.removeArrayEntry = function(/* Array */ arr, /* Object */ obj)
{
	var temp = [];

	if(adf.mf.internal.util.is_array(arr))
	{
		for(var i = 1; i < arr.length; ++i)
		{
			if(arr[i] !== obj)
			{
				temp.push(arr[i]);
			}
		}
	}
	else
	{
		throw new adf.mf.IllegalStateException("array was not passed");
	}
	return temp;
};

/**
 * remove duplicate entries from the array 'arr'.  If 'arr' is
 * not an array the 'arr' object is simply returned.
 */
/* array */
adf.mf.util.removeDuplicates = function(/* Array */ arr)
{
  if(adf.mf.internal.util.is_array(arr))
  {
    arr.sort();
    for(var i = 1; i < arr.length; )
    {
      if(arr[i-1] == arr[i])
      {
        arr.splice(i, 1);
      }
      else
      {
        i++;
      }
    }
  }
  return arr;
};

function elExpressionReplacer (key, value) {
	if (value && value._expr && value.tokens)
		return value._expr;
	return value;
}

/**
 * Convert the passed in object into a string representation for printing.
 *
 * @param   dat - data object to be converted
 * @returns string representation of the dat object
 */
/* String */
adf.mf.util.stringify = function(/* object */ dat)
{
  // Stringify is potentially costly, so profile it. The function call forwarding is made so
  // recursion does not spit out bunch of log timestamps
  var perf = adf.mf.internal.perf.start("adf.mf.util.stringify");
  try
  {
    // Forward the call so calee can recurse
    return JSON.stringify(dat, elExpressionReplacer);
  }
  finally
  {
    perf.stop();
  }

  return return_value;
};


/**
 * Return the number of milliseconds since 01 January, 1970 UTC that the provided
 * date string represents. Attempt to use the native Date.parse, and fall back to
 * adf.mf.internal.converters.dateParser.iso8601.parse if the native one returns
 * NaN. Returns NaN if a valid date cannot be parsed.
 *
 * @param   dateString - string containing a date in a format supported natively,
              or ISO-8601
 * @returns the number of ms since 01 January, 1970 UTC, or NaN if not parsable
 */
/* Number */
adf.mf.internal.converters.dateParser.parse = function(dateStr)
{
  var dateParse = Date.parse(dateStr);

  if (isNaN(dateParse))
  {
    dateParse = adf.mf.internal.converters.dateParser.iso8601.parse(dateStr);
  }

  return dateParse;
};


/**
 * Return the number of milliseconds since 01 January, 1970 UTC that the provided
 * ISO 8601 date string represents.
 *
 * @param   iso8601String - ISO 8601 formatted date string
 * @returns the number of ms since 01 January, 1970 UTC, or NaN if not parsable
 *
 * Most of the information for this standard to support was taken from:
 * http://en.wikipedia.org/wiki/ISO_8601
 *
 * The following ISO 8601 formats are supported by this parser. For now, the date string
 * must be of the format <date> or <date>T<time>, not just a <time>.
 *
 * Dates:
 * YYYY
 * YYYY-MM-DD
 * YYYY-MM
 * YYYYMMDD
 *
 * Times:
 * hh:mm:ss
 * hh:mm
 * hhmmss
 * hhmm
 * hh
 *
 * Decimal fractions may also be added to any of the three time elements. A decimal mark,
 * either a comma or a dot (without any preference as stated in resolution 10 of the 22nd
 * General Conference CGPM in 2003, but with a preference for a comma according to ISO
 * 8601:2004) is used as a separator between the time element and its fraction. A fraction
 * may only be added to the lowest order time element in the representation. To denote "14
 * hours, 30 and one half minutes", do not include a seconds figure. Represent it as
 * "14:30,5", "1430,5", "14:30.5", or "1430.5". There is no limit on the number of decimal
 * places for the decimal fraction.
 *
 * Time zone designators:
 * <time>Z
 * <time>��hh:mm
 * <time>��hhmm
 * <time>��hh
 *
 * When the ISO 8601 string is applied against the regular expression, matches[] should contain
 * the following values. For MM/DD/mm/ss, only one of the corresponding array indicies for each
 * will contain data, the other remaining undefined. Which ones are populated depend on the use
 * of separator characters ('-', ':') in the ISO 8601 string.
 *
 * matches[1]   YYYY <- YYYY-MM-DDThh:mm:ss.fffZ        (the year)
 * matches[2]   MM   <- YYYYMMDDThh:mm:ss.fffZ          (the month when no hyphen separates year & month)
 * matches[3]   DD   <- YYYYMMDDThh:mm:ss.fffZ          (the day when no hyphen separates month & day)
 * matches[4]   MM   <- YYYY-MM-DDThh:mm:ss.fffZ        (the month when a hyphen separates year & month)
 * matches[5]   DD   <- YYYY-MM-DDThh:mm:ss.fffZ        (the day when a hyphen separates month & day)
 * matches[6]   hh   <- YYYY-MM-DDThh:mm:ss.fffZ        (the hours)
 * matches[7]   mm   <- YYYY-MM-DDThh:mm:ss.fffZ        (the minutes when no colon separates hours & minutes)
 * matches[8]   ss   <- YYYY-MM-DDThh:mm:ss.fffZ        (the seconds when no colon separates minutes & seconds)
 * matches[9]   mm   <- YYYY-MM-DDThhmmss.fffZ          (the minutes when a colon separates hours & minutes)
 * matches[10]  ss   <- YYYY-MM-DDThhmmss.fffZ          (the seconds when a colon separates minutes & seconds)
 * matches[11]  fff  <- YYYY-MM-DDThh.fffZ              (the fractional hours)
 *          or  fff  <- YYYY-MM-DDThhmm.fffZ            (the fractional minutes, with or without colon separator)
 *          or  fff  <- YYYY-MM-DDThhmmss.fffZ          (the fractional seconds, with or without colon separator)
 * matches[12]  Z    <- YYYY-MM-DDThh:mm:ss.fffZ        (Zulu time, aka +00:00)
 * matches[13]  ��    <- YYYY-MM-DDThh:mm:ss.fff��zh:zm   ('+' or '-'; the direction of the timezone offset)
 * matches[14]  zh   <- YYYY-MM-DDThh:mm:ss.fff-zh:zm   (the hours of the time zone offset)
 * matches[15]  zm   <- YYYY-MM-DDThh:mm:ss.fff-zh:zm   (the minutes of the time zone offset)
 */
/* Number */
adf.mf.internal.converters.dateParser.iso8601.parse = function(iso8601Str)
{
  var re = /^(\d{4})(?:(\d{2})(\d{2})|-(\d{2})(?:-(\d{2}))?)?(?:T(\d{2})(?::(\d{2})(?::(\d{2}))?|(\d{2})(?:(\d{2}))?)?(?:[,\.](\d+))?(?:(Z)|([+\-])(\d{2})(?::?(\d{2}))?)?)?$/;
  var matches = re.exec(iso8601Str);

  if (!matches)
  {
    return NaN;
  }

  var pc = this.constants;

  // assign parsed values to correct units, initializing with default if unspecified
  var year     = matches[pc.YEAR];
  var month    = matches[pc.MONTH]    || matches[pc.MONTH_HYPHEN]  || "1";
  var day      = matches[pc.DAY]      || matches[pc.DAY_HYPHEN]    || "1";
  var hours    = matches[pc.HOURS]    || "0";
  var minutes  = matches[pc.MINUTES]  || matches[pc.MINUTES_COLON] || "0";
  var seconds  = matches[pc.SECONDS]  || matches[pc.SECONDS_COLON] || "0";
  var fraction = matches[pc.FRACTION] || "0";
  var zulu     = matches[pc.ZULU];

  year = parseInt(year, 10);
  month = parseInt(month, 10);
  day = parseInt(day, 10);

  hours = parseInt(hours, 10);
  minutes = parseInt(minutes, 10);
  seconds = parseInt(seconds, 10);

  var fractionMillis = 0;

  // if fraction specified, determine which time part it belongs to and compute additional ms
  if (matches[pc.FRACTION])
  {
    fraction = parseFloat("." + fraction);

    if (matches[pc.SECONDS] || matches[pc.SECONDS_COLON])
    {
      fractionMillis = Math.round(fraction * 1000);       // 1000 = ms / second
    }
    else if (matches[pc.MINUTES] || matches[pc.MINUTES_COLON])
    {
      fractionMillis = Math.round(fraction * 60000);      // 60 * 1000 = ms / minute
    }
    else
    {
      fractionMillis = Math.round(fraction * 3600000);    // 60 * 60 * 1000 = ms / hour
    }
  }

  // create date from time parts (month is zero-based)
  var dateMillis = Date.UTC(year, month - 1, day, hours, minutes, seconds);

  dateMillis += fractionMillis;

  // adjust for timezone
  if (!zulu)
  {
    var tzPlus    = matches[pc.TZ_PLUS];
    var tzHours   = matches[pc.TZ_HOURS]   || "0";
    var tzMinutes = matches[pc.TZ_MINUTES] || "0";

    var offsetMillis = parseInt(tzHours, 10) * 3600000;   // 60 * 60 * 1000
    offsetMillis += parseInt(tzMinutes, 10) * 60000;      // 60 * 1000;

    if (tzPlus == "+")
    {
      dateMillis += offsetMillis;
    }
    else
    {
      dateMillis -= offsetMillis;
    }
  }

  return dateMillis;
};


adf.mf.internal.converters.dateParser.iso8601.constants =
{
  YEAR:          1,
  MONTH:         2,
  DAY:           3,
  MONTH_HYPHEN:  4,
  DAY_HYPHEN:    5,
  HOURS:         6,
  MINUTES:       7,
  SECONDS:       8,
  MINUTES_COLON: 9,
  SECONDS_COLON: 10,
  FRACTION:      11,
  ZULU:          12,
  TZ_PLUS:       13,
  TZ_HOURS:      14,
  TZ_MINUTES:    15
};

/**
 * INTERNAL FUNCTION used to do token subsitution on the passed in expression
 * with the replacementStack of name/value objects.
 */
adf.mf.internal.util.tokenSubsitution = function(/* string */ expression, /* array */ replacementStack)
{
	var result = expression;

	for(var i = 0; i < replacementStack.length; ++i)
	{
		var  replaceMap = replacementStack[i];
		var  ele        = adf.mf.internal.el.parser.parse(result);

		result = ele.stripLocalValues(true, replaceMap, false).getExpression();
	}
	return result;
};

/**
 * INTERNAL FUNCTION used to do strip the local values and token subsitution in one step.
 */
adf.mf.internal.util.stripLocalValues = function(/* string */ expression, /* context free */ bContextFree, /* array */ replacementStack)
{
		if (!expression.tokens)
			expression = adf.mf.internal.el.parser.parse(expression);

	if(replacementStack != null)
	{
		for(var i = 0; i < replacementStack.length; ++i)
		{
			var replaceMap = replacementStack[i];
			expression = expression.stripLocalValues(bContextFree, replaceMap, true);
		}
	}
	else
	{
		expression  = expression.stripLocalValues(bContextFree, null, true);
	}
	return expression;
};


/**
 * Internal method to determine if the expression is valid as a
 * left hand expression for assignments.
 */
adf.mf.internal.util.isAssignable = function(/* string */ expression)
{
	var c1  = false;
	var c2  = false;
	var exp = expression;

	// since the term is allowed to have wrapping parenthesis we need to remove them if they exist
	exp = exp.replace(/^\(/g, " ").replace(/\)$/g, " ").trim();

	// since an array element is a valid LHS token, remove all characters in and including the brackets [.*]
	exp = exp.replace(/\[[^\]]*\]/g, "replace");

	// remove all the numbers from the expression to remove numeric constants
	exp = exp.replace(/[0-9]/g, "");

	// now look for any operators or parenthesis, denoting more than a single token remains
	c1  = (exp.search(/[!%&|\+\-\*\/\(]/i) == -1);

	// make sure we still have characters, i.e. letters for the variable
	c2  = (exp.length > 0);

	return (c1 && c2);
};


/**
 * INTERNAL FUNCTION used to determine if the input is an array or not
 */
adf.mf.internal.util.is_array = function(input)
{
	return typeof(input)=='object'&&(input instanceof Array);
};

/**
 * INTERNAL FUNCTION appends array2, to array2. It does not create new array.
 */
adf.mf.internal.util.appendAll = function(array1, array2, array2From)
{
	if (!array2From) array2From = 0;
	for (var i = array2From; i < array2.length; i++)
		array1.push(array2[i]);
};

adf.mf.util.obfuscate = function(s) {
	return s.replace(/[a-zA-Z]/g, function(c) {
		return String.fromCharCode((c <= "Z"? 90 : 122) >= (c = c.charCodeAt(0) + 13) ? c : c - 26);
	});
};

/**
 * Check if a string starts with another string
 *
 * @param {?string} findIn the string to check against
 * @param {?string} strToFind the string to look for
 * @return {boolean} true if findIn is a string and strToFind is a string and findIn starts with
 *         strToFind
 */
adf.mf.util.stringStartsWith = function(
	findIn,
	strToFind)
{
	return findIn != null &&
		strToFind != null &&
		strToFind.length > 0 &&
		findIn.length >= strToFind.length &&
		findIn.substring(0, strToFind.length) == strToFind;
};

/**
 * Check if a string ends with another string
 *
 * @param {?string} findIn the string to check against
 * @param {?string} strToFind the string to look for
 * @return {boolean} true if findIn is a string and strToFind is a string and findIn ends with
 *         strToFind
 */
adf.mf.util.stringEndsWith = function(
	findIn,
	strToFind)
{
	return findIn != null &&
		strToFind != null &&
		strToFind.length > 0 &&
		findIn.length >= strToFind.length &&
		findIn.substring(findIn.length - strToFind.length) == strToFind;
};


/////////////////////////////////////// end of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/Utilities.js///////////////////////////////////////



/////////////////////////////////////// start of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/TreeNode.js///////////////////////////////////////

/* Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved. */
/* ------------------- TreeNode.js ---------------------- */
// @requires ELErrors
// @requires AdfPerfTiming

// @requires JavaScriptContext


var adf                    = window.adf                 || {};
adf.mf                     = adf.mf                     || {};
adf.mf.api                 = adf.mf.api                 || {};
adf.mf.el                  = adf.mf.el                  || {};
adf.mf.locale              = adf.mf.locale              || {};
adf.mf.log                 = adf.mf.log                 || {};
adf.mf.resource            = adf.mf.resource            || {};
adf.mf.util                = adf.mf.util                || {};

adf.mf.internal            = adf.mf.internal            || {};
adf.mf.internal.api        = adf.mf.internal.api        || {};
adf.mf.internal.el         = adf.mf.internal.el         || {};
adf.mf.internal.el.parser  = adf.mf.internal.el.parser  || {};
adf.mf.internal.locale     = adf.mf.internal.locale     || {};
adf.mf.internal.log        = adf.mf.internal.log        || {};
adf.mf.internal.mb         = adf.mf.internal.mb         || {};
adf.mf.internal.perf       = adf.mf.internal.perf       || {};
adf.mf.internal.resource   = adf.mf.internal.resource   || {};
adf.mf.internal.util       = adf.mf.internal.util       || {};

(function() {

  var collectionModelExtensionELExpression = adf.mf.internal.el.parser.parse(
    "#{collectionModel.treeNodeBindings.providers}");

  function TreeNode(/* TreeBinding */ tb, /* index */ index, /* ELExpression */ treeBindingEL)
  {
    this.id       = tb.id;
    this.index    = index;
    this.modid    = adf.mf.internal.context.getModId();
    this.treeBindingEL = treeBindingEL;
    this.alias    = null;

    this.getAlias = function (/* boolean */ compressed)
    {
      if (compressed) {
        var ref     = this.id + ".collectionModel.treeNodeBindings.providers['" + this.getKey() + "']";
        var alias   = adf.mf.internal.api.addCompressedReference(ref);
        adf.mf.log.Framework.logp(adf.mf.log.level       .FINE, "TreeNode", "getAlias",
                              "alias="+alias+" expanded="+ref);
        return alias;
      }
      if (!this.alias) {
        this.alias = this.treeBindingEL.concat (collectionModelExtensionELExpression).appendIndex(this.getKey());
      }
      return this.alias;
    };

    /**
     * INTERNAL function to get the current collection
     * model stored for the given tree binding's id.
     */
    this.getTreeNodeBindings = function()
    {
      /* check to see if we already have the latest tree node bindings */
      if (this.modid == adf.mf.internal.context.getModId())
      {
        return tb.treeNodeBindings;
      }
      else
      { /* looks like the cache has been updated, be safe re-fetch */
        var cm = adf.mf.api.getLocalValue("#{" + this.id + ".collectionModel}");

        return (cm.treeNodeBindings || {});
      }
    };
    this.tnb = this.getTreeNodeBindings();

    /**
     * getProvider will update the current provider
     * reference and return it's value to the caller.
     */
    this.getProvider = function()
    {
      var key = this.getKey();

      this.provider = (key != undefined)? this.tnb.providers[key] : undefined;

      return this.provider;
    };

    /**
     * getBindings will return the associated
     * column bindings to the caller.
     */
    this.getBindings = function()
    {
      return this.tnb.columnBindings;
    };

    /*
     * get the current key value
     */
    this.getKey = function()
    {
      this.key = ((index < 0) || (index >= this.tnb.keys.length)) ?
        undefined : this.tnb.keys[index];

      return this.key;
    };

    this.rowKey = function()
    {
      return this.getKey();
    };

    this.provider = this.getProvider();
    this.key      = this.getKey();
    this.note     = '' + (typeof this) + ' with EL variable id: ' + this.id + ' on index ' +
      this.index;
    this['.type'] = 'oracle.adfmf.bindings.dbf.TreeNode';

    // If an attribute is null or an empty array, the value is not being sent by the embedded side.
    // As a result, a request for that attribute will result in a cache miss (undefined value) and
    // cause a round trip to the embedded side. By checking the column attributes during
    // the creation of this object, we can avoid the undefined values by setting the properties to
    // null when not present
    if (this.provider != null)
    {
      for (var attributeName in tb.columnAttributes)
      {
        if (this.provider[attributeName] === undefined &&
          (this.provider.bindings == null || this.provider.bindings[attributeName] === undefined))
        {
          // We are not given the meta-data if this attribute is a true attribute or an accessor.
          // The accessors are stored directly on the provider, but the attributes are stored on
          // the bindings object of the provider. Since we do not know, store the value on the
          // provider directly.
          this.provider[attributeName] = null;
        }
      }
    }
  };

  /**
   *
   * Here are some things to note about using the iterator.
   *
   * Step  1. Resolve #{bindings.products.collectionModel}
   *          adf.mf.api.getValue("#{bindings.products.collectionModel}",
   *                          function(a,b) {value = b[0].value; success();}, failed);
   *
   * Step  2. Resolve #{bindings.products.collectionModel.iterator}
   *          adf.mf.api.getValue("#{bindings.products.collectionModel.iterator}",
   *                          function(a,b) {bpci = b[0].value; success();}, failed);
   *
   * Step 3.  Jumping around the rows with the iterator
   *          Resolve iterator.first():
   *          bpci.first(function(a, b){ adf.mf.api.addVariable('row', b[0].value); success(); }, failed);
   *
   *          Resolve iterator.last():
   *          bpci.first(function(a, b){ adf.mf.api.addVariable('row', b[0].value); success(); }
   *                                function(a, b){ failed();});
   *
   *          Resolve iterator.previous():
   *          bpci.previous(function(a, b){ adf.mf.api.addVariable('row', b[0].value); success(); }, failed);
   *
   *          Resolve iterator.next():
   *          bpci.next(function(a, b){ adf.mf.api.addVariable('row', b[0].value); success(); }, failed);
   *
   * Step  4. Accessing iterator bindings:
   *          Resolve #{row.bindings}:
   *          adf.mf.api.getValue("#{row.bindings}",
   *                          function(a,b) {value = b[0].value; success();},
   *                          function(a,b) {showFailure("unable to resolve"); failure();});
   *
   *          Resolve #{row.bindings.name}:
   *          adf.mf.api.getValue("#{row.bindings.name}",
   *                          function(a,b) {value = b[0].value; success();},
   *                          function(a,b) {showFailure("unable to resolve");});
   *
   *          Resolve #{row.bindings.name.inputValue}:"
   *                     adf.mf.api.getValue("#{row.bindings.name.inputValue}",
   *                                     function(a,b) {value = b[0].value; success();},
   *                                     function(a,b) {showFailure("unable to resolve"); failure();});
   *
   *          Update #{row.bindings.name.inputValue}:"+ stringify(value) + "");
   *                     adf.mf.api.setValue({'name':"#{row.bindings.name.inputValue}", 'value':value},
   *                                      function() {showSuccess("Updated"); success();},
   *                                      function() {showFailure("Unable to updated");});
   *
   *          Resolve #{row.dataProvider}:
   *          adf.mf.api.getValue("#{row.dataProvider}",
   *                          function(a,b) {value = b[0].value; success();},
   *                          function(a,b) {showFailure("unable to resolve"); failure();});
   *
   *          Resolve #{row.dataProvider}:
   *          adf.mf.api.getValue("#{row.dataProvider.name}",
   *                          function(a,b) {value = b[0].value; success();},
   *                          function(a,b) {showFailure("unable to resolve"); failure();});
   *
   *          Update #{row.dataProvider.name}:
   *          adf.mf.api.setLocalValue({'name':"#{row.dataProvider.name}", 'value':value},
   *                               function() {showSuccess("Updated"); success();},
   *                               function() {showFailure("Unable to updated"); failure();});
   *
   * Step 5: Register some data change listeners on #{bindings.products.collectionModel}:
   *         adf.mf.api.addDataChangeListeners("#{bindings.products.collectionModel}",
   *                                            function(v) {showChangeEvent("DCN 1 of " + stringify(v));});
   *         adf.mf.api.addDataChangeListeners("#{bindings.products.collectionModel}",
   *                                            function(v) {showChangeEvent("DCN 2 of " + stringify(v));});
   *         adf.mf.api.addDataChangeListeners("#{bindings.products.collectionModel}",
   *                                            function(v) {showChangeEvent("DCN 3 of " + stringify(v));});
   *
   * Step 6. Access the number of cached rows:
   *         var cr = bpci.getCachedRowCount(0);
   *
   * Step 7. Access the number of rows in a Range Size:
   *         var rs = bpci.getRangeSize();
   *
   * Step 8. Validating the data change event was processed correctly: ");
   *         {
   *            bpci.first(loopBody, function(e){ console.log("Error: first failed: " + stringify(e));});
   *            ...
   *         }
   *         ...
   *
   *         // Easy way to loop thru the number of rows we have cached starting at 0
   *         function loopBody()
   *         {
   *            if(++count < bpci.getCachedRowCount(0))
   *            {
   *               var v1 = adf.mf.api.getLocalValue("id:#{row.bindings.id.inputValue}");
   *               var v2 = adf.mf.api.getLocalValue("name:#{row.bindings.name.inputValue}");
   *
   *               content += "checking provider[" + bpci.index + "] = [" + v1 + "]:" + v2 + "<br>";
   *
   *               bpci.next(function(a, b){ adf.mf.api.addVariable('row', b[0].value); loopBody(); },
   *                         function(a, b){ showSuccess("no more records to check"); showContent();});
   *             }
   *         }
   **/
  function TreeNodeIterator(/* TreeBinding */ tb, /* index */ idx)
  {
    this.id                         = tb.id;
    this.treeNodeBindings           = tb.treeNodeBindings  || {providers:{}, keys:[]};
    this.index                      = idx;
    this.currentKey                 = null;
    this[".type"]                   = "TreeNodeIterator";  /* needed for minimized version to obtain the type */
    this.modid                      = adf.mf.internal.context.getModId();
    this.treeBindingEL              = adf.mf.internal.el.parser.parse("#{" + tb.id + "}");


    /**
     * INTERNAL function to get the current collection
     * model stored for the given tree binding's id.
     */
    this.getTreeNodeBindings = function()
    {
      /* check to see if we already have the latest tree node bindings */
      if(this.modid == adf.mf.internal.context.getModId()) {
        return tb.treeNodeBindings;
      }
      else { /* looks like the cache has been updated, be safe re-fetch */
        var cm = adf.mf.api.getLocalValue("#{" + this.id + ".collectionModel}");

        return (cm.treeNodeBindings || { providers:{}, keys:[]});
      }
    };


    /**
     * create a new provider
     */
    this.createRow = function(provider, /* boolean */ insertFlag, success, failed)
    {
      return adf.mf.api.invokeMethod("oracle.adfmf.bindings.iterator.IteratorHandler", "create",
                                 this.id, this.currentKey, provider, insertFlag,
                                 success, failed);
    };

    /**
     * fetch the first row in the collection
     */
    this.first = function(success, failed)
    {
      this.fetch(0, success, failed);
    };

    /**
     * @returns the current row index
     */
    this.getCurrentIndex = function()
    {
      return this.index;
    };

    /**
     * @returns the current row key
     */
    this.getCurrentKey = function()
    {
      return this.currentKey;
    };

    /**
     * @returns the current provider (row)
     */
    this.getCurrentRow = function()
    {
      var tnb = this.getTreeNodeBindings();

      return (this.currentKey !== undefined)?
          tnb.providers[this.currentKey]: undefined;
    };

    /**
     * @returns true if their are more records buffered that can be read
     */
    this.hasNext = function()
    {
      var tnb = this.getTreeNodeBindings();

      return (this.index < (tnb.keys.length - 1));
    };

    /**
     * @returns true if their are more records buffered that can be read
     */
    this.hasPrevious = function()
    {
      return (this.index > 0);
    };

    /**
     * fetch the last row in the collection
     */
    this.last = function(success, failed)
    {
      var tnb = this.getTreeNodeBindings();

      this.fetch((tnb.keys.length - 1), success, failed);
    };

    /**
     * obtain the next record in the collection
     */
    this.next = function(success, failed)
    {
      // adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "TreeNodeIterator", "next",
          //           "Range Size: " + this.getRangeSize() + " where we have loaded " + this.getCachedRowCount(this.index) + " rows.");
      this.fetch((this.index + 1), success, failed);
    };

    /**
     * fetch the first row in the collection
     */
    /* provider */
    this.localFirst = function()
    {
      return this.localFetch(0);
    };

    /**
     * fetch the last row in the collection
     */
    /* provider */
    this.localLast = function()
    {
      var tnb = this.getTreeNodeBindings();

      return this.localFetch((tnb.keys.length - 1));
    };

    /**
     * get the next provider if you have it already cached, if not will return undefined
     */
    /* provider */
    this.localNext = function()
    {
      return this.localFetch((this.index + 1));
    };

    /**
     * get the previous provider if you have it already cached, if not will return undefined
     */
    /* provider */
    this.localPrevious = function()
    {
      return this.localFetch((this.index - 1));
    };

    /**
     * request the next set of records to be fetched
     */
    this.nextSet = function(success, failed)
    {
      this.fetchSet('next', this.index, success, failed);
    };

    /**
     * obtain the next record in the collection
     */
    this.previous = function(success, failed)
    {
      this.fetch((this.index - 1), success, failed);
    };

    /**
     * request the previous set of records to be fetched
     */
    this.previousSet = function(success, failed)
    {
      this.fetchSet('previous', this.index, success, failed);
    };

    /**
     * request the current record set to be re-fetched
     */
    this.refresh = function(success, failed)
    {
      this.fetchSet('next', this.index, success, failed);
    };

    /**
     * set the current index for the iterator
     *
     * @throws IllegalArgumentException if the index is out of range
     */
    this.setCurrentIndex = function(/* int */ index)
    {
      var tnb = this.getTreeNodeBindings();

      if((index < 0) || (index > (tnb.keys.length - 1)))
      {
        this.index      = -1;
        this.currentKey = undefined;
      }

      this.index      = index;
      this.currentKey = tnb.keys[index];
    };


    /**
     * @return the number of contiguously loaded row starting at a given point.
     *
     * @param startingAtIndex
     */
    /* int */
    this.getCachedRowCount = function(/* int */ startingAtIndex)
    {
      var count = 0;
      var tnb   = this.getTreeNodeBindings();

      if((startingAtIndex < 0) ||
         (startingAtIndex > (tnb.keys.length - 1)))
      {
        return 0;
      }

      // adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "TreeNodeIterator", "getCachedRowCount", "Passed the first test.");
      for(var i = startingAtIndex; i < tnb.keys.length; ++i)
      {
        var k = tnb.keys[i];

        // adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "TreeNodeIterator", "getCachedRowCount", "index: " + i + "  key: " + k);

        if(tnb.providers[k] != undefined) ++count;
                else break;
      }

      return count;
    };


    /**
     * @return the number of rows in a given range
     */
    /* int */
    this.getRangeSize = function()
    {
      var sz = adf.mf.api.getLocalValue("#{" + this.id + ".IterBinding.RangeSize}");
      return (sz == undefined)? 0 : sz;
    };


    /***** internal methods *****/

    /**
     * fetch the first row in the collection
     */
    this.fetch = function(index, success, failed)
    {
      var tnb = this.getTreeNodeBindings();

      this.setCurrentIndex(index);

      if(this.currentKey !== undefined)
      {
        if(tnb.providers[this.currentKey] === undefined)
        {
          if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
          {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "TreeNodeIterator", "fetch",
                                   ("no provider present for the key " + this.currentKey +
                                    " need to fetch the value."));
          }
          this.fetchProviderByKey(this.currentKey, this.index, success, failed);
        }
        else
        {
          if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
          {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "TreeNodeIterator", "fetch",
                                   ("we have a provider for key " + this.currentKey +
                                   " = " + adf.mf.util.stringify(tnb.providers[this.currentKey])));
          }
          this.returnProvider(this.currentKey, new TreeNode(tb, this.index, this.treeBindingEL), success);
        }
      }
      else
      {
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "TreeNodeIterator", "fetch",
                                 "no element found");
        }
        this.returnProvider(undefined, undefined, failed);
      }
    };

    /**
     * fetch a row in the collection
     */
    /* provider */
    this.localFetch = function(index)
    {
      var tnb      = this.getTreeNodeBindings();
      var  oldIndex = this.index;

      this.setCurrentIndex(index);

      if(this.currentKey !== undefined)
      {
        if(tnb.providers[this.currentKey] !== undefined)
        {
          if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
          {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "TreeNodeIterator", "fetch",
                                   ("we have a provider for key " + this.currentKey +
                                   " = " + adf.mf.util.stringify(tnb.providers[this.currentKey])));
          }
          var treeNode = new TreeNode(tb, this.index, this.treeBindingEL);
          var provider = treeNode.getProvider();
          if (provider['rowKey'] === undefined)
          {
            provider['rowKey'] = treeNode.rowKey();
          }
          return treeNode;
        }
      }

      this.setCurrentIndex(oldIndex);  /* move the cursor back to where it was first */
      return undefined;
    };


    this.getKeys = function(success, failed)
    {
      var cm = adf.mf.api.getLocalValue("#{" + this.id + ".collectionModel}");;

      return adf.mf.api.invokeMethod("oracle.adfmf.bindings.iterator.IteratorHandler", "getKeys",
                                          this.id,
                                          [function(a,b) { cm.treeNodeBindings.keys = b; },
                                          success], failed);
    };

    /**
     * remove the current row (provider)
     */
    this.removeCurrentRow = function(success, failed)
    {
      var tnb       = this.getTreeNodeBindings();
      var removeKey = this.currentKey;
      var range     = 0;
      var newIndex  = this.index;

      /* first lets remove the key in the JavaScript cached collection model */
      for(var i = 0; i < tnb.keys.length; ++i)
      {
        if(tnb.keys[i] === this.currentKey)
        {
          tnb.keys.splice(i, 1);
          break;
        }
      }

      range = tnb.keys.length - 1;
      if(newIndex == range)
      {   /* we removed the last one, so move the index to the new last */
        newIndex = (range - 1);
      }
      if(range < 0)
      {  /* there are no elements in the collection any more */
        this.index      = -1;
        this.currentKey = undefined;
      }
      else
      {
        this.setCurrentIndex(newIndex);
      }

      return adf.mf.api.invokeMethod("oracle.adfmf.bindings.iterator.IteratorHandler", "removeRowWithKey",
                                 this.id, removeKey, success, failed);
    };

    this.setCurrentRowKey = function(key)
    {
      var i = this.getTreeNodeBindings().keys.indexOf(key);
      if (i != -1) {
        this.index = i;
        this.currentKey = key;
        return true;
      }
      return false;
    };

    this.setCurrentRowWithKey = function(key, success, failed)
    {
      var tnb      = this.getTreeNodeBindings();
      var newIndex = -1;

      for(var i = 0; i < tnb.keys.length; ++i)
      {
        if(tnb.keys[i] === key)
        {
          newIndex = i;
          break;
        }
      }
      if(newIndex != -1)
      {
        this.setCurrentIndex(newIndex);

        adf.mf.api.invokeMethod("oracle.adfmf.bindings.iterator.IteratorHandler", "setCurrentRowWithKey",
                                      this.id, key, success, failed);
      }
      else
      {
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "TreeNodeIterator", "setCurrentRowWithKey",
                                 ("unable to find the key to set the current row to."));
        }
      }
    };


    /* ---------- internal callback functions ------------- */

    this.fetchSet = function(pcns, index, success, failed)
    {
      var cm    = adf.mf.api.getLocalValue("#{" + this.id + ".collectionModel}");
      var scb   = [];
      var fcb   = [];
      var op    = "oracle.adfmf.bindings.iterator.IteratorHandler:fetchSetRelativeTo";
      var upf   = this.updateProviders;
      var start = adf.mf.internal.perf.start(op);

      scb = scb.concat(
        [
          function(a,b)
          {
            start.stop();
            start = adf.mf.internal.perf.start("adf.mf.api.TreeNodeIterator.updateProviders");
            try
            {
              upf(cm, b);
            }
            finally
            {
              start.stop();
            }
          }
        ]);
      scb = scb.concat(adf.mf.internal.util.is_array(success)? success : [success]);

      fcb = fcb.concat(
        [
          function(a,b)
          {
            start.stop();
            adf.mf.log.logInfoResource("ADFInfoBundle", adf.mf.log.level.WARNING,
              "TreeNodeIterator.fetchSet", "WARN_UNABLE_TO_FETCH_SET", adf.mf.util.stringify(arguments));
          }
        ]);

      fcb = fcb.concat(adf.mf.internal.util.is_array(failed) ? failed : [ failed ]);

      /* pcns: previous, current, or next set */
      return adf.mf.api.invokeMethod(
        "oracle.adfmf.bindings.iterator.IteratorHandler", "fetchSetRelativeTo",
        this.id, pcns, cm.treeNodeBindings.keys[index], scb, fcb);
    };

    /* ---------- internal callback functions ------------- */
    this.fetchProviderByKey = function(key, index, success, failed)
    {
      var rpf = this.returnProvider;

      this.fetchSet("next", index,
      function(a,b) { rpf(key, new TreeNode(tb, index, this.treeBindingEL), success); },
              failed);
    };


    this.updateKeys = function(keys)
    {
      try
      {
        var cm = adf.mf.api.getLocalValue("#{" + this.id + ".collectionModel}");
        cm.treeNodeBindings.keys = keys;
      }
      catch(e)
      {
        adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
          "TreeNodeIterator.updateKeys", "ERROR_TREENODEITERATOR_UPDATE_KEYS", this.id, e);
      }
    };

    this.updateProviders = function(cm, values)
    {
      var providers = values || {};
      var keys      = [];

      if(cm === undefined)
      {
        adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
          "TreeNodeIterator.updateProviders", "ERROR_TREENODEITERATOR_UPDATE_PROVIDERS");
      }
      else
      {
        for(var p in providers)
        {
          if(p !== undefined)
          {
            cm.treeNodeBindings.providers[p] = providers[p];
          }
        }
      }
    };

    this.returnProvider = function(name, provider, callback)
    {
      var request  = [{ 'name':name}];
      var response = [{ 'name':name, 'value': provider }];

      if(adf.mf.internal.util.is_array(callback))
      {
        var count = callback.length;

        for(var i = 0; i < count; ++i)
        {
          callback[i](request, response);
        }
      }
      else
      {
        callback(request, response);
      }
    };
  };

  adf.mf.internal.el.TreeNode   = TreeNode;
  adf.mf.el.TreeNodeIterator    = TreeNodeIterator;
})();




/////////////////////////////////////// end of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/TreeNode.js///////////////////////////////////////



/////////////////////////////////////// start of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/Adfel.js///////////////////////////////////////

/* Copyright (c) 2011, 2016, Oracle and/or its affiliates. All rights reserved. */
/* ------------------- Adfel.js ---------------------- */
// @requires ELErrors
//
// @requires JavaScriptContext

// @requires ELParser
// @requires TreeNode
// @requires Utilities
// @requires AdfPerfTiming
// @requires AdfResource
// @requires AdfLocale

var PERFMON    = true;

var adf                    = window.adf                 || {};
adf.mf                     = adf.mf                     || {};
adf.mf.api                 = adf.mf.api                 || {};
adf.mf.el                  = adf.mf.el                  || {};
adf.mf.locale              = adf.mf.locale              || {};
adf.mf.log                 = adf.mf.log                 || {};
adf.mf.resource            = adf.mf.resource            || {};
adf.mf.util                = adf.mf.util                || {};

adf.mf.internal            = adf.mf.internal            || {};
adf.mf.internal.api        = adf.mf.internal.api        || {};
adf.mf.internal.el         = adf.mf.internal.el         || {};
adf.mf.internal.el.parser  = adf.mf.internal.el.parser  || {};
adf.mf.internal.el.parser.cache  = adf.mf.internal.el.parser.cache  || {};
adf.mf.internal.locale     = adf.mf.internal.locale     || {};
adf.mf.internal.log        = adf.mf.internal.log        || {};
adf.mf.internal.mb         = adf.mf.internal.mb         || {};
adf.mf.internal.perf       = adf.mf.internal.perf       || {};
adf.mf.internal.resource   = adf.mf.internal.resource   || {};
adf.mf.internal.util       = adf.mf.internal.util       || {};



adf.mf.internal.api.constants = adf.mf.internal.api.constants || {
  'KEY_PROPERTY'            : '.key',
  'NULL_FLAG_PROPERTY'      : '.null',
  'TYPE_PROPERTY'           : '.type',
  'TRANSIENT_FLAG_PROPERTY' : '.transient',
  'EXCEPTION_FLAG_PROPERTY' : '.exception',
  'VALUE_REF_PROPERTY'      : '.valueref',
  'WEAK_REFERENCE_PROPERTY' : '.weakref',
  'DEFERRED_PROPERTY'       : '.deferred'
};

(function() {
  /**
   * The JavaScriptContext is basic the javascript model layer.  It is an EL
   * context for javascript.  So things like root level variables, functions,
   * .. as well as wrapping the VMChannel and other base platform items.
   */
  adf.mf.internal.context = new adf.mf.internal.el.JavaScriptContext();

  /**
   * Define a 'default' binding instance
   */
  var bindingInstances    = {};

  /**
   * Topic: Understanding_DataChangeListeners:
   *
   * There is one dataChangeListener for EACH individual variable we are monitoring for
   * changes.  So for the EL Expression #{a + b} there would be two dataChangeListeners
   * (one for 'a' and one for 'b').  Each of these dataChangeListers records are handled
   * independently, since we might actually be monitoring 'a' or 'b' already or as part
   * of another expression.  The dataChangeListeners record contains two arrays; one for
   * the all the unique IDs (EL Expressions) that we should be listening on, and one for
   * the callbacks to notify.
   *
   * Given that, when a data change listener (via addDataChangeListener) is registered,
   * the EL Expression is decomposed into all of it's individual variables where each
   * variable get their own dataChangeListner record.  So given the above EL Expression
   * (#{a + b}), there is an 'a' DCL and a 'b' DCL that would look something like this:
   *
   *   dataChangeListeners["a"] = { "id":["#{a + b}"] "callback":[function() {...}] }
   *   dataChangeListeners["b"] = { "id":["#{a + b}"] "callback":[function() {...}] }
   *
   * now if we then register the EL Expression #{a + c}, we would get something like this:
   *
   *   dataChangeListeners["a"] = { "id":["#{a + b}", "#{a + c}"]
   *                                "callback":[function() {...}, function() {...}] }
   *   dataChangeListeners["b"] = { "id":["#{a + b}"] "callback":[function() {...}] }
   *   dataChangeListeners["c"] = { "id":["#{a + c}"] "callback":[function() {...}] }
   *
   * Notice how the dataChangeListeners["a"]'s id and callback array grew and the
   * inclusion of the "c" dataChangeListener record.
   *
   * Now, when some data is changed in the CVM layer a data change event (DCE) is raised
   * and passed back on a VMChannel response message.  The native container framework then
   * pulls this DCE off the response and passes it to (javascript) processDataChangeEvent.
   * In this javascript function the DCE is disected and for each and every variable/provider
   * change in the DCE the following is done:
   *   1. data is updated in the JavaScriptContext
   *   2. determine if any registered data change listeners exists for that variable or provider.
   *      This is done by simply looking up the variable name in the dataChangeListeners map
   *      (i.e. name->dataChangeListeners records (described above)).
   *
   * Since the data change listener record contains all the registered ELs (id) and handlers
   * (callback), we simply send a notification to all the handlers with each of the registered
   * EL Expression (id in the code).
   *
   * So if we code that looked like this:
   *  adf.mf.api.addDataChangeListeners("#{a}",   fa);   // 1
   *  adf.mf.api.addDataChangeListeners("#{!a}",  fna);  // 2
   *  adf.mf.api.addDataChangeListeners("#{b}",   fb);   // 3
   *  adf.mf.api.addDataChangeListeners("#{a+b}", fab);  // 4
   *
   * Then we receive a data change event for a, then following notifications would
   * be emitted:
   *   fa(#{a})    // registered by line 1
   *   fna(#{!a})  // registered by line 2
   *   fab(#{a+b}) // registered by line 4
   * If we then recieve a data change event fo b, these notifications would be emitted:
   *   fb(#{b})    // registered by line 3
   *   fab(#{a+b}) // registered by line 4
   *
   * To unregister a data change listener simply call adf.mf.api.removeDataChangeListeners
   * i.e.
   *   adf.mf.api.removeDataChangeListeners("#{a+b}") // remove the line 4 listener
   *
   *
   * In addition to the data change listeners for individual EL expressions, one can register for
   * a bulk notification mechanism.  In this case, the framework will not attempt to map individual
   * ELs to specific callback, instead the registered callback(s) will be invoked with the list of
   * ELs that where changed.  This 'bulk' mechanism has some PROs and CONs.  It provides a single
   * notification with all the changed ELs allowing the handler to process all the changes in a single
   * call (allowing a single update event for multiple changes in components like a table).  However,
   * it does place the work of filtering/routing EL changes to the proper sub-component.
   *
   * NOTE: The providers detail will only contain itemized changes provided the 'itemized'
   *       property exists and is true.  Otherwise the entire collection model should be
   *       updated since no detailed information is known.  This detailed information is
   *       delivered as a map with the collection model id being the key.  The value of the
   *       property will be a provider change record in the following format:
   *
   *       {
   *         bindings.notes.collectionModel: {
   *           itemized: true;
   *           created: [0: {key: UID-3464; }];
   *           updated: [];
   *           deleted: [];
   *           dirtied: [];
   *         };
   *       }
   *
   *       where:
   *         itemized: true | false,  // true if created,updated, and deleted information is provided
   *         created:[ c-record ],    // right now only contains a single key property
   *         updated:[ ids ],         // list of IDs that have been updated
   *         deleted:[ ids ]          // list of IDs that have been deleted
   *         dirtied:[ ids ]          // list of IDs that have been updated
   *
   * To register a bulk data change listener the following should be done:
   *   adf.mf.api.addBatchDataChangeListener(variables, providers);
   *
   * Then we you will received data change events as follows:
   *   fa(["#{a}", "{#!a}", "#{a + b}"],
   *      {myCollectionModel:{itemized:true, created:[0: {key: UID-3464; }], updated:[], deleted:[], dirtied:[]})
   *
   */
  var dataChangeListeners                    = {};

  /**
   * INTERNAL: the array of global batch data change listeners
   */
  adf.mf.internal.batchDataChangeListeners   = [];

  /**
   * INTERNAL: the array of pending requests
   */
  adf.mf.internal.batchRequest               = undefined;

  /**
   * INTERNAL: storage for batching missing get local values.
   *
   *   @see adf.mf.api.startGetValueBatchRequest
   *  @see adf.mf.api.flushGetValueBatchRequest
   */
  adf.mf.internal.el.getValueBatch           = undefined;

  /**
   * INTERNAL FUNCTION used to log all errors coming back from the JVM.
   */
  adf.mf.internal.logError = function(req, resp)
  {
    var msg = adf.mf.resource.getInfoString("ADFErrorBundle","ERROR_IN_REQUEST",
        adf.mf.util.stringify(req), adf.mf.util.stringify(resp));

    adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "adf.mf.internal", "logError", msg);
  };
  adf.mf.internal.errorHandlers = [adf.mf.internal.logError];

  /**
   * PUBLIC FUNCTION used to add a new data change listener (callback) for a given el expression (variable)
   *
   * e.g.
   *   adf.mf.api.addDataChangeListeners("#{bindings.apple}",                   appleChangedCallback);
   *   adf.mf.api.addDataChangeListeners("#{bindgins.apple + bindings.orange}", appleOrOrangeChangedCallback);
   *
   *    adf.mf.api.addDataChangeListeners("#{!bindings.foo}",                    bindingsFooChangedCallback);
   *
   *   where the callback would looks something like this:
   *   bindingsFooChangedCallback = function(id)
   *   {
   *      document.write("DataChangeNotification 1 notification for ID: " + id);
   *   }
   *
   * If the same expression/listener combination is registered several times, duplicates are discarded.
   *
   * For more details see @Understanding_DataChangeListeners
   *
   * @export
   */
  adf.mf.api.addDataChangeListeners = function(expression, callback)
  {
    if (!expression.tokens)
      expression = adf.mf.internal.el.parser.parse(expression);
    var variables  = expression.dependencies();
    var id         = expression;

    var perf = adf.mf.internal.perf.start("adf.mf.api.addDataChangeListeners");
    if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINEST))
    {
      adf.mf.log.Framework.logp(adf.mf.log.level.FINEST, "adf.mf.api", "addDataChangeListeners",
          ("addDataChangeListeners " + expression + " ==> " + variables.length + " ==> " + variables.join()));
    }

    for(var v = 0; v < variables.length; ++v)
    {
      var alreadyRegistered = false;
      var variable          = variables[v];
      var dcl               = (variable.slice(0, "bindings".length) == "bindings")?
          currentBindingInstance.dataChangeListeners : dataChangeListeners;

      if(dcl[variable] === undefined)
      {   /* if currently we don't have a DCL record for this variable, create it */
        dcl[variable] = {"id":[], "callback":[]};
      };

      /* add the expression id to the dataChangeListeners */
      alreadyRegistered = false;
      for(var i = 0; i < dcl[variable]["id"].length; ++i)
      {
        if(dcl[variable]["id"][i] == id)
        {
          if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINER))
          {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "adf.mf.api", "addDataChangeListeners",
                ("addDataChangeListener " + variable + " id=" + id + " was already registered."));
          }
          alreadyRegistered = true;
          break;  /* you only need to find one match */
        }
      }
      if(!alreadyRegistered)
      {
        dcl[variable]["id"].push(id);

        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api", "addDataChangeListeners",
              ("there are now " + dcl[variable]["id"].length + " different listener's IDs."));
        }
      }

      alreadyRegistered = false;
      for(var i = 0; i < dcl[variable]["callback"].length; ++i)
      {
        if(dcl[variable]["callback"][i].toString() == callback.toString())
        {
          if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
          {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api", "addDataChangeListeners",
                ("variable " + variable + " already has this callback registered."));
          }

          alreadyRegistered = true;
          break;  /* you only need to find one match */
        }
      }
      if(!alreadyRegistered)
      {
        dcl[variable]["callback"].push(callback);

        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api", "addDataChangeListeners",
              ("there are now " + dcl[variable]["callback"].length + " different callbacks registered."));
        }
      }

      if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINEST))
      {
        adf.mf.log.Framework.logp(adf.mf.log.level.FINEST, "adf.mf.api", "addDataChangeListeners",
            ("adding the " + variable + " " +  adf.mf.util.stringify(dcl[variable]) + " listener."));
      }
    }
    perf.stop();
  };

  /**
   * PUBLIC FUNCTION used to add a new bulk data change listener (callback)
   *
   * e.g.
   *   adf.mf.api.addBatchDataChangeListener(printBatchDataChangeEvent);
   *
   *   where the callback would looks something like this:
   *
   *   printBatchDataChangeEvent = function(variables, providers) {
   *     if(variables != undefined) {
   *       document.write("Batch DCE -- <br> variables = " + adf.mf.util.stringify(variables)+ "<br>");
   *     }
   *     if(providers!= undefined) {
   *       document.write(" providers =" + adf.mf.util.stringify(providers) + "<br>");
   *     }
   *   };
   *
   * If the same listener is registered several times, duplicates are discarded.
   *
   * NOTE: if the providers detail will only contain itemized changes provided the 'itemized'
   *       property exists and is true.  Otherwise the entire collection model should be updated
   *       since no detailed information is known.
   *
   * For more details see @Understanding_DataChangeListeners
   *
   * @export
   */
  adf.mf.api.addBatchDataChangeListener = function(callback)
  {
    for(var i = 0; i < adf.mf.internal.batchDataChangeListeners.length; ++i)
    {
      if(adf.mf.internal.batchDataChangeListeners[i] == callback) return
    }
    adf.mf.internal.batchDataChangeListeners.push(callback);
  };

  /**
   * PUBLIC FUNCTION used to add a new error handler (callback)
   *
   * e.g.
   *   adf.mf.api.addErrorHandler(myErrorHandler);
   *
   *   where the callback would looks something like this:
   *   myErrorHandler = function(adfexception)
   *   {
   *      document.write("Error Handler 1 notification for: " + adfexception);
   *   }
   *
   * If the same handler is registered several times, duplicates are discarded.
   *
   * For more details see @Understanding_ErrorHandlers
   *
   * @export
   */
  adf.mf.api.addErrorHandler = function(callback)
  {
    for(var i = 0; i < adf.mf.internal.errorHandlers.length; ++i)
    {
      if(adf.mf.internal.errorHandlers[i] == callback) return
    }
    adf.mf.internal.errorHandlers.push(callback);
  };


  /**
   * PUBLIC FUNCTION used to get the current context ID.
   *
   * e.g. adf.mf.api.getContextId(successCallback, failedCallback);
   *
   * @deprecated
   * @export
   */
  /* void */
  adf.mf.api.getContextId = function(success, failed) {
    adf.mf.api.invokeMethod("oracle.adfmf.framework.api.Model", "getContextId", success, failed);
  };


  /**
   * PUBLIC FUNCTION used to get the current context's pagedef.
   *
   * e.g. adf.mf.api.getContextId(success, failed);
   *
   * @export
   */
  /* void */
  adf.mf.api.getContextPageDef = function(success, failed)
  {
    adf.mf.api.invokeMethod("oracle.adfmf.framework.api.Model", "getContextPageDef", success, failed);
  };


  /**
   * PUBLIC FINCTION used to get the current context's instance ID
   *
   * @export
   */
  /* void */
  adf.mf.api.getContextInstanceId = function(success, failed)
  {
    adf.mf.api.invokeMethod("oracle.adfmf.framework.api.Model", "getContextInstanceId", success, failed);
  };



  /**
   * PUBLIC FUNCTION used to invoke method in any class in classpath.
   *
   * e.g. adf.mf.api.invokeMethod(classname, methodname, param1, param2, ... , paramN ,successCallback, failedCallback);
   *
   * @param {string}                               classname  - name of the class
   * @param {string}                               methodname - name of the method
   * @param {Array.<string>}                       params     - parameters
   * @param {Array.<function(Object,Object):void>} success    - invoked when the method is successful invoked
   *                                                            (signature: success(request, response))
   * @param {Array.<function(Object,Object):void>} failed     - invoked when an error is encountered
   *                                                            (signature: failed(request, response))
   *
   * Examples:
   *      adf.mf.api.invokeMethod("TestBean", "setStringProp", "foo", success, failed);
   *      adf.mf.api.invokeMethod("TestBean", "getStringProp", success, failed);
   *      adf.mf.api.invokeMethod("TestBean", "testSimpleIntMethod", "101", success, failed); // Integer parameter
   *      adf.mf.api.invokeMethod("TestBean", "testComplexMethod",
   *              {"foo":"newfoo","baz":"newbaz",".type":"TestBeanComplexSubType"}, success, failed); // Comples parameter
   *      adf.mf.api.invokeMethod("TestBean", "getComplexColl", success, failed); // No parameter
   *      adf.mf.api.invokeMethod("TestBean", "testMethodStringStringString", "Hello ", "World", success, failed); // 2 string parameter
   */
  adf.mf.api.invokeMethod = function()
  {
    var args = [].splice.call(arguments,0);   // convert arguments into a real array

    var updatedArgs = [null];  // adding the default communication id

    adf.mf.internal.api.invokeMethod.apply(this, updatedArgs.concat(args));
  };

  /**
   * PUBLIC FUNCTION used to invoke Security Methods
   */
  adf.mf.api.invokeSecurityMethod = function(command, username, password, tenantname, success, failed)
  {
    adf.mf.internal.context.invokeSecurityMethod(command, username, password, tenantname, success, failed);
  };

  /**
   * PUBLIC FUNCTION used to remove all data change listeners associated with the variable
   *
   * For more details see @Understanding_DataChangeListeners
   */
  adf.mf.api.removeDataChangeListeners = function(expression)
  {
    if (!expression.tokens)
      expression = adf.mf.internal.el.parser.parse(expression);
    var variables  = expression.dependencies();
    var id         = expression;

    for(var i = 0; i < variables.length; ++i)
    {
      var v   = variables[i];
      var dcl = ((v.slice(0, "bindings".length) == "bindings")?
          currentBindingInstance.dataChangeListeners : dataChangeListeners);

      try
      {
        var ida = dcl[v]["id"];
        for(var j = 0; j < ida.length; ++j)
        {
          if(ida[j] === id)
          {
            if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
            {
              adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf", "removeDataChangeListeners",
                  ("removing the " + adf.mf.util.stringify(ida[j]) + " listener."));
            }
            ida.splice(j,1);
          }
        }
        if(ida.length == 0)
        {  /* clean up the dataChangeListener all together */
          if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
          {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf", "removeDataChangeListeners",
                ("removing the " + ida + " listener all together."));
          }
          delete dcl[v];
        }
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINEST))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINEST, "adf", "removeDataChangeListeners",
              ("All the current data change listeners in the system:<br> " +
                  adf.mf.util.stringify(dcl)));
        }
      }
      catch(e)
      {
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.SEVERE))
        {
          adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE, "adf.removeDataChangeListeners", "ERROR_EXCEPTION", e);
        }
      }
    }
  };


  /**
   * PUBLIC FUNCTION used to remove a bulk data change listener
   *
   * For more details see @Understanding_DataChangeListeners
   */
  adf.mf.api.removeBatchDataChangeListener = function(callback)
  {
    var temp = [];

    for(var i = 0; i < adf.mf.internal.batchDataChangeListeners.length; ++i)
    {
      if(adf.mf.internal.batchDataChangeListeners[i] != callback)
      {
        temp.push(adf.mf.internal.batchDataChangeListeners[i]);
      }
    }
    adf.mf.internal.batchDataChangeListeners = temp;
  };


  /**
   * PUBLIC FUNCTION used to remove an error handler
   *
   * For more details see @Understanding_ErrorHandlers
   */
  adf.mf.api.removeErrorHandler = function(callback)
  {
    var temp = [];

    for(var i = 0; i < adf.mf.internal.errorHandlers.length; ++i)
    {
      if(adf.mf.internal.errorHandlers[i] != callback)
      {
        temp.push(adf.mf.internal.errorHandlers[i]);
      }
    }
    adf.mf.internal.errorHandlers = temp;
  };


  /**
   * PUBLIC FUNCTION used to reset context. Call this before setting new context.
   * This is exactly the same as calling adf.mf.api.setContext with an empty context name.
   *
   * e.g. adf.mf.api.removeContextInstance(successCallback, failedCallback);
   */
  adf.mf.api.removeContextInstance = function(pageDef, instanceId, success, failed)
  {
    adf.mf.internal.el.resetBindingContext();
    adf.mf.api.invokeMethod("oracle.adfmf.framework.api.Model",
        "removeContextInstance", pageDef, instanceId, success, failed);
  };


  /**
   * PUBLIC FUNCTION used to reset context. Call this before setting new context.
   * This is exactly the same as calling adf.mf.api.setContext with an empty context name.
   *
   * e.g. adf.mf.api.resetContext(successCallback, failedCallback);
   *
   * @deprecated use adf.mf.api.setCurrentContext instead.
   */
  /* void */
  adf.mf.api.resetContext = function(success, failed)
  {
    adf.mf.api.setContext("", success, failed);
  };

  /**
   * PUBLIC FUNCTION used to set context for the specified name
   *
   * e.g. adf.mf.api.setContext("MyPage", "MyPage-1", true, true, successCallback, failedCallback);
   *
   * pageDef    - name of the page definition
   * instanceId - unique id for the instance
   * resetState - reset the bindings associated with this instance
   * reSync     - re-send the initial bindings structure to the container
   * 
   * @deprecated use adf.mf.api.setCurrentContext instead.
   */
  adf.mf.api.setContextInstance = function(pageDef, instanceId, resetState, /* boolean */reSync, success, failed)
  {
    adf.mf.api.setCurrentContext(pageDef, resetState, reSync, true, success, failed);
  };

  /**
   * PUBLIC FUNCTION used to set the current context.
   *
   * e.g. adf.mf.api.setCurrentContext("MyPage", true, true, true, successCallback, failedCallback);
   *
   * pageDef      - name of the page definition
   * resetState   - reset the bindings associated with this instance
   * reSync       - re-send the initial bindings structure to the container
   * newViewScope - should a new viewScope also be initialized, releasing the previous one?
   */
  adf.mf.api.setCurrentContext = function(pageDef, resetState, reSync, newViewScope, success, failed)
  {
    try
    {
      if((pageDef === undefined) || (pageDef === null) || (pageDef.length < 1))
      {
        //
        // clear all the bindings and listeners associated with this context.
        //
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINER))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "adf", "setCurrentContext",
              ("\n\n*******\nBindings = " + adf.mf.util.stringify(adf.mf.api.getLocalValue("#{bindings}")) + "\n*******\n\n"));
        }
        adf.mf.api.removeContextInstance(pageDef, null);
      }
      else
      {
        adf.mf.internal.el.switchBindingInstance(pageDef);
        adf.mf.api.invokeMethod("oracle.adfmf.framework.api.Model", "setCurrentContext",
            pageDef, resetState, reSync, newViewScope, success, failed);
      }
    }
    catch(ge)
    {
      if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.SEVERE))
      {
        adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE, "adf.setCurrentContext", "ERROR_EXCEPTION", ge);
      }
    }
  };


  /**
   * PUBLIC FUNCTION used to clear and then set context for the specified name
   *
   * @param name    - name of the context
   * @param success - call on success
   * @param failed  - call on failed
   *
   * this is the same as calling adf.mf.internal.api.setContext(name, true, success, failed);
   *
   * e.g. adf.mf.api.setContext("myContextName", successCallback, failedCallback);
   *
   * @deprecated use adf.mf.api.setCurrentContext instead.
   */
  /* void */
  adf.mf.api.clearAndSetContext = function(/* context name */ name, success, failed)
  {
    adf.mf.internal.api.setContext(name, true, success, failed);
  };

  /**
   * PUBLIC FUNCTION used to set context for the specified name
   *
   * @param name    - name of the context
   * @param success - call on success
   * @param failed  - call on failed
   *
   * this is the same as calling adf.mf.internal.api.setContext(name, false, success, failed);
   *
   * e.g. adf.mf.api.setContext("myContextName", successCallback, failedCallback);
   *
   * @deprecated use adf.mf.api.setCurrentContext instead.
   */
  /* void */
  adf.mf.api.setContext = function(/* context name */ name, success, failed)
  {
    adf.mf.internal.api.setContext(name, false, success, failed);
  };

  /**
   * INTERNAL FUNCTION used to set context for the specified name
   *
   * @param name       - name of the context
   * @param clearPrior - true for clear the current context
   * @param success    - call on success
   * @param failed     - call on failed
   *
   * e.g. adf.mf.api.setContext("myContextName", true, successCallback, failedCallback);
   *
   * @deprecated use adf.mf.api.setCurrentContext instead.
   */
  /* void */
  adf.mf.internal.api.setContext = function(/* String */ name, /* boolean */clearPrior, success, failed)
  {
    adf.mf.api.setCurrentContext(name, true, true, true, success, failed);
  };


  /**
   * PUBLIC FUNCTION used to create a top-level variable
   * into the context.  This should be thought of as adding
   * a variable to the root namespace for variables.
   *
   * i.e. adf.mf.api.addVariable("name", some_object);
   *
   * addVariable/removeVariable are used to add and then remove
   * temporary variables, like loop iterator variables along with
   * longer lasting variables.
   */
  /* void */
  adf.mf.el.addVariable = function(/* variable name */ name, /* new value */ value)
  {
    adf.mf.log.Framework.logp(adf.mf.log.level.FINEST, "adf.mf.api", "addVariable", name);
    adf.mf.internal.context.setVariable(name, value);
  };
  adf.mf.api.addVariable = adf.mf.el.addVariable;


  /**
   * PUBLIC FUNCTION will evaluate the passed in expression against
   * the local cache ONLY.  If there are terms that are currently
   * not cached or any function calls then undefined will be returned.
   * If the adf.mf.api.startGetValueBatchRequest has been called any
   * EL expression cache misses will be queued to fetched on the
   * adf.mf.api.flushGetValueBatchRequest call.
   *
   * @see adf.mf.api.addVariable
   * @see adf.mf.api.removeVariable
   *
   * @see adf.mf.api.getValue
   * @see adf.mf.api.setValue
   * @see adf.mf.api.setLocalValue
   *
   * @see adf.mf.api.startGetValueBatchRequest
   * @see adf.mf.api.flushGetValueBatchRequest
   */
  adf.mf.el.getLocalValue = function(/* expression */ expression)
  {
    var val = undefined;

    if (!expression.tokens)
      expression = adf.mf.internal.el.parser.parse(expression);

    var addToBatch = false;
    var context = adf.mf.internal.context;

    try
    {
      adf.mf.log.Framework.logp(adf.mf.log.level.FINEST, "adf.mf.api", "getLocalValue", expression.getExpression ());
      adf.mf.internal.el.indexedExpressionUnresolved = false;
      val = expression.evaluate(context);

      // Check if the and indexed expression was unable to get its value. If so
      // no error is thrown, so add the term to the batch
      if (adf.mf.internal.el.indexedExpressionUnresolved)
      {
        addToBatch = true;
      }
    }
    catch(e1)
    {
      addToBatch = true;
    }
    finally
    {
      delete adf.mf.internal.el.indexedExpressionUnresolved;
    }

    if (addToBatch)
    {
      // expression was not found
      if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINER))
      {
        adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api", "getLocalValue",
            ("unable to resolve '" + expression.getExpression() + "' locally."));
      }

      // NOTE: only if the internal batch is defined will we batch the
      // expression to fetch when adf.mf.api.flushGetValueBatchRequest.
      if (adf.mf.internal.el.getValueBatch !== undefined)
      {
        var batch = adf.mf.internal.el.getValueBatch;

        try
        {
          var exp   = expression.stripLocalValues(true, undefined, true);
          var terms = exp.getELTerms();

          for (var t = 0; t < terms.length; ++t)
          {
            var term = terms[t];
            addToBatch = false;

            try
            {
              // Detect when properties were not found in indexed expressions
              // (no exceptions thrown for those)
              adf.mf.internal.el.indexedExpressionUnresolved = false;
              term.evaluate(context);
              if (adf.mf.internal.el.indexedExpressionUnresolved)
              {
                addToBatch = true
              }
            }
            catch(e3)
            {
              addToBatch = true;
            }
            finally
            {
              delete adf.mf.internal.el.indexedExpressionUnresolved;
            }

            if (addToBatch)
            {
              batch.push(term.getExpression());

              if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINER))
              {
                adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "adf.mf.api", "getLocalValue",
                    "we currently do not have term: " + term + " cached, adding to the batch.");
              }
            }
          }
        }
        catch(e2)
        {
          // the only way you would get here is if the EL Expression can not be parsed

          // Look to see if there is any registered error handlers and call them.
          adf.mf.internal.api.notifyErrorHandlers(expression.getExpression (), e2);


          /*
           * Since the bulk of the callers do not handle exceptions well we are going
           * to simply return the exception as the value.  This is similar to what we
           * do in getValues so there is no difference here.  The UI should look at
           * the value and handle it correctly.
           */
          val = e2;
        }
      }
    }

    return val;
  };
  adf.mf.api.getLocalValue = adf.mf.el.getLocalValue;


  /**
   * startGetValueBatchRequest is responsible for starting a new get value
   * batch so theproper "behind the scene" call to get the values can be
   * called at the flushGetValueBatchRequest call.
   *
   * @see adf.mf.api.startGetValueBatchRequest
   */
  /* void */
  adf.mf.el.startGetValueBatchRequest = function()
  {
    if(adf.mf.internal.el.getValueBatch !== undefined)
    {
      throw new adf.mf.ELException("GetValueBatchRequest already started");
    }
    else
    {
      adf.mf.internal.el.getValueBatch = [];
    }
  };
  adf.mf.api.startGetValueBatchRequest = adf.mf.el.startGetValueBatchRequest;

  /**
   * @returns the list of all terms used in any of the ELs
   */
  /* String[] */
  adf.mf.internal.el.getListOfTerms = function(/* array */ els)
  {
    var variables = els || [];
    var terms     = [];
    var length    = 0;

    variables = adf.mf.internal.util.is_array(variables)? variables : [variables];
    length    = variables.length;

    for(var i = 0; i < length; ++i)
    {
      var ele   = adf.mf.internal.el.parser.parse(variables[i]);

      if((ele !== undefined) || (ele !== null)) {
        terms  = terms.concat(ele.dependencies());
      }
    }
    return terms;
  };


  /**
   * flushGetValueBatchRequest is responsible for closing off the current
   * batch and make the proper "behind the scene" call to get the values.
   *
   * @see adf.mf.api.startGetValueBatchRequest
   */
  /* void */
  adf.mf.el.flushGetValueBatchRequest = function()
  {
    if (adf.mf.internal.el.getValueBatch !== undefined)
    {
      if(adf.mf.internal.el.getValueBatch.length > 0)
      {
        var perf = adf.mf.internal.perf.start("adf.mf.api.flushGetValueBatchRequest");

        adf.mf.api.getValue(adf.mf.util.removeDuplicates(adf.mf.internal.el.getValueBatch),
          function(a,b)
          {
            try
            {
              var terms = adf.mf.internal.el.getListOfTerms(a.params[0]);

              adf.mf.internal.api.notifyDataChangeListeners(terms);
              perf.stop();
            }
            catch(e)
            {
              perf.stop();
            }
          },
          function(a, b)
          {
            perf.stop();
          });
      }

      adf.mf.internal.el.getValueBatch = undefined;
    }
    else
    {
      throw new adf.mf.IllegalStateException("No get value batch started.");
    }
  };
  adf.mf.api.flushGetValueBatchRequest = adf.mf.el.flushGetValueBatchRequest;



  /**
   * PUBLIC FUNCTION used to evaluate the expression(s) passed in and return the associated
   * value(s) via the success callback.  Since not all variables may not be resolved only the
   * resolved expressions will be returned in the 'response' property of the success callback.
   *
   * Given that you can use this method to get the value for:
   *
   * Evaluation of a single EL expression:
   * e.g. adf.mf.api.getValue("#{100+2*20/3}", success, failed);
   * e.g. adf.mf.api.getValue("#{bindings.userName.inputValue}", success, failed);
   *
   * Evaluation of an array of EL expressions:
   * e.g. adf.mf.api.getValue(["#{100+2*20/3}", "#{500/2}"], success, failed);
   * e.g. adf.mf.api.getValue(["#{bindings.foo}", "#{applicationScope.username}"], success, failed);
   *
   * Success Callback:
   * success(request, response)
   *   where the request echos the first argument passed in
   *     and the response is an array of name-value-pairs, one for each resolved expression.
   * so if we take our examples above:
   *   e.g. adf.mf.api.getValue("#{100+2*20/3}", success, failed);
   *        success(["#{100+2*20/3}"], [ {name:"#{100+2*20/3}", value:"113.33"} ] )
   *
   *   e.g. adf.mf.api.getValue("#{bindings.userName.inputValue}", success, failed);
   *        success(["#{bindings.userName.inputValue}"], [ {name:"#{bindings.userName.inputValue}", value:"me"} ] )
   *
   *   e.g. adf.mf.api.getValue(["#{100+2*20/3}", "#{500/2}"], success, failed);
   *        success(["#{100+2*20/3}", "#{500/2}"],
   *                [ {name:"#{100+2*20/3}", value:"113.33"}, {name:"#{500/2}", value:"250"} ] )
   *
   * Now let suppose that bindings.foo exists but not bindings.bar.  In this case would see:
   * e.g. adf.mf.api.getValue( ["#{bindings.foo}", "#{bindings.bar}"], success, failed);
   *        success(["#{bindings.foo}", "#{bindings.bar}"],
   *                [{ "name": "#{bindings.foo}", "value": "foo" }] )
   *          *** notice: binding.bar was not part of the result array
   *
   * Failed Callback:
   * failed(request, exception)
   *   where the request echos the first argument passed in
   *     and the exception encountered resulting in all of the expressions failing to be resolved
   *
   * There also exists another way to invoke the getValue used to resolve a property from an already
   * retrieved base object.  This version is used by the AMX layer to do things like iterator variables
   * when doing collection/lists/tables.  In this version, we simply let the EL resolvers determine
   * the "right thing to do" based on the 'base' and 'property' variables:
   *
   * e.g. adf.mf.api.getValue(base, property);
   *   where the value returned is value of the property or nil if it does not exists.
   **/
  adf.mf.el.getValue = function()
  {
    var argv  = arguments;
    var argc  = arguments.length;

    if (argc!=4 && argc!=3 && argc!=2)
    {
      throw new adf.mf.ELException("Wrong number of arguments");
    }

    try
    {
      if (typeof(argv[1])!='object' && (argv[1] instanceof Function))
      {
        /*
         * Note: in order to make [gs]etValue individual errors show up in the error view
         *       we will inject a nvpSimulatedErrors callback into the success callback vector.
         *       We only need to include it in the success because the failed will automatically
         *       be routed to the error handlers (see JavaScriptContext.nonBlockingCall)
         */
        var expression     = (adf.mf.internal.util.is_array(argv[0]))? argv[0] : [argv[0]];
        var success        = (adf.mf.internal.util.is_array(argv[1]))? argv[1] : [argv[1]];
        var failed         = (adf.mf.internal.util.is_array(argv[2]))? argv[2] : [argv[2]];
        var errorHandler   = ((argc == 4) && (argv[3] == true))?
            adf.mf.internal.api.nvpEatErrors :
              adf.mf.internal.api.nvpSimulatedErrors;
        var perf           = adf.mf.internal.perf.start("adf.mf.api.getValue");
        var scb            = [errorHandler,
                              function() { perf.stop(); }];
        var fcb            = [function() { perf.stop(); }];

        expression = arrayToEL (expression);
        try
        {
          var count = expression.length;
          var nvpa  = [];

          for (var i = 0; i < count; i++)
          {
            var temp = undefined;

            if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINEST))
            {
              adf.mf.log.Framework.logp(adf.mf.log.level.FINEST,
                "adf.mf.el","getValue", "evaluating locally" + expression[i].getExpression());
            }

            if((temp = expression[i].evaluate(adf.mf.internal.context)) === undefined)
            {
              throw new adf.mf.PropertyNotFoundException(expression[i].getExpression());
            }
            else
            {
              nvpa.push({"name":expression[i].getExpression(), "expression":expression[i], "value":temp});
            }
          }

          if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
          {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api", "getValue",
                ("adfmf- did not call the server for " + expression.join(", ")));
          }

          // Found everything locally
          perf.stop();
          for(var i = 0; i < success.length; ++i)
          {
            try
            {
              success[i](expression, nvpa);
            }
            catch(fe)
            {
              adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                  "adf.mf.api.getValue", "ERROR_GETVALUE_SUCCESS_CB_ERROR", i, fe);
            }
          }
        }
        catch (e) {
          var terms = getTerms (expression);

          if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
          {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api", "getValue",
                ("adfmf- needs to call the server for " + terms.join(", ")));
          }
          /* inject the addtional callbacks for: caching and peformance */
          scb     = scb.concat(adf.mf.internal.el.cacheResult, success);
          fcb     = fcb.concat(failed);
          adf.mf.api.invokeMethod("oracle.adfmf.framework.api.Model", "getValue", terms, scb, fcb);
        }
      }
      else
      {
        var base     = argv[0];
        var property = argv[1];
        value        = adf.mf.internal.context.getELResolver().getValue(adf.mf.internal.context, base, property);
      }
    }
    catch(ge)
    {
      var expression     = (adf.mf.internal.util.is_array(argv[0]))? argv[0] : [argv[0]];
      var failed         = (adf.mf.internal.util.is_array(argv[2]))? argv[2] : [argv[2]];

      adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
          "adf.mf.api.getValue", "ERROR_EXCEPTION_RESOLVING", ge, expression.join(", "));

      perf.stop();
      for(var i = 0; i < failed.length; ++i)
      {
        try
        {
          failed[i](expression, ge);
        }
        catch(fe)
        {
          adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.api.getValue", "ERROR_GETVALUE_FAILED_CB_ERROR", i, fe);
        }
      }
    }
  };
  adf.mf.api.getValue = adf.mf.el.getValue;

  /**
   * Returns array of terms for given array of expressions.
   *
   * @param {Array} expressions
   * @returns {Array}
   */
  function getTerms (expressions) {
    var map = {};
    for(var i = 0; i < expressions.length; i++)
    {
      var exp = expressions[i];
      var terms = exp.getELTerms();

      for (var j = 0; j < terms.length; j++)
      {
        var term = adf.mf.internal.context.uncompressReference(terms[j]);

        //  If the term is the same as the original expression then we don't want
        //  add it to the result collection a second time.
        if (term.getExpression() != exp.getExpression())
        {
          map[term.getExpression()] = true;
        }
      }
    }
    var result = [];
    for (var i = 0; i < expressions.length; i++)
    {
      result.push(expressions[i].getExpression());
    }
    for (var t in map)
    {
      result.push (t);
    }

    return result;
  }

  /**
   * Converts array of strings to array of ELExpressions.
   *
   * @param {type} expressions
   * @returns {Array}
   */
  function arrayToEL (expressions) {
    if (expressions[0].tokens)
      return expressions;
    var elExpressions = [];
    for (var i = 0; i < expressions.length; i++) {
      elExpressions.push (adf.mf.internal.el.parser.parse (expressions[i]));
    }
    return elExpressions;
  }

  /**
   * PUBLIC FUNCTION used to used to invoke a method expression in the java environment.
   *
   * expression: is the method expression itself
   *             i.e. #{bean.method}  #{applicationScope.bean.method}
   * params    : is an array of zero or more values that should be passed as the method parameters
   *             i.e. []                      - to invoke bean.method()
   *             or ["Hello"]                 - to invoke bean.method(String)
   *             or [[false, false], "Hello"] - to invoke bean.method(boolean[], String)
   * returnType: is the return type
   *             i.e. void                    -
   *             i.e. String                  - return type is a string
   * types     : i.e. []                      - no parameters
   *             i.e. [java.lang.String]      - one parameter of type String
   *             i.e. [java.lang.String, int] - parameter-1 of type String, parameter-2 of type int
   *
   * Given this information the correct method will be looked up and invoked from the given method.
   *
   * Evaluation of a single EL expression:
   * e.g. invoke("#{Bean.foobar}", [parameters], [parameter-types], success, failed);
   *
   * Success Callback:
   * success(request, response)
   *   where the request echos the first argument passed in
   *     and the response is an array of name-value-pairs, one for each resolved expression.
   * so if we take our examples above:
   *   e.g. adf.mf.api.invoke("#{Bean.foobar}", [], "java.lang.String", [], success, failed);
   *        success({method:"#{Bean.foobar}" arguments:[]}, {result:....} )
   *
   * Failed Callback:
   * failed(request, exception)
   *   where the request echos the first argument passed in
   *     and the exception encountered resulting in all of the expressions failing to be resolved
   **/
  adf.mf.el.invoke = function(expression, params, returnType, types, success, failed)
  {
    if (expression && expression.getExpression)
      expression = expression.getExpression ();
    adf.mf.api.invokeMethod("oracle.adfmf.framework.api.Model", "evaluateMethodExpression",
        expression, params, returnType, types, success, failed);
  };


  /**
   * PUBLIC FUNCTION used to update a value for a given variable expression.
   * Since variable expressions are the only type of expressions that can be LHS
   * (left-hand-side) expressions we can rule out all literal, complex, and method
   * expressions from the possible input.
   *
   * A simple name-value-pair object is used to denote the variable expression (name)
   * with it's desired value (value).  An example of this would be:
   *       { "name":"#{applicationScope.foo}", value:"foobar" }
   *
   * Similar to the getValue function, the setValue can take a single name-value-pair
   * or an array of them for doing batch sets.  The following examples will highlight
   * these cases:
   *
   * Passing only a single name-value-pair
   * e.g. adf.mf.api.setValue( { "name": "#{bindings.foo}", "value": "foo" }, success, failed);
   *      resulting in the bindings.foo variable being assigned foo
   *
   * Passing an array of name-value-pairs
   * e.g. adf.mf.api.setValue( [{ "name": "#{bindings.foo}", "value": "foo" },
   *                        { "name": "#{bindings.bar}", "value": "bar" }], success, failed);
   *      resulting in the bindings.foo variable being assigned foo and
   *                       bindings.bar variable being assigned bar
   *
   *
   * Success Callback:
   * success(request, response)
   *   where the request echos the first argument passed in
   *     and the response is an array of name-value-pairs, one for each resolved expression.
   * so if we take our examples above:
   *   e.g. adf.mf.api.setValue( { "name": "#{bindings.foo}", "value": "foo" }, success, failed);
   *        success(["{ "name": "#{bindings.foo}", "value": "foo" }"], [ { "name": "#{bindings.foo}", "value": "foo" } ] )
   *
   * e.g. adf.mf.api.setValue( [{ "name": "#{bindings.foo}", "value": "foo" },
   *                        { "name": "#{bindings.bar}", "value": "bar" }], success, failed);
   *        success([{ "name": "#{bindings.foo}", "value": "foo" },
   *                 { "name": "#{bindings.bar}", "value": "bar" }],
   *                [{ "name": "#{bindings.foo}", "value": "foo" },
   *                 { "name": "#{bindings.bar}", "value": "bar" }] )
   *
   * Now let suppose that bindings.foo exists but not bindings.bar.  In this case would see:
   * e.g. adf.mf.api.setValue( [{ "name": "#{bindings.foo}", "value": "foo" },
   *                        { "name": "#{bindings.bar}", "value": "bar" }], success, failed);
   *        success([{ "name": "#{bindings.foo}", "value": "foo" },
   *                 { "name": "#{bindings.bar}", "value": "bar" }],
   *                [{ "name": "#{bindings.foo}", "value": "foo" }] )
   *          *** notice: binding.bar was not part of the result array
   *
   * Failed Callback:
   * failed(request, exception)
   *   where the request echos the first argument passed in
   *     and the exception encountered resulting in all of the expressions failing to be resolved
   *
   * There also exists another way to invoke the setValue used to set a property from an already
   * retrieved base object.  This version is used by the AMX layer to do things like iterator variables
   * when doing collection/lists/tables.  In this version, we simply let the EL resolvers determine
   * the "right thing to do" based on the 'base' and 'property' variables:
   *
   * e.g. adf.mf.api.setValue(base, property, value);
   *   where the base.property is assigned the value of 'value'
   *
   **/
  adf.mf.el.setValue = function()
  {
    var argv  = arguments;
    var argc  = arguments.length;

    if (argc != 3)
    {
      throw new adf.mf.ELException("Wrong number of arguments");
    }

    try
    {
      /*
       * Note: in order to make [gs]etValue individual errors show up in the error view
       *       we will inject a nvpSimulatedErrors callback into the success callback vector.
       *       We only need to include it in the success because the failed will automatically
       *       be routed to the error handlers (see JavaScriptContext.nonBlockingCall)
       */
      var nvp     = (adf.mf.internal.util.is_array(argv[0]))? argv[0] : [argv[0]];
      var success = (adf.mf.internal.util.is_array(argv[1]))? argv[1] : [argv[1]];
      var failed  = (adf.mf.internal.util.is_array(argv[2]))? argv[2] : [argv[2]];
      var scb     = [];
      var fcb     = [];
      var perf    = adf.mf.internal.perf.start("adf.mf.api.setValue");

      if (success[0] instanceof Function)
      {
        if(adf.mf.internal.isJavaAvailable())
        {  /* since java is available we need to also do the remote write */
          var nvp1 = [];
          for(var i = 0; i < nvp.length; ++i) {
            if (!nvp[i].expression)
              nvp[i].expression = adf.mf.internal.el.parser.parse(nvp[i].name);
            var uncompressedEL = adf.mf.internal.context.uncompressReference(nvp[i].expression);
            nvp[i].expression = uncompressedEL;
            var uncompressedELString = uncompressedEL.getExpression ();
            var v = {name: uncompressedELString, value: nvp[i].value};
            if (nvp[i][adf.mf.internal.api.constants["VALUE_REF_PROPERTY"]]) {
              v[adf.mf.internal.api.constants["VALUE_REF_PROPERTY"]] = true;
            }
            nvp1.push (v);
          }

          adf.mf.log.Framework.logp(adf.mf.log.level.FINEST, "adf.mf.api", "setValue",
            "We now have the uncompressed terms.");

          var rscb = [];

          rscb = rscb.concat([adf.mf.internal.api.nvpSimulatedErrors]);
          rscb = rscb.concat([function() { perf.stop(); }]);
          rscb = rscb.concat(success);

          scb = scb.concat(function() {
            adf.mf.api.invokeMethod("oracle.adfmf.framework.api.Model", "setValue", nvp1, rscb, failed);
          });
          fcb = scb.concat(failed);
        }
        else
        {  /* since java is _NOT_ available store the value locally and notify the data change listeners (ndcl) */
          for(var i = 0; i < nvp.length; ++i)
          {
            if (!nvp[i].expression)
              nvp[i].expression = adf.mf.internal.el.parser.parse(nvp[i].name);
            var uncompressedEL = adf.mf.internal.context.uncompressReference(nvp[i].expression);
            nvp[i].expression = uncompressedEL;
            nvp[i].name = uncompressedEL.getExpression();
          }

          adf.mf.log.Framework.logp(adf.mf.log.level.FINEST, "adf.mf.api", "setValue",
            "We now have the uncompressed terms.");

          var  ndcl = undefined;

          ndcl = [function() { for(var v = 0; v < nvp.length; ++v) {
            var terms = nvp[v].expression.dependencies();
            adf.mf.internal.api.notifyDataChangeListeners(terms); }
          }];

          scb = ndcl.concat(function() { perf.stop(); });
          scb = scb.concat(success);

          fcb = ndcl.concat(function() { perf.stop(); });
          fcb = fcb.concat(failed);
          adf.mf.log.logInfoResource("ADFInfoBundle", adf.mf.log.level.WARNING, "adf.mf.api.setValue", "WARN_SKIP_REMOTE_WRITE");
        }

        adf.mf.log.Framework.logp(adf.mf.log.level.FINEST, "adf.mf.api", "setValue",
          "now calling setLocalValue");

        adf.mf.api.setLocalValue(nvp, scb, fcb);
      }
      else
      {
        var base     = argv[0];
        var property = argv[1];
        var value    = argv[2];

        adf.mf.internal.context.getELResolver().setValue(adf.mf.internal.context, base, property, value);
        perf.stop();
      }
    }
    catch(ge)
    {
      perf.stop();
      adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
          "adf.mf.api.setValue", "ERROR_EXCEPTION", ge);
    }
    finally
    {
      // nothing more that needs to be done here.
    }
  };
  adf.mf.api.setValue = adf.mf.el.setValue;


  /**
   * PUBLIC FUNCTION used to set the value only on the javascript side.
   *
   * @see adf.mf.api.setValue
   */
  adf.mf.el.setLocalValue = function()
  {
    // no-value

    try
    {
      if (arguments.length != 3)
      {
        var errMsg = adf.mf.internal.resource.getResourceStringImpl("ADFErrorBundle", "ERROR_INCORRECT_NUM_ARGS_PASSED");
        throw new adf.mf.ELException(errMsg);
      }

      var argv    = arguments;
      var nvp     = (adf.mf.internal.util.is_array(argv[0]))? argv[0] : [argv[0]];
      var success = (adf.mf.internal.util.is_array(argv[1]))? argv[1] : [argv[1]];
      var failed  = (adf.mf.internal.util.is_array(argv[2]))? argv[2] : [argv[2]];

      if (success[0] instanceof Function)
      {
        try
        {
          var count = nvp.length;
          for (var i = 0; i < count; i++)
          {
            var nvpi = nvp[i];
            var n = nvpi.name;
            if (!nvpi.expression)
              nvpi.expression = adf.mf.internal.el.parser.parse(nvpi.name);
            var v = nvpi.value;

            if((nvpi != null) && (nvpi[adf.mf.internal.api.constants.VALUE_REF_PROPERTY] !== undefined))
            {
              if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
              {
                adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api", "setLocalValue",
                    ("adfmf- not caching '" + n + "' because it is value reference."));
              }
            }
            else
              if((nvpi != null) && (nvpi[adf.mf.internal.api.constants.TRANSIENT_FLAG_PROPERTY] !== undefined))
              {
                if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
                {
                  adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api", "setLocalValue",
                      ("adfmf- not caching '" + n + "' because it is transient."));
                }
              }
              else
              {
                nvpi.expression.setValue (adf.mf.internal.context, v);

                if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
                {
                  adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api", "setLocalValue",
                      ("adfmf- setting local value : " + n));
                }
              }
          }
        }
        catch(e1)
        {
          try
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                "adf.mf.api.setLocalValue", "ERROR_SET_LOCAL_VALUE_FAILED", e1);

            for(var i = 0; i < failed.length; ++i)
            {
              failed[i](nvp, e1);
            }
            if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
            {
              adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api", "setLocalValue",
                  ("set local value failed callback has been executed."));
            }
          }
          catch(e2)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                "adf.mf.api.setLocalValue", "ERROR_SET_LOCAL_VALUE_FAILED_CB", e2);
          }
          return;
        }

        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api", "setLocalValue",
          "set local value is now complete and now calling the success callback(s)");
        }
        for(var i = 0; i < success.length; ++i)
        {
          try
          {
            success[i](nvp, nvp);
          }
          catch(fe)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                "adf.mf.api.setLocalValue", "ERROR_SET_LOCAL_VALUE_SUCCESS_CB_FAILED", i, fe);
          }
        }
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api", "setLocalValue",
              ("set local value is now complete and " + success.length + " success callback has been executed."));
        }
      }
      else
      {
        var base     = argv[0];
        var property = argv[1];
        var value    = argv[2];

        adf.mf.internal.context.getELResolver().setValue(adf.mf.internal.context, base, property, value);
        if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINEST))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINEST,
            "adf.mf.api", "setLocalValue",
            "context.getELResolver().setValue" + base + "." + property);
        }
      }
    }
    catch(ge)
    {
      adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
          "adf.mf.api.setLocalValue", "ERROR_EXCEPTION", ge);
    }
    finally
    {
      // no-value
    }
  };
  adf.mf.api.setLocalValue = adf.mf.el.setLocalValue;


  /**
   * PUBLIC FUNCTION used to remove a top-level variable
   * from the context.  This should be thought of as removing
   * a variable from the root namespace for variables.
   *
   * i.e. adf.mf.api.removeVariable("name");
   */
  /* void */
  adf.mf.el.removeVariable = function(/* variable name */ name)
  {
    adf.mf.log.Framework.logp(adf.mf.log.level.FINEST, "adf.mf.api", "removeVariable", name);
    adf.mf.internal.context.removeVariable(name);
    // no-value
  };
  adf.mf.api.removeVariable = adf.mf.el.removeVariable;


  /**
   * PUBLIC FUNCTION used to create a top-level variable
   * into the context.  This should be thought of as adding
   * a variable to the root namespace for variables.
   *
   * i.e. adf.mf.api.setVariable("name", some_object);
   *
   * Most of the time the 'some_object' will be a property
   * map.  So we can do things like:
   *   adf.mf.api.getValue("${name.property}");
   */
  /* void */
  adf.mf.el.setVariable = function(/* variable name */ name, /* new value */ value)
  {
    var perf = adf.mf.internal.perf.start("adf.mf.api.setVariable");
    try
    {
      adf.mf.internal.context.setVariable(name, value);
      adf.mf.internal.api.notifyDataChangeListeners(name);
    }
    finally
    {
      perf.stop();
    }
  };
  adf.mf.api.setVariable = adf.mf.el.setVariable;

  /**
   * PUBLIC FUNCTION used to create a top-level variable
   * into the context.  This should be thought of as pushing
   * a variable onto the root namespace for variables.
   *
   * i.e. adf.mf.api.pushVariable("name", some_object);
   *
   * Most of the time the 'some_object' will be a property
   * map.  So we can do things like:
   *   adf.mf.api.getValue("${name.property}");
   *
   * NOTE: This call will _NOT_ generate a data change event.
   */
  /* void */
  adf.mf.el.pushVariable = function(/* variable name */ name, /* new value */ value)
  {
    var perf = adf.mf.internal.perf.start("adf.mf.api.pushVariable");
    try
    {
      adf.mf.internal.context.pushVariable(name, value);
    }
    finally
    {
      perf.stop();
    }
  };
  adf.mf.api.pushVariable = adf.mf.el.pushVariable;

  /**
   * PUBLIC FUNCTION used to pop a top-level variable
   * from the context.  This should be thought of as poping
   * a variable off the root namespace for variables.
   *
   * i.e. adf.mf.api.popVariable("name");
   *
   * NOTE: This call will _NOT_ generate a data change event.
   */
  /* void */
  adf.mf.el.popVariable = function(/* variable name */ name)
  {
    var perf = adf.mf.internal.perf.start("adf.mf.api.popVariable");
    try
    {
      adf.mf.internal.context.popVariable(name);
    }
    finally
    {
      perf.stop();
    }
  };
  adf.mf.api.popVariable = adf.mf.el.popVariable;

  /**
   * PUBLIC FUNCTION used to process the data change event associated with response messages.
   *
   * DataChangeEvents can be sent as their own request message or as part of _any_ response
   * message.  This event is sent to inform the javascript side that some data was side-effected
   * in the CVM layer and should be propagated into the javascript cache as well as notify the
   * user interface of the change.  This event has the following JSON represention:
   *
   * DataChangeEvent
   * {
   *    variableChanges: {
   *         elExpression:value
   *         ...
   *    }
   *    providerChanges: {
   *      providerId: {
   *         <operation>:{
   *            current_row_key: { properties filtered by node }
   *            ...
   *         }
   *             ...
   *      }
   *      ...
   *    }
   * }
   *
   * Given that, we need to do the following for each data change event:
   * Variable Changes:
   *    set the value in the local cache
   *    notify anyone interested in that variable, that it has changed.
   *
   * Provider Changes:
   *    on Create:
   *      set the value in the local cache
   *    on Update:
   *      set the value in the local cache
   *      notify anyone interested in that variable, that it has changed.
   *    on Create:
   *      remove the value from the local cache
   *
   * For more details see @Understanding_DataChangeListeners
   */
  adf.mf.api.processDataChangeEvent = function(/* DataChangeEvent */ dce)
  {
    var dcevs  = [];  /* data change event variables  */
    var pdces  = {};  /* provider data change details */

    var perfOp = adf.mf.internal.perf.startOperation("adf.mf.api.processDataChangeEvent");

    try
    {
      if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
      {
        adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api", "processDataChangeEvent",
            ("processing a data change event with " + adf.mf.util.stringify(dce)));
      }

      try
      {
        var hasMoreKeysVariableChanges = {};
        var va;
        // Due to the way the API was created, the embedded side will always fire hasMoreKeys
        // data change events even when the value does not change (the API has no concept of the
        // current value, so has nothing to compare against). The problem with this is that
        // data change events may be expensive to process (like an AMX node tree visit). It
        // should be faster to check these values and ensure that these are broadcast as
        // changes when the value is actually changed.
        for (va in dce.variableChanges)
        {
          if (adf.mf.util.stringEndsWith(va, ".collectionModel.treeNodeBindings.hasMoreKeys"))
          {
            hasMoreKeysVariableChanges[va] = (true == adf.mf.el.getLocalValue("#{" + va + "}"));
          }
        }

        adf.mf.internal.api.updateGenericCacheElement(dce.variableChanges, false); // only update the cache

        /* add to the batch data change events */
        for (va in dce.variableChanges)
        {
          dcevs.push(va);

          if((weakref = adf.mf.internal.context.getWeakReference(va)) !== undefined)
          {
            dcevs.push(weakref);
          }

          // Look to see if the variable is of type TreeBindings and if so, add it to the provider
          // change list
          var variableChange = dce.variableChanges[va];

          // Check to see if the variable change is for the hasMoreKeys property of a collection
          // model's tree node bindings property. If so, treat it as an itemized provider change
          var hasMoreKeysOldValue = hasMoreKeysVariableChanges[va];
          if (hasMoreKeysOldValue != null)
          {
            // EL including the ".collectionModel":
            var collectionModelEl = va.substring(0, va.length - 29);

            // If another property has not changed on the tree bindings, then we can handle a
            // hasMoreKeys change in an optimized fashion.
            if (pdces[collectionModelEl] == null)
            {
              var hasMoreKeysNewValue = (true == adf.mf.el.getLocalValue("#{" + va + "}"));

              if (hasMoreKeysOldValue != hasMoreKeysNewValue)
              {
              // Notify the listeners that the hasMoreKeys property has changed
              pdces[collectionModelEl] = { itemized:true, hasMoreKeysChanged:true };
              dcevs.push(collectionModelEl); // for individual dce notifications
            }
          }
          }
          else if(adf.mf.util.isType(variableChange, "TreeBindings"))
          {
            pdces[va] = { itemized:false };
            dcevs.push(va); // for individual dce notifications
          }
          else if (adf.mf.util.stringEndsWith(va, ".collectionModel.treeNodeBindings.keys"))
          {
            // If the keys array has changed and there are no itemized set of changes in the
            // provider changes, then the entire collection model has changed and a
            // non-itemized change set should be recorded.
            var providerEl = va.substring(0, va.length - 38);
            if (dce.providerChanges[providerEl] == null)
            {
              pdces[collectionModelEl] = { itemized:false };
              dcevs.push(collectionModelEl); // for individual dce notifications
            }
          }
        }
      }
      catch(e)
      {
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.WARNING))
        {
          adf.mf.log.logInfoResource("ADFInfoBundle", adf.mf.log.level.WARNING, "adf.mf.api.processDataChangeEvent", "WARN_PROCESSING_VAR_CHANGES", e);
        }
      }

      var perf = adf.mf.internal.perf.start("adf.mf.api.processDataChangeEvent:providerChanges");
      try
      {
        if(dce.providerChanges !== undefined)
        {
          for(var p in dce.providerChanges)
          {
            // each property key is the name of the provider that has a change
            var pdce = dce.providerChanges[p];

            adf.mf.internal.api.updateGenericCacheElement(pdce.columnAttributes, true);

            if(pdce.providers)
            {
              // these are changes to the column attributes
              var cmpn   = p + ".collectionModel";
              var cmn    = "#{" + cmpn + "}";
              var cm     = adf.mf.api.getLocalValue(cmn);
              var create = pdce.providers.create || {};
              var update = pdce.providers.update || {};
              var dirty  = pdce.providers.dirty  || {};
              var remove = pdce.providers.remove || {};

              if(cm === undefined)
              {
                if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
                {
                  adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api", "processDataChangeEvent",
                      ("Warning: received a data change event before " + cmn + " has been cached.  Ignoring the change."));
                }
                break;
              }

              // See if the record was already added by a variable change
              var pd = pdces[cmpn];

              if (pd == null)
              {
                pd = { itemized:true };
                pdces[cmpn] = pd;
                dcevs.push(cmpn); // for individual dce notifications
              }

              pd.created = []; // Objects with "key" property
              pd.updated = []; // Key strings
              pd.deleted = []; // Key strings
              pd.dirtied = []; // Key strings

              // ensure there is a providers object to access
              var tnb = cm.treeNodeBindings;
              tnb.providers = tnb.providers || {};

              for(var k in create)
              {
                try
                {
                  // make sure we got the keys so we can actually see this new provider :-)
                  pd.created.push({key:k});
                  tnb.providers[k] = create[k];
                }
                catch(e)
                {
                  if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.WARNING))
                  {
                    adf.mf.log.logInfoResource("ADFInfoBundle", adf.mf.log.level.WARNING, "adf.mf.api.processDataChangeEvent", "WARN_PROCESSING_CREATE_DATA_CHANGE", p, e);
                  }
                }
              }
              for(var k in update)
              {
                try
                {
                  /* please note, this will add the provider if it is currently not in the cache */
                  pd.updated.push(k);
                  tnb.providers[k] = update[k];
                }
                catch(e)
                {
                  if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.WARNING))
                  {
                    adf.mf.log.logInfoResource("ADFInfoBundle", adf.mf.log.level.WARNING, "adf.mf.api.processDataChangeEvent", "WARN_PROCESSING_UPDATE_DATA_CHANGE", p, e);
                  }
                }
              }
              for(var k in dirty)
              {
                try
                {
                  /* actually removed the provider, if it is still there */
                  pd.dirtied.push(k);
                  delete tnb.providers[k];
                }
                catch(e)
                {
                  if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.WARNING))
                  {
                    adf.mf.log.logInfoResource("ADFInfoBundle", adf.mf.log.level.WARNING, "adf.mf.api.processDataChangeEvent", "WARN_PROCESSING_REMOVE_DATA_CHANGE", p, e);
                  }
                }
              }

              for(var k in remove)
              {
                try
                {
                  /* actually removed the provider, if it is still there */
                  pd.deleted.push(k);
                  delete tnb.providers[k];
                }
                catch(e)
                {
                  if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.WARNING))
                  {
                    adf.mf.log.logInfoResource("ADFInfoBundle", adf.mf.log.level.WARNING, "adf.mf.api.processDataChangeEvent", "WARN_PROCESSING_REMOVE_DATA_CHANGE", p, e);
                  }
                }
              }
            }
          }
        }
      }
      catch(e)
      {
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.WARNING))
        {
          adf.mf.log.logInfoResource("ADFInfoBundle", adf.mf.log.level.WARNING, "adf.mf.api.processDataChangeEvent", "WARN_PROCESSING_PROVIDER_CHANGES", e);
        }
      }
      finally
      {
        perf.stop();
      }

      /* notify all the data change listeners (registered either individually or batch wise) */
      adf.mf.internal.api.notifyDataChangeListeners(dcevs, pdces);
    }
    catch(ge)
    {
      adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE, "adf.mf.api.processDataChangeEvent", "ERROR_PROCESSING_DATA_CHANGE_EVENT", ge);
    }
    finally
    {
      perfOp.stop();
      adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.api", "processDataChangeEvent", "process data change event done");
    }
  };

  /* String */
  adf.mf.internal.api.getWeakReference = function(/* String */ fqn)
  {
    return adf.mf.internal.context.getWeakReference(fqn);
  };

  /* String */
  adf.mf.internal.api.addCompressedReference = function(/* String */ reference)
  {
    return adf.mf.internal.context.addCompressedReference(reference);
  };

  /**
   * INTERNAL FUNCTION used to determine if Java is available or not.
   */
  adf.mf.internal.isJavaAvailable = function()
  {
    return ((window                                                 !== undefined) &&
        (window.container                                       !== undefined) &&
        (window.container.internal                              !== undefined) &&
        (window.container.internal.device                       !== undefined) &&
        (window.container.internal.device.integration           !== undefined) &&
        (window.container.internal.device.integration.vmchannel !== undefined));
  };


  /**
   * INTERNAL FUNCTION used to determine if we are or not in design time mode.
   */
  adf.mf.internal.isDesignTime = function()
  {
    return false;  /* TBD: add the ajax call to determine if this should be true or false */
  };


  /**
   * INTERNAL FUNCTION used to caches value for an expression
   */
  adf.mf.internal.el.cacheResult = function (request, response)
  {
    if(adf.mf.internal.util.is_array(response))
    {
      // we have an array of name value pairs
      for(var i = 0; i < response.length; ++i)
      {
        var nvp = response[i];

        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINER))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "adf.mf.internal.el", "cacheResult",
              ("adfmf- caching " + response.length + " values."));
        }

        /* we need to make sure we only try to cache individual terms */
                // old way: if(! adf.mf.internal.el.parser.parse(nvp.name).isComplexExpression())
        if(adf.mf.internal.util.isAssignable(nvp.name))
        {
          if(nvp[adf.mf.internal.api.constants.EXCEPTION_FLAG_PROPERTY] === true)
          {
            if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINEST))
            {
              adf.mf.log.Framework.logp(adf.mf.log.level.FINEST,
                "adf.mf.internal.el", "cacheResult",
                "Caching [" + nvp.name + "] as an exception - " + adf.mf.util.stringify(nvp));
            }
          }
          adf.mf.api.setLocalValue(nvp, function() {}, function() {});
        }
        else
        {
          if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINER))
          {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "adf.mf.internal.el", "cacheResult",
                ("not caching complex expression [" + nvp.name + "]."));
          }
        }
      }
    }
  };


  /**
   * INTERNAL FUNCTION used by the processDataChangeEvent handler to update generic
   * properties in the cache.
   *
   * @param values
   *
   * For more details see @Understanding_DataChangeListeners
   */
  adf.mf.internal.api.updateGenericCacheElement = function(/* arguments */)
  {
    var values = (arguments.length > 0)? arguments[0] : null;
    var notify = (arguments.length > 1)? arguments[1] : true;

    /* update the cache's modification id */
    adf.mf.internal.context.updateModId();
    if(values !== undefined)
    {   /* each variable change property is an scalar property that was changed */
      var nvp    = [];

      for(var va in values)
      {
        var vk = '#{' + va + '}';
        var pvk = adf.mf.internal.el.parser.parse(vk);
        nvp.push({'name':vk, 'expression':pvk, 'value':values[va]});
      }

      if(nvp.length > 0)
      {
        try
        {
          adf.mf.api.setLocalValue(nvp,
              function(a,b) { if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINER))
              {
                adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "adf.mf.internal.api", "updateGenericCacheElement",
                    ("updated the java script cache variables " + adf.mf.util.stringify(b)));
              }},
              function(a,b) { if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.SEVERE))
              {
                adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE, "adf.mf.internal.api.updateGenericCacheElement", "ERROR_UNABLE_TO_SET_DATA_CHANGE_VALS", adf.mf.util.stringify(b));
              }});

          if(notify) adf.mf.internal.api.notifyDataChangeListeners(values, null);
        }
        catch(e)
        {
          if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.WARNING))
          {
            adf.mf.log.logInfoResource("ADFInfoBundle", adf.mf.log.level.WARNING, "adf.mf.internal.api.updateGenericCacheElement", "WARN_UPDATING_CACHE");
          }
        }
      }
    };
  };


  /**
   * INTERNAL FUNCTION used to notify all the registered batch listeners
   * that the given variables have changed.
   *
   * #see Understanding_DataChangeListeners
   */
  adf.mf.internal.api.notifyBatchDataChangeListeners = function(variables, details)
  {
    var perf = adf.mf.internal.perf.start("adf.mf.internal.api.notifyBatchDataChangeListeners");

    for(var i = 0; i < adf.mf.internal.batchDataChangeListeners.length; ++i)
    {
      try
      {
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINER))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "adf.mf.internal.api", "notifyBatchDataChangeListeners",
              "notify listener " + i + "th bulk data change callback");
        }
        adf.mf.internal.batchDataChangeListeners[i](variables, details);
      }
      catch(e)
      {
        adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
            "adf.mf.internal.api.notifyBatchDataChangeListeners",
            "ERROR_IN_BULK_NOTIFICATION_CALLBACK", e);
      }
    }

    perf.stop();
  };


  /**
   * INTERNAL FUNCTION used to notify all the registered listeners
   * that the given variable has changed.
   *
   * #see Understanding_DataChangeListeners
   */
  adf.mf.internal.api.notifyIndividualDataChangeListeners = function(variable)
  {
    var values = (adf.mf.internal.util.is_array(variable))? variable : [variable];  /* ensure values is an array */

    var perf = adf.mf.internal.perf.start(
      "adf.mf.internal.api.notifyIndividualDataChangeListeners");

    for(var i = 0; i < values.length; ++i)
    {
      var  v  = values[i];
      var  la = (v.slice(0, "bindings".length) == "bindings")?
      currentBindingInstance.dataChangeListeners[v] : dataChangeListeners[v];

      if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
      {
        adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "adf.mf.internal.api", "notifyIndividualDataChangeListeners",
            ("**NDCL** Variable " + i + ": " + v + " has changed (" + adf.mf.api.getLocalValue(v)  + ") "));
      }
      if(la !== undefined)
      {
        for(var j = 0; j < la["callback"].length; ++j)
        {
          var k = 0;  /* declared out here so we can use it in the exception log message */

          try
          {
            for(k = 0; k < la["id"].length; ++k)
            {
              if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINER))
              {
                adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "adf.mf.internal.api", "notifyDataChangeListeners",
                    ("notify listener " + j + "th callback in " + adf.mf.util.stringify(la) + " listeners"));
              }
              la["callback"][j](la["id"][k]);
            }
          }
          catch(e)
          {
            adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
                "adf.mf.internal.api.notifyIndividualDataChangeListeners",
                "ERROR_IN_INDIVIDUAL_NOTIFICATION_CALLBACK", la["id"][k], e);
          }
        }
      }
      else
      {
        if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINER))
        {
          adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "adf.mf.internal.api", "notifyIndividualDataChangeListeners",
              ("no listener set is defined for " + values[i]));
        }
      }
    }

    perf.stop();
  };

  /**
   * INTERNAL FUNCTION used to notify all the registered listeners
   * that the given variable has changed.
   *
   * #see Understanding_DataChangeListeners
   */
  adf.mf.internal.api.notifyDataChangeListeners = function(vars, pdces)
  {
    if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINEST))
    {
      adf.mf.log.Framework.logp(adf.mf.log.level.FINEST,
        "adf.mf.api", "notifyDataChangeListeners",
        "with variables " + adf.mf.util.stringify(vars));
      adf.mf.log.Framework.logp(adf.mf.log.level.FINEST,
        "adf.mf.api", "notifyDataChangeListeners",
        "with provider details" + adf.mf.util.stringify(pdces));
    }

    /* notify the individual listeners */
    adf.mf.internal.api.notifyIndividualDataChangeListeners(vars);

    /* notify the batched listeners */
    adf.mf.internal.api.notifyBatchDataChangeListeners(vars, pdces);
  };

  /**
   * PRIVATE FUNCTION used to eat standard errors for any name-value exceptions values
   */
  adf.mf.internal.api.nvpEatErrors = function(request, response)
  {
    adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "adf.mf.internal.api", "nvpEatErrors", "eat standard errors");
  };


  /**
   * PRIVATE FUNCTION used to simulate standard errors for any batch or array values
   */
  adf.mf.internal.api.arraySimulatedErrors = function(request, response)
  {
    var requests  = (adf.mf.internal.util.is_array(request ))? request  : [request ];  /* ensure response is an array */
    var responses = (adf.mf.internal.util.is_array(response))? response : [response];  /* ensure response is an array */
    var length    = responses.length;

    var perf = adf.mf.internal.perf.start("adf.mf.internal.api.arraySimulatedErrors");

    for(var i = 0; i < length; ++i)
    {
      var rv = (adf.mf.internal.util.is_array(responses[i]))? responses[i][0] : responses[i];
      if(adf.mf.util.isType(rv, "NameValuePair"))
      {
        /*
         * if the response type is a NVP or and array of them simply forward
         * that request/response to nvpSimulatedErrors handler to do the work
         */
        adf.mf.internal.api.nvpSimulatedErrors(requests[0], responses[i]);
      }
      else if(adf.mf.util.isException(responses[i]))
      {
        try
        {
          /* notify the error handlers */
          adf.mf.internal.api.notifyErrorHandlers(requests[0], responses[i]);
        }
        catch(e)
        {
          adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE,
              "adf.mf.internal.api.arraySimulatedErrors", "ERROR_IN_ERROR_CALLBACK");
        }
      }
    }
    perf.stop();
  };


  /**
   * PRIVATE FUNCTION used to simulate standard errors for any name-value exceptions values
   */
  adf.mf.internal.api.nvpSimulatedErrors = function(request, response)
  {
    var responses = (adf.mf.internal.util.is_array(response))? response : [response];  /* ensure response is an array */

    var perf = adf.mf.internal.perf.start("adf.mf.internal.api.nvpSimulatedErrors");

    for(var i = 0; i < responses.length; ++i)
    {
      var nvp = responses[i];

      if(adf.mf.util.isType(nvp, "NameValuePair"))
      {
        if(nvp[adf.mf.internal.api.constants.EXCEPTION_FLAG_PROPERTY] !== undefined)
        {
          if (adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINEST))
          {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINEST,
              "adf.mf.internal.api", "nvpSimulatedErrors",
              "this is an exception: " + adf.mf.util.stringify(nvp));
          }

          /* lets be sure and make sure the value is true */
          if(nvp[adf.mf.internal.api.constants.EXCEPTION_FLAG_PROPERTY] == true)
          {
            try
            {
              /* notify the error handlers */
              adf.mf.internal.api.notifyErrorHandlers(nvp.name, nvp.value);
            }
            catch(e)
            {
              adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE, "adf.mf.internal.api.nvpSimulatedErrors", "ERROR_IN_ERROR_CALLBACK");
            }
          }
        }
      }
      else
      {
        adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "adf.mf.internal.api", "nvpSimulatedErrors",
            "response element " + i + " was not of type NameValuePair - " + adf.mf.util.stringify(nvp));
      }
    }
    perf.stop();
  };


  /**
   * INTERNAL FUNCTION used to notify all the registered error handlers
   */
  adf.mf.internal.api.notifyErrorHandlers = function(req, resp)
  {
    var perf = adf.mf.internal.perf.start("adf.mf.internal.api.notifyErrorHandlers.callback");

    if((resp != undefined) && (resp != null) && (resp[adf.mf.internal.api.constants.DEFERRED_PROPERTY] != true))
    {
      for(var i = 0; i < adf.mf.internal.errorHandlers.length; ++i)
      {
        try
        {
          if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINER))
          {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "adf.mf.internal.api", "notifyErrorHandlers",
                "notify error handler " + i + " of the error");
          }
          adf.mf.internal.errorHandlers[i](req, resp);
        }
        catch(e)
        {
          adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE, "adf.mf.internal.api.notifyErrorHandlers", "ERROR_CALLING_ERROR_HANDLERS", e);
        }
      }
    }
    perf.stop();
  };


  /**
   * INTERNAL FUNCTION used to clear all the binding variables
   * currently registered.
   */
  adf.mf.internal.el.resetBindingContext = function()
  {
    var perf = adf.mf.internal.perf.start("adf.mf.internal.el.resetBindingContext");

    try
    {
      adf.mf.api.removeVariable('bindings');
      adf.mf.api.addVariable('bindings', {});
      adf.mf.internal.el.clearWeakReferences();
      adf.mf.internal.el.parser.cache.clear();

      /* now clean up all the bindings data change listeners */
      currentBindingInstance.dataChangeListeners = {};
    }
    catch(e)
    {
      adf.mf.log.logInfoResource("ADFErrorBundle", adf.mf.log.level.SEVERE, "adf.mf.internal.el.resetBindingContext", "ERROR_RESETTING_BINDING_CONTEXT", e);
    }
    finally
    {
      perf.stop();
    }
  };

  /**
   * INTERNAL FUNCTION used to clear all weak references in the system.
   */
  /* void */
  adf.mf.internal.el.clearWeakReferences = function()
  {
    adf.mf.log.Framework.logp(adf.mf.log.level.FINEST,
      "adf.mf.internal.el", "clearWeakReferences", name);
    adf.mf.internal.context.clearWeakReferences();
  };


  adf.mf.el.removeCache = function(keys)
  {
    count = keys.length;
    for (var i = 0; i < count; i++) {
      elCache.kill(keys[i]);
    }
  };


  adf.mf.internal.el.getBindingInstance = function(/* String */ id)
  {
    var  bi = bindingInstances[id];

    if(bi == null)
    {
      bi = {"bindings": {}, "dataChangeListeners": {}};
      bindingInstances[id] = bi;
    }

    if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINER))
    {
      adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "adf.mf.internal.el", "purgeCache",
          ("getBindingInstance for '" + id + "' = " + adf.mf.util.stringify(bi)));
    }

    return bi;
  };

  adf.mf.internal.el.switchBindingInstance = function(id)
  {
    if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINER))
    {
      adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "adf.mf.internal.el", "switchBindingInstance",
          ("switchBindingInstance to '" + id + "'"));
    }

    currentBindingInstance = adf.mf.internal.el.getBindingInstance(id);

    adf.mf.api.removeVariable('bindings');
    adf.mf.api.addVariable('bindings',   currentBindingInstance.bindings);
    // adf.mf.internal.el.clearWeakReferences();
    adf.mf.internal.el.parser.cache.clear();
  };

  /**
   * PUBLIC FUNCTION used to add a performance monitor observation.  Can be called with or without the description parameter.
   * @param monitorId      - monitor ID
   * @param description    - description for the given monitor (optional)
   * @param duration       - observation duration.  This is the quantity value that is observed.
   * @param successCB      - success callback
   * @param failureCB      - failure callback
   *
   * e.g. adf.mf.api.addMonitorObservation("MyCategory.methodFoo", 123, successCB, failureCB);
   * e.g. adf.mf.api.addMonitorObservation("MyCategory.methodFoo", "Measures time spent in method Foo", 123, successCB, failureCB);
   *
   */
  /* void */
  adf.mf.api.addMonitorObservation = function()
  {
    var args = [].splice.call(arguments,0);   // convert arguments into a real array
    var updatedArgs = ["oracle.adfmf.framework.api.Model", "addMonitorObservation"];
    adf.mf.api.invokeMethod.apply(this, updatedArgs.concat(args));
  };

  /*
   * Initialize the default binding instance for HTML base interactions
   */
  var currentBindingInstance = adf.mf.internal.el.getBindingInstance("default");
})();



/////////////////////////////////////// end of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/Adfel.js///////////////////////////////////////



/////////////////////////////////////// start of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/AdfelBridge.js///////////////////////////////////////

/* Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved. */
/* ------------------- AdfelBridge.js ---------------------- */
// @requires Adfel


var adf                    = window.adf                 || {};
adf.el                     = adf.el                     || {};
adf.log                    = adf.log                    || {};

adf.mf                     = adf.mf                     || {};
adf.mf.api                 = adf.mf.api                 || {};
adf.mf.api.adf             = adf.mf.api.adf             || {};
adf.mf.el                  = adf.mf.el                  || {};
adf.mf.locale              = adf.mf.locale              || {};
adf.mf.log                 = adf.mf.log                 || {};
adf.mf.resource            = adf.mf.resource            || {};
adf.mf.util                = adf.mf.util                || {};

adf.mf.internal            = adf.mf.internal            || {};
adf.mf.internal.api        = adf.mf.internal.api        || {};
adf.mf.internal.el         = adf.mf.internal.el         || {};
adf.mf.internal.el.parser  = adf.mf.internal.el.parser  || {};
adf.mf.internal.locale     = adf.mf.internal.locale     || {};
adf.mf.internal.log        = adf.mf.internal.log        || {};
adf.mf.internal.mb         = adf.mf.internal.mb         || {};
adf.mf.internal.perf       = adf.mf.internal.perf       || {};
adf.mf.internal.perf.story = adf.mf.internal.perf.story || {};
adf.mf.internal.resource   = adf.mf.internal.resource   || {};
adf.mf.internal.util       = adf.mf.internal.util       || {};


/**
 * PUBLIC FUNCTION used to add a new data change listener (callback) for a given el expression (variable)
 * 
 * e.g.
 *   adf.addDataChangeListeners("#{bindings.apple}",                   appleChangedCallback); 
 *   adf.addDataChangeListeners("#{bindgins.apple + bindings.orange}", appleOrOrangeChangedCallback);
 *                              
 * 	 adf.addDataChangeListeners("#{!bindings.foo}",                    bindingsFooChangedCallback);
 * 
 *   where the callback would looks something like this:
 *   bindingsFooChangedCallback = function(id)
 *   {
 *      document.write("DataChangeNotification 1 notification for ID: " + id);
 *   }
 *   
 * If the same expression/listener combination is registered several times, duplicates are discarded.
 * 
 * For more details see @Understanding_DataChangeListeners
 */
adf.addDataChangeListeners = function(expression, callback) 
{
	adf.mf.api.addDataChangeListeners.apply(this, arguments);
};

adf.mf.api.adf.loadADFMessageBundles = function(baseUrl, loadMessageBundleCallback)
{
	adf.mf.resource.loadADFMessageBundles.apply(this, arguments);
};

adf.mf.api.adf.getInfoString = function(bundleName, key)
{
	adf.mf.resource.getInfoString.apply(this, arguments);
};

adf.mf.api.adf.getErrorId = function(bundleName, key)
{
	adf.mf.resource.adf.getErrorId.apply(this, arguments);
};

adf.mf.api.adf.getErrorCause = function(bundleName, key)
{
	adf.mf.resource.getErrorCause.apply(this, arguments);
};

adf.mf.api.adf.getErrorAction = function(bundleName, key)
{
	adf.mf.resource.getErrorAction.apply(this, arguments);
};

adf.mf.api.adf.logAndThrowErrorResource = function(bundleName, methodName, key)
{
	adf.mf.log.logAndThrowErrorResource.apply(this, arguments);
};

adf.mf.api.adf.logInfoResource = function(bundleName, level, methodName, key)
{
	  adf.mf.log.logInfoResource.apply(this, arguments);
};




/**
 * PUBLIC FUNCTION used to get the current context ID. 
 *
 * e.g. adf.getContextId(success, failed);
 * 
 * @deprecated
 */
/* void */
adf.getContextId = function(success, failed) 
{ 
	adf.mf.api.getContextId.apply(this, arguments);
};


/**
 * PUBLIC FUNCTION used to get the current context's pagedef. 
 *
 * e.g. adf.getContextId(success, failed);
 * 
 */
adf.getContextPageDef = function(success, failed)
{
	adf.mf.api.getContextPageDef.apply(this, arguments);
};

/**
 * PUBLIC FINCTION used to get the current context's instance ID
 */
adf.getContextInstanceId = function(success, failed)
{
	adf.mf.api.getContextInstanceId.apply(this, arguments);
};


/**
 * setContext
 * pageDef    - name of the page definition
 * instanceId - unique id for the instance
 * resetState - reset the bindings associated with this instance
 * reSync     - resend the initial bindings structure to the container
 */
adf.setContextInstance = function(pageDef, instancedId, resetState, /* boolean */reSync, success, failed)
{
	adf.mf.api.setContextInstance.apply(this, arguments);
};

adf.removeContextInstance = function(pageDef, instanceId, success, failed)
{
	adf.mf.api.removeContextInstance.apply(this, arguments);
};


/**
 * PUBLIC FUNCTION used to invoke method in any class in classpath.
 * 
 * e.g. adf.invokeMethod(classname, methodname, param1, param2, ... , paramN ,successCallback, failedCallback);
 *
 * Examples:
 *      adf.invokeMethod("TestBean", "setStringProp", "foo", success, failed);                  
 *      adf.invokeMethod("TestBean", "getStringProp", success, failed);                  
 *      adf.invokeMethod("TestBean", "testSimpleIntMethod", "101", success, failed); // Integer parameter              
 *      adf.invokeMethod("TestBean", "testComplexMethod", 
 *              {"foo":"newfoo","baz":"newbaz",".type":"TestBeanComplexSubType"}, success, failed); // Comples parameter
 *      adf.invokeMethod("TestBean", "getComplexColl", success, failed); // No parameter
 *      adf.invokeMethod("TestBean", "testMethodStringStringString", "Hello ", "World", success, failed); // 2 string parameter
 *
 * @param classname  - name of the class
 * @param methodname - name of the method
 * @param params     - parameters
 * @param success    - invoked when the method is successfull invoked
 *                     (signature: success(request, response))
 * @param failed     - invoked when an error is encountered 
 *                     (signature: failed(request, response))
 */
adf.invokeMethod = function() 
{ 
	adf.mf.api.invokeMethod.apply(this, arguments);
};

/**
 * PUBLIC FUNCTION used to invoke IDM Mobile SDK methods
 */
adf.invokeSecurityMethod = function(command, username, password, tenantname)  
{ 
	adf.mf.security.invokeSecurityMethod.apply(this, arguments); 
};

/**
 * PUBLIC FUNCTION used to remove all data change listeners associated with the variable
 * 
 * For more details see @Understanding_DataChangeListeners
 */
adf.removeDataChangeListeners = function(expression) 
{
	adf.mf.api.removeDataChangeListeners.apply(this, arguments); 
};


/**
 * PUBLIC FUNCTION used to reset context. Call this before setting new context.
 * This is exactly the same as calling adf.setContext with an empty context name.
 *
 * e.g. adf.resetContext(successCallback, failedCallback);
 */
/* void */
adf.resetContext = function(success, failed) 
{ 
	adf.mf.api.resetContext.apply(this, arguments); 
};


/**
 * PUBLIC FUNCTION used to set context for the specified name
 * 
 * e.g. adf.setContext("myContextName", successCallback, failedCallback);
 */
/* void */
adf.setContext = function(/* context name */ name, success, failed) 
{
	adf.mf.api.setContext.apply(this, arguments); 
};


/**
 * PUBLIC FUNCTION used to create a top-level variable
 * into the context.  This should be thought of as adding
 * a variable to the root namespace for variables.
 * 
 * i.e. adf.el.addVariable("name", some_object);
 * 
 * addVariable/removeVariable are used to add and then remove
 * temporary variables, like loop iterator variables along with
 * longer lasting variables.
 */
/* void */
adf.el.addVariable = function(/* variable name */ name, /* new value */ value) 
{ 
	adf.mf.api.addVariable.apply(this, arguments); 
};


/**
 * PUBLIC FUNCTION will evaluate the passed in expression against
 * the local cache ONLY.  If there are terms that are currently
 * not cached or any function calls then undefined will be returned.
 * 
 * @see adf.el.addVariable
 * @see adf.el.removeVariable
 * 
 * @see adf.el.getValue
 * @see adf.el.setValue
 * @see adf.el.setLocalValue
 */
adf.el.getLocalValue = function(/* expression */ expression) 
{
	return adf.mf.api.getLocalValue.apply(this, arguments);
};


/** 
 * PUBLIC FUNCTION used to evaluate the expression(s) passed in and return the associated 
 * value(s) via the success callback.  Since not all variables may not be resolved only the
 * resolved expressions will be returned in the 'response' property of the success callback.
 *  
 * Given that you can use this method to get the value for:
 * 
 * Evaluation of a single EL expression:
 * e.g. adf.el.getValue("#{100+2*20/3}", success, failed);
 * e.g. adf.el.getValue("#{bindings.userName.inputValue}", success, failed);
 * 
 * Evaluation of an array of EL expressions:
 * e.g. adf.el.getValue(["#{100+2*20/3}", "#{500/2}"], success, failed);
 * e.g. adf.el.getValue(["#{bindings.foo}", "#{applicationScope.username}"], success, failed);
 * 
 * Success Callback:
 * success(request, response)
 *   where the request echos the first argument passed in
 *     and the response is an array of name-value-pairs, one for each resolved expression.
 * so if we take our examples above:
 *   e.g. adf.el.getValue("#{100+2*20/3}", success, failed);
 *        success(["#{100+2*20/3}"], [ {name:"#{100+2*20/3}", value:"113.33"} ] )
 *        
 *   e.g. adf.el.getValue("#{bindings.userName.inputValue}", success, failed);
 *        success(["#{bindings.userName.inputValue}"], [ {name:"#{bindings.userName.inputValue}", value:"me"} ] )
 * 
 *   e.g. adf.el.getValue(["#{100+2*20/3}", "#{500/2}"], success, failed);
 *        success(["#{100+2*20/3}", "#{500/2}"], 
 *                [ {name:"#{100+2*20/3}", value:"113.33"}, {name:"#{500/2}", value:"250"} ] )
 * 
 * Now let suppose that bindings.foo exists but not bindings.bar.  In this case would see:
 * e.g. adf.el.getValue( ["#{bindings.foo}", "#{bindings.bar}"], success, failed);
 *        success(["#{bindings.foo}", "#{bindings.bar}"], 
 *                [{ "name": "#{bindings.foo}", "value": "foo" }] )
 *          *** notice: binding.bar was not part of the result array
 *          
 * Failed Callback:
 * failed(request, exception)
 *   where the request echos the first argument passed in
 *     and the exception encountered resulting in all of the expressions failing to be resolved
 *   
 * There also exists another way to invoke the getValue used to resolve a property from an already 
 * retrieved base object.  This version is used by the AMX layer to do things like iterator variables
 * when doing collection/lists/tables.  In this version, we simply let the EL resolvers determine
 * the "right thing to do" based on the 'base' and 'property' variables:
 * 
 * e.g. adf.el.getValue(base, property);
 *   where the value returned is value of the property or nil if it does not exists.
 **/
adf.el.getValue = function() 
{
	adf.mf.api.getValue.apply(this, arguments);
};


/** 
 * PUBLIC FUNCTION used to used to invoke a method expression in the java environment.
 * 
 * expression: is the method expression itself
 *             i.e. #{bean.method}  #{applicationScope.bean.method}
 * params    : is an array of zero or more values that should be passed as the method parameters
 *             i.e. []                      - to invoke bean.method()
 *             or ["Hello"]                 - to invoke bean.method(String)
 *             or [[false, false], "Hello"] - to invoke bean.method(boolean[], String)
 * returnType: is the return type
 *             i.e. void                    - 
 *             i.e. String                  - return type is a string
 * types     : i.e. []                      - no parameters
 *             i.e. [java.lang.String]      - one parameter of type String
 *             i.e. [java.lang.String, int] - parameter-1 of type String, parameter-2 of type int
 *               
 * Given this information the correct method will be looked up and invoked from the given method.
 * 
 * Evaluation of a single EL expression:
 * e.g. invoke("#{Bean.foobar}", [parameters], [parameter-types], success, failed);
 * 
 * Success Callback:
 * success(request, response)
 *   where the request echos the first argument passed in
 *     and the response is an array of name-value-pairs, one for each resolved expression.
 * so if we take our examples above:
 *   e.g. adf.el.invoke("#{Bean.foobar}", [], "java.lang.String", [], success, failed);
 *        success({method:"#{Bean.foobar}" arguments:[]}, {result:....} )
 *          
 * Failed Callback:
 * failed(request, exception)
 *   where the request echos the first argument passed in
 *     and the exception encountered resulting in all of the expressions failing to be resolved
 **/
adf.el.invoke = function(expression, params, returnType, types, success, failed) 
{
	adf.mf.api.invoke.apply(this, arguments);
};


/** 
 * PUBLIC FUNCTION used to update a value for a given variable expression.
 * Since variable expressions are the only type of expressions that can be LHS
 * (left-hand-side) expressions we can rule out all literal, complex, and method 
 * expressions from the possible input.
 * 
 * A simple name-value-pair object is used to denote the variable expression (name)
 * with it's desired value (value).  An example of this would be: 
 *       { "name":"#{applicationScope.foo}", value:"foobar" } 
 * 
 * Similar to the getValue function, the setValue can take a single name-value-pair
 * or an array of them for doing batch sets.  The following examples will highlight
 * these cases:
 * 
 * Passing only a single name-value-pair
 * e.g. adf.el.setValue( { "name": "#{bindings.foo}", "value": "foo" }, success, failed);
 *      resulting in the bindings.foo variable being assigned foo
 *      
 * Passing an array of name-value-pairs
 * e.g. adf.el.setValue( [{ "name": "#{bindings.foo}", "value": "foo" }, 
 *                        { "name": "#{bindings.bar}", "value": "bar" }], success, failed);
 *      resulting in the bindings.foo variable being assigned foo and 
 *                       bindings.bar variable being assigned bar
 *      
 * 
 * Success Callback:
 * success(request, response)
 *   where the request echos the first argument passed in
 *     and the response is an array of name-value-pairs, one for each resolved expression.
 * so if we take our examples above:
 *   e.g. adf.el.setValue( { "name": "#{bindings.foo}", "value": "foo" }, success, failed);
 *        success(["{ "name": "#{bindings.foo}", "value": "foo" }"], [ { "name": "#{bindings.foo}", "value": "foo" } ] )
 *        
 * e.g. adf.el.setValue( [{ "name": "#{bindings.foo}", "value": "foo" }, 
 *                        { "name": "#{bindings.bar}", "value": "bar" }], success, failed);
 *        success([{ "name": "#{bindings.foo}", "value": "foo" }, 
 *                 { "name": "#{bindings.bar}", "value": "bar" }], 
 *                [{ "name": "#{bindings.foo}", "value": "foo" }, 
 *                 { "name": "#{bindings.bar}", "value": "bar" }] )
 * 
 * Now let suppose that bindings.foo exists but not bindings.bar.  In this case would see:
 * e.g. adf.el.setValue( [{ "name": "#{bindings.foo}", "value": "foo" }, 
 *                        { "name": "#{bindings.bar}", "value": "bar" }], success, failed);
 *        success([{ "name": "#{bindings.foo}", "value": "foo" }, 
 *                 { "name": "#{bindings.bar}", "value": "bar" }], 
 *                [{ "name": "#{bindings.foo}", "value": "foo" }] )
 *          *** notice: binding.bar was not part of the result array
 * 
 * Failed Callback:
 * failed(request, exception)
 *   where the request echos the first argument passed in
 *     and the exception encountered resulting in all of the expressions failing to be resolved
 *   
 * There also exists another way to invoke the setValue used to set a property from an already 
 * retrieved base object.  This version is used by the AMX layer to do things like iterator variables
 * when doing collection/lists/tables.  In this version, we simply let the EL resolvers determine
 * the "right thing to do" based on the 'base' and 'property' variables:
 * 
 * e.g. adf.el.setValue(base, property, value);
 *   where the base.property is assigned the value of 'value'
 **/
adf.el.setValue = function(nvp, success, failed) 
{
	adf.mf.api.setValue.apply(this, arguments);
};


/**
 * PUBLIC FUNCTION used to set the value only on the javascript side.
 * 
 * @see adf.el.setValue
 */
adf.el.setLocalValue = function() 
{
	adf.mf.api.setLocalValue.apply(this, arguments);
};


/**
 * PUBLIC FUNCTION used to remove a top-level variable
 * from the context.  This should be thought of as removing
 * a variable from the root namespace for variables.
 * 
 * i.e. adf.el.removeVariable("name");
 */
/* void */
adf.el.removeVariable = function(/* variable name */ name) 
{
	adf.mf.api.removeVariable.apply(this, arguments);
};


/**
 * PUBLIC FUNCTION used to create a top-level variable
 * into the context.  This should be thought of as adding
 * a variable to the root namespace for variables.
 * 
 * i.e. adf.el.setVariable("name", some_object);
 * 
 * Most of the time the 'some_object' will be a property
 * map.  So we can do things like:
 *   adf.el.getValue("${name.property}");
 */
/* void */
adf.el.setVariable = function(/* variable name */ name, /* new value */ value) 
{
	adf.mf.api.setVariable.apply(this, arguments);
};

 /**
 * PUBLIC FUNCTION used to process the data change event associated with response messages.
 * 
 * DataChangeEvents can be sent as their own request message or as part of _any_ response
 * message.  This event is sent to inform the javascript side that some data was side-effected
 * in the CVM layer and should be propagated into the javascript cache as well as notify the 
 * user interface of the change.  This event has the following JSON represention:
 *   
 * DataChangeEvent
 * {
 *  	variableChanges: {
 * 	    	elExpression:value
 * 	    	...
 *  	}
 *  	providerChanges: {
 *  		providerId: {
 *  		   <operation>:{ 
 *  		      current_row_key: { properties filtered by node }
 *  		      ...
 *  		   }
 *             ...
 *  		}
 *  		...
 *  	}
 * }
 * 
 * Given that, we need to do the following for each data change event:
 * Variable Changes:
 *    set the value in the local cache
 *    notify anyone interested in that variable, that it has changed.
 *    
 * Provider Changes:
 *    on Create:
 *      set the value in the local cache
 *    on Update:
 *      set the value in the local cache
 *      notify anyone interested in that variable, that it has changed.
 *    on Create:
 *      remove the value from the local cache
 * 
 * For more details see @Understanding_DataChangeListeners
 */
adf.processDataChangeEvent = function(/* DataChangeEvent */ dce)
{
	adf.mf.api.processDataChangeEvent.apply(this, arguments);
};


/////////////////////////////////////// end of /Users/miniadmin/Documents/HudsonHome/jobs/maf-v2.2.0.0.0-tip/workspace/Container/JavaScript/ADF/js/AdfelBridge.js///////////////////////////////////////

if (!window.jQuery) {
/*!
 * jQuery JavaScript Library v2.0.0
 * http://jquery.com/
 *
 * Includes Sizzle.js
 * http://sizzlejs.com/
 *
 * Copyright 2005, 2013 jQuery Foundation, Inc. and other contributors
 * Released under the MIT license
 * http://jquery.org/license
 *
 * Date: 2013-04-18
 */
(function( window, undefined ) {

// Can't do this because several apps including ASP.NET trace
// the stack via arguments.caller.callee and Firefox dies if
// you try to trace through "use strict" call chains. (#13335)
// Support: Firefox 18+
//"use strict";
var
	// A central reference to the root jQuery(document)
	rootjQuery,

	// The deferred used on DOM ready
	readyList,

	// Support: IE9
	// For `typeof xmlNode.method` instead of `xmlNode.method !== undefined`
	core_strundefined = typeof undefined,

	// Use the correct document accordingly with window argument (sandbox)
	location = window.location,
	document = window.document,
	docElem = document.documentElement,

	// Map over jQuery in case of overwrite
	_jQuery = window.jQuery,

	// Map over the $ in case of overwrite
	_$ = window.$,

	// [[Class]] -> type pairs
	class2type = {},

	// List of deleted data cache ids, so we can reuse them
	core_deletedIds = [],

	core_version = "2.0.0",

	// Save a reference to some core methods
	core_concat = core_deletedIds.concat,
	core_push = core_deletedIds.push,
	core_slice = core_deletedIds.slice,
	core_indexOf = core_deletedIds.indexOf,
	core_toString = class2type.toString,
	core_hasOwn = class2type.hasOwnProperty,
	core_trim = core_version.trim,

	// Define a local copy of jQuery
	jQuery = function( selector, context ) {
		// The jQuery object is actually just the init constructor 'enhanced'
		return new jQuery.fn.init( selector, context, rootjQuery );
	},

	// Used for matching numbers
	core_pnum = /[+-]?(?:\d*\.|)\d+(?:[eE][+-]?\d+|)/.source,

	// Used for splitting on whitespace
	core_rnotwhite = /\S+/g,

	// A simple way to check for HTML strings
	// Prioritize #id over <tag> to avoid XSS via location.hash (#9521)
	// Strict HTML recognition (#11290: must start with <)
	rquickExpr = /^(?:(<[\w\W]+>)[^>]*|#([\w-]*))$/,

	// Match a standalone tag
	rsingleTag = /^<(\w+)\s*\/?>(?:<\/\1>|)$/,

	// Matches dashed string for camelizing
	rmsPrefix = /^-ms-/,
	rdashAlpha = /-([\da-z])/gi,

	// Used by jQuery.camelCase as callback to replace()
	fcamelCase = function( all, letter ) {
		return letter.toUpperCase();
	},

	// The ready event handler and self cleanup method
	completed = function() {
		document.removeEventListener( "DOMContentLoaded", completed, false );
		window.removeEventListener( "load", completed, false );
		jQuery.ready();
	};

jQuery.fn = jQuery.prototype = {
	// The current version of jQuery being used
	jquery: core_version,

	constructor: jQuery,
	init: function( selector, context, rootjQuery ) {
		var match, elem;

		// HANDLE: $(""), $(null), $(undefined), $(false)
		if ( !selector ) {
			return this;
		}

		// Handle HTML strings
		if ( typeof selector === "string" ) {
			if ( selector.charAt(0) === "<" && selector.charAt( selector.length - 1 ) === ">" && selector.length >= 3 ) {
				// Assume that strings that start and end with <> are HTML and skip the regex check
				match = [ null, selector, null ];

			} else {
				match = rquickExpr.exec( selector );
			}

			// Match html or make sure no context is specified for #id
			if ( match && (match[1] || !context) ) {

				// HANDLE: $(html) -> $(array)
				if ( match[1] ) {
					context = context instanceof jQuery ? context[0] : context;

					// scripts is true for back-compat
					jQuery.merge( this, jQuery.parseHTML(
						match[1],
						context && context.nodeType ? context.ownerDocument || context : document,
						true
					) );

					// HANDLE: $(html, props)
					if ( rsingleTag.test( match[1] ) && jQuery.isPlainObject( context ) ) {
						for ( match in context ) {
							// Properties of context are called as methods if possible
							if ( jQuery.isFunction( this[ match ] ) ) {
								this[ match ]( context[ match ] );

							// ...and otherwise set as attributes
							} else {
								this.attr( match, context[ match ] );
							}
						}
					}

					return this;

				// HANDLE: $(#id)
				} else {
					elem = document.getElementById( match[2] );

					// Check parentNode to catch when Blackberry 4.6 returns
					// nodes that are no longer in the document #6963
					if ( elem && elem.parentNode ) {
						// Inject the element directly into the jQuery object
						this.length = 1;
						this[0] = elem;
					}

					this.context = document;
					this.selector = selector;
					return this;
				}

			// HANDLE: $(expr, $(...))
			} else if ( !context || context.jquery ) {
				return ( context || rootjQuery ).find( selector );

			// HANDLE: $(expr, context)
			// (which is just equivalent to: $(context).find(expr)
			} else {
				return this.constructor( context ).find( selector );
			}

		// HANDLE: $(DOMElement)
		} else if ( selector.nodeType ) {
			this.context = this[0] = selector;
			this.length = 1;
			return this;

		// HANDLE: $(function)
		// Shortcut for document ready
		} else if ( jQuery.isFunction( selector ) ) {
			return rootjQuery.ready( selector );
		}

		if ( selector.selector !== undefined ) {
			this.selector = selector.selector;
			this.context = selector.context;
		}

		return jQuery.makeArray( selector, this );
	},

	// Start with an empty selector
	selector: "",

	// The default length of a jQuery object is 0
	length: 0,

	toArray: function() {
		return core_slice.call( this );
	},

	// Get the Nth element in the matched element set OR
	// Get the whole matched element set as a clean array
	get: function( num ) {
		return num == null ?

			// Return a 'clean' array
			this.toArray() :

			// Return just the object
			( num < 0 ? this[ this.length + num ] : this[ num ] );
	},

	// Take an array of elements and push it onto the stack
	// (returning the new matched element set)
	pushStack: function( elems ) {

		// Build a new jQuery matched element set
		var ret = jQuery.merge( this.constructor(), elems );

		// Add the old object onto the stack (as a reference)
		ret.prevObject = this;
		ret.context = this.context;

		// Return the newly-formed element set
		return ret;
	},

	// Execute a callback for every element in the matched set.
	// (You can seed the arguments with an array of args, but this is
	// only used internally.)
	each: function( callback, args ) {
		return jQuery.each( this, callback, args );
	},

	ready: function( fn ) {
		// Add the callback
		jQuery.ready.promise().done( fn );

		return this;
	},

	slice: function() {
		return this.pushStack( core_slice.apply( this, arguments ) );
	},

	first: function() {
		return this.eq( 0 );
	},

	last: function() {
		return this.eq( -1 );
	},

	eq: function( i ) {
		var len = this.length,
			j = +i + ( i < 0 ? len : 0 );
		return this.pushStack( j >= 0 && j < len ? [ this[j] ] : [] );
	},

	map: function( callback ) {
		return this.pushStack( jQuery.map(this, function( elem, i ) {
			return callback.call( elem, i, elem );
		}));
	},

	end: function() {
		return this.prevObject || this.constructor(null);
	},

	// For internal use only.
	// Behaves like an Array's method, not like a jQuery method.
	push: core_push,
	sort: [].sort,
	splice: [].splice
};

// Give the init function the jQuery prototype for later instantiation
jQuery.fn.init.prototype = jQuery.fn;

jQuery.extend = jQuery.fn.extend = function() {
	var options, name, src, copy, copyIsArray, clone,
		target = arguments[0] || {},
		i = 1,
		length = arguments.length,
		deep = false;

	// Handle a deep copy situation
	if ( typeof target === "boolean" ) {
		deep = target;
		target = arguments[1] || {};
		// skip the boolean and the target
		i = 2;
	}

	// Handle case when target is a string or something (possible in deep copy)
	if ( typeof target !== "object" && !jQuery.isFunction(target) ) {
		target = {};
	}

	// extend jQuery itself if only one argument is passed
	if ( length === i ) {
		target = this;
		--i;
	}

	for ( ; i < length; i++ ) {
		// Only deal with non-null/undefined values
		if ( (options = arguments[ i ]) != null ) {
			// Extend the base object
			for ( name in options ) {
				src = target[ name ];
				copy = options[ name ];

				// Prevent never-ending loop
				if ( target === copy ) {
					continue;
				}

				// Recurse if we're merging plain objects or arrays
				if ( deep && copy && ( jQuery.isPlainObject(copy) || (copyIsArray = jQuery.isArray(copy)) ) ) {
					if ( copyIsArray ) {
						copyIsArray = false;
						clone = src && jQuery.isArray(src) ? src : [];

					} else {
						clone = src && jQuery.isPlainObject(src) ? src : {};
					}

					// Never move original objects, clone them
					target[ name ] = jQuery.extend( deep, clone, copy );

				// Don't bring in undefined values
				} else if ( copy !== undefined ) {
					target[ name ] = copy;
				}
			}
		}
	}

	// Return the modified object
	return target;
};

jQuery.extend({
	// Unique for each copy of jQuery on the page
	expando: "jQuery" + ( core_version + Math.random() ).replace( /\D/g, "" ),

	noConflict: function( deep ) {
		if ( window.$ === jQuery ) {
			window.$ = _$;
		}

		if ( deep && window.jQuery === jQuery ) {
			window.jQuery = _jQuery;
		}

		return jQuery;
	},

	// Is the DOM ready to be used? Set to true once it occurs.
	isReady: false,

	// A counter to track how many items to wait for before
	// the ready event fires. See #6781
	readyWait: 1,

	// Hold (or release) the ready event
	holdReady: function( hold ) {
		if ( hold ) {
			jQuery.readyWait++;
		} else {
			jQuery.ready( true );
		}
	},

	// Handle when the DOM is ready
	ready: function( wait ) {

		// Abort if there are pending holds or we're already ready
		if ( wait === true ? --jQuery.readyWait : jQuery.isReady ) {
			return;
		}

		// Remember that the DOM is ready
		jQuery.isReady = true;

		// If a normal DOM Ready event fired, decrement, and wait if need be
		if ( wait !== true && --jQuery.readyWait > 0 ) {
			return;
		}

		// If there are functions bound, to execute
		readyList.resolveWith( document, [ jQuery ] );

		// Trigger any bound ready events
		if ( jQuery.fn.trigger ) {
			jQuery( document ).trigger("ready").off("ready");
		}
	},

	// See test/unit/core.js for details concerning isFunction.
	// Since version 1.3, DOM methods and functions like alert
	// aren't supported. They return false on IE (#2968).
	isFunction: function( obj ) {
		return jQuery.type(obj) === "function";
	},

	isArray: Array.isArray,

	isWindow: function( obj ) {
		return obj != null && obj === obj.window;
	},

	isNumeric: function( obj ) {
		return !isNaN( parseFloat(obj) ) && isFinite( obj );
	},

	type: function( obj ) {
		if ( obj == null ) {
			return String( obj );
		}
		// Support: Safari <= 5.1 (functionish RegExp)
		return typeof obj === "object" || typeof obj === "function" ?
			class2type[ core_toString.call(obj) ] || "object" :
			typeof obj;
	},

	isPlainObject: function( obj ) {
		// Not plain objects:
		// - Any object or value whose internal [[Class]] property is not "[object Object]"
		// - DOM nodes
		// - window
		if ( jQuery.type( obj ) !== "object" || obj.nodeType || jQuery.isWindow( obj ) ) {
			return false;
		}

		// Support: Firefox <20
		// The try/catch suppresses exceptions thrown when attempting to access
		// the "constructor" property of certain host objects, ie. |window.location|
		// https://bugzilla.mozilla.org/show_bug.cgi?id=814622
		try {
			if ( obj.constructor &&
					!core_hasOwn.call( obj.constructor.prototype, "isPrototypeOf" ) ) {
				return false;
			}
		} catch ( e ) {
			return false;
		}

		// If the function hasn't returned already, we're confident that
		// |obj| is a plain object, created by {} or constructed with new Object
		return true;
	},

	isEmptyObject: function( obj ) {
		var name;
		for ( name in obj ) {
			return false;
		}
		return true;
	},

	error: function( msg ) {
		throw new Error( msg );
	},

	// data: string of html
	// context (optional): If specified, the fragment will be created in this context, defaults to document
	// keepScripts (optional): If true, will include scripts passed in the html string
	parseHTML: function( data, context, keepScripts ) {
		if ( !data || typeof data !== "string" ) {
			return null;
		}
		if ( typeof context === "boolean" ) {
			keepScripts = context;
			context = false;
		}
		context = context || document;

		var parsed = rsingleTag.exec( data ),
			scripts = !keepScripts && [];

		// Single tag
		if ( parsed ) {
			return [ context.createElement( parsed[1] ) ];
		}

		parsed = jQuery.buildFragment( [ data ], context, scripts );

		if ( scripts ) {
			jQuery( scripts ).remove();
		}

		return jQuery.merge( [], parsed.childNodes );
	},

	parseJSON: JSON.parse,

	// Cross-browser xml parsing
	parseXML: function( data ) {
		var xml, tmp;
		if ( !data || typeof data !== "string" ) {
			return null;
		}

		// Support: IE9
		try {
			tmp = new DOMParser();
			xml = tmp.parseFromString( data , "text/xml" );
		} catch ( e ) {
			xml = undefined;
		}

		if ( !xml || xml.getElementsByTagName( "parsererror" ).length ) {
			jQuery.error( "Invalid XML: " + data );
		}
		return xml;
	},

	noop: function() {},

	// Evaluates a script in a global context
	globalEval: function( code ) {
		var script,
				indirect = eval;

		code = jQuery.trim( code );

		if ( code ) {
			// If the code includes a valid, prologue position
			// strict mode pragma, execute code by injecting a
			// script tag into the document.
			if ( code.indexOf("use strict") === 1 ) {
				script = document.createElement("script");
				script.text = code;
				document.head.appendChild( script ).parentNode.removeChild( script );
			} else {
			// Otherwise, avoid the DOM node creation, insertion
			// and removal by using an indirect global eval
				indirect( code );
			}
		}
	},

	// Convert dashed to camelCase; used by the css and data modules
	// Microsoft forgot to hump their vendor prefix (#9572)
	camelCase: function( string ) {
		return string.replace( rmsPrefix, "ms-" ).replace( rdashAlpha, fcamelCase );
	},

	nodeName: function( elem, name ) {
		return elem.nodeName && elem.nodeName.toLowerCase() === name.toLowerCase();
	},

	// args is for internal usage only
	each: function( obj, callback, args ) {
		var value,
			i = 0,
			length = obj.length,
			isArray = isArraylike( obj );

		if ( args ) {
			if ( isArray ) {
				for ( ; i < length; i++ ) {
					value = callback.apply( obj[ i ], args );

					if ( value === false ) {
						break;
					}
				}
			} else {
				for ( i in obj ) {
					value = callback.apply( obj[ i ], args );

					if ( value === false ) {
						break;
					}
				}
			}

		// A special, fast, case for the most common use of each
		} else {
			if ( isArray ) {
				for ( ; i < length; i++ ) {
					value = callback.call( obj[ i ], i, obj[ i ] );

					if ( value === false ) {
						break;
					}
				}
			} else {
				for ( i in obj ) {
					value = callback.call( obj[ i ], i, obj[ i ] );

					if ( value === false ) {
						break;
					}
				}
			}
		}

		return obj;
	},

	trim: function( text ) {
		return text == null ? "" : core_trim.call( text );
	},

	// results is for internal usage only
	makeArray: function( arr, results ) {
		var ret = results || [];

		if ( arr != null ) {
			if ( isArraylike( Object(arr) ) ) {
				jQuery.merge( ret,
					typeof arr === "string" ?
					[ arr ] : arr
				);
			} else {
				core_push.call( ret, arr );
			}
		}

		return ret;
	},

	inArray: function( elem, arr, i ) {
		return arr == null ? -1 : core_indexOf.call( arr, elem, i );
	},

	merge: function( first, second ) {
		var l = second.length,
			i = first.length,
			j = 0;

		if ( typeof l === "number" ) {
			for ( ; j < l; j++ ) {
				first[ i++ ] = second[ j ];
			}
		} else {
			while ( second[j] !== undefined ) {
				first[ i++ ] = second[ j++ ];
			}
		}

		first.length = i;

		return first;
	},

	grep: function( elems, callback, inv ) {
		var retVal,
			ret = [],
			i = 0,
			length = elems.length;
		inv = !!inv;

		// Go through the array, only saving the items
		// that pass the validator function
		for ( ; i < length; i++ ) {
			retVal = !!callback( elems[ i ], i );
			if ( inv !== retVal ) {
				ret.push( elems[ i ] );
			}
		}

		return ret;
	},

	// arg is for internal usage only
	map: function( elems, callback, arg ) {
		var value,
			i = 0,
			length = elems.length,
			isArray = isArraylike( elems ),
			ret = [];

		// Go through the array, translating each of the items to their
		if ( isArray ) {
			for ( ; i < length; i++ ) {
				value = callback( elems[ i ], i, arg );

				if ( value != null ) {
					ret[ ret.length ] = value;
				}
			}

		// Go through every key on the object,
		} else {
			for ( i in elems ) {
				value = callback( elems[ i ], i, arg );

				if ( value != null ) {
					ret[ ret.length ] = value;
				}
			}
		}

		// Flatten any nested arrays
		return core_concat.apply( [], ret );
	},

	// A global GUID counter for objects
	guid: 1,

	// Bind a function to a context, optionally partially applying any
	// arguments.
	proxy: function( fn, context ) {
		var tmp, args, proxy;

		if ( typeof context === "string" ) {
			tmp = fn[ context ];
			context = fn;
			fn = tmp;
		}

		// Quick check to determine if target is callable, in the spec
		// this throws a TypeError, but we will just return undefined.
		if ( !jQuery.isFunction( fn ) ) {
			return undefined;
		}

		// Simulated bind
		args = core_slice.call( arguments, 2 );
		proxy = function() {
			return fn.apply( context || this, args.concat( core_slice.call( arguments ) ) );
		};

		// Set the guid of unique handler to the same of original handler, so it can be removed
		proxy.guid = fn.guid = fn.guid || jQuery.guid++;

		return proxy;
	},

	// Multifunctional method to get and set values of a collection
	// The value/s can optionally be executed if it's a function
	access: function( elems, fn, key, value, chainable, emptyGet, raw ) {
		var i = 0,
			length = elems.length,
			bulk = key == null;

		// Sets many values
		if ( jQuery.type( key ) === "object" ) {
			chainable = true;
			for ( i in key ) {
				jQuery.access( elems, fn, i, key[i], true, emptyGet, raw );
			}

		// Sets one value
		} else if ( value !== undefined ) {
			chainable = true;

			if ( !jQuery.isFunction( value ) ) {
				raw = true;
			}

			if ( bulk ) {
				// Bulk operations run against the entire set
				if ( raw ) {
					fn.call( elems, value );
					fn = null;

				// ...except when executing function values
				} else {
					bulk = fn;
					fn = function( elem, key, value ) {
						return bulk.call( jQuery( elem ), value );
					};
				}
			}

			if ( fn ) {
				for ( ; i < length; i++ ) {
					fn( elems[i], key, raw ? value : value.call( elems[i], i, fn( elems[i], key ) ) );
				}
			}
		}

		return chainable ?
			elems :

			// Gets
			bulk ?
				fn.call( elems ) :
				length ? fn( elems[0], key ) : emptyGet;
	},

	now: Date.now,

	// A method for quickly swapping in/out CSS properties to get correct calculations.
	// Note: this method belongs to the css module but it's needed here for the support module.
	// If support gets modularized, this method should be moved back to the css module.
	swap: function( elem, options, callback, args ) {
		var ret, name,
			old = {};

		// Remember the old values, and insert the new ones
		for ( name in options ) {
			old[ name ] = elem.style[ name ];
			elem.style[ name ] = options[ name ];
		}

		ret = callback.apply( elem, args || [] );

		// Revert the old values
		for ( name in options ) {
			elem.style[ name ] = old[ name ];
		}

		return ret;
	}
});

jQuery.ready.promise = function( obj ) {
	if ( !readyList ) {

		readyList = jQuery.Deferred();

		// Catch cases where $(document).ready() is called after the browser event has already occurred.
		// we once tried to use readyState "interactive" here, but it caused issues like the one
		// discovered by ChrisS here: http://bugs.jquery.com/ticket/12282#comment:15
		if ( document.readyState === "complete" ) {
			// Handle it asynchronously to allow scripts the opportunity to delay ready
			setTimeout( jQuery.ready );

		} else {

			// Use the handy event callback
			document.addEventListener( "DOMContentLoaded", completed, false );

			// A fallback to window.onload, that will always work
			window.addEventListener( "load", completed, false );
		}
	}
	return readyList.promise( obj );
};

// Populate the class2type map
jQuery.each("Boolean Number String Function Array Date RegExp Object Error".split(" "), function(i, name) {
	class2type[ "[object " + name + "]" ] = name.toLowerCase();
});

function isArraylike( obj ) {
	var length = obj.length,
		type = jQuery.type( obj );

	if ( jQuery.isWindow( obj ) ) {
		return false;
	}

	if ( obj.nodeType === 1 && length ) {
		return true;
	}

	return type === "array" || type !== "function" &&
		( length === 0 ||
		typeof length === "number" && length > 0 && ( length - 1 ) in obj );
}

// All jQuery objects should point back to these
rootjQuery = jQuery(document);
/*!
 * Sizzle CSS Selector Engine v1.9.2-pre
 * http://sizzlejs.com/
 *
 * Copyright 2013 jQuery Foundation, Inc. and other contributors
 * Released under the MIT license
 * http://jquery.org/license
 *
 * Date: 2013-04-16
 */
(function( window, undefined ) {

var i,
	cachedruns,
	Expr,
	getText,
	isXML,
	compile,
	outermostContext,
	sortInput,

	// Local document vars
	setDocument,
	document,
	docElem,
	documentIsHTML,
	rbuggyQSA,
	rbuggyMatches,
	matches,
	contains,

	// Instance-specific data
	expando = "sizzle" + -(new Date()),
	preferredDoc = window.document,
	support = {},
	dirruns = 0,
	done = 0,
	classCache = createCache(),
	tokenCache = createCache(),
	compilerCache = createCache(),
	hasDuplicate = false,
	sortOrder = function() { return 0; },

	// General-purpose constants
	strundefined = typeof undefined,
	MAX_NEGATIVE = 1 << 31,

	// Array methods
	arr = [],
	pop = arr.pop,
	push_native = arr.push,
	push = arr.push,
	slice = arr.slice,
	// Use a stripped-down indexOf if we can't use a native one
	indexOf = arr.indexOf || function( elem ) {
		var i = 0,
			len = this.length;
		for ( ; i < len; i++ ) {
			if ( this[i] === elem ) {
				return i;
			}
		}
		return -1;
	},

	booleans = "checked|selected|async|autofocus|autoplay|controls|defer|disabled|hidden|ismap|loop|multiple|open|readonly|required|scoped",

	// Regular expressions

	// Whitespace characters http://www.w3.org/TR/css3-selectors/#whitespace
	whitespace = "[\\x20\\t\\r\\n\\f]",
	// http://www.w3.org/TR/css3-syntax/#characters
	characterEncoding = "(?:\\\\.|[\\w-]|[^\\x00-\\xa0])+",

	// Loosely modeled on CSS identifier characters
	// An unquoted value should be a CSS identifier http://www.w3.org/TR/css3-selectors/#attribute-selectors
	// Proper syntax: http://www.w3.org/TR/CSS21/syndata.html#value-def-identifier
	identifier = characterEncoding.replace( "w", "w#" ),

	// Acceptable operators http://www.w3.org/TR/selectors/#attribute-selectors
	attributes = "\\[" + whitespace + "*(" + characterEncoding + ")" + whitespace +
		"*(?:([*^$|!~]?=)" + whitespace + "*(?:(['\"])((?:\\\\.|[^\\\\])*?)\\3|(" + identifier + ")|)|)" + whitespace + "*\\]",

	// Prefer arguments quoted,
	//   then not containing pseudos/brackets,
	//   then attribute selectors/non-parenthetical expressions,
	//   then anything else
	// These preferences are here to reduce the number of selectors
	//   needing tokenize in the PSEUDO preFilter
	pseudos = ":(" + characterEncoding + ")(?:\\(((['\"])((?:\\\\.|[^\\\\])*?)\\3|((?:\\\\.|[^\\\\()[\\]]|" + attributes.replace( 3, 8 ) + ")*)|.*)\\)|)",

	// Leading and non-escaped trailing whitespace, capturing some non-whitespace characters preceding the latter
	rtrim = new RegExp( "^" + whitespace + "+|((?:^|[^\\\\])(?:\\\\.)*)" + whitespace + "+$", "g" ),

	rcomma = new RegExp( "^" + whitespace + "*," + whitespace + "*" ),
	rcombinators = new RegExp( "^" + whitespace + "*([>+~]|" + whitespace + ")" + whitespace + "*" ),

	rsibling = new RegExp( whitespace + "*[+~]" ),
	rattributeQuotes = new RegExp( "=" + whitespace + "*([^\\]'\"]*)" + whitespace + "*\\]", "g" ),

	rpseudo = new RegExp( pseudos ),
	ridentifier = new RegExp( "^" + identifier + "$" ),

	matchExpr = {
		"ID": new RegExp( "^#(" + characterEncoding + ")" ),
		"CLASS": new RegExp( "^\\.(" + characterEncoding + ")" ),
		"TAG": new RegExp( "^(" + characterEncoding.replace( "w", "w*" ) + ")" ),
		"ATTR": new RegExp( "^" + attributes ),
		"PSEUDO": new RegExp( "^" + pseudos ),
		"CHILD": new RegExp( "^:(only|first|last|nth|nth-last)-(child|of-type)(?:\\(" + whitespace +
			"*(even|odd|(([+-]|)(\\d*)n|)" + whitespace + "*(?:([+-]|)" + whitespace +
			"*(\\d+)|))" + whitespace + "*\\)|)", "i" ),
		"boolean": new RegExp( "^(?:" + booleans + ")$", "i" ),
		// For use in libraries implementing .is()
		// We use this for POS matching in `select`
		"needsContext": new RegExp( "^" + whitespace + "*[>+~]|:(even|odd|eq|gt|lt|nth|first|last)(?:\\(" +
			whitespace + "*((?:-\\d)?\\d*)" + whitespace + "*\\)|)(?=[^-]|$)", "i" )
	},

	rnative = /^[^{]+\{\s*\[native \w/,

	// Easily-parseable/retrievable ID or TAG or CLASS selectors
	rquickExpr = /^(?:#([\w-]+)|(\w+)|\.([\w-]+))$/,

	rinputs = /^(?:input|select|textarea|button)$/i,
	rheader = /^h\d$/i,

	rescape = /'|\\/g,

	// CSS escapes http://www.w3.org/TR/CSS21/syndata.html#escaped-characters
	runescape = /\\([\da-fA-F]{1,6}[\x20\t\r\n\f]?|.)/g,
	funescape = function( _, escaped ) {
		var high = "0x" + escaped - 0x10000;
		// NaN means non-codepoint
		return high !== high ?
			escaped :
			// BMP codepoint
			high < 0 ?
				String.fromCharCode( high + 0x10000 ) :
				// Supplemental Plane codepoint (surrogate pair)
				String.fromCharCode( high >> 10 | 0xD800, high & 0x3FF | 0xDC00 );
	};

// Optimize for push.apply( _, NodeList )
try {
	push.apply(
		(arr = slice.call( preferredDoc.childNodes )),
		preferredDoc.childNodes
	);
	// Support: Android<4.0
	// Detect silently failing push.apply
	arr[ preferredDoc.childNodes.length ].nodeType;
} catch ( e ) {
	push = { apply: arr.length ?

		// Leverage slice if possible
		function( target, els ) {
			push_native.apply( target, slice.call(els) );
		} :

		// Support: IE<9
		// Otherwise append directly
		function( target, els ) {
			var j = target.length,
				i = 0;
			// Can't trust NodeList.length
			while ( (target[j++] = els[i++]) ) {}
			target.length = j - 1;
		}
	};
}

/**
 * For feature detection
 * @param {Function} fn The function to test for native support
 */
function isNative( fn ) {
	return rnative.test( fn + "" );
}

/**
 * Create key-value caches of limited size
 * @returns {Function(string, Object)} Returns the Object data after storing it on itself with
 *	property name the (space-suffixed) string and (if the cache is larger than Expr.cacheLength)
 *	deleting the oldest entry
 */
function createCache() {
	var cache,
		keys = [];

	return (cache = function( key, value ) {
		// Use (key + " ") to avoid collision with native prototype properties (see Issue #157)
		if ( keys.push( key += " " ) > Expr.cacheLength ) {
			// Only keep the most recent entries
			delete cache[ keys.shift() ];
		}
		return (cache[ key ] = value);
	});
}

/**
 * Mark a function for special use by Sizzle
 * @param {Function} fn The function to mark
 */
function markFunction( fn ) {
	fn[ expando ] = true;
	return fn;
}

/**
 * Support testing using an element
 * @param {Function} fn Passed the created div and expects a boolean result
 */
function assert( fn ) {
	var div = document.createElement("div");

	try {
		return !!fn( div );
	} catch (e) {
		return false;
	} finally {
		if ( div.parentNode ) {
			div.parentNode.removeChild( div );
		}
		// release memory in IE
		div = null;
	}
}

function Sizzle( selector, context, results, seed ) {
	var match, elem, m, nodeType,
		// QSA vars
		i, groups, old, nid, newContext, newSelector;

	if ( ( context ? context.ownerDocument || context : preferredDoc ) !== document ) {
		setDocument( context );
	}

	context = context || document;
	results = results || [];

	if ( !selector || typeof selector !== "string" ) {
		return results;
	}

	if ( (nodeType = context.nodeType) !== 1 && nodeType !== 9 ) {
		return [];
	}

	if ( documentIsHTML && !seed ) {

		// Shortcuts
		if ( (match = rquickExpr.exec( selector )) ) {
			// Speed-up: Sizzle("#ID")
			if ( (m = match[1]) ) {
				if ( nodeType === 9 ) {
					elem = context.getElementById( m );
					// Check parentNode to catch when Blackberry 4.6 returns
					// nodes that are no longer in the document #6963
					if ( elem && elem.parentNode ) {
						// Handle the case where IE, Opera, and Webkit return items
						// by name instead of ID
						if ( elem.id === m ) {
							results.push( elem );
							return results;
						}
					} else {
						return results;
					}
				} else {
					// Context is not a document
					if ( context.ownerDocument && (elem = context.ownerDocument.getElementById( m )) &&
						contains( context, elem ) && elem.id === m ) {
						results.push( elem );
						return results;
					}
				}

			// Speed-up: Sizzle("TAG")
			} else if ( match[2] ) {
				push.apply( results, context.getElementsByTagName( selector ) );
				return results;

			// Speed-up: Sizzle(".CLASS")
			} else if ( (m = match[3]) && support.getElementsByClassName && context.getElementsByClassName ) {
				push.apply( results, context.getElementsByClassName( m ) );
				return results;
			}
		}

		// QSA path
		if ( support.qsa && (!rbuggyQSA || !rbuggyQSA.test( selector )) ) {
			nid = old = expando;
			newContext = context;
			newSelector = nodeType === 9 && selector;

			// qSA works strangely on Element-rooted queries
			// We can work around this by specifying an extra ID on the root
			// and working up from there (Thanks to Andrew Dupont for the technique)
			// IE 8 doesn't work on object elements
			if ( nodeType === 1 && context.nodeName.toLowerCase() !== "object" ) {
				groups = tokenize( selector );

				if ( (old = context.getAttribute("id")) ) {
					nid = old.replace( rescape, "\\$&" );
				} else {
					context.setAttribute( "id", nid );
				}
				nid = "[id='" + nid + "'] ";

				i = groups.length;
				while ( i-- ) {
					groups[i] = nid + toSelector( groups[i] );
				}
				newContext = rsibling.test( selector ) && context.parentNode || context;
				newSelector = groups.join(",");
			}

			if ( newSelector ) {
				try {
					push.apply( results,
						newContext.querySelectorAll( newSelector )
					);
					return results;
				} catch(qsaError) {
				} finally {
					if ( !old ) {
						context.removeAttribute("id");
					}
				}
			}
		}
	}

	// All others
	return select( selector.replace( rtrim, "$1" ), context, results, seed );
}

/**
 * Detect xml
 * @param {Element|Object} elem An element or a document
 */
isXML = Sizzle.isXML = function( elem ) {
	// documentElement is verified for cases where it doesn't yet exist
	// (such as loading iframes in IE - #4833)
	var documentElement = elem && (elem.ownerDocument || elem).documentElement;
	return documentElement ? documentElement.nodeName !== "HTML" : false;
};

/**
 * Sets document-related variables once based on the current document
 * @param {Element|Object} [doc] An element or document object to use to set the document
 * @returns {Object} Returns the current document
 */
setDocument = Sizzle.setDocument = function( node ) {
	var doc = node ? node.ownerDocument || node : preferredDoc;

	// If no document and documentElement is available, return
	if ( doc === document || doc.nodeType !== 9 || !doc.documentElement ) {
		return document;
	}

	// Set our document
	document = doc;
	docElem = doc.documentElement;

	// Support tests
	documentIsHTML = !isXML( doc );

	// Check if getElementsByTagName("*") returns only elements
	support.getElementsByTagName = assert(function( div ) {
		div.appendChild( doc.createComment("") );
		return !div.getElementsByTagName("*").length;
	});

	// Support: IE<8
	// Verify that getAttribute really returns attributes and not properties (excepting IE8 booleans)
	support.attributes = assert(function( div ) {
		div.className = "i";
		return !div.getAttribute("className");
	});

	// Check if getElementsByClassName can be trusted
	support.getElementsByClassName = assert(function( div ) {
		div.innerHTML = "<div class='a'></div><div class='a i'></div>";

		// Support: Safari<4
		// Catch class over-caching
		div.firstChild.className = "i";
		// Support: Opera<10
		// Catch gEBCN failure to find non-leading classes
		return div.getElementsByClassName("i").length === 2;
	});

	// Support: Webkit<537.32 - Safari 6.0.3/Chrome 25 (fixed in Chrome 27)
	// Detached nodes confoundingly follow *each other*
	support.sortDetached = assert(function( div1 ) {
		// Should return 1, but returns 4 (following)
		return div1.compareDocumentPosition( document.createElement("div") ) & 1;
	});

	// Support: IE<10
	// Check if getElementById returns elements by name
	// Support: Windows 8 Native Apps
	// Assigning innerHTML with "name" attributes throws uncatchable exceptions
	// (http://msdn.microsoft.com/en-us/library/ie/hh465388.aspx)
	// and the broken getElementById methods don't pick up programatically-set names,
	// so use a roundabout getElementsByName test
	support.getById = assert(function( div ) {
		docElem.appendChild( div ).id = expando;
		return !doc.getElementsByName || !doc.getElementsByName( expando ).length;
	});

	// ID find and filter
	if ( support.getById ) {
		Expr.find["ID"] = function( id, context ) {
			if ( typeof context.getElementById !== strundefined && documentIsHTML ) {
				var m = context.getElementById( id );
				// Check parentNode to catch when Blackberry 4.6 returns
				// nodes that are no longer in the document #6963
				return m && m.parentNode ? [m] : [];
			}
		};
		Expr.filter["ID"] = function( id ) {
			var attrId = id.replace( runescape, funescape );
			return function( elem ) {
				return elem.getAttribute("id") === attrId;
			};
		};
	} else {
		Expr.find["ID"] = function( id, context ) {
			if ( typeof context.getElementById !== strundefined && documentIsHTML ) {
				var m = context.getElementById( id );

				return m ?
					m.id === id || typeof m.getAttributeNode !== strundefined && m.getAttributeNode("id").value === id ?
						[m] :
						undefined :
					[];
			}
		};
		Expr.filter["ID"] =  function( id ) {
			var attrId = id.replace( runescape, funescape );
			return function( elem ) {
				var node = typeof elem.getAttributeNode !== strundefined && elem.getAttributeNode("id");
				return node && node.value === attrId;
			};
		};
	}

	// Tag
	Expr.find["TAG"] = support.getElementsByTagName ?
		function( tag, context ) {
			if ( typeof context.getElementsByTagName !== strundefined ) {
				return context.getElementsByTagName( tag );
			}
		} :
		function( tag, context ) {
			var elem,
				tmp = [],
				i = 0,
				results = context.getElementsByTagName( tag );

			// Filter out possible comments
			if ( tag === "*" ) {
				while ( (elem = results[i++]) ) {
					if ( elem.nodeType === 1 ) {
						tmp.push( elem );
					}
				}

				return tmp;
			}
			return results;
		};

	// Class
	Expr.find["CLASS"] = support.getElementsByClassName && function( className, context ) {
		if ( typeof context.getElementsByClassName !== strundefined && documentIsHTML ) {
			return context.getElementsByClassName( className );
		}
	};

	// QSA and matchesSelector support

	// matchesSelector(:active) reports false when true (IE9/Opera 11.5)
	rbuggyMatches = [];

	// qSa(:focus) reports false when true (Chrome 21)
	// We allow this because of a bug in IE8/9 that throws an error
	// whenever `document.activeElement` is accessed on an iframe
	// So, we allow :focus to pass through QSA all the time to avoid the IE error
	// See http://bugs.jquery.com/ticket/13378
	rbuggyQSA = [];

	if ( (support.qsa = isNative(doc.querySelectorAll)) ) {
		// Build QSA regex
		// Regex strategy adopted from Diego Perini
		assert(function( div ) {
			// Select is set to empty string on purpose
			// This is to test IE's treatment of not explicitly
			// setting a boolean content attribute,
			// since its presence should be enough
			// http://bugs.jquery.com/ticket/12359
			div.innerHTML = "<select><option selected=''></option></select>";

			// Support: IE8
			// Boolean attributes and "value" are not treated correctly
			if ( !div.querySelectorAll("[selected]").length ) {
				rbuggyQSA.push( "\\[" + whitespace + "*(?:value|" + booleans + ")" );
			}

			// Webkit/Opera - :checked should return selected option elements
			// http://www.w3.org/TR/2011/REC-css3-selectors-20110929/#checked
			// IE8 throws error here and will not see later tests
			if ( !div.querySelectorAll(":checked").length ) {
				rbuggyQSA.push(":checked");
			}
		});

		assert(function( div ) {

			// Support: Opera 10-12/IE8
			// ^= $= *= and empty values
			// Should not select anything
			// Support: Windows 8 Native Apps
			// The type attribute is restricted during .innerHTML assignment
			var input = document.createElement("input");
			input.setAttribute( "type", "hidden" );
			div.appendChild( input ).setAttribute( "t", "" );

			if ( div.querySelectorAll("[t^='']").length ) {
				rbuggyQSA.push( "[*^$]=" + whitespace + "*(?:''|\"\")" );
			}

			// FF 3.5 - :enabled/:disabled and hidden elements (hidden elements are still enabled)
			// IE8 throws error here and will not see later tests
			if ( !div.querySelectorAll(":enabled").length ) {
				rbuggyQSA.push( ":enabled", ":disabled" );
			}

			// Opera 10-11 does not throw on post-comma invalid pseudos
			div.querySelectorAll("*,:x");
			rbuggyQSA.push(",.*:");
		});
	}

	if ( (support.matchesSelector = isNative( (matches = docElem.webkitMatchesSelector ||
		docElem.mozMatchesSelector ||
		docElem.oMatchesSelector ||
		docElem.msMatchesSelector) )) ) {

		assert(function( div ) {
			// Check to see if it's possible to do matchesSelector
			// on a disconnected node (IE 9)
			support.disconnectedMatch = matches.call( div, "div" );

			// This should fail with an exception
			// Gecko does not error, returns false instead
			matches.call( div, "[s!='']:x" );
			rbuggyMatches.push( "!=", pseudos );
		});
	}

	rbuggyQSA = rbuggyQSA.length && new RegExp( rbuggyQSA.join("|") );
	rbuggyMatches = rbuggyMatches.length && new RegExp( rbuggyMatches.join("|") );

	// Element contains another
	// Purposefully does not implement inclusive descendent
	// As in, an element does not contain itself
	contains = isNative(docElem.contains) || docElem.compareDocumentPosition ?
		function( a, b ) {
			var adown = a.nodeType === 9 ? a.documentElement : a,
				bup = b && b.parentNode;
			return a === bup || !!( bup && bup.nodeType === 1 && (
				adown.contains ?
					adown.contains( bup ) :
					a.compareDocumentPosition && a.compareDocumentPosition( bup ) & 16
			));
		} :
		function( a, b ) {
			if ( b ) {
				while ( (b = b.parentNode) ) {
					if ( b === a ) {
						return true;
					}
				}
			}
			return false;
		};

	// Document order sorting
	sortOrder = docElem.compareDocumentPosition ?
	function( a, b ) {

		// Flag for duplicate removal
		if ( a === b ) {
			hasDuplicate = true;
			return 0;
		}

		var compare = b.compareDocumentPosition && a.compareDocumentPosition && a.compareDocumentPosition( b );

		if ( compare ) {
			// Disconnected nodes
			if ( compare & 1 ||
				(!support.sortDetached && b.compareDocumentPosition( a ) === compare) ) {

				// Choose the first element that is related to our preferred document
				if ( a === doc || contains(preferredDoc, a) ) {
					return -1;
				}
				if ( b === doc || contains(preferredDoc, b) ) {
					return 1;
				}

				// Maintain original order
				return sortInput ?
					( indexOf.call( sortInput, a ) - indexOf.call( sortInput, b ) ) :
					0;
			}

			return compare & 4 ? -1 : 1;
		}

		// Not directly comparable, sort on existence of method
		return a.compareDocumentPosition ? -1 : 1;
	} :
	function( a, b ) {
		var cur,
			i = 0,
			aup = a.parentNode,
			bup = b.parentNode,
			ap = [ a ],
			bp = [ b ];

		// Exit early if the nodes are identical
		if ( a === b ) {
			hasDuplicate = true;
			return 0;

		// Parentless nodes are either documents or disconnected
		} else if ( !aup || !bup ) {
			return a === doc ? -1 :
				b === doc ? 1 :
				aup ? -1 :
				bup ? 1 :
				sortInput ?
				( indexOf.call( sortInput, a ) - indexOf.call( sortInput, b ) ) :
				0;

		// If the nodes are siblings, we can do a quick check
		} else if ( aup === bup ) {
			return siblingCheck( a, b );
		}

		// Otherwise we need full lists of their ancestors for comparison
		cur = a;
		while ( (cur = cur.parentNode) ) {
			ap.unshift( cur );
		}
		cur = b;
		while ( (cur = cur.parentNode) ) {
			bp.unshift( cur );
		}

		// Walk down the tree looking for a discrepancy
		while ( ap[i] === bp[i] ) {
			i++;
		}

		return i ?
			// Do a sibling check if the nodes have a common ancestor
			siblingCheck( ap[i], bp[i] ) :

			// Otherwise nodes in our document sort first
			ap[i] === preferredDoc ? -1 :
			bp[i] === preferredDoc ? 1 :
			0;
	};

	return document;
};

Sizzle.matches = function( expr, elements ) {
	return Sizzle( expr, null, null, elements );
};

Sizzle.matchesSelector = function( elem, expr ) {
	// Set document vars if needed
	if ( ( elem.ownerDocument || elem ) !== document ) {
		setDocument( elem );
	}

	// Make sure that attribute selectors are quoted
	expr = expr.replace( rattributeQuotes, "='$1']" );

	// rbuggyQSA always contains :focus, so no need for an existence check
	if ( support.matchesSelector && documentIsHTML &&
		(!rbuggyMatches || !rbuggyMatches.test(expr)) &&
		(!rbuggyQSA     || !rbuggyQSA.test(expr)) ) {

		try {
			var ret = matches.call( elem, expr );

			// IE 9's matchesSelector returns false on disconnected nodes
			if ( ret || support.disconnectedMatch ||
					// As well, disconnected nodes are said to be in a document
					// fragment in IE 9
					elem.document && elem.document.nodeType !== 11 ) {
				return ret;
			}
		} catch(e) {}
	}

	return Sizzle( expr, document, null, [elem] ).length > 0;
};

Sizzle.contains = function( context, elem ) {
	// Set document vars if needed
	if ( ( context.ownerDocument || context ) !== document ) {
		setDocument( context );
	}
	return contains( context, elem );
};

Sizzle.attr = function( elem, name ) {
	// Set document vars if needed
	if ( ( elem.ownerDocument || elem ) !== document ) {
		setDocument( elem );
	}

	var fn = Expr.attrHandle[ name.toLowerCase() ],
		val = fn && fn( elem, name, !documentIsHTML );

	return val === undefined ?
		support.attributes || !documentIsHTML ?
			elem.getAttribute( name ) :
			(val = elem.getAttributeNode(name)) && val.specified ?
				val.value :
				null :
		val;
};

Sizzle.error = function( msg ) {
	throw new Error( "Syntax error, unrecognized expression: " + msg );
};

// Document sorting and removing duplicates
Sizzle.uniqueSort = function( results ) {
	var elem,
		duplicates = [],
		j = 0,
		i = 0;

	// Unless we *know* we can detect duplicates, assume their presence
	hasDuplicate = !support.detectDuplicates;
	sortInput = !support.sortStable && results.slice( 0 );
	results.sort( sortOrder );

	if ( hasDuplicate ) {
		while ( (elem = results[i++]) ) {
			if ( elem === results[ i ] ) {
				j = duplicates.push( i );
			}
		}
		while ( j-- ) {
			results.splice( duplicates[ j ], 1 );
		}
	}

	return results;
};

/**
 * Checks document order of two siblings
 * @param {Element} a
 * @param {Element} b
 * @returns Returns -1 if a precedes b, 1 if a follows b
 */
function siblingCheck( a, b ) {
	var cur = b && a,
		diff = cur && ( ~b.sourceIndex || MAX_NEGATIVE ) - ( ~a.sourceIndex || MAX_NEGATIVE );

	// Use IE sourceIndex if available on both nodes
	if ( diff ) {
		return diff;
	}

	// Check if b follows a
	if ( cur ) {
		while ( (cur = cur.nextSibling) ) {
			if ( cur === b ) {
				return -1;
			}
		}
	}

	return a ? 1 : -1;
}

// Fetches boolean attributes by node
function boolHandler( elem, name, isXML ) {
	var val;
	return isXML ?
		undefined :
		(val = elem.getAttributeNode( name )) && val.specified ?
			val.value :
			elem[ name ] === true ? name.toLowerCase() : null;
}

// Fetches attributes without interpolation
// http://msdn.microsoft.com/en-us/library/ms536429%28VS.85%29.aspx
function interpolationHandler( elem, name, isXML ) {
	var val;
	return isXML ?
		undefined :
		(val = elem.getAttribute( name, name.toLowerCase() === "type" ? 1 : 2 ));
}

// Returns a function to use in pseudos for input types
function createInputPseudo( type ) {
	return function( elem ) {
		var name = elem.nodeName.toLowerCase();
		return name === "input" && elem.type === type;
	};
}

// Returns a function to use in pseudos for buttons
function createButtonPseudo( type ) {
	return function( elem ) {
		var name = elem.nodeName.toLowerCase();
		return (name === "input" || name === "button") && elem.type === type;
	};
}

// Returns a function to use in pseudos for positionals
function createPositionalPseudo( fn ) {
	return markFunction(function( argument ) {
		argument = +argument;
		return markFunction(function( seed, matches ) {
			var j,
				matchIndexes = fn( [], seed.length, argument ),
				i = matchIndexes.length;

			// Match elements found at the specified indexes
			while ( i-- ) {
				if ( seed[ (j = matchIndexes[i]) ] ) {
					seed[j] = !(matches[j] = seed[j]);
				}
			}
		});
	});
}

/**
 * Utility function for retrieving the text value of an array of DOM nodes
 * @param {Array|Element} elem
 */
getText = Sizzle.getText = function( elem ) {
	var node,
		ret = "",
		i = 0,
		nodeType = elem.nodeType;

	if ( !nodeType ) {
		// If no nodeType, this is expected to be an array
		for ( ; (node = elem[i]); i++ ) {
			// Do not traverse comment nodes
			ret += getText( node );
		}
	} else if ( nodeType === 1 || nodeType === 9 || nodeType === 11 ) {
		// Use textContent for elements
		// innerText usage removed for consistency of new lines (see #11153)
		if ( typeof elem.textContent === "string" ) {
			return elem.textContent;
		} else {
			// Traverse its children
			for ( elem = elem.firstChild; elem; elem = elem.nextSibling ) {
				ret += getText( elem );
			}
		}
	} else if ( nodeType === 3 || nodeType === 4 ) {
		return elem.nodeValue;
	}
	// Do not include comment or processing instruction nodes

	return ret;
};

Expr = Sizzle.selectors = {

	// Can be adjusted by the user
	cacheLength: 50,

	createPseudo: markFunction,

	match: matchExpr,

	attrHandle: {},

	find: {},

	relative: {
		">": { dir: "parentNode", first: true },
		" ": { dir: "parentNode" },
		"+": { dir: "previousSibling", first: true },
		"~": { dir: "previousSibling" }
	},

	preFilter: {
		"ATTR": function( match ) {
			match[1] = match[1].replace( runescape, funescape );

			// Move the given value to match[3] whether quoted or unquoted
			match[3] = ( match[4] || match[5] || "" ).replace( runescape, funescape );

			if ( match[2] === "~=" ) {
				match[3] = " " + match[3] + " ";
			}

			return match.slice( 0, 4 );
		},

		"CHILD": function( match ) {
			/* matches from matchExpr["CHILD"]
				1 type (only|nth|...)
				2 what (child|of-type)
				3 argument (even|odd|\d*|\d*n([+-]\d+)?|...)
				4 xn-component of xn+y argument ([+-]?\d*n|)
				5 sign of xn-component
				6 x of xn-component
				7 sign of y-component
				8 y of y-component
			*/
			match[1] = match[1].toLowerCase();

			if ( match[1].slice( 0, 3 ) === "nth" ) {
				// nth-* requires argument
				if ( !match[3] ) {
					Sizzle.error( match[0] );
				}

				// numeric x and y parameters for Expr.filter.CHILD
				// remember that false/true cast respectively to 0/1
				match[4] = +( match[4] ? match[5] + (match[6] || 1) : 2 * ( match[3] === "even" || match[3] === "odd" ) );
				match[5] = +( ( match[7] + match[8] ) || match[3] === "odd" );

			// other types prohibit arguments
			} else if ( match[3] ) {
				Sizzle.error( match[0] );
			}

			return match;
		},

		"PSEUDO": function( match ) {
			var excess,
				unquoted = !match[5] && match[2];

			if ( matchExpr["CHILD"].test( match[0] ) ) {
				return null;
			}

			// Accept quoted arguments as-is
			if ( match[4] ) {
				match[2] = match[4];

			// Strip excess characters from unquoted arguments
			} else if ( unquoted && rpseudo.test( unquoted ) &&
				// Get excess from tokenize (recursively)
				(excess = tokenize( unquoted, true )) &&
				// advance to the next closing parenthesis
				(excess = unquoted.indexOf( ")", unquoted.length - excess ) - unquoted.length) ) {

				// excess is a negative index
				match[0] = match[0].slice( 0, excess );
				match[2] = unquoted.slice( 0, excess );
			}

			// Return only captures needed by the pseudo filter method (type and argument)
			return match.slice( 0, 3 );
		}
	},

	filter: {

		"TAG": function( nodeNameSelector ) {
			var nodeName = nodeNameSelector.replace( runescape, funescape ).toLowerCase();
			return nodeNameSelector === "*" ?
				function() { return true; } :
				function( elem ) {
					return elem.nodeName && elem.nodeName.toLowerCase() === nodeName;
				};
		},

		"CLASS": function( className ) {
			var pattern = classCache[ className + " " ];

			return pattern ||
				(pattern = new RegExp( "(^|" + whitespace + ")" + className + "(" + whitespace + "|$)" )) &&
				classCache( className, function( elem ) {
					return pattern.test( typeof elem.className === "string" && elem.className || typeof elem.getAttribute !== strundefined && elem.getAttribute("class") || "" );
				});
		},

		"ATTR": function( name, operator, check ) {
			return function( elem ) {
				var result = Sizzle.attr( elem, name );

				if ( result == null ) {
					return operator === "!=";
				}
				if ( !operator ) {
					return true;
				}

				result += "";

				return operator === "=" ? result === check :
					operator === "!=" ? result !== check :
					operator === "^=" ? check && result.indexOf( check ) === 0 :
					operator === "*=" ? check && result.indexOf( check ) > -1 :
					operator === "$=" ? check && result.slice( -check.length ) === check :
					operator === "~=" ? ( " " + result + " " ).indexOf( check ) > -1 :
					operator === "|=" ? result === check || result.slice( 0, check.length + 1 ) === check + "-" :
					false;
			};
		},

		"CHILD": function( type, what, argument, first, last ) {
			var simple = type.slice( 0, 3 ) !== "nth",
				forward = type.slice( -4 ) !== "last",
				ofType = what === "of-type";

			return first === 1 && last === 0 ?

				// Shortcut for :nth-*(n)
				function( elem ) {
					return !!elem.parentNode;
				} :

				function( elem, context, xml ) {
					var cache, outerCache, node, diff, nodeIndex, start,
						dir = simple !== forward ? "nextSibling" : "previousSibling",
						parent = elem.parentNode,
						name = ofType && elem.nodeName.toLowerCase(),
						useCache = !xml && !ofType;

					if ( parent ) {

						// :(first|last|only)-(child|of-type)
						if ( simple ) {
							while ( dir ) {
								node = elem;
								while ( (node = node[ dir ]) ) {
									if ( ofType ? node.nodeName.toLowerCase() === name : node.nodeType === 1 ) {
										return false;
									}
								}
								// Reverse direction for :only-* (if we haven't yet done so)
								start = dir = type === "only" && !start && "nextSibling";
							}
							return true;
						}

						start = [ forward ? parent.firstChild : parent.lastChild ];

						// non-xml :nth-child(...) stores cache data on `parent`
						if ( forward && useCache ) {
							// Seek `elem` from a previously-cached index
							outerCache = parent[ expando ] || (parent[ expando ] = {});
							cache = outerCache[ type ] || [];
							nodeIndex = cache[0] === dirruns && cache[1];
							diff = cache[0] === dirruns && cache[2];
							node = nodeIndex && parent.childNodes[ nodeIndex ];

							while ( (node = ++nodeIndex && node && node[ dir ] ||

								// Fallback to seeking `elem` from the start
								(diff = nodeIndex = 0) || start.pop()) ) {

								// When found, cache indexes on `parent` and break
								if ( node.nodeType === 1 && ++diff && node === elem ) {
									outerCache[ type ] = [ dirruns, nodeIndex, diff ];
									break;
								}
							}

						// Use previously-cached element index if available
						} else if ( useCache && (cache = (elem[ expando ] || (elem[ expando ] = {}))[ type ]) && cache[0] === dirruns ) {
							diff = cache[1];

						// xml :nth-child(...) or :nth-last-child(...) or :nth(-last)?-of-type(...)
						} else {
							// Use the same loop as above to seek `elem` from the start
							while ( (node = ++nodeIndex && node && node[ dir ] ||
								(diff = nodeIndex = 0) || start.pop()) ) {

								if ( ( ofType ? node.nodeName.toLowerCase() === name : node.nodeType === 1 ) && ++diff ) {
									// Cache the index of each encountered element
									if ( useCache ) {
										(node[ expando ] || (node[ expando ] = {}))[ type ] = [ dirruns, diff ];
									}

									if ( node === elem ) {
										break;
									}
								}
							}
						}

						// Incorporate the offset, then check against cycle size
						diff -= last;
						return diff === first || ( diff % first === 0 && diff / first >= 0 );
					}
				};
		},

		"PSEUDO": function( pseudo, argument ) {
			// pseudo-class names are case-insensitive
			// http://www.w3.org/TR/selectors/#pseudo-classes
			// Prioritize by case sensitivity in case custom pseudos are added with uppercase letters
			// Remember that setFilters inherits from pseudos
			var args,
				fn = Expr.pseudos[ pseudo ] || Expr.setFilters[ pseudo.toLowerCase() ] ||
					Sizzle.error( "unsupported pseudo: " + pseudo );

			// The user may use createPseudo to indicate that
			// arguments are needed to create the filter function
			// just as Sizzle does
			if ( fn[ expando ] ) {
				return fn( argument );
			}

			// But maintain support for old signatures
			if ( fn.length > 1 ) {
				args = [ pseudo, pseudo, "", argument ];
				return Expr.setFilters.hasOwnProperty( pseudo.toLowerCase() ) ?
					markFunction(function( seed, matches ) {
						var idx,
							matched = fn( seed, argument ),
							i = matched.length;
						while ( i-- ) {
							idx = indexOf.call( seed, matched[i] );
							seed[ idx ] = !( matches[ idx ] = matched[i] );
						}
					}) :
					function( elem ) {
						return fn( elem, 0, args );
					};
			}

			return fn;
		}
	},

	pseudos: {
		// Potentially complex pseudos
		"not": markFunction(function( selector ) {
			// Trim the selector passed to compile
			// to avoid treating leading and trailing
			// spaces as combinators
			var input = [],
				results = [],
				matcher = compile( selector.replace( rtrim, "$1" ) );

			return matcher[ expando ] ?
				markFunction(function( seed, matches, context, xml ) {
					var elem,
						unmatched = matcher( seed, null, xml, [] ),
						i = seed.length;

					// Match elements unmatched by `matcher`
					while ( i-- ) {
						if ( (elem = unmatched[i]) ) {
							seed[i] = !(matches[i] = elem);
						}
					}
				}) :
				function( elem, context, xml ) {
					input[0] = elem;
					matcher( input, null, xml, results );
					return !results.pop();
				};
		}),

		"has": markFunction(function( selector ) {
			return function( elem ) {
				return Sizzle( selector, elem ).length > 0;
			};
		}),

		"contains": markFunction(function( text ) {
			return function( elem ) {
				return ( elem.textContent || elem.innerText || getText( elem ) ).indexOf( text ) > -1;
			};
		}),

		// "Whether an element is represented by a :lang() selector
		// is based solely on the element's language value
		// being equal to the identifier C,
		// or beginning with the identifier C immediately followed by "-".
		// The matching of C against the element's language value is performed case-insensitively.
		// The identifier C does not have to be a valid language name."
		// http://www.w3.org/TR/selectors/#lang-pseudo
		"lang": markFunction( function( lang ) {
			// lang value must be a valid identifier
			if ( !ridentifier.test(lang || "") ) {
				Sizzle.error( "unsupported lang: " + lang );
			}
			lang = lang.replace( runescape, funescape ).toLowerCase();
			return function( elem ) {
				var elemLang;
				do {
					if ( (elemLang = documentIsHTML ?
						elem.lang :
						elem.getAttribute("xml:lang") || elem.getAttribute("lang")) ) {

						elemLang = elemLang.toLowerCase();
						return elemLang === lang || elemLang.indexOf( lang + "-" ) === 0;
					}
				} while ( (elem = elem.parentNode) && elem.nodeType === 1 );
				return false;
			};
		}),

		// Miscellaneous
		"target": function( elem ) {
			var hash = window.location && window.location.hash;
			return hash && hash.slice( 1 ) === elem.id;
		},

		"root": function( elem ) {
			return elem === docElem;
		},

		"focus": function( elem ) {
			return elem === document.activeElement && (!document.hasFocus || document.hasFocus()) && !!(elem.type || elem.href || ~elem.tabIndex);
		},

		// Boolean properties
		"enabled": function( elem ) {
			return elem.disabled === false;
		},

		"disabled": function( elem ) {
			return elem.disabled === true;
		},

		"checked": function( elem ) {
			// In CSS3, :checked should return both checked and selected elements
			// http://www.w3.org/TR/2011/REC-css3-selectors-20110929/#checked
			var nodeName = elem.nodeName.toLowerCase();
			return (nodeName === "input" && !!elem.checked) || (nodeName === "option" && !!elem.selected);
		},

		"selected": function( elem ) {
			// Accessing this property makes selected-by-default
			// options in Safari work properly
			if ( elem.parentNode ) {
				elem.parentNode.selectedIndex;
			}

			return elem.selected === true;
		},

		// Contents
		"empty": function( elem ) {
			// http://www.w3.org/TR/selectors/#empty-pseudo
			// :empty is only affected by element nodes and content nodes(including text(3), cdata(4)),
			//   not comment, processing instructions, or others
			// Thanks to Diego Perini for the nodeName shortcut
			//   Greater than "@" means alpha characters (specifically not starting with "#" or "?")
			for ( elem = elem.firstChild; elem; elem = elem.nextSibling ) {
				if ( elem.nodeName > "@" || elem.nodeType === 3 || elem.nodeType === 4 ) {
					return false;
				}
			}
			return true;
		},

		"parent": function( elem ) {
			return !Expr.pseudos["empty"]( elem );
		},

		// Element/input types
		"header": function( elem ) {
			return rheader.test( elem.nodeName );
		},

		"input": function( elem ) {
			return rinputs.test( elem.nodeName );
		},

		"button": function( elem ) {
			var name = elem.nodeName.toLowerCase();
			return name === "input" && elem.type === "button" || name === "button";
		},

		"text": function( elem ) {
			var attr;
			// IE6 and 7 will map elem.type to 'text' for new HTML5 types (search, etc)
			// use getAttribute instead to test this case
			return elem.nodeName.toLowerCase() === "input" &&
				elem.type === "text" &&
				( (attr = elem.getAttribute("type")) == null || attr.toLowerCase() === elem.type );
		},

		// Position-in-collection
		"first": createPositionalPseudo(function() {
			return [ 0 ];
		}),

		"last": createPositionalPseudo(function( matchIndexes, length ) {
			return [ length - 1 ];
		}),

		"eq": createPositionalPseudo(function( matchIndexes, length, argument ) {
			return [ argument < 0 ? argument + length : argument ];
		}),

		"even": createPositionalPseudo(function( matchIndexes, length ) {
			var i = 0;
			for ( ; i < length; i += 2 ) {
				matchIndexes.push( i );
			}
			return matchIndexes;
		}),

		"odd": createPositionalPseudo(function( matchIndexes, length ) {
			var i = 1;
			for ( ; i < length; i += 2 ) {
				matchIndexes.push( i );
			}
			return matchIndexes;
		}),

		"lt": createPositionalPseudo(function( matchIndexes, length, argument ) {
			var i = argument < 0 ? argument + length : argument;
			for ( ; --i >= 0; ) {
				matchIndexes.push( i );
			}
			return matchIndexes;
		}),

		"gt": createPositionalPseudo(function( matchIndexes, length, argument ) {
			var i = argument < 0 ? argument + length : argument;
			for ( ; ++i < length; ) {
				matchIndexes.push( i );
			}
			return matchIndexes;
		})
	}
};

// Add button/input type pseudos
for ( i in { radio: true, checkbox: true, file: true, password: true, image: true } ) {
	Expr.pseudos[ i ] = createInputPseudo( i );
}
for ( i in { submit: true, reset: true } ) {
	Expr.pseudos[ i ] = createButtonPseudo( i );
}

function tokenize( selector, parseOnly ) {
	var matched, match, tokens, type,
		soFar, groups, preFilters,
		cached = tokenCache[ selector + " " ];

	if ( cached ) {
		return parseOnly ? 0 : cached.slice( 0 );
	}

	soFar = selector;
	groups = [];
	preFilters = Expr.preFilter;

	while ( soFar ) {

		// Comma and first run
		if ( !matched || (match = rcomma.exec( soFar )) ) {
			if ( match ) {
				// Don't consume trailing commas as valid
				soFar = soFar.slice( match[0].length ) || soFar;
			}
			groups.push( tokens = [] );
		}

		matched = false;

		// Combinators
		if ( (match = rcombinators.exec( soFar )) ) {
			matched = match.shift();
			tokens.push( {
				value: matched,
				// Cast descendant combinators to space
				type: match[0].replace( rtrim, " " )
			} );
			soFar = soFar.slice( matched.length );
		}

		// Filters
		for ( type in Expr.filter ) {
			if ( (match = matchExpr[ type ].exec( soFar )) && (!preFilters[ type ] ||
				(match = preFilters[ type ]( match ))) ) {
				matched = match.shift();
				tokens.push( {
					value: matched,
					type: type,
					matches: match
				} );
				soFar = soFar.slice( matched.length );
			}
		}

		if ( !matched ) {
			break;
		}
	}

	// Return the length of the invalid excess
	// if we're just parsing
	// Otherwise, throw an error or return tokens
	return parseOnly ?
		soFar.length :
		soFar ?
			Sizzle.error( selector ) :
			// Cache the tokens
			tokenCache( selector, groups ).slice( 0 );
}

function toSelector( tokens ) {
	var i = 0,
		len = tokens.length,
		selector = "";
	for ( ; i < len; i++ ) {
		selector += tokens[i].value;
	}
	return selector;
}

function addCombinator( matcher, combinator, base ) {
	var dir = combinator.dir,
		checkNonElements = base && dir === "parentNode",
		doneName = done++;

	return combinator.first ?
		// Check against closest ancestor/preceding element
		function( elem, context, xml ) {
			while ( (elem = elem[ dir ]) ) {
				if ( elem.nodeType === 1 || checkNonElements ) {
					return matcher( elem, context, xml );
				}
			}
		} :

		// Check against all ancestor/preceding elements
		function( elem, context, xml ) {
			var data, cache, outerCache,
				dirkey = dirruns + " " + doneName;

			// We can't set arbitrary data on XML nodes, so they don't benefit from dir caching
			if ( xml ) {
				while ( (elem = elem[ dir ]) ) {
					if ( elem.nodeType === 1 || checkNonElements ) {
						if ( matcher( elem, context, xml ) ) {
							return true;
						}
					}
				}
			} else {
				while ( (elem = elem[ dir ]) ) {
					if ( elem.nodeType === 1 || checkNonElements ) {
						outerCache = elem[ expando ] || (elem[ expando ] = {});
						if ( (cache = outerCache[ dir ]) && cache[0] === dirkey ) {
							if ( (data = cache[1]) === true || data === cachedruns ) {
								return data === true;
							}
						} else {
							cache = outerCache[ dir ] = [ dirkey ];
							cache[1] = matcher( elem, context, xml ) || cachedruns;
							if ( cache[1] === true ) {
								return true;
							}
						}
					}
				}
			}
		};
}

function elementMatcher( matchers ) {
	return matchers.length > 1 ?
		function( elem, context, xml ) {
			var i = matchers.length;
			while ( i-- ) {
				if ( !matchers[i]( elem, context, xml ) ) {
					return false;
				}
			}
			return true;
		} :
		matchers[0];
}

function condense( unmatched, map, filter, context, xml ) {
	var elem,
		newUnmatched = [],
		i = 0,
		len = unmatched.length,
		mapped = map != null;

	for ( ; i < len; i++ ) {
		if ( (elem = unmatched[i]) ) {
			if ( !filter || filter( elem, context, xml ) ) {
				newUnmatched.push( elem );
				if ( mapped ) {
					map.push( i );
				}
			}
		}
	}

	return newUnmatched;
}

function setMatcher( preFilter, selector, matcher, postFilter, postFinder, postSelector ) {
	if ( postFilter && !postFilter[ expando ] ) {
		postFilter = setMatcher( postFilter );
	}
	if ( postFinder && !postFinder[ expando ] ) {
		postFinder = setMatcher( postFinder, postSelector );
	}
	return markFunction(function( seed, results, context, xml ) {
		var temp, i, elem,
			preMap = [],
			postMap = [],
			preexisting = results.length,

			// Get initial elements from seed or context
			elems = seed || multipleContexts( selector || "*", context.nodeType ? [ context ] : context, [] ),

			// Prefilter to get matcher input, preserving a map for seed-results synchronization
			matcherIn = preFilter && ( seed || !selector ) ?
				condense( elems, preMap, preFilter, context, xml ) :
				elems,

			matcherOut = matcher ?
				// If we have a postFinder, or filtered seed, or non-seed postFilter or preexisting results,
				postFinder || ( seed ? preFilter : preexisting || postFilter ) ?

					// ...intermediate processing is necessary
					[] :

					// ...otherwise use results directly
					results :
				matcherIn;

		// Find primary matches
		if ( matcher ) {
			matcher( matcherIn, matcherOut, context, xml );
		}

		// Apply postFilter
		if ( postFilter ) {
			temp = condense( matcherOut, postMap );
			postFilter( temp, [], context, xml );

			// Un-match failing elements by moving them back to matcherIn
			i = temp.length;
			while ( i-- ) {
				if ( (elem = temp[i]) ) {
					matcherOut[ postMap[i] ] = !(matcherIn[ postMap[i] ] = elem);
				}
			}
		}

		if ( seed ) {
			if ( postFinder || preFilter ) {
				if ( postFinder ) {
					// Get the final matcherOut by condensing this intermediate into postFinder contexts
					temp = [];
					i = matcherOut.length;
					while ( i-- ) {
						if ( (elem = matcherOut[i]) ) {
							// Restore matcherIn since elem is not yet a final match
							temp.push( (matcherIn[i] = elem) );
						}
					}
					postFinder( null, (matcherOut = []), temp, xml );
				}

				// Move matched elements from seed to results to keep them synchronized
				i = matcherOut.length;
				while ( i-- ) {
					if ( (elem = matcherOut[i]) &&
						(temp = postFinder ? indexOf.call( seed, elem ) : preMap[i]) > -1 ) {

						seed[temp] = !(results[temp] = elem);
					}
				}
			}

		// Add elements to results, through postFinder if defined
		} else {
			matcherOut = condense(
				matcherOut === results ?
					matcherOut.splice( preexisting, matcherOut.length ) :
					matcherOut
			);
			if ( postFinder ) {
				postFinder( null, results, matcherOut, xml );
			} else {
				push.apply( results, matcherOut );
			}
		}
	});
}

function matcherFromTokens( tokens ) {
	var checkContext, matcher, j,
		len = tokens.length,
		leadingRelative = Expr.relative[ tokens[0].type ],
		implicitRelative = leadingRelative || Expr.relative[" "],
		i = leadingRelative ? 1 : 0,

		// The foundational matcher ensures that elements are reachable from top-level context(s)
		matchContext = addCombinator( function( elem ) {
			return elem === checkContext;
		}, implicitRelative, true ),
		matchAnyContext = addCombinator( function( elem ) {
			return indexOf.call( checkContext, elem ) > -1;
		}, implicitRelative, true ),
		matchers = [ function( elem, context, xml ) {
			return ( !leadingRelative && ( xml || context !== outermostContext ) ) || (
				(checkContext = context).nodeType ?
					matchContext( elem, context, xml ) :
					matchAnyContext( elem, context, xml ) );
		} ];

	for ( ; i < len; i++ ) {
		if ( (matcher = Expr.relative[ tokens[i].type ]) ) {
			matchers = [ addCombinator(elementMatcher( matchers ), matcher) ];
		} else {
			matcher = Expr.filter[ tokens[i].type ].apply( null, tokens[i].matches );

			// Return special upon seeing a positional matcher
			if ( matcher[ expando ] ) {
				// Find the next relative operator (if any) for proper handling
				j = ++i;
				for ( ; j < len; j++ ) {
					if ( Expr.relative[ tokens[j].type ] ) {
						break;
					}
				}
				return setMatcher(
					i > 1 && elementMatcher( matchers ),
					i > 1 && toSelector( tokens.slice( 0, i - 1 ) ).replace( rtrim, "$1" ),
					matcher,
					i < j && matcherFromTokens( tokens.slice( i, j ) ),
					j < len && matcherFromTokens( (tokens = tokens.slice( j )) ),
					j < len && toSelector( tokens )
				);
			}
			matchers.push( matcher );
		}
	}

	return elementMatcher( matchers );
}

function matcherFromGroupMatchers( elementMatchers, setMatchers ) {
	// A counter to specify which element is currently being matched
	var matcherCachedRuns = 0,
		bySet = setMatchers.length > 0,
		byElement = elementMatchers.length > 0,
		superMatcher = function( seed, context, xml, results, expandContext ) {
			var elem, j, matcher,
				setMatched = [],
				matchedCount = 0,
				i = "0",
				unmatched = seed && [],
				outermost = expandContext != null,
				contextBackup = outermostContext,
				// We must always have either seed elements or context
				elems = seed || byElement && Expr.find["TAG"]( "*", expandContext && context.parentNode || context ),
				// Use integer dirruns iff this is the outermost matcher
				dirrunsUnique = (dirruns += contextBackup == null ? 1 : Math.random() || 0.1);

			if ( outermost ) {
				outermostContext = context !== document && context;
				cachedruns = matcherCachedRuns;
			}

			// Add elements passing elementMatchers directly to results
			// Keep `i` a string if there are no elements so `matchedCount` will be "00" below
			for ( ; (elem = elems[i]) != null; i++ ) {
				if ( byElement && elem ) {
					j = 0;
					while ( (matcher = elementMatchers[j++]) ) {
						if ( matcher( elem, context, xml ) ) {
							results.push( elem );
							break;
						}
					}
					if ( outermost ) {
						dirruns = dirrunsUnique;
						cachedruns = ++matcherCachedRuns;
					}
				}

				// Track unmatched elements for set filters
				if ( bySet ) {
					// They will have gone through all possible matchers
					if ( (elem = !matcher && elem) ) {
						matchedCount--;
					}

					// Lengthen the array for every element, matched or not
					if ( seed ) {
						unmatched.push( elem );
					}
				}
			}

			// Apply set filters to unmatched elements
			matchedCount += i;
			if ( bySet && i !== matchedCount ) {
				j = 0;
				while ( (matcher = setMatchers[j++]) ) {
					matcher( unmatched, setMatched, context, xml );
				}

				if ( seed ) {
					// Reintegrate element matches to eliminate the need for sorting
					if ( matchedCount > 0 ) {
						while ( i-- ) {
							if ( !(unmatched[i] || setMatched[i]) ) {
								setMatched[i] = pop.call( results );
							}
						}
					}

					// Discard index placeholder values to get only actual matches
					setMatched = condense( setMatched );
				}

				// Add matches to results
				push.apply( results, setMatched );

				// Seedless set matches succeeding multiple successful matchers stipulate sorting
				if ( outermost && !seed && setMatched.length > 0 &&
					( matchedCount + setMatchers.length ) > 1 ) {

					Sizzle.uniqueSort( results );
				}
			}

			// Override manipulation of globals by nested matchers
			if ( outermost ) {
				dirruns = dirrunsUnique;
				outermostContext = contextBackup;
			}

			return unmatched;
		};

	return bySet ?
		markFunction( superMatcher ) :
		superMatcher;
}

compile = Sizzle.compile = function( selector, group /* Internal Use Only */ ) {
	var i,
		setMatchers = [],
		elementMatchers = [],
		cached = compilerCache[ selector + " " ];

	if ( !cached ) {
		// Generate a function of recursive functions that can be used to check each element
		if ( !group ) {
			group = tokenize( selector );
		}
		i = group.length;
		while ( i-- ) {
			cached = matcherFromTokens( group[i] );
			if ( cached[ expando ] ) {
				setMatchers.push( cached );
			} else {
				elementMatchers.push( cached );
			}
		}

		// Cache the compiled function
		cached = compilerCache( selector, matcherFromGroupMatchers( elementMatchers, setMatchers ) );
	}
	return cached;
};

function multipleContexts( selector, contexts, results ) {
	var i = 0,
		len = contexts.length;
	for ( ; i < len; i++ ) {
		Sizzle( selector, contexts[i], results );
	}
	return results;
}

function select( selector, context, results, seed ) {
	var i, tokens, token, type, find,
		match = tokenize( selector );

	if ( !seed ) {
		// Try to minimize operations if there is only one group
		if ( match.length === 1 ) {

			// Take a shortcut and set the context if the root selector is an ID
			tokens = match[0] = match[0].slice( 0 );
			if ( tokens.length > 2 && (token = tokens[0]).type === "ID" &&
					context.nodeType === 9 && documentIsHTML &&
					Expr.relative[ tokens[1].type ] ) {

				context = ( Expr.find["ID"]( token.matches[0].replace(runescape, funescape), context ) || [] )[0];
				if ( !context ) {
					return results;
				}

				selector = selector.slice( tokens.shift().value.length );
			}

			// Fetch a seed set for right-to-left matching
			i = matchExpr["needsContext"].test( selector ) ? 0 : tokens.length;
			while ( i-- ) {
				token = tokens[i];

				// Abort if we hit a combinator
				if ( Expr.relative[ (type = token.type) ] ) {
					break;
				}
				if ( (find = Expr.find[ type ]) ) {
					// Search, expanding context for leading sibling combinators
					if ( (seed = find(
						token.matches[0].replace( runescape, funescape ),
						rsibling.test( tokens[0].type ) && context.parentNode || context
					)) ) {

						// If seed is empty or no tokens remain, we can return early
						tokens.splice( i, 1 );
						selector = seed.length && toSelector( tokens );
						if ( !selector ) {
							push.apply( results, seed );
							return results;
						}

						break;
					}
				}
			}
		}
	}

	// Compile and execute a filtering function
	// Provide `match` to avoid retokenization if we modified the selector above
	compile( selector, match )(
		seed,
		context,
		!documentIsHTML,
		results,
		rsibling.test( selector )
	);
	return results;
}

// Deprecated
Expr.pseudos["nth"] = Expr.pseudos["eq"];

// Easy API for creating new setFilters
function setFilters() {}
setFilters.prototype = Expr.filters = Expr.pseudos;
Expr.setFilters = new setFilters();

// One-time assignments

// Sort stability
support.sortStable = expando.split("").sort( sortOrder ).join("") === expando;

// Initialize against the default document
setDocument();

// Support: Chrome<<14
// Always assume duplicates if they aren't passed to the comparison function
[0, 0].sort( sortOrder );
support.detectDuplicates = hasDuplicate;

// Support: IE<8
// Prevent attribute/property "interpolation"
assert(function( div ) {
	div.innerHTML = "<a href='#'></a>";
	if ( div.firstChild.getAttribute("href") !== "#" ) {
		var attrs = "type|href|height|width".split("|"),
			i = attrs.length;
		while ( i-- ) {
			Expr.attrHandle[ attrs[i] ] = interpolationHandler;
		}
	}
});

// Support: IE<9
// Use getAttributeNode to fetch booleans when getAttribute lies
assert(function( div ) {
	if ( div.getAttribute("disabled") != null ) {
		var attrs = booleans.split("|"),
			i = attrs.length;
		while ( i-- ) {
			Expr.attrHandle[ attrs[i] ] = boolHandler;
		}
	}
});

jQuery.find = Sizzle;
jQuery.expr = Sizzle.selectors;
jQuery.expr[":"] = jQuery.expr.pseudos;
jQuery.unique = Sizzle.uniqueSort;
jQuery.text = Sizzle.getText;
jQuery.isXMLDoc = Sizzle.isXML;
jQuery.contains = Sizzle.contains;


})( window );
// String to Object options format cache
var optionsCache = {};

// Convert String-formatted options into Object-formatted ones and store in cache
function createOptions( options ) {
	var object = optionsCache[ options ] = {};
	jQuery.each( options.match( core_rnotwhite ) || [], function( _, flag ) {
		object[ flag ] = true;
	});
	return object;
}

/*
 * Create a callback list using the following parameters:
 *
 *	options: an optional list of space-separated options that will change how
 *			the callback list behaves or a more traditional option object
 *
 * By default a callback list will act like an event callback list and can be
 * "fired" multiple times.
 *
 * Possible options:
 *
 *	once:			will ensure the callback list can only be fired once (like a Deferred)
 *
 *	memory:			will keep track of previous values and will call any callback added
 *					after the list has been fired right away with the latest "memorized"
 *					values (like a Deferred)
 *
 *	unique:			will ensure a callback can only be added once (no duplicate in the list)
 *
 *	stopOnFalse:	interrupt callings when a callback returns false
 *
 */
jQuery.Callbacks = function( options ) {

	// Convert options from String-formatted to Object-formatted if needed
	// (we check in cache first)
	options = typeof options === "string" ?
		( optionsCache[ options ] || createOptions( options ) ) :
		jQuery.extend( {}, options );

	var // Last fire value (for non-forgettable lists)
		memory,
		// Flag to know if list was already fired
		fired,
		// Flag to know if list is currently firing
		firing,
		// First callback to fire (used internally by add and fireWith)
		firingStart,
		// End of the loop when firing
		firingLength,
		// Index of currently firing callback (modified by remove if needed)
		firingIndex,
		// Actual callback list
		list = [],
		// Stack of fire calls for repeatable lists
		stack = !options.once && [],
		// Fire callbacks
		fire = function( data ) {
			memory = options.memory && data;
			fired = true;
			firingIndex = firingStart || 0;
			firingStart = 0;
			firingLength = list.length;
			firing = true;
			for ( ; list && firingIndex < firingLength; firingIndex++ ) {
				if ( list[ firingIndex ].apply( data[ 0 ], data[ 1 ] ) === false && options.stopOnFalse ) {
					memory = false; // To prevent further calls using add
					break;
				}
			}
			firing = false;
			if ( list ) {
				if ( stack ) {
					if ( stack.length ) {
						fire( stack.shift() );
					}
				} else if ( memory ) {
					list = [];
				} else {
					self.disable();
				}
			}
		},
		// Actual Callbacks object
		self = {
			// Add a callback or a collection of callbacks to the list
			add: function() {
				if ( list ) {
					// First, we save the current length
					var start = list.length;
					(function add( args ) {
						jQuery.each( args, function( _, arg ) {
							var type = jQuery.type( arg );
							if ( type === "function" ) {
								if ( !options.unique || !self.has( arg ) ) {
									list.push( arg );
								}
							} else if ( arg && arg.length && type !== "string" ) {
								// Inspect recursively
								add( arg );
							}
						});
					})( arguments );
					// Do we need to add the callbacks to the
					// current firing batch?
					if ( firing ) {
						firingLength = list.length;
					// With memory, if we're not firing then
					// we should call right away
					} else if ( memory ) {
						firingStart = start;
						fire( memory );
					}
				}
				return this;
			},
			// Remove a callback from the list
			remove: function() {
				if ( list ) {
					jQuery.each( arguments, function( _, arg ) {
						var index;
						while( ( index = jQuery.inArray( arg, list, index ) ) > -1 ) {
							list.splice( index, 1 );
							// Handle firing indexes
							if ( firing ) {
								if ( index <= firingLength ) {
									firingLength--;
								}
								if ( index <= firingIndex ) {
									firingIndex--;
								}
							}
						}
					});
				}
				return this;
			},
			// Check if a given callback is in the list.
			// If no argument is given, return whether or not list has callbacks attached.
			has: function( fn ) {
				return fn ? jQuery.inArray( fn, list ) > -1 : !!( list && list.length );
			},
			// Remove all callbacks from the list
			empty: function() {
				list = [];
				firingLength = 0;
				return this;
			},
			// Have the list do nothing anymore
			disable: function() {
				list = stack = memory = undefined;
				return this;
			},
			// Is it disabled?
			disabled: function() {
				return !list;
			},
			// Lock the list in its current state
			lock: function() {
				stack = undefined;
				if ( !memory ) {
					self.disable();
				}
				return this;
			},
			// Is it locked?
			locked: function() {
				return !stack;
			},
			// Call all callbacks with the given context and arguments
			fireWith: function( context, args ) {
				args = args || [];
				args = [ context, args.slice ? args.slice() : args ];
				if ( list && ( !fired || stack ) ) {
					if ( firing ) {
						stack.push( args );
					} else {
						fire( args );
					}
				}
				return this;
			},
			// Call all the callbacks with the given arguments
			fire: function() {
				self.fireWith( this, arguments );
				return this;
			},
			// To know if the callbacks have already been called at least once
			fired: function() {
				return !!fired;
			}
		};

	return self;
};
jQuery.extend({

	Deferred: function( func ) {
		var tuples = [
				// action, add listener, listener list, final state
				[ "resolve", "done", jQuery.Callbacks("once memory"), "resolved" ],
				[ "reject", "fail", jQuery.Callbacks("once memory"), "rejected" ],
				[ "notify", "progress", jQuery.Callbacks("memory") ]
			],
			state = "pending",
			promise = {
				state: function() {
					return state;
				},
				always: function() {
					deferred.done( arguments ).fail( arguments );
					return this;
				},
				then: function( /* fnDone, fnFail, fnProgress */ ) {
					var fns = arguments;
					return jQuery.Deferred(function( newDefer ) {
						jQuery.each( tuples, function( i, tuple ) {
							var action = tuple[ 0 ],
								fn = jQuery.isFunction( fns[ i ] ) && fns[ i ];
							// deferred[ done | fail | progress ] for forwarding actions to newDefer
							deferred[ tuple[1] ](function() {
								var returned = fn && fn.apply( this, arguments );
								if ( returned && jQuery.isFunction( returned.promise ) ) {
									returned.promise()
										.done( newDefer.resolve )
										.fail( newDefer.reject )
										.progress( newDefer.notify );
								} else {
									newDefer[ action + "With" ]( this === promise ? newDefer.promise() : this, fn ? [ returned ] : arguments );
								}
							});
						});
						fns = null;
					}).promise();
				},
				// Get a promise for this deferred
				// If obj is provided, the promise aspect is added to the object
				promise: function( obj ) {
					return obj != null ? jQuery.extend( obj, promise ) : promise;
				}
			},
			deferred = {};

		// Keep pipe for back-compat
		promise.pipe = promise.then;

		// Add list-specific methods
		jQuery.each( tuples, function( i, tuple ) {
			var list = tuple[ 2 ],
				stateString = tuple[ 3 ];

			// promise[ done | fail | progress ] = list.add
			promise[ tuple[1] ] = list.add;

			// Handle state
			if ( stateString ) {
				list.add(function() {
					// state = [ resolved | rejected ]
					state = stateString;

				// [ reject_list | resolve_list ].disable; progress_list.lock
				}, tuples[ i ^ 1 ][ 2 ].disable, tuples[ 2 ][ 2 ].lock );
			}

			// deferred[ resolve | reject | notify ]
			deferred[ tuple[0] ] = function() {
				deferred[ tuple[0] + "With" ]( this === deferred ? promise : this, arguments );
				return this;
			};
			deferred[ tuple[0] + "With" ] = list.fireWith;
		});

		// Make the deferred a promise
		promise.promise( deferred );

		// Call given func if any
		if ( func ) {
			func.call( deferred, deferred );
		}

		// All done!
		return deferred;
	},

	// Deferred helper
	when: function( subordinate /* , ..., subordinateN */ ) {
		var i = 0,
			resolveValues = core_slice.call( arguments ),
			length = resolveValues.length,

			// the count of uncompleted subordinates
			remaining = length !== 1 || ( subordinate && jQuery.isFunction( subordinate.promise ) ) ? length : 0,

			// the master Deferred. If resolveValues consist of only a single Deferred, just use that.
			deferred = remaining === 1 ? subordinate : jQuery.Deferred(),

			// Update function for both resolve and progress values
			updateFunc = function( i, contexts, values ) {
				return function( value ) {
					contexts[ i ] = this;
					values[ i ] = arguments.length > 1 ? core_slice.call( arguments ) : value;
					if( values === progressValues ) {
						deferred.notifyWith( contexts, values );
					} else if ( !( --remaining ) ) {
						deferred.resolveWith( contexts, values );
					}
				};
			},

			progressValues, progressContexts, resolveContexts;

		// add listeners to Deferred subordinates; treat others as resolved
		if ( length > 1 ) {
			progressValues = new Array( length );
			progressContexts = new Array( length );
			resolveContexts = new Array( length );
			for ( ; i < length; i++ ) {
				if ( resolveValues[ i ] && jQuery.isFunction( resolveValues[ i ].promise ) ) {
					resolveValues[ i ].promise()
						.done( updateFunc( i, resolveContexts, resolveValues ) )
						.fail( deferred.reject )
						.progress( updateFunc( i, progressContexts, progressValues ) );
				} else {
					--remaining;
				}
			}
		}

		// if we're not waiting on anything, resolve the master
		if ( !remaining ) {
			deferred.resolveWith( resolveContexts, resolveValues );
		}

		return deferred.promise();
	}
});
jQuery.support = (function( support ) {
	var input = document.createElement("input"),
		fragment = document.createDocumentFragment(),
		div = document.createElement("div"),
		select = document.createElement("select"),
		opt = select.appendChild( document.createElement("option") );

	// Finish early in limited environments
	if ( !input.type ) {
		return support;
	}

	input.type = "checkbox";

	// Support: Safari 5.1, iOS 5.1, Android 4.x, Android 2.3
	// Check the default checkbox/radio value ("" on old WebKit; "on" elsewhere)
	support.checkOn = input.value !== "";

	// Must access the parent to make an option select properly
	// Support: IE9, IE10
	support.optSelected = opt.selected;

	// Will be defined later
	support.reliableMarginRight = true;
	support.boxSizingReliable = true;
	support.pixelPosition = false;

	// Make sure checked status is properly cloned
	// Support: IE9, IE10
	input.checked = true;
	support.noCloneChecked = input.cloneNode( true ).checked;

	// Make sure that the options inside disabled selects aren't marked as disabled
	// (WebKit marks them as disabled)
	select.disabled = true;
	support.optDisabled = !opt.disabled;

	// Check if an input maintains its value after becoming a radio
	// Support: IE9, IE10
	input = document.createElement("input");
	input.value = "t";
	input.type = "radio";
	support.radioValue = input.value === "t";

	// #11217 - WebKit loses check when the name is after the checked attribute
	input.setAttribute( "checked", "t" );
	input.setAttribute( "name", "t" );

	fragment.appendChild( input );

	// Support: Safari 5.1, Android 4.x, Android 2.3
	// old WebKit doesn't clone checked state correctly in fragments
	support.checkClone = fragment.cloneNode( true ).cloneNode( true ).lastChild.checked;

	// Support: Firefox, Chrome, Safari
	// Beware of CSP restrictions (https://developer.mozilla.org/en/Security/CSP)
	support.focusinBubbles = "onfocusin" in window;

	div.style.backgroundClip = "content-box";
	div.cloneNode( true ).style.backgroundClip = "";
	support.clearCloneStyle = div.style.backgroundClip === "content-box";

	// Run tests that need a body at doc ready
	jQuery(function() {
		var container, marginDiv,
			// Support: Firefox, Android 2.3 (Prefixed box-sizing versions).
			divReset = "padding:0;margin:0;border:0;display:block;-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box",
			body = document.getElementsByTagName("body")[ 0 ];

		if ( !body ) {
			// Return for frameset docs that don't have a body
			return;
		}

		container = document.createElement("div");
		container.style.cssText = "border:0;width:0;height:0;position:absolute;top:0;left:-9999px;margin-top:1px";

		// Check box-sizing and margin behavior.
		body.appendChild( container ).appendChild( div );
		div.innerHTML = "";
		// Support: Firefox, Android 2.3 (Prefixed box-sizing versions).
		div.style.cssText = "-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;padding:1px;border:1px;display:block;width:4px;margin-top:1%;position:absolute;top:1%";

		// Workaround failing boxSizing test due to offsetWidth returning wrong value
		// with some non-1 values of body zoom, ticket #13543
		jQuery.swap( body, body.style.zoom != null ? { zoom: 1 } : {}, function() {
			support.boxSizing = div.offsetWidth === 4;
		});

		// Use window.getComputedStyle because jsdom on node.js will break without it.
		if ( window.getComputedStyle ) {
			support.pixelPosition = ( window.getComputedStyle( div, null ) || {} ).top !== "1%";
			support.boxSizingReliable = ( window.getComputedStyle( div, null ) || { width: "4px" } ).width === "4px";

			// Support: Android 2.3
			// Check if div with explicit width and no margin-right incorrectly
			// gets computed margin-right based on width of container. (#3333)
			// WebKit Bug 13343 - getComputedStyle returns wrong value for margin-right
			marginDiv = div.appendChild( document.createElement("div") );
			marginDiv.style.cssText = div.style.cssText = divReset;
			marginDiv.style.marginRight = marginDiv.style.width = "0";
			div.style.width = "1px";

			support.reliableMarginRight =
				!parseFloat( ( window.getComputedStyle( marginDiv, null ) || {} ).marginRight );
		}

		body.removeChild( container );
	});

	return support;
})( {} );

/*
	Implementation Summary

	1. Enforce API surface and semantic compatibility with 1.9.x branch
	2. Improve the module's maintainability by reducing the storage
		paths to a single mechanism.
	3. Use the same single mechanism to support "private" and "user" data.
	4. _Never_ expose "private" data to user code (TODO: Drop _data, _removeData)
	5. Avoid exposing implementation details on user objects (eg. expando properties)
	6. Provide a clear path for implementation upgrade to WeakMap in 2014
*/
var data_user, data_priv,
	rbrace = /(?:\{[\s\S]*\}|\[[\s\S]*\])$/,
	rmultiDash = /([A-Z])/g;

function Data() {
	// Support: Android < 4,
	// Old WebKit does not have Object.preventExtensions/freeze method,
	// return new empty object instead with no [[set]] accessor
	Object.defineProperty( this.cache = {}, 0, {
		get: function() {
			return {};
		}
	});

	this.expando = jQuery.expando + Math.random();
}

Data.uid = 1;

Data.accepts = function( owner ) {
	// Accepts only:
	//  - Node
	//    - Node.ELEMENT_NODE
	//    - Node.DOCUMENT_NODE
	//  - Object
	//    - Any
	return owner.nodeType ?
		owner.nodeType === 1 || owner.nodeType === 9 : true;
};

Data.prototype = {
	key: function( owner ) {
		// We can accept data for non-element nodes in modern browsers,
		// but we should not, see #8335.
		// Always return the key for a frozen object.
		if ( !Data.accepts( owner ) ) {
			return 0;
		}

		var descriptor = {},
			// Check if the owner object already has a cache key
			unlock = owner[ this.expando ];

		// If not, create one
		if ( !unlock ) {
			unlock = Data.uid++;

			// Secure it in a non-enumerable, non-writable property
			try {
				descriptor[ this.expando ] = { value: unlock };
				Object.defineProperties( owner, descriptor );

			// Support: Android < 4
			// Fallback to a less secure definition
			} catch ( e ) {
				descriptor[ this.expando ] = unlock;
				jQuery.extend( owner, descriptor );
			}
		}

		// Ensure the cache object
		if ( !this.cache[ unlock ] ) {
			this.cache[ unlock ] = {};
		}

		return unlock;
	},
	set: function( owner, data, value ) {
		var prop,
			// There may be an unlock assigned to this node,
			// if there is no entry for this "owner", create one inline
			// and set the unlock as though an owner entry had always existed
			unlock = this.key( owner ),
			cache = this.cache[ unlock ];

		// Handle: [ owner, key, value ] args
		if ( typeof data === "string" ) {
			cache[ data ] = value;

		// Handle: [ owner, { properties } ] args
		} else {
			// Support an expectation from the old data system where plain
			// objects used to initialize would be set to the cache by
			// reference, instead of having properties and values copied.
			// Note, this will kill the connection between
			// "this.cache[ unlock ]" and "cache"
			if ( jQuery.isEmptyObject( cache ) ) {
				this.cache[ unlock ] = data;
			// Otherwise, copy the properties one-by-one to the cache object
			} else {
				for ( prop in data ) {
					cache[ prop ] = data[ prop ];
				}
			}
		}
	},
	get: function( owner, key ) {
		// Either a valid cache is found, or will be created.
		// New caches will be created and the unlock returned,
		// allowing direct access to the newly created
		// empty data object. A valid owner object must be provided.
		var cache = this.cache[ this.key( owner ) ];

		return key === undefined ?
			cache : cache[ key ];
	},
	access: function( owner, key, value ) {
		// In cases where either:
		//
		//   1. No key was specified
		//   2. A string key was specified, but no value provided
		//
		// Take the "read" path and allow the get method to determine
		// which value to return, respectively either:
		//
		//   1. The entire cache object
		//   2. The data stored at the key
		//
		if ( key === undefined ||
				((key && typeof key === "string") && value === undefined) ) {
			return this.get( owner, key );
		}

		// [*]When the key is not a string, or both a key and value
		// are specified, set or extend (existing objects) with either:
		//
		//   1. An object of properties
		//   2. A key and value
		//
		this.set( owner, key, value );

		// Since the "set" path can have two possible entry points
		// return the expected data based on which path was taken[*]
		return value !== undefined ? value : key;
	},
	remove: function( owner, key ) {
		var i, name,
			unlock = this.key( owner ),
			cache = this.cache[ unlock ];

		if ( key === undefined ) {
			this.cache[ unlock ] = {};

		} else {
			// Support array or space separated string of keys
			if ( jQuery.isArray( key ) ) {
				// If "name" is an array of keys...
				// When data is initially created, via ("key", "val") signature,
				// keys will be converted to camelCase.
				// Since there is no way to tell _how_ a key was added, remove
				// both plain key and camelCase key. #12786
				// This will only penalize the array argument path.
				name = key.concat( key.map( jQuery.camelCase ) );
			} else {
				// Try the string as a key before any manipulation
				if ( key in cache ) {
					name = [ key ];
				} else {
					// If a key with the spaces exists, use it.
					// Otherwise, create an array by matching non-whitespace
					name = jQuery.camelCase( key );
					name = name in cache ?
						[ name ] : ( name.match( core_rnotwhite ) || [] );
				}
			}

			i = name.length;
			while ( i-- ) {
				delete cache[ name[ i ] ];
			}
		}
	},
	hasData: function( owner ) {
		return !jQuery.isEmptyObject(
			this.cache[ owner[ this.expando ] ] || {}
		);
	},
	discard: function( owner ) {
		delete this.cache[ this.key( owner ) ];
	}
};

// These may be used throughout the jQuery core codebase
data_user = new Data();
data_priv = new Data();


jQuery.extend({
	acceptData: Data.accepts,

	hasData: function( elem ) {
		return data_user.hasData( elem ) || data_priv.hasData( elem );
	},

	data: function( elem, name, data ) {
		return data_user.access( elem, name, data );
	},

	removeData: function( elem, name ) {
		data_user.remove( elem, name );
	},

	// TODO: Now that all calls to _data and _removeData have been replaced
	// with direct calls to data_priv methods, these can be deprecated.
	_data: function( elem, name, data ) {
		return data_priv.access( elem, name, data );
	},

	_removeData: function( elem, name ) {
		data_priv.remove( elem, name );
	}
});

jQuery.fn.extend({
	data: function( key, value ) {
		var attrs, name,
			elem = this[ 0 ],
			i = 0,
			data = null;

		// Gets all values
		if ( key === undefined ) {
			if ( this.length ) {
				data = data_user.get( elem );

				if ( elem.nodeType === 1 && !data_priv.get( elem, "hasDataAttrs" ) ) {
					attrs = elem.attributes;
					for ( ; i < attrs.length; i++ ) {
						name = attrs[ i ].name;

						if ( name.indexOf( "data-" ) === 0 ) {
							name = jQuery.camelCase( name.substring(5) );
							dataAttr( elem, name, data[ name ] );
						}
					}
					data_priv.set( elem, "hasDataAttrs", true );
				}
			}

			return data;
		}

		// Sets multiple values
		if ( typeof key === "object" ) {
			return this.each(function() {
				data_user.set( this, key );
			});
		}

		return jQuery.access( this, function( value ) {
			var data,
				camelKey = jQuery.camelCase( key );

			// The calling jQuery object (element matches) is not empty
			// (and therefore has an element appears at this[ 0 ]) and the
			// `value` parameter was not undefined. An empty jQuery object
			// will result in `undefined` for elem = this[ 0 ] which will
			// throw an exception if an attempt to read a data cache is made.
			if ( elem && value === undefined ) {
				// Attempt to get data from the cache
				// with the key as-is
				data = data_user.get( elem, key );
				if ( data !== undefined ) {
					return data;
				}

				// Attempt to get data from the cache
				// with the key camelized
				data = data_user.get( elem, camelKey );
				if ( data !== undefined ) {
					return data;
				}

				// Attempt to "discover" the data in
				// HTML5 custom data-* attrs
				data = dataAttr( elem, camelKey, undefined );
				if ( data !== undefined ) {
					return data;
				}

				// We tried really hard, but the data doesn't exist.
				return;
			}

			// Set the data...
			this.each(function() {
				// First, attempt to store a copy or reference of any
				// data that might've been store with a camelCased key.
				var data = data_user.get( this, camelKey );

				// For HTML5 data-* attribute interop, we have to
				// store property names with dashes in a camelCase form.
				// This might not apply to all properties...*
				data_user.set( this, camelKey, value );

				// *... In the case of properties that might _actually_
				// have dashes, we need to also store a copy of that
				// unchanged property.
				if ( key.indexOf("-") !== -1 && data !== undefined ) {
					data_user.set( this, key, value );
				}
			});
		}, null, value, arguments.length > 1, null, true );
	},

	removeData: function( key ) {
		return this.each(function() {
			data_user.remove( this, key );
		});
	}
});

function dataAttr( elem, key, data ) {
	var name;

	// If nothing was found internally, try to fetch any
	// data from the HTML5 data-* attribute
	if ( data === undefined && elem.nodeType === 1 ) {
		name = "data-" + key.replace( rmultiDash, "-$1" ).toLowerCase();
		data = elem.getAttribute( name );

		if ( typeof data === "string" ) {
			try {
				data = data === "true" ? true :
					data === "false" ? false :
					data === "null" ? null :
					// Only convert to a number if it doesn't change the string
					+data + "" === data ? +data :
					rbrace.test( data ) ? JSON.parse( data ) :
					data;
			} catch( e ) {}

			// Make sure we set the data so it isn't changed later
			data_user.set( elem, key, data );
		} else {
			data = undefined;
		}
	}
	return data;
}
jQuery.extend({
	queue: function( elem, type, data ) {
		var queue;

		if ( elem ) {
			type = ( type || "fx" ) + "queue";
			queue = data_priv.get( elem, type );

			// Speed up dequeue by getting out quickly if this is just a lookup
			if ( data ) {
				if ( !queue || jQuery.isArray( data ) ) {
					queue = data_priv.access( elem, type, jQuery.makeArray(data) );
				} else {
					queue.push( data );
				}
			}
			return queue || [];
		}
	},

	dequeue: function( elem, type ) {
		type = type || "fx";

		var queue = jQuery.queue( elem, type ),
			startLength = queue.length,
			fn = queue.shift(),
			hooks = jQuery._queueHooks( elem, type ),
			next = function() {
				jQuery.dequeue( elem, type );
			};

		// If the fx queue is dequeued, always remove the progress sentinel
		if ( fn === "inprogress" ) {
			fn = queue.shift();
			startLength--;
		}

		hooks.cur = fn;
		if ( fn ) {

			// Add a progress sentinel to prevent the fx queue from being
			// automatically dequeued
			if ( type === "fx" ) {
				queue.unshift( "inprogress" );
			}

			// clear up the last queue stop function
			delete hooks.stop;
			fn.call( elem, next, hooks );
		}

		if ( !startLength && hooks ) {
			hooks.empty.fire();
		}
	},

	// not intended for public consumption - generates a queueHooks object, or returns the current one
	_queueHooks: function( elem, type ) {
		var key = type + "queueHooks";
		return data_priv.get( elem, key ) || data_priv.access( elem, key, {
			empty: jQuery.Callbacks("once memory").add(function() {
				data_priv.remove( elem, [ type + "queue", key ] );
			})
		});
	}
});

jQuery.fn.extend({
	queue: function( type, data ) {
		var setter = 2;

		if ( typeof type !== "string" ) {
			data = type;
			type = "fx";
			setter--;
		}

		if ( arguments.length < setter ) {
			return jQuery.queue( this[0], type );
		}

		return data === undefined ?
			this :
			this.each(function() {
				var queue = jQuery.queue( this, type, data );

				// ensure a hooks for this queue
				jQuery._queueHooks( this, type );

				if ( type === "fx" && queue[0] !== "inprogress" ) {
					jQuery.dequeue( this, type );
				}
			});
	},
	dequeue: function( type ) {
		return this.each(function() {
			jQuery.dequeue( this, type );
		});
	},
	// Based off of the plugin by Clint Helfers, with permission.
	// http://blindsignals.com/index.php/2009/07/jquery-delay/
	delay: function( time, type ) {
		time = jQuery.fx ? jQuery.fx.speeds[ time ] || time : time;
		type = type || "fx";

		return this.queue( type, function( next, hooks ) {
			var timeout = setTimeout( next, time );
			hooks.stop = function() {
				clearTimeout( timeout );
			};
		});
	},
	clearQueue: function( type ) {
		return this.queue( type || "fx", [] );
	},
	// Get a promise resolved when queues of a certain type
	// are emptied (fx is the type by default)
	promise: function( type, obj ) {
		var tmp,
			count = 1,
			defer = jQuery.Deferred(),
			elements = this,
			i = this.length,
			resolve = function() {
				if ( !( --count ) ) {
					defer.resolveWith( elements, [ elements ] );
				}
			};

		if ( typeof type !== "string" ) {
			obj = type;
			type = undefined;
		}
		type = type || "fx";

		while( i-- ) {
			tmp = data_priv.get( elements[ i ], type + "queueHooks" );
			if ( tmp && tmp.empty ) {
				count++;
				tmp.empty.add( resolve );
			}
		}
		resolve();
		return defer.promise( obj );
	}
});
var nodeHook, boolHook,
	rclass = /[\t\r\n]/g,
	rreturn = /\r/g,
	rfocusable = /^(?:input|select|textarea|button)$/i;

jQuery.fn.extend({
	attr: function( name, value ) {
		return jQuery.access( this, jQuery.attr, name, value, arguments.length > 1 );
	},

	removeAttr: function( name ) {
		return this.each(function() {
			jQuery.removeAttr( this, name );
		});
	},

	prop: function( name, value ) {
		return jQuery.access( this, jQuery.prop, name, value, arguments.length > 1 );
	},

	removeProp: function( name ) {
		return this.each(function() {
			delete this[ jQuery.propFix[ name ] || name ];
		});
	},

	addClass: function( value ) {
		var classes, elem, cur, clazz, j,
			i = 0,
			len = this.length,
			proceed = typeof value === "string" && value;

		if ( jQuery.isFunction( value ) ) {
			return this.each(function( j ) {
				jQuery( this ).addClass( value.call( this, j, this.className ) );
			});
		}

		if ( proceed ) {
			// The disjunction here is for better compressibility (see removeClass)
			classes = ( value || "" ).match( core_rnotwhite ) || [];

			for ( ; i < len; i++ ) {
				elem = this[ i ];
				cur = elem.nodeType === 1 && ( elem.className ?
					( " " + elem.className + " " ).replace( rclass, " " ) :
					" "
				);

				if ( cur ) {
					j = 0;
					while ( (clazz = classes[j++]) ) {
						if ( cur.indexOf( " " + clazz + " " ) < 0 ) {
							cur += clazz + " ";
						}
					}
					elem.className = jQuery.trim( cur );

				}
			}
		}

		return this;
	},

	removeClass: function( value ) {
		var classes, elem, cur, clazz, j,
			i = 0,
			len = this.length,
			proceed = arguments.length === 0 || typeof value === "string" && value;

		if ( jQuery.isFunction( value ) ) {
			return this.each(function( j ) {
				jQuery( this ).removeClass( value.call( this, j, this.className ) );
			});
		}
		if ( proceed ) {
			classes = ( value || "" ).match( core_rnotwhite ) || [];

			for ( ; i < len; i++ ) {
				elem = this[ i ];
				// This expression is here for better compressibility (see addClass)
				cur = elem.nodeType === 1 && ( elem.className ?
					( " " + elem.className + " " ).replace( rclass, " " ) :
					""
				);

				if ( cur ) {
					j = 0;
					while ( (clazz = classes[j++]) ) {
						// Remove *all* instances
						while ( cur.indexOf( " " + clazz + " " ) >= 0 ) {
							cur = cur.replace( " " + clazz + " ", " " );
						}
					}
					elem.className = value ? jQuery.trim( cur ) : "";
				}
			}
		}

		return this;
	},

	toggleClass: function( value, stateVal ) {
		var type = typeof value,
			isBool = typeof stateVal === "boolean";

		if ( jQuery.isFunction( value ) ) {
			return this.each(function( i ) {
				jQuery( this ).toggleClass( value.call(this, i, this.className, stateVal), stateVal );
			});
		}

		return this.each(function() {
			if ( type === "string" ) {
				// toggle individual class names
				var className,
					i = 0,
					self = jQuery( this ),
					state = stateVal,
					classNames = value.match( core_rnotwhite ) || [];

				while ( (className = classNames[ i++ ]) ) {
					// check each className given, space separated list
					state = isBool ? state : !self.hasClass( className );
					self[ state ? "addClass" : "removeClass" ]( className );
				}

			// Toggle whole class name
			} else if ( type === core_strundefined || type === "boolean" ) {
				if ( this.className ) {
					// store className if set
					data_priv.set( this, "__className__", this.className );
				}

				// If the element has a class name or if we're passed "false",
				// then remove the whole classname (if there was one, the above saved it).
				// Otherwise bring back whatever was previously saved (if anything),
				// falling back to the empty string if nothing was stored.
				this.className = this.className || value === false ? "" : data_priv.get( this, "__className__" ) || "";
			}
		});
	},

	hasClass: function( selector ) {
		var className = " " + selector + " ",
			i = 0,
			l = this.length;
		for ( ; i < l; i++ ) {
			if ( this[i].nodeType === 1 && (" " + this[i].className + " ").replace(rclass, " ").indexOf( className ) >= 0 ) {
				return true;
			}
		}

		return false;
	},

	val: function( value ) {
		var hooks, ret, isFunction,
			elem = this[0];

		if ( !arguments.length ) {
			if ( elem ) {
				hooks = jQuery.valHooks[ elem.type ] || jQuery.valHooks[ elem.nodeName.toLowerCase() ];

				if ( hooks && "get" in hooks && (ret = hooks.get( elem, "value" )) !== undefined ) {
					return ret;
				}

				ret = elem.value;

				return typeof ret === "string" ?
					// handle most common string cases
					ret.replace(rreturn, "") :
					// handle cases where value is null/undef or number
					ret == null ? "" : ret;
			}

			return;
		}

		isFunction = jQuery.isFunction( value );

		return this.each(function( i ) {
			var val,
				self = jQuery(this);

			if ( this.nodeType !== 1 ) {
				return;
			}

			if ( isFunction ) {
				val = value.call( this, i, self.val() );
			} else {
				val = value;
			}

			// Treat null/undefined as ""; convert numbers to string
			if ( val == null ) {
				val = "";
			} else if ( typeof val === "number" ) {
				val += "";
			} else if ( jQuery.isArray( val ) ) {
				val = jQuery.map(val, function ( value ) {
					return value == null ? "" : value + "";
				});
			}

			hooks = jQuery.valHooks[ this.type ] || jQuery.valHooks[ this.nodeName.toLowerCase() ];

			// If set returns undefined, fall back to normal setting
			if ( !hooks || !("set" in hooks) || hooks.set( this, val, "value" ) === undefined ) {
				this.value = val;
			}
		});
	}
});

jQuery.extend({
	valHooks: {
		option: {
			get: function( elem ) {
				// attributes.value is undefined in Blackberry 4.7 but
				// uses .value. See #6932
				var val = elem.attributes.value;
				return !val || val.specified ? elem.value : elem.text;
			}
		},
		select: {
			get: function( elem ) {
				var value, option,
					options = elem.options,
					index = elem.selectedIndex,
					one = elem.type === "select-one" || index < 0,
					values = one ? null : [],
					max = one ? index + 1 : options.length,
					i = index < 0 ?
						max :
						one ? index : 0;

				// Loop through all the selected options
				for ( ; i < max; i++ ) {
					option = options[ i ];

					// IE6-9 doesn't update selected after form reset (#2551)
					if ( ( option.selected || i === index ) &&
							// Don't return options that are disabled or in a disabled optgroup
							( jQuery.support.optDisabled ? !option.disabled : option.getAttribute("disabled") === null ) &&
							( !option.parentNode.disabled || !jQuery.nodeName( option.parentNode, "optgroup" ) ) ) {

						// Get the specific value for the option
						value = jQuery( option ).val();

						// We don't need an array for one selects
						if ( one ) {
							return value;
						}

						// Multi-Selects return an array
						values.push( value );
					}
				}

				return values;
			},

			set: function( elem, value ) {
				var optionSet, option,
					options = elem.options,
					values = jQuery.makeArray( value ),
					i = options.length;

				while ( i-- ) {
					option = options[ i ];
					if ( (option.selected = jQuery.inArray( jQuery(option).val(), values ) >= 0) ) {
						optionSet = true;
					}
				}

				// force browsers to behave consistently when non-matching value is set
				if ( !optionSet ) {
					elem.selectedIndex = -1;
				}
				return values;
			}
		}
	},

	attr: function( elem, name, value ) {
		var hooks, ret,
			nType = elem.nodeType;

		// don't get/set attributes on text, comment and attribute nodes
		if ( !elem || nType === 3 || nType === 8 || nType === 2 ) {
			return;
		}

		// Fallback to prop when attributes are not supported
		if ( typeof elem.getAttribute === core_strundefined ) {
			return jQuery.prop( elem, name, value );
		}

		// All attributes are lowercase
		// Grab necessary hook if one is defined
		if ( nType !== 1 || !jQuery.isXMLDoc( elem ) ) {
			name = name.toLowerCase();
			hooks = jQuery.attrHooks[ name ] ||
				( jQuery.expr.match.boolean.test( name ) ? boolHook : nodeHook );
		}

		if ( value !== undefined ) {

			if ( value === null ) {
				jQuery.removeAttr( elem, name );

			} else if ( hooks && "set" in hooks && (ret = hooks.set( elem, value, name )) !== undefined ) {
				return ret;

			} else {
				elem.setAttribute( name, value + "" );
				return value;
			}

		} else if ( hooks && "get" in hooks && (ret = hooks.get( elem, name )) !== null ) {
			return ret;

		} else {
			ret = jQuery.find.attr( elem, name );

			// Non-existent attributes return null, we normalize to undefined
			return ret == null ?
				undefined :
				ret;
		}
	},

	removeAttr: function( elem, value ) {
		var name, propName,
			i = 0,
			attrNames = value && value.match( core_rnotwhite );

		if ( attrNames && elem.nodeType === 1 ) {
			while ( (name = attrNames[i++]) ) {
				propName = jQuery.propFix[ name ] || name;

				// Boolean attributes get special treatment (#10870)
				if ( jQuery.expr.match.boolean.test( name ) ) {
					// Set corresponding property to false
					elem[ propName ] = false;
				}

				elem.removeAttribute( name );
			}
		}
	},

	attrHooks: {
		type: {
			set: function( elem, value ) {
				if ( !jQuery.support.radioValue && value === "radio" && jQuery.nodeName(elem, "input") ) {
					// Setting the type on a radio button after the value resets the value in IE6-9
					// Reset value to default in case type is set after value during creation
					var val = elem.value;
					elem.setAttribute( "type", value );
					if ( val ) {
						elem.value = val;
					}
					return value;
				}
			}
		}
	},

	propFix: {
		"for": "htmlFor",
		"class": "className"
	},

	prop: function( elem, name, value ) {
		var ret, hooks, notxml,
			nType = elem.nodeType;

		// don't get/set properties on text, comment and attribute nodes
		if ( !elem || nType === 3 || nType === 8 || nType === 2 ) {
			return;
		}

		notxml = nType !== 1 || !jQuery.isXMLDoc( elem );

		if ( notxml ) {
			// Fix name and attach hooks
			name = jQuery.propFix[ name ] || name;
			hooks = jQuery.propHooks[ name ];
		}

		if ( value !== undefined ) {
			return hooks && "set" in hooks && (ret = hooks.set( elem, value, name )) !== undefined ?
				ret :
				( elem[ name ] = value );

		} else {
			return hooks && "get" in hooks && (ret = hooks.get( elem, name )) !== null ?
				ret :
				elem[ name ];
		}
	},

	propHooks: {
		tabIndex: {
			get: function( elem ) {
				return elem.hasAttribute( "tabindex" ) || rfocusable.test( elem.nodeName ) || elem.href ?
					elem.tabIndex :
					-1;
			}
		}
	}
});

// Hooks for boolean attributes
boolHook = {
	set: function( elem, value, name ) {
		if ( value === false ) {
			// Remove boolean attributes when set to false
			jQuery.removeAttr( elem, name );
		} else {
			elem.setAttribute( name, name );
		}
		return name;
	}
};
jQuery.each( jQuery.expr.match.boolean.source.match( /\w+/g ), function( i, name ) {
	var getter = jQuery.expr.attrHandle[ name ] || jQuery.find.attr;

	jQuery.expr.attrHandle[ name ] = function( elem, name, isXML ) {
		var fn = jQuery.expr.attrHandle[ name ],
			ret = isXML ?
				undefined :
				/* jshint eqeqeq: false */
				// Temporarily disable this handler to check existence
				(jQuery.expr.attrHandle[ name ] = undefined) !=
					getter( elem, name, isXML ) ?

					name.toLowerCase() :
					null;

		// Restore handler
		jQuery.expr.attrHandle[ name ] = fn;

		return ret;
	};
});

// Support: IE9+
// Selectedness for an option in an optgroup can be inaccurate
if ( !jQuery.support.optSelected ) {
	jQuery.propHooks.selected = {
		get: function( elem ) {
			var parent = elem.parentNode;
			if ( parent && parent.parentNode ) {
				parent.parentNode.selectedIndex;
			}
			return null;
		}
	};
}

jQuery.each([
	"tabIndex",
	"readOnly",
	"maxLength",
	"cellSpacing",
	"cellPadding",
	"rowSpan",
	"colSpan",
	"useMap",
	"frameBorder",
	"contentEditable"
], function() {
	jQuery.propFix[ this.toLowerCase() ] = this;
});

// Radios and checkboxes getter/setter
jQuery.each([ "radio", "checkbox" ], function() {
	jQuery.valHooks[ this ] = {
		set: function( elem, value ) {
			if ( jQuery.isArray( value ) ) {
				return ( elem.checked = jQuery.inArray( jQuery(elem).val(), value ) >= 0 );
			}
		}
	};
	if ( !jQuery.support.checkOn ) {
		jQuery.valHooks[ this ].get = function( elem ) {
			// Support: Webkit
			// "" is returned instead of "on" if a value isn't specified
			return elem.getAttribute("value") === null ? "on" : elem.value;
		};
	}
});
var rkeyEvent = /^key/,
	rmouseEvent = /^(?:mouse|contextmenu)|click/,
	rfocusMorph = /^(?:focusinfocus|focusoutblur)$/,
	rtypenamespace = /^([^.]*)(?:\.(.+)|)$/;

function returnTrue() {
	return true;
}

function returnFalse() {
	return false;
}

function safeActiveElement() {
	try {
		return document.activeElement;
	} catch ( err ) { }
}

/*
 * Helper functions for managing events -- not part of the public interface.
 * Props to Dean Edwards' addEvent library for many of the ideas.
 */
jQuery.event = {

	global: {},

	add: function( elem, types, handler, data, selector ) {

		var handleObjIn, eventHandle, tmp,
			events, t, handleObj,
			special, handlers, type, namespaces, origType,
			elemData = data_priv.get( elem );

		// Don't attach events to noData or text/comment nodes (but allow plain objects)
		if ( !elemData ) {
			return;
		}

		// Caller can pass in an object of custom data in lieu of the handler
		if ( handler.handler ) {
			handleObjIn = handler;
			handler = handleObjIn.handler;
			selector = handleObjIn.selector;
		}

		// Make sure that the handler has a unique ID, used to find/remove it later
		if ( !handler.guid ) {
			handler.guid = jQuery.guid++;
		}

		// Init the element's event structure and main handler, if this is the first
		if ( !(events = elemData.events) ) {
			events = elemData.events = {};
		}
		if ( !(eventHandle = elemData.handle) ) {
			eventHandle = elemData.handle = function( e ) {
				// Discard the second event of a jQuery.event.trigger() and
				// when an event is called after a page has unloaded
				return typeof jQuery !== core_strundefined && (!e || jQuery.event.triggered !== e.type) ?
					jQuery.event.dispatch.apply( eventHandle.elem, arguments ) :
					undefined;
			};
			// Add elem as a property of the handle fn to prevent a memory leak with IE non-native events
			eventHandle.elem = elem;
		}

		// Handle multiple events separated by a space
		types = ( types || "" ).match( core_rnotwhite ) || [""];
		t = types.length;
		while ( t-- ) {
			tmp = rtypenamespace.exec( types[t] ) || [];
			type = origType = tmp[1];
			namespaces = ( tmp[2] || "" ).split( "." ).sort();

			// There *must* be a type, no attaching namespace-only handlers
			if ( !type ) {
				continue;
			}

			// If event changes its type, use the special event handlers for the changed type
			special = jQuery.event.special[ type ] || {};

			// If selector defined, determine special event api type, otherwise given type
			type = ( selector ? special.delegateType : special.bindType ) || type;

			// Update special based on newly reset type
			special = jQuery.event.special[ type ] || {};

			// handleObj is passed to all event handlers
			handleObj = jQuery.extend({
				type: type,
				origType: origType,
				data: data,
				handler: handler,
				guid: handler.guid,
				selector: selector,
				needsContext: selector && jQuery.expr.match.needsContext.test( selector ),
				namespace: namespaces.join(".")
			}, handleObjIn );

			// Init the event handler queue if we're the first
			if ( !(handlers = events[ type ]) ) {
				handlers = events[ type ] = [];
				handlers.delegateCount = 0;

				// Only use addEventListener if the special events handler returns false
				if ( !special.setup || special.setup.call( elem, data, namespaces, eventHandle ) === false ) {
					if ( elem.addEventListener ) {
						elem.addEventListener( type, eventHandle, false );
					}
				}
			}

			if ( special.add ) {
				special.add.call( elem, handleObj );

				if ( !handleObj.handler.guid ) {
					handleObj.handler.guid = handler.guid;
				}
			}

			// Add to the element's handler list, delegates in front
			if ( selector ) {
				handlers.splice( handlers.delegateCount++, 0, handleObj );
			} else {
				handlers.push( handleObj );
			}

			// Keep track of which events have ever been used, for event optimization
			jQuery.event.global[ type ] = true;
		}

		// Nullify elem to prevent memory leaks in IE
		elem = null;
	},

	// Detach an event or set of events from an element
	remove: function( elem, types, handler, selector, mappedTypes ) {

		var j, origCount, tmp,
			events, t, handleObj,
			special, handlers, type, namespaces, origType,
			elemData = data_priv.hasData( elem ) && data_priv.get( elem );

		if ( !elemData || !(events = elemData.events) ) {
			return;
		}

		// Once for each type.namespace in types; type may be omitted
		types = ( types || "" ).match( core_rnotwhite ) || [""];
		t = types.length;
		while ( t-- ) {
			tmp = rtypenamespace.exec( types[t] ) || [];
			type = origType = tmp[1];
			namespaces = ( tmp[2] || "" ).split( "." ).sort();

			// Unbind all events (on this namespace, if provided) for the element
			if ( !type ) {
				for ( type in events ) {
					jQuery.event.remove( elem, type + types[ t ], handler, selector, true );
				}
				continue;
			}

			special = jQuery.event.special[ type ] || {};
			type = ( selector ? special.delegateType : special.bindType ) || type;
			handlers = events[ type ] || [];
			tmp = tmp[2] && new RegExp( "(^|\\.)" + namespaces.join("\\.(?:.*\\.|)") + "(\\.|$)" );

			// Remove matching events
			origCount = j = handlers.length;
			while ( j-- ) {
				handleObj = handlers[ j ];

				if ( ( mappedTypes || origType === handleObj.origType ) &&
					( !handler || handler.guid === handleObj.guid ) &&
					( !tmp || tmp.test( handleObj.namespace ) ) &&
					( !selector || selector === handleObj.selector || selector === "**" && handleObj.selector ) ) {
					handlers.splice( j, 1 );

					if ( handleObj.selector ) {
						handlers.delegateCount--;
					}
					if ( special.remove ) {
						special.remove.call( elem, handleObj );
					}
				}
			}

			// Remove generic event handler if we removed something and no more handlers exist
			// (avoids potential for endless recursion during removal of special event handlers)
			if ( origCount && !handlers.length ) {
				if ( !special.teardown || special.teardown.call( elem, namespaces, elemData.handle ) === false ) {
					jQuery.removeEvent( elem, type, elemData.handle );
				}

				delete events[ type ];
			}
		}

		// Remove the expando if it's no longer used
		if ( jQuery.isEmptyObject( events ) ) {
			delete elemData.handle;
			data_priv.remove( elem, "events" );
		}
	},

	trigger: function( event, data, elem, onlyHandlers ) {

		var i, cur, tmp, bubbleType, ontype, handle, special,
			eventPath = [ elem || document ],
			type = core_hasOwn.call( event, "type" ) ? event.type : event,
			namespaces = core_hasOwn.call( event, "namespace" ) ? event.namespace.split(".") : [];

		cur = tmp = elem = elem || document;

		// Don't do events on text and comment nodes
		if ( elem.nodeType === 3 || elem.nodeType === 8 ) {
			return;
		}

		// focus/blur morphs to focusin/out; ensure we're not firing them right now
		if ( rfocusMorph.test( type + jQuery.event.triggered ) ) {
			return;
		}

		if ( type.indexOf(".") >= 0 ) {
			// Namespaced trigger; create a regexp to match event type in handle()
			namespaces = type.split(".");
			type = namespaces.shift();
			namespaces.sort();
		}
		ontype = type.indexOf(":") < 0 && "on" + type;

		// Caller can pass in a jQuery.Event object, Object, or just an event type string
		event = event[ jQuery.expando ] ?
			event :
			new jQuery.Event( type, typeof event === "object" && event );

		// Trigger bitmask: & 1 for native handlers; & 2 for jQuery (always true)
		event.isTrigger = onlyHandlers ? 2 : 3;
		event.namespace = namespaces.join(".");
		event.namespace_re = event.namespace ?
			new RegExp( "(^|\\.)" + namespaces.join("\\.(?:.*\\.|)") + "(\\.|$)" ) :
			null;

		// Clean up the event in case it is being reused
		event.result = undefined;
		if ( !event.target ) {
			event.target = elem;
		}

		// Clone any incoming data and prepend the event, creating the handler arg list
		data = data == null ?
			[ event ] :
			jQuery.makeArray( data, [ event ] );

		// Allow special events to draw outside the lines
		special = jQuery.event.special[ type ] || {};
		if ( !onlyHandlers && special.trigger && special.trigger.apply( elem, data ) === false ) {
			return;
		}

		// Determine event propagation path in advance, per W3C events spec (#9951)
		// Bubble up to document, then to window; watch for a global ownerDocument var (#9724)
		if ( !onlyHandlers && !special.noBubble && !jQuery.isWindow( elem ) ) {

			bubbleType = special.delegateType || type;
			if ( !rfocusMorph.test( bubbleType + type ) ) {
				cur = cur.parentNode;
			}
			for ( ; cur; cur = cur.parentNode ) {
				eventPath.push( cur );
				tmp = cur;
			}

			// Only add window if we got to document (e.g., not plain obj or detached DOM)
			if ( tmp === (elem.ownerDocument || document) ) {
				eventPath.push( tmp.defaultView || tmp.parentWindow || window );
			}
		}

		// Fire handlers on the event path
		i = 0;
		while ( (cur = eventPath[i++]) && !event.isPropagationStopped() ) {

			event.type = i > 1 ?
				bubbleType :
				special.bindType || type;

			// jQuery handler
			handle = ( data_priv.get( cur, "events" ) || {} )[ event.type ] && data_priv.get( cur, "handle" );
			if ( handle ) {
				handle.apply( cur, data );
			}

			// Native handler
			handle = ontype && cur[ ontype ];
			if ( handle && jQuery.acceptData( cur ) && handle.apply && handle.apply( cur, data ) === false ) {
				event.preventDefault();
			}
		}
		event.type = type;

		// If nobody prevented the default action, do it now
		if ( !onlyHandlers && !event.isDefaultPrevented() ) {

			if ( (!special._default || special._default.apply( eventPath.pop(), data ) === false) &&
				jQuery.acceptData( elem ) ) {

				// Call a native DOM method on the target with the same name name as the event.
				// Don't do default actions on window, that's where global variables be (#6170)
				if ( ontype && jQuery.isFunction( elem[ type ] ) && !jQuery.isWindow( elem ) ) {

					// Don't re-trigger an onFOO event when we call its FOO() method
					tmp = elem[ ontype ];

					if ( tmp ) {
						elem[ ontype ] = null;
					}

					// Prevent re-triggering of the same event, since we already bubbled it above
					jQuery.event.triggered = type;
					elem[ type ]();
					jQuery.event.triggered = undefined;

					if ( tmp ) {
						elem[ ontype ] = tmp;
					}
				}
			}
		}

		return event.result;
	},

	dispatch: function( event ) {

		// Make a writable jQuery.Event from the native event object
		event = jQuery.event.fix( event );

		var i, j, ret, matched, handleObj,
			handlerQueue = [],
			args = core_slice.call( arguments ),
			handlers = ( data_priv.get( this, "events" ) || {} )[ event.type ] || [],
			special = jQuery.event.special[ event.type ] || {};

		// Use the fix-ed jQuery.Event rather than the (read-only) native event
		args[0] = event;
		event.delegateTarget = this;

		// Call the preDispatch hook for the mapped type, and let it bail if desired
		if ( special.preDispatch && special.preDispatch.call( this, event ) === false ) {
			return;
		}

		// Determine handlers
		handlerQueue = jQuery.event.handlers.call( this, event, handlers );

		// Run delegates first; they may want to stop propagation beneath us
		i = 0;
		while ( (matched = handlerQueue[ i++ ]) && !event.isPropagationStopped() ) {
			event.currentTarget = matched.elem;

			j = 0;
			while ( (handleObj = matched.handlers[ j++ ]) && !event.isImmediatePropagationStopped() ) {

				// Triggered event must either 1) have no namespace, or
				// 2) have namespace(s) a subset or equal to those in the bound event (both can have no namespace).
				if ( !event.namespace_re || event.namespace_re.test( handleObj.namespace ) ) {

					event.handleObj = handleObj;
					event.data = handleObj.data;

					ret = ( (jQuery.event.special[ handleObj.origType ] || {}).handle || handleObj.handler )
							.apply( matched.elem, args );

					if ( ret !== undefined ) {
						if ( (event.result = ret) === false ) {
							event.preventDefault();
							event.stopPropagation();
						}
					}
				}
			}
		}

		// Call the postDispatch hook for the mapped type
		if ( special.postDispatch ) {
			special.postDispatch.call( this, event );
		}

		return event.result;
	},

	handlers: function( event, handlers ) {
		var i, matches, sel, handleObj,
			handlerQueue = [],
			delegateCount = handlers.delegateCount,
			cur = event.target;

		// Find delegate handlers
		// Black-hole SVG <use> instance trees (#13180)
		// Avoid non-left-click bubbling in Firefox (#3861)
		if ( delegateCount && cur.nodeType && (!event.button || event.type !== "click") ) {

			for ( ; cur !== this; cur = cur.parentNode || this ) {

				// Don't process clicks on disabled elements (#6911, #8165, #11382, #11764)
				if ( cur.disabled !== true || event.type !== "click" ) {
					matches = [];
					for ( i = 0; i < delegateCount; i++ ) {
						handleObj = handlers[ i ];

						// Don't conflict with Object.prototype properties (#13203)
						sel = handleObj.selector + " ";

						if ( matches[ sel ] === undefined ) {
							matches[ sel ] = handleObj.needsContext ?
								jQuery( sel, this ).index( cur ) >= 0 :
								jQuery.find( sel, this, null, [ cur ] ).length;
						}
						if ( matches[ sel ] ) {
							matches.push( handleObj );
						}
					}
					if ( matches.length ) {
						handlerQueue.push({ elem: cur, handlers: matches });
					}
				}
			}
		}

		// Add the remaining (directly-bound) handlers
		if ( delegateCount < handlers.length ) {
			handlerQueue.push({ elem: this, handlers: handlers.slice( delegateCount ) });
		}

		return handlerQueue;
	},

	// Includes some event props shared by KeyEvent and MouseEvent
	props: "altKey bubbles cancelable ctrlKey currentTarget eventPhase metaKey relatedTarget shiftKey target timeStamp view which".split(" "),

	fixHooks: {},

	keyHooks: {
		props: "char charCode key keyCode".split(" "),
		filter: function( event, original ) {

			// Add which for key events
			if ( event.which == null ) {
				event.which = original.charCode != null ? original.charCode : original.keyCode;
			}

			return event;
		}
	},

	mouseHooks: {
		props: "button buttons clientX clientY offsetX offsetY pageX pageY screenX screenY toElement".split(" "),
		filter: function( event, original ) {
			var eventDoc, doc, body,
				button = original.button;

			// Calculate pageX/Y if missing and clientX/Y available
			if ( event.pageX == null && original.clientX != null ) {
				eventDoc = event.target.ownerDocument || document;
				doc = eventDoc.documentElement;
				body = eventDoc.body;

				event.pageX = original.clientX + ( doc && doc.scrollLeft || body && body.scrollLeft || 0 ) - ( doc && doc.clientLeft || body && body.clientLeft || 0 );
				event.pageY = original.clientY + ( doc && doc.scrollTop  || body && body.scrollTop  || 0 ) - ( doc && doc.clientTop  || body && body.clientTop  || 0 );
			}

			// Add which for click: 1 === left; 2 === middle; 3 === right
			// Note: button is not normalized, so don't use it
			if ( !event.which && button !== undefined ) {
				event.which = ( button & 1 ? 1 : ( button & 2 ? 3 : ( button & 4 ? 2 : 0 ) ) );
			}

			return event;
		}
	},

	fix: function( event ) {
		if ( event[ jQuery.expando ] ) {
			return event;
		}

		// Create a writable copy of the event object and normalize some properties
		var i, prop, copy,
			type = event.type,
			originalEvent = event,
			fixHook = this.fixHooks[ type ];

		if ( !fixHook ) {
			this.fixHooks[ type ] = fixHook =
				rmouseEvent.test( type ) ? this.mouseHooks :
				rkeyEvent.test( type ) ? this.keyHooks :
				{};
		}
		copy = fixHook.props ? this.props.concat( fixHook.props ) : this.props;

		event = new jQuery.Event( originalEvent );

		i = copy.length;
		while ( i-- ) {
			prop = copy[ i ];
			event[ prop ] = originalEvent[ prop ];
		}

		// Support: Safari 6.0+, Chrome < 28
		// Target should not be a text node (#504, #13143)
		if ( event.target.nodeType === 3 ) {
			event.target = event.target.parentNode;
		}

		return fixHook.filter? fixHook.filter( event, originalEvent ) : event;
	},

	special: {
		load: {
			// Prevent triggered image.load events from bubbling to window.load
			noBubble: true
		},
		focus: {
			// Fire native event if possible so blur/focus sequence is correct
			trigger: function() {
				if ( this !== safeActiveElement() && this.focus ) {
					this.focus();
					return false;
				}
			},
			delegateType: "focusin"
		},
		blur: {
			trigger: function() {
				if ( this === safeActiveElement() && this.blur ) {
					this.blur();
					return false;
				}
			},
			delegateType: "focusout"
		},
		click: {
			// For checkbox, fire native event so checked state will be right
			trigger: function() {
				if ( this.type === "checkbox" && this.click && jQuery.nodeName( this, "input" ) ) {
					this.click();
					return false;
				}
			},

			// For cross-browser consistency, don't fire native .click() on links
			_default: function( event ) {
				return jQuery.nodeName( event.target, "a" );
			}
		},

		beforeunload: {
			postDispatch: function( event ) {

				// Support: Firefox 20+
				// Firefox doesn't alert if the returnValue field is not set.
				if ( event.result !== undefined ) {
					event.originalEvent.returnValue = event.result;
				}
			}
		}
	},

	simulate: function( type, elem, event, bubble ) {
		// Piggyback on a donor event to simulate a different one.
		// Fake originalEvent to avoid donor's stopPropagation, but if the
		// simulated event prevents default then we do the same on the donor.
		var e = jQuery.extend(
			new jQuery.Event(),
			event,
			{
				type: type,
				isSimulated: true,
				originalEvent: {}
			}
		);
		if ( bubble ) {
			jQuery.event.trigger( e, null, elem );
		} else {
			jQuery.event.dispatch.call( elem, e );
		}
		if ( e.isDefaultPrevented() ) {
			event.preventDefault();
		}
	}
};

jQuery.removeEvent = function( elem, type, handle ) {
	if ( elem.removeEventListener ) {
		elem.removeEventListener( type, handle, false );
	}
};

jQuery.Event = function( src, props ) {
	// Allow instantiation without the 'new' keyword
	if ( !(this instanceof jQuery.Event) ) {
		return new jQuery.Event( src, props );
	}

	// Event object
	if ( src && src.type ) {
		this.originalEvent = src;
		this.type = src.type;

		// Events bubbling up the document may have been marked as prevented
		// by a handler lower down the tree; reflect the correct value.
		this.isDefaultPrevented = ( src.defaultPrevented ||
			src.getPreventDefault && src.getPreventDefault() ) ? returnTrue : returnFalse;

	// Event type
	} else {
		this.type = src;
	}

	// Put explicitly provided properties onto the event object
	if ( props ) {
		jQuery.extend( this, props );
	}

	// Create a timestamp if incoming event doesn't have one
	this.timeStamp = src && src.timeStamp || jQuery.now();

	// Mark it as fixed
	this[ jQuery.expando ] = true;
};

// jQuery.Event is based on DOM3 Events as specified by the ECMAScript Language Binding
// http://www.w3.org/TR/2003/WD-DOM-Level-3-Events-20030331/ecma-script-binding.html
jQuery.Event.prototype = {
	isDefaultPrevented: returnFalse,
	isPropagationStopped: returnFalse,
	isImmediatePropagationStopped: returnFalse,

	preventDefault: function() {
		var e = this.originalEvent;

		this.isDefaultPrevented = returnTrue;

		if ( e && e.preventDefault ) {
			e.preventDefault();
		}
	},
	stopPropagation: function() {
		var e = this.originalEvent;

		this.isPropagationStopped = returnTrue;

		if ( e && e.stopPropagation ) {
			e.stopPropagation();
		}
	},
	stopImmediatePropagation: function() {
		this.isImmediatePropagationStopped = returnTrue;
		this.stopPropagation();
	}
};

// Create mouseenter/leave events using mouseover/out and event-time checks
// Support: Chrome 15+
jQuery.each({
	mouseenter: "mouseover",
	mouseleave: "mouseout"
}, function( orig, fix ) {
	jQuery.event.special[ orig ] = {
		delegateType: fix,
		bindType: fix,

		handle: function( event ) {
			var ret,
				target = this,
				related = event.relatedTarget,
				handleObj = event.handleObj;

			// For mousenter/leave call the handler if related is outside the target.
			// NB: No relatedTarget if the mouse left/entered the browser window
			if ( !related || (related !== target && !jQuery.contains( target, related )) ) {
				event.type = handleObj.origType;
				ret = handleObj.handler.apply( this, arguments );
				event.type = fix;
			}
			return ret;
		}
	};
});

// Create "bubbling" focus and blur events
// Support: Firefox, Chrome, Safari
if ( !jQuery.support.focusinBubbles ) {
	jQuery.each({ focus: "focusin", blur: "focusout" }, function( orig, fix ) {

		// Attach a single capturing handler while someone wants focusin/focusout
		var attaches = 0,
			handler = function( event ) {
				jQuery.event.simulate( fix, event.target, jQuery.event.fix( event ), true );
			};

		jQuery.event.special[ fix ] = {
			setup: function() {
				if ( attaches++ === 0 ) {
					document.addEventListener( orig, handler, true );
				}
			},
			teardown: function() {
				if ( --attaches === 0 ) {
					document.removeEventListener( orig, handler, true );
				}
			}
		};
	});
}

jQuery.fn.extend({

	on: function( types, selector, data, fn, /*INTERNAL*/ one ) {
		var origFn, type;

		// Types can be a map of types/handlers
		if ( typeof types === "object" ) {
			// ( types-Object, selector, data )
			if ( typeof selector !== "string" ) {
				// ( types-Object, data )
				data = data || selector;
				selector = undefined;
			}
			for ( type in types ) {
				this.on( type, selector, data, types[ type ], one );
			}
			return this;
		}

		if ( data == null && fn == null ) {
			// ( types, fn )
			fn = selector;
			data = selector = undefined;
		} else if ( fn == null ) {
			if ( typeof selector === "string" ) {
				// ( types, selector, fn )
				fn = data;
				data = undefined;
			} else {
				// ( types, data, fn )
				fn = data;
				data = selector;
				selector = undefined;
			}
		}
		if ( fn === false ) {
			fn = returnFalse;
		} else if ( !fn ) {
			return this;
		}

		if ( one === 1 ) {
			origFn = fn;
			fn = function( event ) {
				// Can use an empty set, since event contains the info
				jQuery().off( event );
				return origFn.apply( this, arguments );
			};
			// Use same guid so caller can remove using origFn
			fn.guid = origFn.guid || ( origFn.guid = jQuery.guid++ );
		}
		return this.each( function() {
			jQuery.event.add( this, types, fn, data, selector );
		});
	},
	one: function( types, selector, data, fn ) {
		return this.on( types, selector, data, fn, 1 );
	},
	off: function( types, selector, fn ) {
		var handleObj, type;
		if ( types && types.preventDefault && types.handleObj ) {
			// ( event )  dispatched jQuery.Event
			handleObj = types.handleObj;
			jQuery( types.delegateTarget ).off(
				handleObj.namespace ? handleObj.origType + "." + handleObj.namespace : handleObj.origType,
				handleObj.selector,
				handleObj.handler
			);
			return this;
		}
		if ( typeof types === "object" ) {
			// ( types-object [, selector] )
			for ( type in types ) {
				this.off( type, selector, types[ type ] );
			}
			return this;
		}
		if ( selector === false || typeof selector === "function" ) {
			// ( types [, fn] )
			fn = selector;
			selector = undefined;
		}
		if ( fn === false ) {
			fn = returnFalse;
		}
		return this.each(function() {
			jQuery.event.remove( this, types, fn, selector );
		});
	},

	trigger: function( type, data ) {
		return this.each(function() {
			jQuery.event.trigger( type, data, this );
		});
	},
	triggerHandler: function( type, data ) {
		var elem = this[0];
		if ( elem ) {
			return jQuery.event.trigger( type, data, elem, true );
		}
	}
});
var isSimple = /^.[^:#\[\.,]*$/,
	rneedsContext = jQuery.expr.match.needsContext,
	// methods guaranteed to produce a unique set when starting from a unique set
	guaranteedUnique = {
		children: true,
		contents: true,
		next: true,
		prev: true
	};

jQuery.fn.extend({
	find: function( selector ) {
		var self, matched, i,
			l = this.length;

		if ( typeof selector !== "string" ) {
			self = this;
			return this.pushStack( jQuery( selector ).filter(function() {
				for ( i = 0; i < l; i++ ) {
					if ( jQuery.contains( self[ i ], this ) ) {
						return true;
					}
				}
			}) );
		}

		matched = [];
		for ( i = 0; i < l; i++ ) {
			jQuery.find( selector, this[ i ], matched );
		}

		// Needed because $( selector, context ) becomes $( context ).find( selector )
		matched = this.pushStack( l > 1 ? jQuery.unique( matched ) : matched );
		matched.selector = ( this.selector ? this.selector + " " : "" ) + selector;
		return matched;
	},

	has: function( target ) {
		var targets = jQuery( target, this ),
			l = targets.length;

		return this.filter(function() {
			var i = 0;
			for ( ; i < l; i++ ) {
				if ( jQuery.contains( this, targets[i] ) ) {
					return true;
				}
			}
		});
	},

	not: function( selector ) {
		return this.pushStack( winnow(this, selector || [], true) );
	},

	filter: function( selector ) {
		return this.pushStack( winnow(this, selector || [], false) );
	},

	is: function( selector ) {
		return !!selector && (
			typeof selector === "string" ?
				// If this is a positional/relative selector, check membership in the returned set
				// so $("p:first").is("p:last") won't return true for a doc with two "p".
				rneedsContext.test( selector ) ?
					jQuery( selector, this.context ).index( this[ 0 ] ) >= 0 :
					jQuery.filter( selector, this ).length > 0 :
				this.filter( selector ).length > 0 );
	},

	closest: function( selectors, context ) {
		var cur,
			i = 0,
			l = this.length,
			matched = [],
			pos = ( rneedsContext.test( selectors ) || typeof selectors !== "string" ) ?
				jQuery( selectors, context || this.context ) :
				0;

		for ( ; i < l; i++ ) {
			for ( cur = this[i]; cur && cur !== context; cur = cur.parentNode ) {
				// Always skip document fragments
				if ( cur.nodeType < 11 && (pos ?
					pos.index(cur) > -1 :

					// Don't pass non-elements to Sizzle
					cur.nodeType === 1 &&
						jQuery.find.matchesSelector(cur, selectors)) ) {

					cur = matched.push( cur );
					break;
				}
			}
		}

		return this.pushStack( matched.length > 1 ? jQuery.unique( matched ) : matched );
	},

	// Determine the position of an element within
	// the matched set of elements
	index: function( elem ) {

		// No argument, return index in parent
		if ( !elem ) {
			return ( this[ 0 ] && this[ 0 ].parentNode ) ? this.first().prevAll().length : -1;
		}

		// index in selector
		if ( typeof elem === "string" ) {
			return core_indexOf.call( jQuery( elem ), this[ 0 ] );
		}

		// Locate the position of the desired element
		return core_indexOf.call( this,

			// If it receives a jQuery object, the first element is used
			elem.jquery ? elem[ 0 ] : elem
		);
	},

	add: function( selector, context ) {
		var set = typeof selector === "string" ?
				jQuery( selector, context ) :
				jQuery.makeArray( selector && selector.nodeType ? [ selector ] : selector ),
			all = jQuery.merge( this.get(), set );

		return this.pushStack( jQuery.unique(all) );
	},

	addBack: function( selector ) {
		return this.add( selector == null ?
			this.prevObject : this.prevObject.filter(selector)
		);
	}
});

function sibling( cur, dir ) {
	while ( (cur = cur[dir]) && cur.nodeType !== 1 ) {}

	return cur;
}

jQuery.each({
	parent: function( elem ) {
		var parent = elem.parentNode;
		return parent && parent.nodeType !== 11 ? parent : null;
	},
	parents: function( elem ) {
		return jQuery.dir( elem, "parentNode" );
	},
	parentsUntil: function( elem, i, until ) {
		return jQuery.dir( elem, "parentNode", until );
	},
	next: function( elem ) {
		return sibling( elem, "nextSibling" );
	},
	prev: function( elem ) {
		return sibling( elem, "previousSibling" );
	},
	nextAll: function( elem ) {
		return jQuery.dir( elem, "nextSibling" );
	},
	prevAll: function( elem ) {
		return jQuery.dir( elem, "previousSibling" );
	},
	nextUntil: function( elem, i, until ) {
		return jQuery.dir( elem, "nextSibling", until );
	},
	prevUntil: function( elem, i, until ) {
		return jQuery.dir( elem, "previousSibling", until );
	},
	siblings: function( elem ) {
		return jQuery.sibling( ( elem.parentNode || {} ).firstChild, elem );
	},
	children: function( elem ) {
		return jQuery.sibling( elem.firstChild );
	},
	contents: function( elem ) {
		return jQuery.nodeName( elem, "iframe" ) ?
			elem.contentDocument || elem.contentWindow.document :
			jQuery.merge( [], elem.childNodes );
	}
}, function( name, fn ) {
	jQuery.fn[ name ] = function( until, selector ) {
		var matched = jQuery.map( this, fn, until );

		if ( name.slice( -5 ) !== "Until" ) {
			selector = until;
		}

		if ( selector && typeof selector === "string" ) {
			matched = jQuery.filter( selector, matched );
		}

		if ( this.length > 1 ) {
			// Remove duplicates
			if ( !guaranteedUnique[ name ] ) {
				jQuery.unique( matched );
			}

			// Reverse order for parents* and prev*
			if ( name[ 0 ] === "p" ) {
				matched.reverse();
			}
		}

		return this.pushStack( matched );
	};
});

jQuery.extend({
	filter: function( expr, elems, not ) {
		var elem = elems[ 0 ];

		if ( not ) {
			expr = ":not(" + expr + ")";
		}

		return elems.length === 1 && elem.nodeType === 1 ?
			jQuery.find.matchesSelector( elem, expr ) ? [ elem ] : [] :
			jQuery.find.matches( expr, jQuery.grep( elems, function( elem ) {
				return elem.nodeType === 1;
			}));
	},

	dir: function( elem, dir, until ) {
		var matched = [],
			truncate = until !== undefined;

		while ( (elem = elem[ dir ]) && elem.nodeType !== 9 ) {
			if ( elem.nodeType === 1 ) {
				if ( truncate && jQuery( elem ).is( until ) ) {
					break;
				}
				matched.push( elem );
			}
		}
		return matched;
	},

	sibling: function( n, elem ) {
		var matched = [];

		for ( ; n; n = n.nextSibling ) {
			if ( n.nodeType === 1 && n !== elem ) {
				matched.push( n );
			}
		}

		return matched;
	}
});

// Implement the identical functionality for filter and not
function winnow( elements, qualifier, not ) {
	if ( jQuery.isFunction( qualifier ) ) {
		return jQuery.grep( elements, function( elem, i ) {
			/* jshint -W018 */
			return !!qualifier.call( elem, i, elem ) !== not;
		});

	}

	if ( qualifier.nodeType ) {
		return jQuery.grep( elements, function( elem ) {
			return ( elem === qualifier ) !== not;
		});

	}

	if ( typeof qualifier === "string" ) {
		if ( isSimple.test( qualifier ) ) {
			return jQuery.filter( qualifier, elements, not );
		}

		qualifier = jQuery.filter( qualifier, elements );
	}

	return jQuery.grep( elements, function( elem ) {
		return ( core_indexOf.call( qualifier, elem ) >= 0 ) !== not;
	});
}
var rxhtmlTag = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi,
	rtagName = /<([\w:]+)/,
	rhtml = /<|&#?\w+;/,
	rnoInnerhtml = /<(?:script|style|link)/i,
	manipulation_rcheckableType = /^(?:checkbox|radio)$/i,
	// checked="checked" or checked
	rchecked = /checked\s*(?:[^=]|=\s*.checked.)/i,
	rscriptType = /^$|\/(?:java|ecma)script/i,
	rscriptTypeMasked = /^true\/(.*)/,
	rcleanScript = /^\s*<!(?:\[CDATA\[|--)|(?:\]\]|--)>\s*$/g,

	// We have to close these tags to support XHTML (#13200)
	wrapMap = {

		// Support: IE 9
		option: [ 1, "<select multiple='multiple'>", "</select>" ],

		thead: [ 1, "<table>", "</table>" ],
		tr: [ 2, "<table><tbody>", "</tbody></table>" ],
		td: [ 3, "<table><tbody><tr>", "</tr></tbody></table>" ],

		_default: [ 0, "", "" ]
	};

// Support: IE 9
wrapMap.optgroup = wrapMap.option;

wrapMap.tbody = wrapMap.tfoot = wrapMap.colgroup = wrapMap.caption = wrapMap.col = wrapMap.thead;
wrapMap.th = wrapMap.td;

jQuery.fn.extend({
	text: function( value ) {
		return jQuery.access( this, function( value ) {
			return value === undefined ?
				jQuery.text( this ) :
				this.empty().append( ( this[ 0 ] && this[ 0 ].ownerDocument || document ).createTextNode( value ) );
		}, null, value, arguments.length );
	},

	append: function() {
		return this.domManip( arguments, function( elem ) {
			if ( this.nodeType === 1 || this.nodeType === 11 || this.nodeType === 9 ) {
				var target = manipulationTarget( this, elem );
				target.appendChild( elem );
			}
		});
	},

	prepend: function() {
		return this.domManip( arguments, function( elem ) {
			if ( this.nodeType === 1 || this.nodeType === 11 || this.nodeType === 9 ) {
				var target = manipulationTarget( this, elem );
				target.insertBefore( elem, target.firstChild );
			}
		});
	},

	before: function() {
		return this.domManip( arguments, function( elem ) {
			if ( this.parentNode ) {
				this.parentNode.insertBefore( elem, this );
			}
		});
	},

	after: function() {
		return this.domManip( arguments, function( elem ) {
			if ( this.parentNode ) {
				this.parentNode.insertBefore( elem, this.nextSibling );
			}
		});
	},

	// keepData is for internal use only--do not document
	remove: function( selector, keepData ) {
		var elem,
			elems = selector ? jQuery.filter( selector, this ) : this,
			i = 0;

		for ( ; (elem = elems[i]) != null; i++ ) {
			if ( !keepData && elem.nodeType === 1 ) {
				jQuery.cleanData( getAll( elem ) );
			}

			if ( elem.parentNode ) {
				if ( keepData && jQuery.contains( elem.ownerDocument, elem ) ) {
					setGlobalEval( getAll( elem, "script" ) );
				}
				elem.parentNode.removeChild( elem );
			}
		}

		return this;
	},

	empty: function() {
		var elem,
			i = 0;

		for ( ; (elem = this[i]) != null; i++ ) {
			if ( elem.nodeType === 1 ) {

				// Prevent memory leaks
				jQuery.cleanData( getAll( elem, false ) );

				// Remove any remaining nodes
				elem.textContent = "";
			}
		}

		return this;
	},

	clone: function( dataAndEvents, deepDataAndEvents ) {
		dataAndEvents = dataAndEvents == null ? false : dataAndEvents;
		deepDataAndEvents = deepDataAndEvents == null ? dataAndEvents : deepDataAndEvents;

		return this.map( function () {
			return jQuery.clone( this, dataAndEvents, deepDataAndEvents );
		});
	},

	html: function( value ) {
		return jQuery.access( this, function( value ) {
			var elem = this[ 0 ] || {},
				i = 0,
				l = this.length;

			if ( value === undefined && elem.nodeType === 1 ) {
				return elem.innerHTML;
			}

			// See if we can take a shortcut and just use innerHTML
			if ( typeof value === "string" && !rnoInnerhtml.test( value ) &&
				!wrapMap[ ( rtagName.exec( value ) || [ "", "" ] )[ 1 ].toLowerCase() ] ) {

				value = value.replace( rxhtmlTag, "<$1></$2>" );

				try {
					for ( ; i < l; i++ ) {
						elem = this[ i ] || {};

						// Remove element nodes and prevent memory leaks
						if ( elem.nodeType === 1 ) {
							jQuery.cleanData( getAll( elem, false ) );
							elem.innerHTML = value;
						}
					}

					elem = 0;

				// If using innerHTML throws an exception, use the fallback method
				} catch( e ) {}
			}

			if ( elem ) {
				this.empty().append( value );
			}
		}, null, value, arguments.length );
	},

	replaceWith: function() {
		var
			// Snapshot the DOM in case .domManip sweeps something relevant into its fragment
			args = jQuery.map( this, function( elem ) {
				return [ elem.nextSibling, elem.parentNode ];
			}),
			i = 0;

		// Make the changes, replacing each context element with the new content
		this.domManip( arguments, function( elem ) {
			var next = args[ i++ ],
				parent = args[ i++ ];

			if ( parent ) {
				jQuery( this ).remove();
				parent.insertBefore( elem, next );
			}
		// Allow new content to include elements from the context set
		}, true );

		// Force removal if there was no new content (e.g., from empty arguments)
		return i ? this : this.remove();
	},

	detach: function( selector ) {
		return this.remove( selector, true );
	},

	domManip: function( args, callback, allowIntersection ) {

		// Flatten any nested arrays
		args = core_concat.apply( [], args );

		var fragment, first, scripts, hasScripts, node, doc,
			i = 0,
			l = this.length,
			set = this,
			iNoClone = l - 1,
			value = args[ 0 ],
			isFunction = jQuery.isFunction( value );

		// We can't cloneNode fragments that contain checked, in WebKit
		if ( isFunction || !( l <= 1 || typeof value !== "string" || jQuery.support.checkClone || !rchecked.test( value ) ) ) {
			return this.each(function( index ) {
				var self = set.eq( index );
				if ( isFunction ) {
					args[ 0 ] = value.call( this, index, self.html() );
				}
				self.domManip( args, callback, allowIntersection );
			});
		}

		if ( l ) {
			fragment = jQuery.buildFragment( args, this[ 0 ].ownerDocument, false, !allowIntersection && this );
			first = fragment.firstChild;

			if ( fragment.childNodes.length === 1 ) {
				fragment = first;
			}

			if ( first ) {
				scripts = jQuery.map( getAll( fragment, "script" ), disableScript );
				hasScripts = scripts.length;

				// Use the original fragment for the last item instead of the first because it can end up
				// being emptied incorrectly in certain situations (#8070).
				for ( ; i < l; i++ ) {
					node = fragment;

					if ( i !== iNoClone ) {
						node = jQuery.clone( node, true, true );

						// Keep references to cloned scripts for later restoration
						if ( hasScripts ) {
							// Support: QtWebKit
							// jQuery.merge because core_push.apply(_, arraylike) throws
							jQuery.merge( scripts, getAll( node, "script" ) );
						}
					}

					callback.call( this[ i ], node, i );
				}

				if ( hasScripts ) {
					doc = scripts[ scripts.length - 1 ].ownerDocument;

					// Reenable scripts
					jQuery.map( scripts, restoreScript );

					// Evaluate executable scripts on first document insertion
					for ( i = 0; i < hasScripts; i++ ) {
						node = scripts[ i ];
						if ( rscriptType.test( node.type || "" ) &&
							!data_priv.access( node, "globalEval" ) && jQuery.contains( doc, node ) ) {

							if ( node.src ) {
								// Hope ajax is available...
								jQuery._evalUrl( node.src );
							} else {
								jQuery.globalEval( node.textContent.replace( rcleanScript, "" ) );
							}
						}
					}
				}
			}
		}

		return this;
	}
});

jQuery.each({
	appendTo: "append",
	prependTo: "prepend",
	insertBefore: "before",
	insertAfter: "after",
	replaceAll: "replaceWith"
}, function( name, original ) {
	jQuery.fn[ name ] = function( selector ) {
		var elems,
			ret = [],
			insert = jQuery( selector ),
			last = insert.length - 1,
			i = 0;

		for ( ; i <= last; i++ ) {
			elems = i === last ? this : this.clone( true );
			jQuery( insert[ i ] )[ original ]( elems );

			// Support: QtWebKit
			// .get() because core_push.apply(_, arraylike) throws
			core_push.apply( ret, elems.get() );
		}

		return this.pushStack( ret );
	};
});

jQuery.extend({
	clone: function( elem, dataAndEvents, deepDataAndEvents ) {
		var i, l, srcElements, destElements,
			clone = elem.cloneNode( true ),
			inPage = jQuery.contains( elem.ownerDocument, elem );

		// Support: IE >= 9
		// Fix Cloning issues
		if ( !jQuery.support.noCloneChecked && ( elem.nodeType === 1 || elem.nodeType === 11 ) && !jQuery.isXMLDoc( elem ) ) {

			// We eschew Sizzle here for performance reasons: http://jsperf.com/getall-vs-sizzle/2
			destElements = getAll( clone );
			srcElements = getAll( elem );

			for ( i = 0, l = srcElements.length; i < l; i++ ) {
				fixInput( srcElements[ i ], destElements[ i ] );
			}
		}

		// Copy the events from the original to the clone
		if ( dataAndEvents ) {
			if ( deepDataAndEvents ) {
				srcElements = srcElements || getAll( elem );
				destElements = destElements || getAll( clone );

				for ( i = 0, l = srcElements.length; i < l; i++ ) {
					cloneCopyEvent( srcElements[ i ], destElements[ i ] );
				}
			} else {
				cloneCopyEvent( elem, clone );
			}
		}

		// Preserve script evaluation history
		destElements = getAll( clone, "script" );
		if ( destElements.length > 0 ) {
			setGlobalEval( destElements, !inPage && getAll( elem, "script" ) );
		}

		// Return the cloned set
		return clone;
	},

	buildFragment: function( elems, context, scripts, selection ) {
		var elem, tmp, tag, wrap, contains, j,
			i = 0,
			l = elems.length,
			fragment = context.createDocumentFragment(),
			nodes = [];

		for ( ; i < l; i++ ) {
			elem = elems[ i ];

			if ( elem || elem === 0 ) {

				// Add nodes directly
				if ( jQuery.type( elem ) === "object" ) {
					// Support: QtWebKit
					// jQuery.merge because core_push.apply(_, arraylike) throws
					jQuery.merge( nodes, elem.nodeType ? [ elem ] : elem );

				// Convert non-html into a text node
				} else if ( !rhtml.test( elem ) ) {
					nodes.push( context.createTextNode( elem ) );

				// Convert html into DOM nodes
				} else {
					tmp = tmp || fragment.appendChild( context.createElement("div") );

					// Deserialize a standard representation
					tag = ( rtagName.exec( elem ) || ["", ""] )[ 1 ].toLowerCase();
					wrap = wrapMap[ tag ] || wrapMap._default;
					tmp.innerHTML = wrap[ 1 ] + elem.replace( rxhtmlTag, "<$1></$2>" ) + wrap[ 2 ];

					// Descend through wrappers to the right content
					j = wrap[ 0 ];
					while ( j-- ) {
						tmp = tmp.firstChild;
					}

					// Support: QtWebKit
					// jQuery.merge because core_push.apply(_, arraylike) throws
					jQuery.merge( nodes, tmp.childNodes );

					// Remember the top-level container
					tmp = fragment.firstChild;

					// Fixes #12346
					// Support: Webkit, IE
					tmp.textContent = "";
				}
			}
		}

		// Remove wrapper from fragment
		fragment.textContent = "";

		i = 0;
		while ( (elem = nodes[ i++ ]) ) {

			// #4087 - If origin and destination elements are the same, and this is
			// that element, do not do anything
			if ( selection && jQuery.inArray( elem, selection ) !== -1 ) {
				continue;
			}

			contains = jQuery.contains( elem.ownerDocument, elem );

			// Append to fragment
			tmp = getAll( fragment.appendChild( elem ), "script" );

			// Preserve script evaluation history
			if ( contains ) {
				setGlobalEval( tmp );
			}

			// Capture executables
			if ( scripts ) {
				j = 0;
				while ( (elem = tmp[ j++ ]) ) {
					if ( rscriptType.test( elem.type || "" ) ) {
						scripts.push( elem );
					}
				}
			}
		}

		return fragment;
	},

	cleanData: function( elems ) {
		var data, elem, type,
			l = elems.length,
			i = 0,
			special = jQuery.event.special;

		for ( ; i < l; i++ ) {
			elem = elems[ i ];

			if ( jQuery.acceptData( elem ) ) {

				data = data_priv.access( elem );

				if ( data ) {
					for ( type in data.events ) {
						if ( special[ type ] ) {
							jQuery.event.remove( elem, type );

						// This is a shortcut to avoid jQuery.event.remove's overhead
						} else {
							jQuery.removeEvent( elem, type, data.handle );
						}
					}
				}
			}
			// Discard any remaining `private` and `user` data
			// One day we'll replace the dual arrays with a WeakMap and this won't be an issue.
			// (Splices the data objects out of the internal cache arrays)
			data_user.discard( elem );
			data_priv.discard( elem );
		}
	},

	_evalUrl: function( url ) {
		return jQuery.ajax({
			url: url,
			type: "GET",
			dataType: "text",
			async: false,
			global: false,
			success: jQuery.globalEval
		});
	}
});

// Support: 1.x compatibility
// Manipulating tables requires a tbody
function manipulationTarget( elem, content ) {
	return jQuery.nodeName( elem, "table" ) &&
		jQuery.nodeName( content.nodeType === 1 ? content : content.firstChild, "tr" ) ?

		elem.getElementsByTagName("tbody")[0] ||
			elem.appendChild( elem.ownerDocument.createElement("tbody") ) :
		elem;
}

// Replace/restore the type attribute of script elements for safe DOM manipulation
function disableScript( elem ) {
	elem.type = (elem.getAttribute("type") !== null) + "/" + elem.type;
	return elem;
}
function restoreScript( elem ) {
	var match = rscriptTypeMasked.exec( elem.type );

	if ( match ) {
		elem.type = match[ 1 ];
	} else {
		elem.removeAttribute("type");
	}

	return elem;
}

// Mark scripts as having already been evaluated
function setGlobalEval( elems, refElements ) {
	var l = elems.length,
		i = 0;

	for ( ; i < l; i++ ) {
		data_priv.set(
			elems[ i ], "globalEval", !refElements || data_priv.get( refElements[ i ], "globalEval" )
		);
	}
}

function cloneCopyEvent( src, dest ) {
	var i, l, type, pdataOld, pdataCur, udataOld, udataCur, events;

	if ( dest.nodeType !== 1 ) {
		return;
	}

	// 1. Copy private data: events, handlers, etc.
	if ( data_priv.hasData( src ) ) {
		pdataOld = data_priv.access( src );
		pdataCur = jQuery.extend( {}, pdataOld );
		events = pdataOld.events;

		data_priv.set( dest, pdataCur );

		if ( events ) {
			delete pdataCur.handle;
			pdataCur.events = {};

			for ( type in events ) {
				for ( i = 0, l = events[ type ].length; i < l; i++ ) {
					jQuery.event.add( dest, type, events[ type ][ i ] );
				}
			}
		}
	}

	// 2. Copy user data
	if ( data_user.hasData( src ) ) {
		udataOld = data_user.access( src );
		udataCur = jQuery.extend( {}, udataOld );

		data_user.set( dest, udataCur );
	}
}


function getAll( context, tag ) {
	var ret = context.getElementsByTagName ? context.getElementsByTagName( tag || "*" ) :
			context.querySelectorAll ? context.querySelectorAll( tag || "*" ) :
			[];

	return tag === undefined || tag && jQuery.nodeName( context, tag ) ?
		jQuery.merge( [ context ], ret ) :
		ret;
}

// Support: IE >= 9
function fixInput( src, dest ) {
	var nodeName = dest.nodeName.toLowerCase();

	// Fails to persist the checked state of a cloned checkbox or radio button.
	if ( nodeName === "input" && manipulation_rcheckableType.test( src.type ) ) {
		dest.checked = src.checked;

	// Fails to return the selected option to the default selected state when cloning options
	} else if ( nodeName === "input" || nodeName === "textarea" ) {
		dest.defaultValue = src.defaultValue;
	}
}
jQuery.fn.extend({
	wrapAll: function( html ) {
		var wrap;

		if ( jQuery.isFunction( html ) ) {
			return this.each(function( i ) {
				jQuery( this ).wrapAll( html.call(this, i) );
			});
		}

		if ( this[ 0 ] ) {

			// The elements to wrap the target around
			wrap = jQuery( html, this[ 0 ].ownerDocument ).eq( 0 ).clone( true );

			if ( this[ 0 ].parentNode ) {
				wrap.insertBefore( this[ 0 ] );
			}

			wrap.map(function() {
				var elem = this;

				while ( elem.firstElementChild ) {
					elem = elem.firstElementChild;
				}

				return elem;
			}).append( this );
		}

		return this;
	},

	wrapInner: function( html ) {
		if ( jQuery.isFunction( html ) ) {
			return this.each(function( i ) {
				jQuery( this ).wrapInner( html.call(this, i) );
			});
		}

		return this.each(function() {
			var self = jQuery( this ),
				contents = self.contents();

			if ( contents.length ) {
				contents.wrapAll( html );

			} else {
				self.append( html );
			}
		});
	},

	wrap: function( html ) {
		var isFunction = jQuery.isFunction( html );

		return this.each(function( i ) {
			jQuery( this ).wrapAll( isFunction ? html.call(this, i) : html );
		});
	},

	unwrap: function() {
		return this.parent().each(function() {
			if ( !jQuery.nodeName( this, "body" ) ) {
				jQuery( this ).replaceWith( this.childNodes );
			}
		}).end();
	}
});
var curCSS, iframe,
	// swappable if display is none or starts with table except "table", "table-cell", or "table-caption"
	// see here for display values: https://developer.mozilla.org/en-US/docs/CSS/display
	rdisplayswap = /^(none|table(?!-c[ea]).+)/,
	rmargin = /^margin/,
	rnumsplit = new RegExp( "^(" + core_pnum + ")(.*)$", "i" ),
	rnumnonpx = new RegExp( "^(" + core_pnum + ")(?!px)[a-z%]+$", "i" ),
	rrelNum = new RegExp( "^([+-])=(" + core_pnum + ")", "i" ),
	elemdisplay = { BODY: "block" },

	cssShow = { position: "absolute", visibility: "hidden", display: "block" },
	cssNormalTransform = {
		letterSpacing: 0,
		fontWeight: 400
	},

	cssExpand = [ "Top", "Right", "Bottom", "Left" ],
	cssPrefixes = [ "Webkit", "O", "Moz", "ms" ];

// return a css property mapped to a potentially vendor prefixed property
function vendorPropName( style, name ) {

	// shortcut for names that are not vendor prefixed
	if ( name in style ) {
		return name;
	}

	// check for vendor prefixed names
	var capName = name.charAt(0).toUpperCase() + name.slice(1),
		origName = name,
		i = cssPrefixes.length;

	while ( i-- ) {
		name = cssPrefixes[ i ] + capName;
		if ( name in style ) {
			return name;
		}
	}

	return origName;
}

function isHidden( elem, el ) {
	// isHidden might be called from jQuery#filter function;
	// in that case, element will be second argument
	elem = el || elem;
	return jQuery.css( elem, "display" ) === "none" || !jQuery.contains( elem.ownerDocument, elem );
}

// NOTE: we've included the "window" in window.getComputedStyle
// because jsdom on node.js will break without it.
function getStyles( elem ) {
	return window.getComputedStyle( elem, null );
}

function showHide( elements, show ) {
	var display, elem, hidden,
		values = [],
		index = 0,
		length = elements.length;

	for ( ; index < length; index++ ) {
		elem = elements[ index ];
		if ( !elem.style ) {
			continue;
		}

		values[ index ] = data_priv.get( elem, "olddisplay" );
		display = elem.style.display;
		if ( show ) {
			// Reset the inline display of this element to learn if it is
			// being hidden by cascaded rules or not
			if ( !values[ index ] && display === "none" ) {
				elem.style.display = "";
			}

			// Set elements which have been overridden with display: none
			// in a stylesheet to whatever the default browser style is
			// for such an element
			if ( elem.style.display === "" && isHidden( elem ) ) {
				values[ index ] = data_priv.access( elem, "olddisplay", css_defaultDisplay(elem.nodeName) );
			}
		} else {

			if ( !values[ index ] ) {
				hidden = isHidden( elem );

				if ( display && display !== "none" || !hidden ) {
					data_priv.set( elem, "olddisplay", hidden ? display : jQuery.css(elem, "display") );
				}
			}
		}
	}

	// Set the display of most of the elements in a second loop
	// to avoid the constant reflow
	for ( index = 0; index < length; index++ ) {
		elem = elements[ index ];
		if ( !elem.style ) {
			continue;
		}
		if ( !show || elem.style.display === "none" || elem.style.display === "" ) {
			elem.style.display = show ? values[ index ] || "" : "none";
		}
	}

	return elements;
}

jQuery.fn.extend({
	css: function( name, value ) {
		return jQuery.access( this, function( elem, name, value ) {
			var styles, len,
				map = {},
				i = 0;

			if ( jQuery.isArray( name ) ) {
				styles = getStyles( elem );
				len = name.length;

				for ( ; i < len; i++ ) {
					map[ name[ i ] ] = jQuery.css( elem, name[ i ], false, styles );
				}

				return map;
			}

			return value !== undefined ?
				jQuery.style( elem, name, value ) :
				jQuery.css( elem, name );
		}, name, value, arguments.length > 1 );
	},
	show: function() {
		return showHide( this, true );
	},
	hide: function() {
		return showHide( this );
	},
	toggle: function( state ) {
		var bool = typeof state === "boolean";

		return this.each(function() {
			if ( bool ? state : isHidden( this ) ) {
				jQuery( this ).show();
			} else {
				jQuery( this ).hide();
			}
		});
	}
});

jQuery.extend({
	// Add in style property hooks for overriding the default
	// behavior of getting and setting a style property
	cssHooks: {
		opacity: {
			get: function( elem, computed ) {
				if ( computed ) {
					// We should always get a number back from opacity
					var ret = curCSS( elem, "opacity" );
					return ret === "" ? "1" : ret;
				}
			}
		}
	},

	// Exclude the following css properties to add px
	cssNumber: {
		"columnCount": true,
		"fillOpacity": true,
		"fontWeight": true,
		"lineHeight": true,
		"opacity": true,
		"orphans": true,
		"widows": true,
		"zIndex": true,
		"zoom": true
	},

	// Add in properties whose names you wish to fix before
	// setting or getting the value
	cssProps: {
		// normalize float css property
		"float": "cssFloat"
	},

	// Get and set the style property on a DOM Node
	style: function( elem, name, value, extra ) {
		// Don't set styles on text and comment nodes
		if ( !elem || elem.nodeType === 3 || elem.nodeType === 8 || !elem.style ) {
			return;
		}

		// Make sure that we're working with the right name
		var ret, type, hooks,
			origName = jQuery.camelCase( name ),
			style = elem.style;

		name = jQuery.cssProps[ origName ] || ( jQuery.cssProps[ origName ] = vendorPropName( style, origName ) );

		// gets hook for the prefixed version
		// followed by the unprefixed version
		hooks = jQuery.cssHooks[ name ] || jQuery.cssHooks[ origName ];

		// Check if we're setting a value
		if ( value !== undefined ) {
			type = typeof value;

			// convert relative number strings (+= or -=) to relative numbers. #7345
			if ( type === "string" && (ret = rrelNum.exec( value )) ) {
				value = ( ret[1] + 1 ) * ret[2] + parseFloat( jQuery.css( elem, name ) );
				// Fixes bug #9237
				type = "number";
			}

			// Make sure that NaN and null values aren't set. See: #7116
			if ( value == null || type === "number" && isNaN( value ) ) {
				return;
			}

			// If a number was passed in, add 'px' to the (except for certain CSS properties)
			if ( type === "number" && !jQuery.cssNumber[ origName ] ) {
				value += "px";
			}

			// Fixes #8908, it can be done more correctly by specifying setters in cssHooks,
			// but it would mean to define eight (for every problematic property) identical functions
			if ( !jQuery.support.clearCloneStyle && value === "" && name.indexOf("background") === 0 ) {
				style[ name ] = "inherit";
			}

			// If a hook was provided, use that value, otherwise just set the specified value
			if ( !hooks || !("set" in hooks) || (value = hooks.set( elem, value, extra )) !== undefined ) {
				style[ name ] = value;
			}

		} else {
			// If a hook was provided get the non-computed value from there
			if ( hooks && "get" in hooks && (ret = hooks.get( elem, false, extra )) !== undefined ) {
				return ret;
			}

			// Otherwise just get the value from the style object
			return style[ name ];
		}
	},

	css: function( elem, name, extra, styles ) {
		var val, num, hooks,
			origName = jQuery.camelCase( name );

		// Make sure that we're working with the right name
		name = jQuery.cssProps[ origName ] || ( jQuery.cssProps[ origName ] = vendorPropName( elem.style, origName ) );

		// gets hook for the prefixed version
		// followed by the unprefixed version
		hooks = jQuery.cssHooks[ name ] || jQuery.cssHooks[ origName ];

		// If a hook was provided get the computed value from there
		if ( hooks && "get" in hooks ) {
			val = hooks.get( elem, true, extra );
		}

		// Otherwise, if a way to get the computed value exists, use that
		if ( val === undefined ) {
			val = curCSS( elem, name, styles );
		}

		//convert "normal" to computed value
		if ( val === "normal" && name in cssNormalTransform ) {
			val = cssNormalTransform[ name ];
		}

		// Return, converting to number if forced or a qualifier was provided and val looks numeric
		if ( extra === "" || extra ) {
			num = parseFloat( val );
			return extra === true || jQuery.isNumeric( num ) ? num || 0 : val;
		}
		return val;
	}
});

curCSS = function( elem, name, _computed ) {
	var width, minWidth, maxWidth,
		computed = _computed || getStyles( elem ),

		// Support: IE9
		// getPropertyValue is only needed for .css('filter') in IE9, see #12537
		ret = computed ? computed.getPropertyValue( name ) || computed[ name ] : undefined,
		style = elem.style;

	if ( computed ) {

		if ( ret === "" && !jQuery.contains( elem.ownerDocument, elem ) ) {
			ret = jQuery.style( elem, name );
		}

		// Support: Safari 5.1
		// A tribute to the "awesome hack by Dean Edwards"
		// Safari 5.1.7 (at least) returns percentage for a larger set of values, but width seems to be reliably pixels
		// this is against the CSSOM draft spec: http://dev.w3.org/csswg/cssom/#resolved-values
		if ( rnumnonpx.test( ret ) && rmargin.test( name ) ) {

			// Remember the original values
			width = style.width;
			minWidth = style.minWidth;
			maxWidth = style.maxWidth;

			// Put in the new values to get a computed value out
			style.minWidth = style.maxWidth = style.width = ret;
			ret = computed.width;

			// Revert the changed values
			style.width = width;
			style.minWidth = minWidth;
			style.maxWidth = maxWidth;
		}
	}

	return ret;
};


function setPositiveNumber( elem, value, subtract ) {
	var matches = rnumsplit.exec( value );
	return matches ?
		// Guard against undefined "subtract", e.g., when used as in cssHooks
		Math.max( 0, matches[ 1 ] - ( subtract || 0 ) ) + ( matches[ 2 ] || "px" ) :
		value;
}

function augmentWidthOrHeight( elem, name, extra, isBorderBox, styles ) {
	var i = extra === ( isBorderBox ? "border" : "content" ) ?
		// If we already have the right measurement, avoid augmentation
		4 :
		// Otherwise initialize for horizontal or vertical properties
		name === "width" ? 1 : 0,

		val = 0;

	for ( ; i < 4; i += 2 ) {
		// both box models exclude margin, so add it if we want it
		if ( extra === "margin" ) {
			val += jQuery.css( elem, extra + cssExpand[ i ], true, styles );
		}

		if ( isBorderBox ) {
			// border-box includes padding, so remove it if we want content
			if ( extra === "content" ) {
				val -= jQuery.css( elem, "padding" + cssExpand[ i ], true, styles );
			}

			// at this point, extra isn't border nor margin, so remove border
			if ( extra !== "margin" ) {
				val -= jQuery.css( elem, "border" + cssExpand[ i ] + "Width", true, styles );
			}
		} else {
			// at this point, extra isn't content, so add padding
			val += jQuery.css( elem, "padding" + cssExpand[ i ], true, styles );

			// at this point, extra isn't content nor padding, so add border
			if ( extra !== "padding" ) {
				val += jQuery.css( elem, "border" + cssExpand[ i ] + "Width", true, styles );
			}
		}
	}

	return val;
}

function getWidthOrHeight( elem, name, extra ) {

	// Start with offset property, which is equivalent to the border-box value
	var valueIsBorderBox = true,
		val = name === "width" ? elem.offsetWidth : elem.offsetHeight,
		styles = getStyles( elem ),
		isBorderBox = jQuery.support.boxSizing && jQuery.css( elem, "boxSizing", false, styles ) === "border-box";

	// some non-html elements return undefined for offsetWidth, so check for null/undefined
	// svg - https://bugzilla.mozilla.org/show_bug.cgi?id=649285
	// MathML - https://bugzilla.mozilla.org/show_bug.cgi?id=491668
	if ( val <= 0 || val == null ) {
		// Fall back to computed then uncomputed css if necessary
		val = curCSS( elem, name, styles );
		if ( val < 0 || val == null ) {
			val = elem.style[ name ];
		}

		// Computed unit is not pixels. Stop here and return.
		if ( rnumnonpx.test(val) ) {
			return val;
		}

		// we need the check for style in case a browser which returns unreliable values
		// for getComputedStyle silently falls back to the reliable elem.style
		valueIsBorderBox = isBorderBox && ( jQuery.support.boxSizingReliable || val === elem.style[ name ] );

		// Normalize "", auto, and prepare for extra
		val = parseFloat( val ) || 0;
	}

	// use the active box-sizing model to add/subtract irrelevant styles
	return ( val +
		augmentWidthOrHeight(
			elem,
			name,
			extra || ( isBorderBox ? "border" : "content" ),
			valueIsBorderBox,
			styles
		)
	) + "px";
}

// Try to determine the default display value of an element
function css_defaultDisplay( nodeName ) {
	var doc = document,
		display = elemdisplay[ nodeName ];

	if ( !display ) {
		display = actualDisplay( nodeName, doc );

		// If the simple way fails, read from inside an iframe
		if ( display === "none" || !display ) {
			// Use the already-created iframe if possible
			iframe = ( iframe ||
				jQuery("<iframe frameborder='0' width='0' height='0'/>")
				.css( "cssText", "display:block !important" )
			).appendTo( doc.documentElement );

			// Always write a new HTML skeleton so Webkit and Firefox don't choke on reuse
			doc = ( iframe[0].contentWindow || iframe[0].contentDocument ).document;
			doc.write("<!doctype html><html><body>");
			doc.close();

			display = actualDisplay( nodeName, doc );
			iframe.detach();
		}

		// Store the correct default display
		elemdisplay[ nodeName ] = display;
	}

	return display;
}

// Called ONLY from within css_defaultDisplay
function actualDisplay( name, doc ) {
	var elem = jQuery( doc.createElement( name ) ).appendTo( doc.body ),
		display = jQuery.css( elem[0], "display" );
	elem.remove();
	return display;
}

jQuery.each([ "height", "width" ], function( i, name ) {
	jQuery.cssHooks[ name ] = {
		get: function( elem, computed, extra ) {
			if ( computed ) {
				// certain elements can have dimension info if we invisibly show them
				// however, it must have a current display style that would benefit from this
				return elem.offsetWidth === 0 && rdisplayswap.test( jQuery.css( elem, "display" ) ) ?
					jQuery.swap( elem, cssShow, function() {
						return getWidthOrHeight( elem, name, extra );
					}) :
					getWidthOrHeight( elem, name, extra );
			}
		},

		set: function( elem, value, extra ) {
			var styles = extra && getStyles( elem );
			return setPositiveNumber( elem, value, extra ?
				augmentWidthOrHeight(
					elem,
					name,
					extra,
					jQuery.support.boxSizing && jQuery.css( elem, "boxSizing", false, styles ) === "border-box",
					styles
				) : 0
			);
		}
	};
});

// These hooks cannot be added until DOM ready because the support test
// for it is not run until after DOM ready
jQuery(function() {
	// Support: Android 2.3
	if ( !jQuery.support.reliableMarginRight ) {
		jQuery.cssHooks.marginRight = {
			get: function( elem, computed ) {
				if ( computed ) {
					// Support: Android 2.3
					// WebKit Bug 13343 - getComputedStyle returns wrong value for margin-right
					// Work around by temporarily setting element display to inline-block
					return jQuery.swap( elem, { "display": "inline-block" },
						curCSS, [ elem, "marginRight" ] );
				}
			}
		};
	}

	// Webkit bug: https://bugs.webkit.org/show_bug.cgi?id=29084
	// getComputedStyle returns percent when specified for top/left/bottom/right
	// rather than make the css module depend on the offset module, we just check for it here
	if ( !jQuery.support.pixelPosition && jQuery.fn.position ) {
		jQuery.each( [ "top", "left" ], function( i, prop ) {
			jQuery.cssHooks[ prop ] = {
				get: function( elem, computed ) {
					if ( computed ) {
						computed = curCSS( elem, prop );
						// if curCSS returns percentage, fallback to offset
						return rnumnonpx.test( computed ) ?
							jQuery( elem ).position()[ prop ] + "px" :
							computed;
					}
				}
			};
		});
	}

});

if ( jQuery.expr && jQuery.expr.filters ) {
	jQuery.expr.filters.hidden = function( elem ) {
		// Support: Opera <= 12.12
		// Opera reports offsetWidths and offsetHeights less than zero on some elements
		return elem.offsetWidth <= 0 && elem.offsetHeight <= 0;
	};

	jQuery.expr.filters.visible = function( elem ) {
		return !jQuery.expr.filters.hidden( elem );
	};
}

// These hooks are used by animate to expand properties
jQuery.each({
	margin: "",
	padding: "",
	border: "Width"
}, function( prefix, suffix ) {
	jQuery.cssHooks[ prefix + suffix ] = {
		expand: function( value ) {
			var i = 0,
				expanded = {},

				// assumes a single number if not a string
				parts = typeof value === "string" ? value.split(" ") : [ value ];

			for ( ; i < 4; i++ ) {
				expanded[ prefix + cssExpand[ i ] + suffix ] =
					parts[ i ] || parts[ i - 2 ] || parts[ 0 ];
			}

			return expanded;
		}
	};

	if ( !rmargin.test( prefix ) ) {
		jQuery.cssHooks[ prefix + suffix ].set = setPositiveNumber;
	}
});
var r20 = /%20/g,
	rbracket = /\[\]$/,
	rCRLF = /\r?\n/g,
	rsubmitterTypes = /^(?:submit|button|image|reset|file)$/i,
	rsubmittable = /^(?:input|select|textarea|keygen)/i;

jQuery.fn.extend({
	serialize: function() {
		return jQuery.param( this.serializeArray() );
	},
	serializeArray: function() {
		return this.map(function(){
			// Can add propHook for "elements" to filter or add form elements
			var elements = jQuery.prop( this, "elements" );
			return elements ? jQuery.makeArray( elements ) : this;
		})
		.filter(function(){
			var type = this.type;
			// Use .is(":disabled") so that fieldset[disabled] works
			return this.name && !jQuery( this ).is( ":disabled" ) &&
				rsubmittable.test( this.nodeName ) && !rsubmitterTypes.test( type ) &&
				( this.checked || !manipulation_rcheckableType.test( type ) );
		})
		.map(function( i, elem ){
			var val = jQuery( this ).val();

			return val == null ?
				null :
				jQuery.isArray( val ) ?
					jQuery.map( val, function( val ){
						return { name: elem.name, value: val.replace( rCRLF, "\r\n" ) };
					}) :
					{ name: elem.name, value: val.replace( rCRLF, "\r\n" ) };
		}).get();
	}
});

//Serialize an array of form elements or a set of
//key/values into a query string
jQuery.param = function( a, traditional ) {
	var prefix,
		s = [],
		add = function( key, value ) {
			// If value is a function, invoke it and return its value
			value = jQuery.isFunction( value ) ? value() : ( value == null ? "" : value );
			s[ s.length ] = encodeURIComponent( key ) + "=" + encodeURIComponent( value );
		};

	// Set traditional to true for jQuery <= 1.3.2 behavior.
	if ( traditional === undefined ) {
		traditional = jQuery.ajaxSettings && jQuery.ajaxSettings.traditional;
	}

	// If an array was passed in, assume that it is an array of form elements.
	if ( jQuery.isArray( a ) || ( a.jquery && !jQuery.isPlainObject( a ) ) ) {
		// Serialize the form elements
		jQuery.each( a, function() {
			add( this.name, this.value );
		});

	} else {
		// If traditional, encode the "old" way (the way 1.3.2 or older
		// did it), otherwise encode params recursively.
		for ( prefix in a ) {
			buildParams( prefix, a[ prefix ], traditional, add );
		}
	}

	// Return the resulting serialization
	return s.join( "&" ).replace( r20, "+" );
};

function buildParams( prefix, obj, traditional, add ) {
	var name;

	if ( jQuery.isArray( obj ) ) {
		// Serialize array item.
		jQuery.each( obj, function( i, v ) {
			if ( traditional || rbracket.test( prefix ) ) {
				// Treat each array item as a scalar.
				add( prefix, v );

			} else {
				// Item is non-scalar (array or object), encode its numeric index.
				buildParams( prefix + "[" + ( typeof v === "object" ? i : "" ) + "]", v, traditional, add );
			}
		});

	} else if ( !traditional && jQuery.type( obj ) === "object" ) {
		// Serialize object item.
		for ( name in obj ) {
			buildParams( prefix + "[" + name + "]", obj[ name ], traditional, add );
		}

	} else {
		// Serialize scalar item.
		add( prefix, obj );
	}
}
jQuery.each( ("blur focus focusin focusout load resize scroll unload click dblclick " +
	"mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave " +
	"change select submit keydown keypress keyup error contextmenu").split(" "), function( i, name ) {

	// Handle event binding
	jQuery.fn[ name ] = function( data, fn ) {
		return arguments.length > 0 ?
			this.on( name, null, data, fn ) :
			this.trigger( name );
	};
});

jQuery.fn.extend({
	hover: function( fnOver, fnOut ) {
		return this.mouseenter( fnOver ).mouseleave( fnOut || fnOver );
	},

	bind: function( types, data, fn ) {
		return this.on( types, null, data, fn );
	},
	unbind: function( types, fn ) {
		return this.off( types, null, fn );
	},

	delegate: function( selector, types, data, fn ) {
		return this.on( types, selector, data, fn );
	},
	undelegate: function( selector, types, fn ) {
		// ( namespace ) or ( selector, types [, fn] )
		return arguments.length === 1 ? this.off( selector, "**" ) : this.off( types, selector || "**", fn );
	}
});
var
	// Document location
	ajaxLocParts,
	ajaxLocation,

	ajax_nonce = jQuery.now(),

	ajax_rquery = /\?/,
	rhash = /#.*$/,
	rts = /([?&])_=[^&]*/,
	rheaders = /^(.*?):[ \t]*([^\r\n]*)$/mg,
	// #7653, #8125, #8152: local protocol detection
	rlocalProtocol = /^(?:about|app|app-storage|.+-extension|file|res|widget):$/,
	rnoContent = /^(?:GET|HEAD)$/,
	rprotocol = /^\/\//,
	rurl = /^([\w.+-]+:)(?:\/\/([^\/?#:]*)(?::(\d+)|)|)/,

	// Keep a copy of the old load method
	_load = jQuery.fn.load,

	/* Prefilters
	 * 1) They are useful to introduce custom dataTypes (see ajax/jsonp.js for an example)
	 * 2) These are called:
	 *    - BEFORE asking for a transport
	 *    - AFTER param serialization (s.data is a string if s.processData is true)
	 * 3) key is the dataType
	 * 4) the catchall symbol "*" can be used
	 * 5) execution will start with transport dataType and THEN continue down to "*" if needed
	 */
	prefilters = {},

	/* Transports bindings
	 * 1) key is the dataType
	 * 2) the catchall symbol "*" can be used
	 * 3) selection will start with transport dataType and THEN go to "*" if needed
	 */
	transports = {},

	// Avoid comment-prolog char sequence (#10098); must appease lint and evade compression
	allTypes = "*/".concat("*");

// #8138, IE may throw an exception when accessing
// a field from window.location if document.domain has been set
try {
	ajaxLocation = location.href;
} catch( e ) {
	// Use the href attribute of an A element
	// since IE will modify it given document.location
	ajaxLocation = document.createElement( "a" );
	ajaxLocation.href = "";
	ajaxLocation = ajaxLocation.href;
}

// Segment location into parts
ajaxLocParts = rurl.exec( ajaxLocation.toLowerCase() ) || [];

// Base "constructor" for jQuery.ajaxPrefilter and jQuery.ajaxTransport
function addToPrefiltersOrTransports( structure ) {

	// dataTypeExpression is optional and defaults to "*"
	return function( dataTypeExpression, func ) {

		if ( typeof dataTypeExpression !== "string" ) {
			func = dataTypeExpression;
			dataTypeExpression = "*";
		}

		var dataType,
			i = 0,
			dataTypes = dataTypeExpression.toLowerCase().match( core_rnotwhite ) || [];

		if ( jQuery.isFunction( func ) ) {
			// For each dataType in the dataTypeExpression
			while ( (dataType = dataTypes[i++]) ) {
				// Prepend if requested
				if ( dataType[0] === "+" ) {
					dataType = dataType.slice( 1 ) || "*";
					(structure[ dataType ] = structure[ dataType ] || []).unshift( func );

				// Otherwise append
				} else {
					(structure[ dataType ] = structure[ dataType ] || []).push( func );
				}
			}
		}
	};
}

// Base inspection function for prefilters and transports
function inspectPrefiltersOrTransports( structure, options, originalOptions, jqXHR ) {

	var inspected = {},
		seekingTransport = ( structure === transports );

	function inspect( dataType ) {
		var selected;
		inspected[ dataType ] = true;
		jQuery.each( structure[ dataType ] || [], function( _, prefilterOrFactory ) {
			var dataTypeOrTransport = prefilterOrFactory( options, originalOptions, jqXHR );
			if( typeof dataTypeOrTransport === "string" && !seekingTransport && !inspected[ dataTypeOrTransport ] ) {
				options.dataTypes.unshift( dataTypeOrTransport );
				inspect( dataTypeOrTransport );
				return false;
			} else if ( seekingTransport ) {
				return !( selected = dataTypeOrTransport );
			}
		});
		return selected;
	}

	return inspect( options.dataTypes[ 0 ] ) || !inspected[ "*" ] && inspect( "*" );
}

// A special extend for ajax options
// that takes "flat" options (not to be deep extended)
// Fixes #9887
function ajaxExtend( target, src ) {
	var key, deep,
		flatOptions = jQuery.ajaxSettings.flatOptions || {};

	for ( key in src ) {
		if ( src[ key ] !== undefined ) {
			( flatOptions[ key ] ? target : ( deep || (deep = {}) ) )[ key ] = src[ key ];
		}
	}
	if ( deep ) {
		jQuery.extend( true, target, deep );
	}

	return target;
}

jQuery.fn.load = function( url, params, callback ) {
	if ( typeof url !== "string" && _load ) {
		return _load.apply( this, arguments );
	}

	var selector, type, response,
		self = this,
		off = url.indexOf(" ");

	if ( off >= 0 ) {
		selector = url.slice( off );
		url = url.slice( 0, off );
	}

	// If it's a function
	if ( jQuery.isFunction( params ) ) {

		// We assume that it's the callback
		callback = params;
		params = undefined;

	// Otherwise, build a param string
	} else if ( params && typeof params === "object" ) {
		type = "POST";
	}

	// If we have elements to modify, make the request
	if ( self.length > 0 ) {
		jQuery.ajax({
			url: url,

			// if "type" variable is undefined, then "GET" method will be used
			type: type,
			dataType: "html",
			data: params
		}).done(function( responseText ) {

			// Save response for use in complete callback
			response = arguments;

			self.html( selector ?

				// If a selector was specified, locate the right elements in a dummy div
				// Exclude scripts to avoid IE 'Permission Denied' errors
				jQuery("<div>").append( jQuery.parseHTML( responseText ) ).find( selector ) :

				// Otherwise use the full result
				responseText );

		}).complete( callback && function( jqXHR, status ) {
			self.each( callback, response || [ jqXHR.responseText, status, jqXHR ] );
		});
	}

	return this;
};

// Attach a bunch of functions for handling common AJAX events
jQuery.each( [ "ajaxStart", "ajaxStop", "ajaxComplete", "ajaxError", "ajaxSuccess", "ajaxSend" ], function( i, type ){
	jQuery.fn[ type ] = function( fn ){
		return this.on( type, fn );
	};
});

jQuery.extend({

	// Counter for holding the number of active queries
	active: 0,

	// Last-Modified header cache for next request
	lastModified: {},
	etag: {},

	ajaxSettings: {
		url: ajaxLocation,
		type: "GET",
		isLocal: rlocalProtocol.test( ajaxLocParts[ 1 ] ),
		global: true,
		processData: true,
		async: true,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		/*
		timeout: 0,
		data: null,
		dataType: null,
		username: null,
		password: null,
		cache: null,
		throws: false,
		traditional: false,
		headers: {},
		*/

		accepts: {
			"*": allTypes,
			text: "text/plain",
			html: "text/html",
			xml: "application/xml, text/xml",
			json: "application/json, text/javascript"
		},

		contents: {
			xml: /xml/,
			html: /html/,
			json: /json/
		},

		responseFields: {
			xml: "responseXML",
			text: "responseText",
			json: "responseJSON"
		},

		// Data converters
		// Keys separate source (or catchall "*") and destination types with a single space
		converters: {

			// Convert anything to text
			"* text": String,

			// Text to html (true = no transformation)
			"text html": true,

			// Evaluate text as a json expression
			"text json": jQuery.parseJSON,

			// Parse text as xml
			"text xml": jQuery.parseXML
		},

		// For options that shouldn't be deep extended:
		// you can add your own custom options here if
		// and when you create one that shouldn't be
		// deep extended (see ajaxExtend)
		flatOptions: {
			url: true,
			context: true
		}
	},

	// Creates a full fledged settings object into target
	// with both ajaxSettings and settings fields.
	// If target is omitted, writes into ajaxSettings.
	ajaxSetup: function( target, settings ) {
		return settings ?

			// Building a settings object
			ajaxExtend( ajaxExtend( target, jQuery.ajaxSettings ), settings ) :

			// Extending ajaxSettings
			ajaxExtend( jQuery.ajaxSettings, target );
	},

	ajaxPrefilter: addToPrefiltersOrTransports( prefilters ),
	ajaxTransport: addToPrefiltersOrTransports( transports ),

	// Main method
	ajax: function( url, options ) {

		// If url is an object, simulate pre-1.5 signature
		if ( typeof url === "object" ) {
			options = url;
			url = undefined;
		}

		// Force options to be an object
		options = options || {};

		var transport,
			// URL without anti-cache param
			cacheURL,
			// Response headers
			responseHeadersString,
			responseHeaders,
			// timeout handle
			timeoutTimer,
			// Cross-domain detection vars
			parts,
			// To know if global events are to be dispatched
			fireGlobals,
			// Loop variable
			i,
			// Create the final options object
			s = jQuery.ajaxSetup( {}, options ),
			// Callbacks context
			callbackContext = s.context || s,
			// Context for global events is callbackContext if it is a DOM node or jQuery collection
			globalEventContext = s.context && ( callbackContext.nodeType || callbackContext.jquery ) ?
				jQuery( callbackContext ) :
				jQuery.event,
			// Deferreds
			deferred = jQuery.Deferred(),
			completeDeferred = jQuery.Callbacks("once memory"),
			// Status-dependent callbacks
			statusCode = s.statusCode || {},
			// Headers (they are sent all at once)
			requestHeaders = {},
			requestHeadersNames = {},
			// The jqXHR state
			state = 0,
			// Default abort message
			strAbort = "canceled",
			// Fake xhr
			jqXHR = {
				readyState: 0,

				// Builds headers hashtable if needed
				getResponseHeader: function( key ) {
					var match;
					if ( state === 2 ) {
						if ( !responseHeaders ) {
							responseHeaders = {};
							while ( (match = rheaders.exec( responseHeadersString )) ) {
								responseHeaders[ match[1].toLowerCase() ] = match[ 2 ];
							}
						}
						match = responseHeaders[ key.toLowerCase() ];
					}
					return match == null ? null : match;
				},

				// Raw string
				getAllResponseHeaders: function() {
					return state === 2 ? responseHeadersString : null;
				},

				// Caches the header
				setRequestHeader: function( name, value ) {
					var lname = name.toLowerCase();
					if ( !state ) {
						name = requestHeadersNames[ lname ] = requestHeadersNames[ lname ] || name;
						requestHeaders[ name ] = value;
					}
					return this;
				},

				// Overrides response content-type header
				overrideMimeType: function( type ) {
					if ( !state ) {
						s.mimeType = type;
					}
					return this;
				},

				// Status-dependent callbacks
				statusCode: function( map ) {
					var code;
					if ( map ) {
						if ( state < 2 ) {
							for ( code in map ) {
								// Lazy-add the new callback in a way that preserves old ones
								statusCode[ code ] = [ statusCode[ code ], map[ code ] ];
							}
						} else {
							// Execute the appropriate callbacks
							jqXHR.always( map[ jqXHR.status ] );
						}
					}
					return this;
				},

				// Cancel the request
				abort: function( statusText ) {
					var finalText = statusText || strAbort;
					if ( transport ) {
						transport.abort( finalText );
					}
					done( 0, finalText );
					return this;
				}
			};

		// Attach deferreds
		deferred.promise( jqXHR ).complete = completeDeferred.add;
		jqXHR.success = jqXHR.done;
		jqXHR.error = jqXHR.fail;

		// Remove hash character (#7531: and string promotion)
		// Add protocol if not provided (prefilters might expect it)
		// Handle falsy url in the settings object (#10093: consistency with old signature)
		// We also use the url parameter if available
		s.url = ( ( url || s.url || ajaxLocation ) + "" ).replace( rhash, "" )
			.replace( rprotocol, ajaxLocParts[ 1 ] + "//" );

		// Alias method option to type as per ticket #12004
		s.type = options.method || options.type || s.method || s.type;

		// Extract dataTypes list
		s.dataTypes = jQuery.trim( s.dataType || "*" ).toLowerCase().match( core_rnotwhite ) || [""];

		// A cross-domain request is in order when we have a protocol:host:port mismatch
		if ( s.crossDomain == null ) {
			parts = rurl.exec( s.url.toLowerCase() );
			s.crossDomain = !!( parts &&
				( parts[ 1 ] !== ajaxLocParts[ 1 ] || parts[ 2 ] !== ajaxLocParts[ 2 ] ||
					( parts[ 3 ] || ( parts[ 1 ] === "http:" ? "80" : "443" ) ) !==
						( ajaxLocParts[ 3 ] || ( ajaxLocParts[ 1 ] === "http:" ? "80" : "443" ) ) )
			);
		}

		// Convert data if not already a string
		if ( s.data && s.processData && typeof s.data !== "string" ) {
			s.data = jQuery.param( s.data, s.traditional );
		}

		// Apply prefilters
		inspectPrefiltersOrTransports( prefilters, s, options, jqXHR );

		// If request was aborted inside a prefilter, stop there
		if ( state === 2 ) {
			return jqXHR;
		}

		// We can fire global events as of now if asked to
		fireGlobals = s.global;

		// Watch for a new set of requests
		if ( fireGlobals && jQuery.active++ === 0 ) {
			jQuery.event.trigger("ajaxStart");
		}

		// Uppercase the type
		s.type = s.type.toUpperCase();

		// Determine if request has content
		s.hasContent = !rnoContent.test( s.type );

		// Save the URL in case we're toying with the If-Modified-Since
		// and/or If-None-Match header later on
		cacheURL = s.url;

		// More options handling for requests with no content
		if ( !s.hasContent ) {

			// If data is available, append data to url
			if ( s.data ) {
				cacheURL = ( s.url += ( ajax_rquery.test( cacheURL ) ? "&" : "?" ) + s.data );
				// #9682: remove data so that it's not used in an eventual retry
				delete s.data;
			}

			// Add anti-cache in url if needed
			if ( s.cache === false ) {
				s.url = rts.test( cacheURL ) ?

					// If there is already a '_' parameter, set its value
					cacheURL.replace( rts, "$1_=" + ajax_nonce++ ) :

					// Otherwise add one to the end
					cacheURL + ( ajax_rquery.test( cacheURL ) ? "&" : "?" ) + "_=" + ajax_nonce++;
			}
		}

		// Set the If-Modified-Since and/or If-None-Match header, if in ifModified mode.
		if ( s.ifModified ) {
			if ( jQuery.lastModified[ cacheURL ] ) {
				jqXHR.setRequestHeader( "If-Modified-Since", jQuery.lastModified[ cacheURL ] );
			}
			if ( jQuery.etag[ cacheURL ] ) {
				jqXHR.setRequestHeader( "If-None-Match", jQuery.etag[ cacheURL ] );
			}
		}

		// Set the correct header, if data is being sent
		if ( s.data && s.hasContent && s.contentType !== false || options.contentType ) {
			jqXHR.setRequestHeader( "Content-Type", s.contentType );
		}

		// Set the Accepts header for the server, depending on the dataType
		jqXHR.setRequestHeader(
			"Accept",
			s.dataTypes[ 0 ] && s.accepts[ s.dataTypes[0] ] ?
				s.accepts[ s.dataTypes[0] ] + ( s.dataTypes[ 0 ] !== "*" ? ", " + allTypes + "; q=0.01" : "" ) :
				s.accepts[ "*" ]
		);

		// Check for headers option
		for ( i in s.headers ) {
			jqXHR.setRequestHeader( i, s.headers[ i ] );
		}

		// Allow custom headers/mimetypes and early abort
		if ( s.beforeSend && ( s.beforeSend.call( callbackContext, jqXHR, s ) === false || state === 2 ) ) {
			// Abort if not done already and return
			return jqXHR.abort();
		}

		// aborting is no longer a cancellation
		strAbort = "abort";

		// Install callbacks on deferreds
		for ( i in { success: 1, error: 1, complete: 1 } ) {
			jqXHR[ i ]( s[ i ] );
		}

		// Get transport
		transport = inspectPrefiltersOrTransports( transports, s, options, jqXHR );

		// If no transport, we auto-abort
		if ( !transport ) {
			done( -1, "No Transport" );
		} else {
			jqXHR.readyState = 1;

			// Send global event
			if ( fireGlobals ) {
				globalEventContext.trigger( "ajaxSend", [ jqXHR, s ] );
			}
			// Timeout
			if ( s.async && s.timeout > 0 ) {
				timeoutTimer = setTimeout(function() {
					jqXHR.abort("timeout");
				}, s.timeout );
			}

			try {
				state = 1;
				transport.send( requestHeaders, done );
			} catch ( e ) {
				// Propagate exception as error if not done
				if ( state < 2 ) {
					done( -1, e );
				// Simply rethrow otherwise
				} else {
					throw e;
				}
			}
		}

		// Callback for when everything is done
		function done( status, nativeStatusText, responses, headers ) {
			var isSuccess, success, error, response, modified,
				statusText = nativeStatusText;

			// Called once
			if ( state === 2 ) {
				return;
			}

			// State is "done" now
			state = 2;

			// Clear timeout if it exists
			if ( timeoutTimer ) {
				clearTimeout( timeoutTimer );
			}

			// Dereference transport for early garbage collection
			// (no matter how long the jqXHR object will be used)
			transport = undefined;

			// Cache response headers
			responseHeadersString = headers || "";

			// Set readyState
			jqXHR.readyState = status > 0 ? 4 : 0;

			// Determine if successful
			isSuccess = status >= 200 && status < 300 || status === 304;

			// Get response data
			if ( responses ) {
				response = ajaxHandleResponses( s, jqXHR, responses );
			}

			// Convert no matter what (that way responseXXX fields are always set)
			response = ajaxConvert( s, response, jqXHR, isSuccess );

			// If successful, handle type chaining
			if ( isSuccess ) {

				// Set the If-Modified-Since and/or If-None-Match header, if in ifModified mode.
				if ( s.ifModified ) {
					modified = jqXHR.getResponseHeader("Last-Modified");
					if ( modified ) {
						jQuery.lastModified[ cacheURL ] = modified;
					}
					modified = jqXHR.getResponseHeader("etag");
					if ( modified ) {
						jQuery.etag[ cacheURL ] = modified;
					}
				}

				// if no content
				if ( status === 204 ) {
					statusText = "nocontent";

				// if not modified
				} else if ( status === 304 ) {
					statusText = "notmodified";

				// If we have data, let's convert it
				} else {
					statusText = response.state;
					success = response.data;
					error = response.error;
					isSuccess = !error;
				}
			} else {
				// We extract error from statusText
				// then normalize statusText and status for non-aborts
				error = statusText;
				if ( status || !statusText ) {
					statusText = "error";
					if ( status < 0 ) {
						status = 0;
					}
				}
			}

			// Set data for the fake xhr object
			jqXHR.status = status;
			jqXHR.statusText = ( nativeStatusText || statusText ) + "";

			// Success/Error
			if ( isSuccess ) {
				deferred.resolveWith( callbackContext, [ success, statusText, jqXHR ] );
			} else {
				deferred.rejectWith( callbackContext, [ jqXHR, statusText, error ] );
			}

			// Status-dependent callbacks
			jqXHR.statusCode( statusCode );
			statusCode = undefined;

			if ( fireGlobals ) {
				globalEventContext.trigger( isSuccess ? "ajaxSuccess" : "ajaxError",
					[ jqXHR, s, isSuccess ? success : error ] );
			}

			// Complete
			completeDeferred.fireWith( callbackContext, [ jqXHR, statusText ] );

			if ( fireGlobals ) {
				globalEventContext.trigger( "ajaxComplete", [ jqXHR, s ] );
				// Handle the global AJAX counter
				if ( !( --jQuery.active ) ) {
					jQuery.event.trigger("ajaxStop");
				}
			}
		}

		return jqXHR;
	},

	getJSON: function( url, data, callback ) {
		return jQuery.get( url, data, callback, "json" );
	},

	getScript: function( url, callback ) {
		return jQuery.get( url, undefined, callback, "script" );
	}
});

jQuery.each( [ "get", "post" ], function( i, method ) {
	jQuery[ method ] = function( url, data, callback, type ) {
		// shift arguments if data argument was omitted
		if ( jQuery.isFunction( data ) ) {
			type = type || callback;
			callback = data;
			data = undefined;
		}

		return jQuery.ajax({
			url: url,
			type: method,
			dataType: type,
			data: data,
			success: callback
		});
	};
});

/* Handles responses to an ajax request:
 * - finds the right dataType (mediates between content-type and expected dataType)
 * - returns the corresponding response
 */
function ajaxHandleResponses( s, jqXHR, responses ) {

	var ct, type, finalDataType, firstDataType,
		contents = s.contents,
		dataTypes = s.dataTypes;

	// Remove auto dataType and get content-type in the process
	while( dataTypes[ 0 ] === "*" ) {
		dataTypes.shift();
		if ( ct === undefined ) {
			ct = s.mimeType || jqXHR.getResponseHeader("Content-Type");
		}
	}

	// Check if we're dealing with a known content-type
	if ( ct ) {
		for ( type in contents ) {
			if ( contents[ type ] && contents[ type ].test( ct ) ) {
				dataTypes.unshift( type );
				break;
			}
		}
	}

	// Check to see if we have a response for the expected dataType
	if ( dataTypes[ 0 ] in responses ) {
		finalDataType = dataTypes[ 0 ];
	} else {
		// Try convertible dataTypes
		for ( type in responses ) {
			if ( !dataTypes[ 0 ] || s.converters[ type + " " + dataTypes[0] ] ) {
				finalDataType = type;
				break;
			}
			if ( !firstDataType ) {
				firstDataType = type;
			}
		}
		// Or just use first one
		finalDataType = finalDataType || firstDataType;
	}

	// If we found a dataType
	// We add the dataType to the list if needed
	// and return the corresponding response
	if ( finalDataType ) {
		if ( finalDataType !== dataTypes[ 0 ] ) {
			dataTypes.unshift( finalDataType );
		}
		return responses[ finalDataType ];
	}
}

/* Chain conversions given the request and the original response
 * Also sets the responseXXX fields on the jqXHR instance
 */
function ajaxConvert( s, response, jqXHR, isSuccess ) {
	var conv2, current, conv, tmp, prev,
		converters = {},
		// Work with a copy of dataTypes in case we need to modify it for conversion
		dataTypes = s.dataTypes.slice();

	// Create converters map with lowercased keys
	if ( dataTypes[ 1 ] ) {
		for ( conv in s.converters ) {
			converters[ conv.toLowerCase() ] = s.converters[ conv ];
		}
	}

	current = dataTypes.shift();

	// Convert to each sequential dataType
	while ( current ) {

		if ( s.responseFields[ current ] ) {
			jqXHR[ s.responseFields[ current ] ] = response;
		}

		// Apply the dataFilter if provided
		if ( !prev && isSuccess && s.dataFilter ) {
			response = s.dataFilter( response, s.dataType );
		}

		prev = current;
		current = dataTypes.shift();

		if ( current ) {

		// There's only work to do if current dataType is non-auto
			if ( current === "*" ) {

				current = prev;

			// Convert response if prev dataType is non-auto and differs from current
			} else if ( prev !== "*" && prev !== current ) {

				// Seek a direct converter
				conv = converters[ prev + " " + current ] || converters[ "* " + current ];

				// If none found, seek a pair
				if ( !conv ) {
					for ( conv2 in converters ) {

						// If conv2 outputs current
						tmp = conv2.split( " " );
						if ( tmp[ 1 ] === current ) {

							// If prev can be converted to accepted input
							conv = converters[ prev + " " + tmp[ 0 ] ] ||
								converters[ "* " + tmp[ 0 ] ];
							if ( conv ) {
								// Condense equivalence converters
								if ( conv === true ) {
									conv = converters[ conv2 ];

								// Otherwise, insert the intermediate dataType
								} else if ( converters[ conv2 ] !== true ) {
									current = tmp[ 0 ];
									dataTypes.unshift( tmp[ 1 ] );
								}
								break;
							}
						}
					}
				}

				// Apply converter (if not an equivalence)
				if ( conv !== true ) {

					// Unless errors are allowed to bubble, catch and return them
					if ( conv && s[ "throws" ] ) {
						response = conv( response );
					} else {
						try {
							response = conv( response );
						} catch ( e ) {
							return { state: "parsererror", error: conv ? e : "No conversion from " + prev + " to " + current };
						}
					}
				}
			}
		}
	}

	return { state: "success", data: response };
}
// Install script dataType
jQuery.ajaxSetup({
	accepts: {
		script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"
	},
	contents: {
		script: /(?:java|ecma)script/
	},
	converters: {
		"text script": function( text ) {
			jQuery.globalEval( text );
			return text;
		}
	}
});

// Handle cache's special case and crossDomain
jQuery.ajaxPrefilter( "script", function( s ) {
	if ( s.cache === undefined ) {
		s.cache = false;
	}
	if ( s.crossDomain ) {
		s.type = "GET";
	}
});

// Bind script tag hack transport
jQuery.ajaxTransport( "script", function( s ) {
	// This transport only deals with cross domain requests
	if ( s.crossDomain ) {
		var script, callback;
		return {
			send: function( _, complete ) {
				script = jQuery("<script>").prop({
					async: true,
					charset: s.scriptCharset,
					src: s.url
				}).on(
					"load error",
					callback = function( evt ) {
						script.remove();
						callback = null;
						if ( evt ) {
							complete( evt.type === "error" ? 404 : 200, evt.type );
						}
					}
				);
				document.head.appendChild( script[ 0 ] );
			},
			abort: function() {
				if ( callback ) {
					callback();
				}
			}
		};
	}
});
var oldCallbacks = [],
	rjsonp = /(=)\?(?=&|$)|\?\?/;

// Default jsonp settings
jQuery.ajaxSetup({
	jsonp: "callback",
	jsonpCallback: function() {
		var callback = oldCallbacks.pop() || ( jQuery.expando + "_" + ( ajax_nonce++ ) );
		this[ callback ] = true;
		return callback;
	}
});

// Detect, normalize options and install callbacks for jsonp requests
jQuery.ajaxPrefilter( "json jsonp", function( s, originalSettings, jqXHR ) {

	var callbackName, overwritten, responseContainer,
		jsonProp = s.jsonp !== false && ( rjsonp.test( s.url ) ?
			"url" :
			typeof s.data === "string" && !( s.contentType || "" ).indexOf("application/x-www-form-urlencoded") && rjsonp.test( s.data ) && "data"
		);

	// Handle iff the expected data type is "jsonp" or we have a parameter to set
	if ( jsonProp || s.dataTypes[ 0 ] === "jsonp" ) {

		// Get callback name, remembering preexisting value associated with it
		callbackName = s.jsonpCallback = jQuery.isFunction( s.jsonpCallback ) ?
			s.jsonpCallback() :
			s.jsonpCallback;

		// Insert callback into url or form data
		if ( jsonProp ) {
			s[ jsonProp ] = s[ jsonProp ].replace( rjsonp, "$1" + callbackName );
		} else if ( s.jsonp !== false ) {
			s.url += ( ajax_rquery.test( s.url ) ? "&" : "?" ) + s.jsonp + "=" + callbackName;
		}

		// Use data converter to retrieve json after script execution
		s.converters["script json"] = function() {
			if ( !responseContainer ) {
				jQuery.error( callbackName + " was not called" );
			}
			return responseContainer[ 0 ];
		};

		// force json dataType
		s.dataTypes[ 0 ] = "json";

		// Install callback
		overwritten = window[ callbackName ];
		window[ callbackName ] = function() {
			responseContainer = arguments;
		};

		// Clean-up function (fires after converters)
		jqXHR.always(function() {
			// Restore preexisting value
			window[ callbackName ] = overwritten;

			// Save back as free
			if ( s[ callbackName ] ) {
				// make sure that re-using the options doesn't screw things around
				s.jsonpCallback = originalSettings.jsonpCallback;

				// save the callback name for future use
				oldCallbacks.push( callbackName );
			}

			// Call if it was a function and we have a response
			if ( responseContainer && jQuery.isFunction( overwritten ) ) {
				overwritten( responseContainer[ 0 ] );
			}

			responseContainer = overwritten = undefined;
		});

		// Delegate to script
		return "script";
	}
});
jQuery.ajaxSettings.xhr = function() {
	try {
		return new XMLHttpRequest();
	} catch( e ) {}
};

var xhrSupported = jQuery.ajaxSettings.xhr(),
	xhrSuccessStatus = {
		// file protocol always yields status code 0, assume 200
		0: 200,
		// Support: IE9
		// #1450: sometimes IE returns 1223 when it should be 204
		1223: 204
	},
	// Support: IE9
	// We need to keep track of outbound xhr and abort them manually
	// because IE is not smart enough to do it all by itself
	xhrId = 0,
	xhrCallbacks = {};

if ( window.ActiveXObject ) {
	jQuery( window ).on( "unload", function() {
		for( var key in xhrCallbacks ) {
			xhrCallbacks[ key ]();
		}
		xhrCallbacks = undefined;
	});
}

jQuery.support.cors = !!xhrSupported && ( "withCredentials" in xhrSupported );
jQuery.support.ajax = xhrSupported = !!xhrSupported;

jQuery.ajaxTransport(function( options ) {
	var callback;
	// Cross domain only allowed if supported through XMLHttpRequest
	if ( jQuery.support.cors || xhrSupported && !options.crossDomain ) {
		return {
			send: function( headers, complete ) {
				var i, id,
					xhr = options.xhr();
				xhr.open( options.type, options.url, options.async, options.username, options.password );
				// Apply custom fields if provided
				if ( options.xhrFields ) {
					for ( i in options.xhrFields ) {
						xhr[ i ] = options.xhrFields[ i ];
					}
				}
				// Override mime type if needed
				if ( options.mimeType && xhr.overrideMimeType ) {
					xhr.overrideMimeType( options.mimeType );
				}
				// X-Requested-With header
				// For cross-domain requests, seeing as conditions for a preflight are
				// akin to a jigsaw puzzle, we simply never set it to be sure.
				// (it can always be set on a per-request basis or even using ajaxSetup)
				// For same-domain requests, won't change header if already provided.
				if ( !options.crossDomain && !headers["X-Requested-With"] ) {
					headers["X-Requested-With"] = "XMLHttpRequest";
				}
				// Set headers
				for ( i in headers ) {
					xhr.setRequestHeader( i, headers[ i ] );
				}
				// Callback
				callback = function( type ) {
					return function() {
						if ( callback ) {
							delete xhrCallbacks[ id ];
							callback = xhr.onload = xhr.onerror = null;
							if ( type === "abort" ) {
								xhr.abort();
							} else if ( type === "error" ) {
								complete(
									// file protocol always yields status 0, assume 404
									xhr.status || 404,
									xhr.statusText
								);
							} else {
								complete(
									xhrSuccessStatus[ xhr.status ] || xhr.status,
									xhr.statusText,
									// Support: IE9
									// #11426: When requesting binary data, IE9 will throw an exception
									// on any attempt to access responseText
									typeof xhr.responseText === "string" ? {
										text: xhr.responseText
									} : undefined,
									xhr.getAllResponseHeaders()
								);
							}
						}
					};
				};
				// Listen to events
				xhr.onload = callback();
				xhr.onerror = callback("error");
				// Create the abort callback
				callback = xhrCallbacks[( id = xhrId++ )] = callback("abort");
				// Do send the request
				// This may raise an exception which is actually
				// handled in jQuery.ajax (so no try/catch here)
				xhr.send( options.hasContent && options.data || null );
			},
			abort: function() {
				if ( callback ) {
					callback();
				}
			}
		};
	}
});
var fxNow, timerId,
	rfxtypes = /^(?:toggle|show|hide)$/,
	rfxnum = new RegExp( "^(?:([+-])=|)(" + core_pnum + ")([a-z%]*)$", "i" ),
	rrun = /queueHooks$/,
	animationPrefilters = [ defaultPrefilter ],
	tweeners = {
		"*": [function( prop, value ) {
			var end, unit,
				tween = this.createTween( prop, value ),
				parts = rfxnum.exec( value ),
				target = tween.cur(),
				start = +target || 0,
				scale = 1,
				maxIterations = 20;

			if ( parts ) {
				end = +parts[2];
				unit = parts[3] || ( jQuery.cssNumber[ prop ] ? "" : "px" );

				// We need to compute starting value
				if ( unit !== "px" && start ) {
					// Iteratively approximate from a nonzero starting point
					// Prefer the current property, because this process will be trivial if it uses the same units
					// Fallback to end or a simple constant
					start = jQuery.css( tween.elem, prop, true ) || end || 1;

					do {
						// If previous iteration zeroed out, double until we get *something*
						// Use a string for doubling factor so we don't accidentally see scale as unchanged below
						scale = scale || ".5";

						// Adjust and apply
						start = start / scale;
						jQuery.style( tween.elem, prop, start + unit );

					// Update scale, tolerating zero or NaN from tween.cur()
					// And breaking the loop if scale is unchanged or perfect, or if we've just had enough
					} while ( scale !== (scale = tween.cur() / target) && scale !== 1 && --maxIterations );
				}

				tween.unit = unit;
				tween.start = start;
				// If a +=/-= token was provided, we're doing a relative animation
				tween.end = parts[1] ? start + ( parts[1] + 1 ) * end : end;
			}
			return tween;
		}]
	};

// Animations created synchronously will run synchronously
function createFxNow() {
	setTimeout(function() {
		fxNow = undefined;
	});
	return ( fxNow = jQuery.now() );
}

function createTweens( animation, props ) {
	jQuery.each( props, function( prop, value ) {
		var collection = ( tweeners[ prop ] || [] ).concat( tweeners[ "*" ] ),
			index = 0,
			length = collection.length;
		for ( ; index < length; index++ ) {
			if ( collection[ index ].call( animation, prop, value ) ) {

				// we're done with this property
				return;
			}
		}
	});
}

function Animation( elem, properties, options ) {
	var result,
		stopped,
		index = 0,
		length = animationPrefilters.length,
		deferred = jQuery.Deferred().always( function() {
			// don't match elem in the :animated selector
			delete tick.elem;
		}),
		tick = function() {
			if ( stopped ) {
				return false;
			}
			var currentTime = fxNow || createFxNow(),
				remaining = Math.max( 0, animation.startTime + animation.duration - currentTime ),
				// archaic crash bug won't allow us to use 1 - ( 0.5 || 0 ) (#12497)
				temp = remaining / animation.duration || 0,
				percent = 1 - temp,
				index = 0,
				length = animation.tweens.length;

			for ( ; index < length ; index++ ) {
				animation.tweens[ index ].run( percent );
			}

			deferred.notifyWith( elem, [ animation, percent, remaining ]);

			if ( percent < 1 && length ) {
				return remaining;
			} else {
				deferred.resolveWith( elem, [ animation ] );
				return false;
			}
		},
		animation = deferred.promise({
			elem: elem,
			props: jQuery.extend( {}, properties ),
			opts: jQuery.extend( true, { specialEasing: {} }, options ),
			originalProperties: properties,
			originalOptions: options,
			startTime: fxNow || createFxNow(),
			duration: options.duration,
			tweens: [],
			createTween: function( prop, end ) {
				var tween = jQuery.Tween( elem, animation.opts, prop, end,
						animation.opts.specialEasing[ prop ] || animation.opts.easing );
				animation.tweens.push( tween );
				return tween;
			},
			stop: function( gotoEnd ) {
				var index = 0,
					// if we are going to the end, we want to run all the tweens
					// otherwise we skip this part
					length = gotoEnd ? animation.tweens.length : 0;
				if ( stopped ) {
					return this;
				}
				stopped = true;
				for ( ; index < length ; index++ ) {
					animation.tweens[ index ].run( 1 );
				}

				// resolve when we played the last frame
				// otherwise, reject
				if ( gotoEnd ) {
					deferred.resolveWith( elem, [ animation, gotoEnd ] );
				} else {
					deferred.rejectWith( elem, [ animation, gotoEnd ] );
				}
				return this;
			}
		}),
		props = animation.props;

	propFilter( props, animation.opts.specialEasing );

	for ( ; index < length ; index++ ) {
		result = animationPrefilters[ index ].call( animation, elem, props, animation.opts );
		if ( result ) {
			return result;
		}
	}

	createTweens( animation, props );

	if ( jQuery.isFunction( animation.opts.start ) ) {
		animation.opts.start.call( elem, animation );
	}

	jQuery.fx.timer(
		jQuery.extend( tick, {
			elem: elem,
			anim: animation,
			queue: animation.opts.queue
		})
	);

	// attach callbacks from options
	return animation.progress( animation.opts.progress )
		.done( animation.opts.done, animation.opts.complete )
		.fail( animation.opts.fail )
		.always( animation.opts.always );
}

function propFilter( props, specialEasing ) {
	var index, name, easing, value, hooks;

	// camelCase, specialEasing and expand cssHook pass
	for ( index in props ) {
		name = jQuery.camelCase( index );
		easing = specialEasing[ name ];
		value = props[ index ];
		if ( jQuery.isArray( value ) ) {
			easing = value[ 1 ];
			value = props[ index ] = value[ 0 ];
		}

		if ( index !== name ) {
			props[ name ] = value;
			delete props[ index ];
		}

		hooks = jQuery.cssHooks[ name ];
		if ( hooks && "expand" in hooks ) {
			value = hooks.expand( value );
			delete props[ name ];

			// not quite $.extend, this wont overwrite keys already present.
			// also - reusing 'index' from above because we have the correct "name"
			for ( index in value ) {
				if ( !( index in props ) ) {
					props[ index ] = value[ index ];
					specialEasing[ index ] = easing;
				}
			}
		} else {
			specialEasing[ name ] = easing;
		}
	}
}

jQuery.Animation = jQuery.extend( Animation, {

	tweener: function( props, callback ) {
		if ( jQuery.isFunction( props ) ) {
			callback = props;
			props = [ "*" ];
		} else {
			props = props.split(" ");
		}

		var prop,
			index = 0,
			length = props.length;

		for ( ; index < length ; index++ ) {
			prop = props[ index ];
			tweeners[ prop ] = tweeners[ prop ] || [];
			tweeners[ prop ].unshift( callback );
		}
	},

	prefilter: function( callback, prepend ) {
		if ( prepend ) {
			animationPrefilters.unshift( callback );
		} else {
			animationPrefilters.push( callback );
		}
	}
});

function defaultPrefilter( elem, props, opts ) {
	/* jshint validthis: true */
	var index, prop, value, length, dataShow, toggle, tween, hooks, oldfire,
		anim = this,
		style = elem.style,
		orig = {},
		handled = [],
		hidden = elem.nodeType && isHidden( elem );

	// handle queue: false promises
	if ( !opts.queue ) {
		hooks = jQuery._queueHooks( elem, "fx" );
		if ( hooks.unqueued == null ) {
			hooks.unqueued = 0;
			oldfire = hooks.empty.fire;
			hooks.empty.fire = function() {
				if ( !hooks.unqueued ) {
					oldfire();
				}
			};
		}
		hooks.unqueued++;

		anim.always(function() {
			// doing this makes sure that the complete handler will be called
			// before this completes
			anim.always(function() {
				hooks.unqueued--;
				if ( !jQuery.queue( elem, "fx" ).length ) {
					hooks.empty.fire();
				}
			});
		});
	}

	// height/width overflow pass
	if ( elem.nodeType === 1 && ( "height" in props || "width" in props ) ) {
		// Make sure that nothing sneaks out
		// Record all 3 overflow attributes because IE9-10 do not
		// change the overflow attribute when overflowX and
		// overflowY are set to the same value
		opts.overflow = [ style.overflow, style.overflowX, style.overflowY ];

		// Set display property to inline-block for height/width
		// animations on inline elements that are having width/height animated
		if ( jQuery.css( elem, "display" ) === "inline" &&
				jQuery.css( elem, "float" ) === "none" ) {

			style.display = "inline-block";
		}
	}

	if ( opts.overflow ) {
		style.overflow = "hidden";
		anim.always(function() {
			style.overflow = opts.overflow[ 0 ];
			style.overflowX = opts.overflow[ 1 ];
			style.overflowY = opts.overflow[ 2 ];
		});
	}


	// show/hide pass
	dataShow = data_priv.get( elem, "fxshow" );
	for ( index in props ) {
		value = props[ index ];
		if ( rfxtypes.exec( value ) ) {
			delete props[ index ];
			toggle = toggle || value === "toggle";
			if ( value === ( hidden ? "hide" : "show" ) ) {

				// If there is dataShow left over from a stopped hide or show and we are going to proceed with show, we should pretend to be hidden
				if( value === "show" && dataShow !== undefined && dataShow[ index ] !== undefined ) {
					hidden = true;
				} else {
					continue;
				}
			}
			handled.push( index );
		}
	}

	length = handled.length;
	if ( length ) {
		dataShow = data_priv.get( elem, "fxshow" ) || data_priv.access( elem, "fxshow", {} );
		if ( "hidden" in dataShow ) {
			hidden = dataShow.hidden;
		}

		// store state if its toggle - enables .stop().toggle() to "reverse"
		if ( toggle ) {
			dataShow.hidden = !hidden;
		}
		if ( hidden ) {
			jQuery( elem ).show();
		} else {
			anim.done(function() {
				jQuery( elem ).hide();
			});
		}
		anim.done(function() {
			var prop;

			data_priv.remove( elem, "fxshow" );
			for ( prop in orig ) {
				jQuery.style( elem, prop, orig[ prop ] );
			}
		});
		for ( index = 0 ; index < length ; index++ ) {
			prop = handled[ index ];
			tween = anim.createTween( prop, hidden ? dataShow[ prop ] : 0 );
			orig[ prop ] = dataShow[ prop ] || jQuery.style( elem, prop );

			if ( !( prop in dataShow ) ) {
				dataShow[ prop ] = tween.start;
				if ( hidden ) {
					tween.end = tween.start;
					tween.start = prop === "width" || prop === "height" ? 1 : 0;
				}
			}
		}
	}
}

function Tween( elem, options, prop, end, easing ) {
	return new Tween.prototype.init( elem, options, prop, end, easing );
}
jQuery.Tween = Tween;

Tween.prototype = {
	constructor: Tween,
	init: function( elem, options, prop, end, easing, unit ) {
		this.elem = elem;
		this.prop = prop;
		this.easing = easing || "swing";
		this.options = options;
		this.start = this.now = this.cur();
		this.end = end;
		this.unit = unit || ( jQuery.cssNumber[ prop ] ? "" : "px" );
	},
	cur: function() {
		var hooks = Tween.propHooks[ this.prop ];

		return hooks && hooks.get ?
			hooks.get( this ) :
			Tween.propHooks._default.get( this );
	},
	run: function( percent ) {
		var eased,
			hooks = Tween.propHooks[ this.prop ];

		if ( this.options.duration ) {
			this.pos = eased = jQuery.easing[ this.easing ](
				percent, this.options.duration * percent, 0, 1, this.options.duration
			);
		} else {
			this.pos = eased = percent;
		}
		this.now = ( this.end - this.start ) * eased + this.start;

		if ( this.options.step ) {
			this.options.step.call( this.elem, this.now, this );
		}

		if ( hooks && hooks.set ) {
			hooks.set( this );
		} else {
			Tween.propHooks._default.set( this );
		}
		return this;
	}
};

Tween.prototype.init.prototype = Tween.prototype;

Tween.propHooks = {
	_default: {
		get: function( tween ) {
			var result;

			if ( tween.elem[ tween.prop ] != null &&
				(!tween.elem.style || tween.elem.style[ tween.prop ] == null) ) {
				return tween.elem[ tween.prop ];
			}

			// passing an empty string as a 3rd parameter to .css will automatically
			// attempt a parseFloat and fallback to a string if the parse fails
			// so, simple values such as "10px" are parsed to Float.
			// complex values such as "rotate(1rad)" are returned as is.
			result = jQuery.css( tween.elem, tween.prop, "" );
			// Empty strings, null, undefined and "auto" are converted to 0.
			return !result || result === "auto" ? 0 : result;
		},
		set: function( tween ) {
			// use step hook for back compat - use cssHook if its there - use .style if its
			// available and use plain properties where available
			if ( jQuery.fx.step[ tween.prop ] ) {
				jQuery.fx.step[ tween.prop ]( tween );
			} else if ( tween.elem.style && ( tween.elem.style[ jQuery.cssProps[ tween.prop ] ] != null || jQuery.cssHooks[ tween.prop ] ) ) {
				jQuery.style( tween.elem, tween.prop, tween.now + tween.unit );
			} else {
				tween.elem[ tween.prop ] = tween.now;
			}
		}
	}
};

// Support: IE9
// Panic based approach to setting things on disconnected nodes

Tween.propHooks.scrollTop = Tween.propHooks.scrollLeft = {
	set: function( tween ) {
		if ( tween.elem.nodeType && tween.elem.parentNode ) {
			tween.elem[ tween.prop ] = tween.now;
		}
	}
};

jQuery.each([ "toggle", "show", "hide" ], function( i, name ) {
	var cssFn = jQuery.fn[ name ];
	jQuery.fn[ name ] = function( speed, easing, callback ) {
		return speed == null || typeof speed === "boolean" ?
			cssFn.apply( this, arguments ) :
			this.animate( genFx( name, true ), speed, easing, callback );
	};
});

jQuery.fn.extend({
	fadeTo: function( speed, to, easing, callback ) {

		// show any hidden elements after setting opacity to 0
		return this.filter( isHidden ).css( "opacity", 0 ).show()

			// animate to the value specified
			.end().animate({ opacity: to }, speed, easing, callback );
	},
	animate: function( prop, speed, easing, callback ) {
		var empty = jQuery.isEmptyObject( prop ),
			optall = jQuery.speed( speed, easing, callback ),
			doAnimation = function() {
				// Operate on a copy of prop so per-property easing won't be lost
				var anim = Animation( this, jQuery.extend( {}, prop ), optall );
				doAnimation.finish = function() {
					anim.stop( true );
				};
				// Empty animations, or finishing resolves immediately
				if ( empty || data_priv.get( this, "finish" ) ) {
					anim.stop( true );
				}
			};
			doAnimation.finish = doAnimation;

		return empty || optall.queue === false ?
			this.each( doAnimation ) :
			this.queue( optall.queue, doAnimation );
	},
	stop: function( type, clearQueue, gotoEnd ) {
		var stopQueue = function( hooks ) {
			var stop = hooks.stop;
			delete hooks.stop;
			stop( gotoEnd );
		};

		if ( typeof type !== "string" ) {
			gotoEnd = clearQueue;
			clearQueue = type;
			type = undefined;
		}
		if ( clearQueue && type !== false ) {
			this.queue( type || "fx", [] );
		}

		return this.each(function() {
			var dequeue = true,
				index = type != null && type + "queueHooks",
				timers = jQuery.timers,
				data = data_priv.get( this );

			if ( index ) {
				if ( data[ index ] && data[ index ].stop ) {
					stopQueue( data[ index ] );
				}
			} else {
				for ( index in data ) {
					if ( data[ index ] && data[ index ].stop && rrun.test( index ) ) {
						stopQueue( data[ index ] );
					}
				}
			}

			for ( index = timers.length; index--; ) {
				if ( timers[ index ].elem === this && (type == null || timers[ index ].queue === type) ) {
					timers[ index ].anim.stop( gotoEnd );
					dequeue = false;
					timers.splice( index, 1 );
				}
			}

			// start the next in the queue if the last step wasn't forced
			// timers currently will call their complete callbacks, which will dequeue
			// but only if they were gotoEnd
			if ( dequeue || !gotoEnd ) {
				jQuery.dequeue( this, type );
			}
		});
	},
	finish: function( type ) {
		if ( type !== false ) {
			type = type || "fx";
		}
		return this.each(function() {
			var index,
				data = data_priv.get( this ),
				queue = data[ type + "queue" ],
				hooks = data[ type + "queueHooks" ],
				timers = jQuery.timers,
				length = queue ? queue.length : 0;

			// enable finishing flag on private data
			data.finish = true;

			// empty the queue first
			jQuery.queue( this, type, [] );

			if ( hooks && hooks.cur && hooks.cur.finish ) {
				hooks.cur.finish.call( this );
			}

			// look for any active animations, and finish them
			for ( index = timers.length; index--; ) {
				if ( timers[ index ].elem === this && timers[ index ].queue === type ) {
					timers[ index ].anim.stop( true );
					timers.splice( index, 1 );
				}
			}

			// look for any animations in the old queue and finish them
			for ( index = 0; index < length; index++ ) {
				if ( queue[ index ] && queue[ index ].finish ) {
					queue[ index ].finish.call( this );
				}
			}

			// turn off finishing flag
			delete data.finish;
		});
	}
});

// Generate parameters to create a standard animation
function genFx( type, includeWidth ) {
	var which,
		attrs = { height: type },
		i = 0;

	// if we include width, step value is 1 to do all cssExpand values,
	// if we don't include width, step value is 2 to skip over Left and Right
	includeWidth = includeWidth? 1 : 0;
	for( ; i < 4 ; i += 2 - includeWidth ) {
		which = cssExpand[ i ];
		attrs[ "margin" + which ] = attrs[ "padding" + which ] = type;
	}

	if ( includeWidth ) {
		attrs.opacity = attrs.width = type;
	}

	return attrs;
}

// Generate shortcuts for custom animations
jQuery.each({
	slideDown: genFx("show"),
	slideUp: genFx("hide"),
	slideToggle: genFx("toggle"),
	fadeIn: { opacity: "show" },
	fadeOut: { opacity: "hide" },
	fadeToggle: { opacity: "toggle" }
}, function( name, props ) {
	jQuery.fn[ name ] = function( speed, easing, callback ) {
		return this.animate( props, speed, easing, callback );
	};
});

jQuery.speed = function( speed, easing, fn ) {
	var opt = speed && typeof speed === "object" ? jQuery.extend( {}, speed ) : {
		complete: fn || !fn && easing ||
			jQuery.isFunction( speed ) && speed,
		duration: speed,
		easing: fn && easing || easing && !jQuery.isFunction( easing ) && easing
	};

	opt.duration = jQuery.fx.off ? 0 : typeof opt.duration === "number" ? opt.duration :
		opt.duration in jQuery.fx.speeds ? jQuery.fx.speeds[ opt.duration ] : jQuery.fx.speeds._default;

	// normalize opt.queue - true/undefined/null -> "fx"
	if ( opt.queue == null || opt.queue === true ) {
		opt.queue = "fx";
	}

	// Queueing
	opt.old = opt.complete;

	opt.complete = function() {
		if ( jQuery.isFunction( opt.old ) ) {
			opt.old.call( this );
		}

		if ( opt.queue ) {
			jQuery.dequeue( this, opt.queue );
		}
	};

	return opt;
};

jQuery.easing = {
	linear: function( p ) {
		return p;
	},
	swing: function( p ) {
		return 0.5 - Math.cos( p*Math.PI ) / 2;
	}
};

jQuery.timers = [];
jQuery.fx = Tween.prototype.init;
jQuery.fx.tick = function() {
	var timer,
		timers = jQuery.timers,
		i = 0;

	fxNow = jQuery.now();

	for ( ; i < timers.length; i++ ) {
		timer = timers[ i ];
		// Checks the timer has not already been removed
		if ( !timer() && timers[ i ] === timer ) {
			timers.splice( i--, 1 );
		}
	}

	if ( !timers.length ) {
		jQuery.fx.stop();
	}
	fxNow = undefined;
};

jQuery.fx.timer = function( timer ) {
	if ( timer() && jQuery.timers.push( timer ) ) {
		jQuery.fx.start();
	}
};

jQuery.fx.interval = 13;

jQuery.fx.start = function() {
	if ( !timerId ) {
		timerId = setInterval( jQuery.fx.tick, jQuery.fx.interval );
	}
};

jQuery.fx.stop = function() {
	clearInterval( timerId );
	timerId = null;
};

jQuery.fx.speeds = {
	slow: 600,
	fast: 200,
	// Default speed
	_default: 400
};

// Back Compat <1.8 extension point
jQuery.fx.step = {};

if ( jQuery.expr && jQuery.expr.filters ) {
	jQuery.expr.filters.animated = function( elem ) {
		return jQuery.grep(jQuery.timers, function( fn ) {
			return elem === fn.elem;
		}).length;
	};
}
jQuery.fn.offset = function( options ) {
	if ( arguments.length ) {
		return options === undefined ?
			this :
			this.each(function( i ) {
				jQuery.offset.setOffset( this, options, i );
			});
	}

	var docElem, win,
		elem = this[ 0 ],
		box = { top: 0, left: 0 },
		doc = elem && elem.ownerDocument;

	if ( !doc ) {
		return;
	}

	docElem = doc.documentElement;

	// Make sure it's not a disconnected DOM node
	if ( !jQuery.contains( docElem, elem ) ) {
		return box;
	}

	// If we don't have gBCR, just use 0,0 rather than error
	// BlackBerry 5, iOS 3 (original iPhone)
	if ( typeof elem.getBoundingClientRect !== core_strundefined ) {
		box = elem.getBoundingClientRect();
	}
	win = getWindow( doc );
	return {
		top: box.top + win.pageYOffset - docElem.clientTop,
		left: box.left + win.pageXOffset - docElem.clientLeft
	};
};

jQuery.offset = {

	setOffset: function( elem, options, i ) {
		var curPosition, curLeft, curCSSTop, curTop, curOffset, curCSSLeft, calculatePosition,
			position = jQuery.css( elem, "position" ),
			curElem = jQuery( elem ),
			props = {};

		// Set position first, in-case top/left are set even on static elem
		if ( position === "static" ) {
			elem.style.position = "relative";
		}

		curOffset = curElem.offset();
		curCSSTop = jQuery.css( elem, "top" );
		curCSSLeft = jQuery.css( elem, "left" );
		calculatePosition = ( position === "absolute" || position === "fixed" ) && ( curCSSTop + curCSSLeft ).indexOf("auto") > -1;

		// Need to be able to calculate position if either top or left is auto and position is either absolute or fixed
		if ( calculatePosition ) {
			curPosition = curElem.position();
			curTop = curPosition.top;
			curLeft = curPosition.left;

		} else {
			curTop = parseFloat( curCSSTop ) || 0;
			curLeft = parseFloat( curCSSLeft ) || 0;
		}

		if ( jQuery.isFunction( options ) ) {
			options = options.call( elem, i, curOffset );
		}

		if ( options.top != null ) {
			props.top = ( options.top - curOffset.top ) + curTop;
		}
		if ( options.left != null ) {
			props.left = ( options.left - curOffset.left ) + curLeft;
		}

		if ( "using" in options ) {
			options.using.call( elem, props );

		} else {
			curElem.css( props );
		}
	}
};


jQuery.fn.extend({

	position: function() {
		if ( !this[ 0 ] ) {
			return;
		}

		var offsetParent, offset,
			elem = this[ 0 ],
			parentOffset = { top: 0, left: 0 };

		// Fixed elements are offset from window (parentOffset = {top:0, left: 0}, because it is it's only offset parent
		if ( jQuery.css( elem, "position" ) === "fixed" ) {
			// We assume that getBoundingClientRect is available when computed position is fixed
			offset = elem.getBoundingClientRect();

		} else {
			// Get *real* offsetParent
			offsetParent = this.offsetParent();

			// Get correct offsets
			offset = this.offset();
			if ( !jQuery.nodeName( offsetParent[ 0 ], "html" ) ) {
				parentOffset = offsetParent.offset();
			}

			// Add offsetParent borders
			parentOffset.top += jQuery.css( offsetParent[ 0 ], "borderTopWidth", true );
			parentOffset.left += jQuery.css( offsetParent[ 0 ], "borderLeftWidth", true );
		}

		// Subtract parent offsets and element margins
		return {
			top: offset.top - parentOffset.top - jQuery.css( elem, "marginTop", true ),
			left: offset.left - parentOffset.left - jQuery.css( elem, "marginLeft", true )
		};
	},

	offsetParent: function() {
		return this.map(function() {
			var offsetParent = this.offsetParent || docElem;

			while ( offsetParent && ( !jQuery.nodeName( offsetParent, "html" ) && jQuery.css( offsetParent, "position") === "static" ) ) {
				offsetParent = offsetParent.offsetParent;
			}

			return offsetParent || docElem;
		});
	}
});


// Create scrollLeft and scrollTop methods
jQuery.each( {scrollLeft: "pageXOffset", scrollTop: "pageYOffset"}, function( method, prop ) {
	var top = "pageYOffset" === prop;

	jQuery.fn[ method ] = function( val ) {
		return jQuery.access( this, function( elem, method, val ) {
			var win = getWindow( elem );

			if ( val === undefined ) {
				return win ? win[ prop ] : elem[ method ];
			}

			if ( win ) {
				win.scrollTo(
					!top ? val : window.pageXOffset,
					top ? val : window.pageYOffset
				);

			} else {
				elem[ method ] = val;
			}
		}, method, val, arguments.length, null );
	};
});

function getWindow( elem ) {
	return jQuery.isWindow( elem ) ? elem : elem.nodeType === 9 && elem.defaultView;
}
// Create innerHeight, innerWidth, height, width, outerHeight and outerWidth methods
jQuery.each( { Height: "height", Width: "width" }, function( name, type ) {
	jQuery.each( { padding: "inner" + name, content: type, "": "outer" + name }, function( defaultExtra, funcName ) {
		// margin is only for outerHeight, outerWidth
		jQuery.fn[ funcName ] = function( margin, value ) {
			var chainable = arguments.length && ( defaultExtra || typeof margin !== "boolean" ),
				extra = defaultExtra || ( margin === true || value === true ? "margin" : "border" );

			return jQuery.access( this, function( elem, type, value ) {
				var doc;

				if ( jQuery.isWindow( elem ) ) {
					// As of 5/8/2012 this will yield incorrect results for Mobile Safari, but there
					// isn't a whole lot we can do. See pull request at this URL for discussion:
					// https://github.com/jquery/jquery/pull/764
					return elem.document.documentElement[ "client" + name ];
				}

				// Get document width or height
				if ( elem.nodeType === 9 ) {
					doc = elem.documentElement;

					// Either scroll[Width/Height] or offset[Width/Height] or client[Width/Height],
					// whichever is greatest
					return Math.max(
						elem.body[ "scroll" + name ], doc[ "scroll" + name ],
						elem.body[ "offset" + name ], doc[ "offset" + name ],
						doc[ "client" + name ]
					);
				}

				return value === undefined ?
					// Get width or height on the element, requesting but not forcing parseFloat
					jQuery.css( elem, type, extra ) :

					// Set width or height on the element
					jQuery.style( elem, type, value, extra );
			}, type, chainable ? margin : undefined, chainable, null );
		};
	});
});
// Limit scope pollution from any deprecated API
// (function() {

// The number of elements contained in the matched element set
jQuery.fn.size = function() {
	return this.length;
};

jQuery.fn.andSelf = jQuery.fn.addBack;

// })();
if ( typeof module === "object" && typeof module.exports === "object" ) {
	// Expose jQuery as module.exports in loaders that implement the Node
	// module pattern (including browserify). Do not create the global, since
	// the user will be storing it themselves locally, and globals are frowned
	// upon in the Node module world.
	module.exports = jQuery;
} else {
	// Register as a named AMD module, since jQuery can be concatenated with other
	// files that may use define, but not via a proper concatenation script that
	// understands anonymous AMD modules. A named AMD is safest and most robust
	// way to register. Lowercase jquery is used because AMD module names are
	// derived from file names, and jQuery is normally delivered in a lowercase
	// file name. Do this after creating the global so that if an AMD module wants
	// to call noConflict to hide this version of jQuery, it will work.
	if ( typeof define === "function" && define.amd ) {
		define( "jquery", [], function () { return jQuery; } );
	}
}

// If there is a window object, that at least has a document property,
// define jQuery and $ identifiers
if ( typeof window === "object" && typeof window.document === "object" ) {
	window.jQuery = window.$ = jQuery;
}

})( window );
}
/* Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved. */
/*
 *
 * ADF Mobile v1.1 (iOS and Android)
 *
 * http://www.oracle.com/technetwork/developer-tools/adf/overview/adf-mobile-096323.html
 *
 * Copyright (c) 2011 Oracle.
 * All rights reserved.
 *
 */

try
{
    /* Helper code to resolve anonymous callback functions,

    If the function callback can be resolved by name it is returned unaltered.
    If the function is defined in an unknown scope and can't be resolved, an internal reference to the function is added to the internal map.

    Callbacks added to the map are one time use only, they will be deleted once called.

    example 1:
    function myCallback(){};
    fString = GetFunctionName(myCallback);
    - result, the function is defined in the global scope, and will be returned as is because it can be resolved by name.

    example 2:
    fString = GetFunctionName(function(){};);
    - result, the function is defined in place, so it will be returned unchanged.

    example 3:
    function myMethod()
    {
        var funk = function(){};
        fString = GetFunctionName(funk);
    }
    - result, the function CANNOT be resolved by name, so an internal reference wrapper is created and returned.
    */
   window._anomFunkMap = window._anomFunkMap || {};
   window._anomFunkMapNextId = window._anomFunkMapNextId || 0;

   window.anomToNameFunk = window.anomToNameFunk || function(fun)
   {
      var funkId = "f" + _anomFunkMapNextId++;
      var funk = function()
      {
         fun.apply(this,arguments);
         _anomFunkMap[funkId] = null;
         delete _anomFunkMap[funkId];
      }
      _anomFunkMap[funkId] = funk;

      return "_anomFunkMap." + funkId;
   };

   window.GetFunctionName = window.GetFunctionName || function(fn)
   {
      if (typeof fn === "function") {
         var name= fn.name;
         if (!name) {
            var m = fn.toString().match(/^\s*function\s+([^\s\(]+)/);
            name= m && m[1];
         }
         if (name && (window[name] === fn)) {
            return name;
         } else {
            return anomToNameFunk(fn);
         }
      }else {
         return null;
      }
   };

   // Page level API 'namespace' objects - 'window' is the same as
   // using 'var adf;'...
   window.adf = window.adf || {};
   window.adf.mf = window.adf.mf || {};
   window.adf.mf.internal = window.adf.mf.internal || {};
   window.adf.mf.internal.di = window.adf.mf.internal.di || {};
   window.adf.mf.internal.di.api = window.adf.mf.internal.di.api || {};
   window.getAdfmfApiRoot = function()
   {
      return adf.mf.internal.di.api;
   }
   // Location for all the adf.pg functions is container.internal.device.integration
   window.container = window.container || {};
   window.container.internal = window.container.internal || {};
   window.container.internal.device = window.container.internal.device || {};
   window.container.internal.device.integration = window.container.internal.device.integration || {};
   window.containerInternalRoot = window.containerInternalRoot || function()
   {
      return container.internal.device.integration;
   }
   window.containerInternalRootDescription = window.containerInternalRootDescription || function()
   {
      return "container.internal.device.integration";
   }
   containerInternalRoot().getAdfmfPhoneGap = containerInternalRoot().getAdfmfPhoneGap || function ()
   {
      if(typeof(Cordova) == 'undefined')
      {
         alert("Cordova is undefined.");
         return null;
      }
      return Cordova;
   }

   /**
    * The 'adf.pg' varable is used to denote that we are running on a phonegap device and
    *        the user interface layer should act appropriately.
    */
   window.adf.pg = "RUNNING ON A PHONEGAP DEVICE";

   /**
    * Gets the AMX Includes specified for this feature.
    */
   containerInternalRoot().getAmxIncludeList = function(successCB, failureCB)
   {
      var cordovaRequest = new CordovaRequest({params:[]},
                                              function(request, result){ successCB(result) },
                                              failureCB);
      cordovaRequest.setPlugin("ADFMobileShell");
      cordovaRequest.setMethod("getAmxIncludeList");
      cordovaRequest.execute();
   };

   /**
    * Represents the API for interacting with features in the ADF Mobile Container.
    */
   window.ADFMobileFeatures = function()
   {
      this.inProgress = false;
      this.records = new Array();
      this.hideNavigationBar_errorCallback = null;
      this.showNavigationBar_errorCallback = null;
      this.registerActivationHandler_errorCallback = null;
   };

   /**
    * The method to register a function as a handler for activation notices.
    */
   ADFMobileFeatures.prototype.registerActivationHandler = function(scb, ecb)
   {
      // this is necessary until we fully move ADFMobileFeatures.m to use the passed-in ecb value
      this.registerActivationHandler_errorCallback = null;
      var bErrCallback = (ecb == undefined || ecb == null) ? false : true;
      if (bErrCallback)
      {
         this.registerActivationHandler_errorCallback = ecb;
      }

      // console.log("creating registerActivationHandler request");
      var cordovaRequest = new CordovaRequest({params:[window.GetFunctionName(scb)]}, function(){/* Don't Care about success. */}, ecb);
      cordovaRequest.setPlugin("ADFMobileFeatures");
      cordovaRequest.setMethod("registerActivationHandler");
      cordovaRequest.execute();
   };

   /**
    * Represents the API for interacting with the CVM in the ADF Mobile Native Framework.
    */
   window.ADFMobileJava = function()
   {
      this.inProgress = false;
      this.invokeJava_resultsCallback = null;
      this.invokeJava_errorCallback = null;
   };

   /**
    * The method to send a message into the VMChannel
    */
   ADFMobileJava.prototype.invoke = function(passedInMT, request, successCB, errorCB)
   {
      try
      {
         // console.log("creating ADFMobileJava.prototype.invoke request");
         var cordovaRequest = new CordovaRequest(request, successCB, errorCB);
         cordovaRequest.execute();
      }
      catch(e)
      {
         var msg = ("Error in ADFMobileJava.prototype.invoke - error=" + adf.mf.util.stringify(e) + " for request = " + adf.mf.util.stringify(request));

         alert(msg);

         adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "device.integration", "invoke",
               ("Error creating nd executing CordovaRequest error = " + msg));
      }
   };

   /**
    * Represents the API for interacting with regions in the ADF Mobile Container.
    */
   window.AdfmfSlidingWindowPlugIn =  window.AdfmfSlidingWindowPlugIn || function()
   {
   };

   /**
    * The method to create a sliding window
    * The success and failure callback methods have the signature function(request,result)
    * in the case of success the result object is the window identifier
    * in the case of failure the result object is a json map.  the error message can be obtained with result.description
    */
   AdfmfSlidingWindowPlugIn.prototype.create =    AdfmfSlidingWindowPlugIn.prototype.create ||  function(/* String */ featureId, success, failure)
   {
      var cordovaRequest = new CordovaRequest({params:[featureId]},success, failure);
      cordovaRequest.setPlugin("AdfmfSlidingWindowPlugIn");
      cordovaRequest.setMethod("create");
      cordovaRequest.execute();
   };

 /**
    * The method to destroy a sliding window
    */
   AdfmfSlidingWindowPlugIn.prototype.destroy =    AdfmfSlidingWindowPlugIn.prototype.destroy || function(/* String */ windowId, success, failure)
   {
      var cordovaRequest = new CordovaRequest({params : [windowId]}, success, failure);
      cordovaRequest.setPlugin("AdfmfSlidingWindowPlugIn");
      cordovaRequest.setMethod("destroy");
      cordovaRequest.execute();
   };

/**
    * The method to get the current window id for the webview this is called within.  Returns an empty string if the current
    web view was not created with the sliding window plugin
    */
   AdfmfSlidingWindowPlugIn.prototype.getCurrentWindowId =    AdfmfSlidingWindowPlugIn.prototype.getCurrentWindowId || function(success,failure)
   {
      var cordovaRequest = new CordovaRequest({params:[]}, success, failure);

      cordovaRequest.setPlugin("AdfmfSlidingWindowPlugIn");
      cordovaRequest.setMethod("getCurrentWindowId");
      cordovaRequest.execute();
   };

  /**
    * The method to get the top sliding window's identifier as returned by create
    */
   AdfmfSlidingWindowPlugIn.prototype.getTopWindowId =    AdfmfSlidingWindowPlugIn.prototype.getTopWindowId || function(success, failure)
   {
      var cordovaRequest = new CordovaRequest({params:[]}, success, failure);
      cordovaRequest.setPlugin("AdfmfSlidingWindowPlugIn");
      cordovaRequest.setMethod("getTopWindowId");
      cordovaRequest.execute();
   };

   /**
    * The method to get the identifiers of all the sliding windows that are created and valid
    */
   AdfmfSlidingWindowPlugIn.prototype.getWindowIds =    AdfmfSlidingWindowPlugIn.prototype.getWindowIds || function(success, failure)
   {
      var cordovaRequest = new CordovaRequest({params:[]}, success, failure);
      cordovaRequest.setPlugin("AdfmfSlidingWindowPlugIn");
      cordovaRequest.setMethod("getWindowIds");
      cordovaRequest.execute();
   };

   /**
    * The method to show a sliding window
    */
   AdfmfSlidingWindowPlugIn.prototype.show =    AdfmfSlidingWindowPlugIn.prototype.show || function(/* String */ windowId, options, success, failure)
   {
      var cordovaRequest = new CordovaRequest({params:[windowId, options]}, success, failure);
      cordovaRequest.setPlugin("AdfmfSlidingWindowPlugIn");
      cordovaRequest.setMethod("show");
      cordovaRequest.execute();
   };

   /**
    * The method to hide a sliding window
    */
   AdfmfSlidingWindowPlugIn.prototype.hide = AdfmfSlidingWindowPlugIn.prototype.hide || function(/* String */ windowId, success, failure)
   {
      var cordovaRequest = new CordovaRequest({params : [windowId]}, success, failure);
      cordovaRequest.setPlugin("AdfmfSlidingWindowPlugIn");
      cordovaRequest.setMethod("hide");
      cordovaRequest.execute();
   };

   /**
    * The method to get information about a sliding window
    */
    AdfmfSlidingWindowPlugIn.prototype.getWindowInfo =    AdfmfSlidingWindowPlugIn.prototype.getWindowInfo || function(/* String */ windowId, success, failure)
    {
    var cordovaRequest = new CordovaRequest({params:[windowId]}, success, failure);
    cordovaRequest.setPlugin("AdfmfSlidingWindowPlugIn");
    cordovaRequest.setMethod("getWindowInfo");
    cordovaRequest.execute();
    };

   /**
    * Represents the API for interacting with Push Notifications in the ADF Mobile Container.
    */
   window.ADFMobilePushNotifications = window.ADFMobilePushNotifications || function()
   {
   };

   /**
    * Register for notifications
    */
   ADFMobilePushNotifications.prototype.register = function(params, successCB, failureCB)
   {
      var cordovaRequest = new CordovaRequest(params, successCB, failureCB);
      cordovaRequest.setPlugin("PushPlugin");
      cordovaRequest.setMethod("register");
      cordovaRequest.execute();
   };

   /**
    * Represents the API for interacting with Local Notifications 
    */
   window.MafLocalNotification = window.MafLocalNotification || function()
   {
   };

   /**
    * local notifications - add
    */
   MafLocalNotification.prototype.add = function(params, successCB, failureCB)
   {
      var cordovaRequest = new CordovaRequest(params, successCB, failureCB);
      cordovaRequest.setPlugin("MafLocalNotification");
      cordovaRequest.setMethod("add");
      cordovaRequest.execute();
   };
   
   /**
    * local notifications - cancel
    */
   MafLocalNotification.prototype.cancel = function(params, successCB, failureCB)
   {
      var cordovaRequest = new CordovaRequest(params, successCB, failureCB);
      cordovaRequest.setPlugin("MafLocalNotification");
      cordovaRequest.setMethod("cancel");
      cordovaRequest.execute();
   };   

   /**
    * OutstandingRequestList is the implementation for the collection of request currently being processed.
    **/
   OutstandingRequestList = function()
   {
      this.queue      = {};
      this.nextId     = 500;
      this.size       = 0;

      /* return a unique request id */
      this.getUniqueRequestId = function()
      {
         return "CR-" + (++this.nextId);;
      };

      /* echo the outstanding request queue */
      this.showQueue = function()
      {
         try {
            var buf = " ";
            var cnt = 0;

            for(property in this.queue) {
               if((property !== undefined) && (property !== null)) {
                  if((typeof this.queue[property]) != 'function')
                  {
                     buf += ("\"" + property + ":" + this.queue[property].requestId + ":" + this.queue[property].timestamp + "\" ");
                     cnt++;
                  }
               }
            }
            return (" queue:"+cnt+"= [" + buf + "]");
         }
         catch(e)
         {
            return ("--error--");
         }
      };

      /* get (but do not remove) the request associated with the given id */
      this.getRequest = function(/* String */ id)
      {
         return this.queue[id];
      };

      /* insert a new request into the queue */
      this.insert = function(/* CordovaRequest */ cr)
      {
         this.size++;
         this.queue[cr.requestId] = cr;
         // console.log("OutstandingRequestList: insert: request=" + cr.requestId + "/" + cr.timestamp + " -- " + this.showQueue() + " scb="+cr.scb.length);
      };

      /* remove the associated request from the queue */
      this.remove = function(/* String */ id)
      {
         var cr = this.queue[id];

         if(cr === undefined) {
            adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "device.integration", "remove",
                  ("Error remvoving CordovaRequest: ERROR in Outstanding Request List ["+id+"] is not found."));
         }
         else {
            if(cr.requestId != id) {
               adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "device.integration", "remove",
                     ("Error remvoving CordovaRequest: ERROR in Outstanding Request List queue[" + id + "]/" + cr.timestamp + " is not " + cr.requestId));
            }
            delete this.queue[id];
            // console.log("OutstandingRequestList: remove: id=" + id + " request="+cr.requestId + this.showQueue() + " scb="+cr.scb.length);
         }

         return cr;
      };

      return this;
   };
   adf.mf.internal.processingRequestQueue = new OutstandingRequestList();


   /* Generate the associated success callback to be invoked by Corodova */
   containerInternalRoot().cordovaSuccessCallback = function(/* String */ id)
   {
      var body = "";

      body += "try{ ";
      body += "  var req = adf.mf.internal.processingRequestQueue.remove('" + id + "'); ";
      body += "  if((req != undefined) && (req != null))";
      body += "  {";
      body += "    adf.mf.log.Framework.logp(adf.mf.log.level.FINER, 'adf.mf.device.integration', 'cordovaSuccessCallback', 'Cordova Response Success Callback' + req.requestId);";
      // body += "    console.log('SUCCESS (id / req): " + id + " / ' + req.requestId );";
      body += "    req.success(r);";
      body += "  }else{";
      body += "    adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, 'adf.mf.device.integration', 'cordovaSuccessCallback', 'Cordova Success Response Handler Error: Request Id (" + id + ") was not found.');";
      body += "  }";
      body += "}catch(e){";
      body += "  adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, 'adf.mf.device.integration', 'cordovaSuccessCallback', 'Cordova Success Response Handler Error: Request Id (" + id + ") error=' + e);";
      body += "  alert('Cordova Response Success Handler Error:' + e);";
      body += "}";

      return new Function('r', body);
   };

   /* Generate the associated failure callback to be invoked by Corodova */
   containerInternalRoot().cordovaFailureCallback = function(/* String */ id)
   {
      var body = "";

      body += "try{ ";
      body += "  var req = adf.mf.internal.processingRequestQueue.remove('" + id + "'); ";
      body += "  if((req != undefined) && (req != null))";
      body += "  {";
      body += "    adf.mf.log.Framework.logp(adf.mf.log.level.FINER, 'adf.mf.device.integration', 'cordovaFailureCallback', 'Cordova Response Failure Callback' + req.requestId);";
      // body += "    console.log('FAILURE (id / req): " + id + " / ' + req.requestId + ' r=' + adf.mf.util.stringify(r));";
      body += "    req.failure(r);";
      body += "  }else{";
      body += "    adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, 'adf.mf.device.integration', 'cordovaSuccessCallback', 'Cordova Failure Response Handler Error: Request Id (" + id + ") was not found.');";
      body += "  }";
      body += "}catch(e){";
      body += "  adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, 'adf.mf.device.integration', 'cordovaFailureCallback', 'Cordova Failure Response Handler Error: Request Id (" + id + ") error=' + e);";
      body += "  alert('Cordova Response Failure Handler Error:' + e);";
      body += "}";

      return new Function('r', body);
   };

   /* Request object used to hold the pending Cordova request that is out being processed */
   CordovaRequest = function(request, success, failed)
   {
      try
      {
         this.timestamp      = "T" + ((new Date()).getTime() % 600000);
         this.requestId      = adf.mf.internal.processingRequestQueue.getUniqueRequestId();
         this.scb            = adf.mf.internal.util.is_array(success)? success : [success];
         this.fcb            = adf.mf.internal.util.is_array(failed)?  failed  : [failed ];
         this.plugin         = "ADFMobileShell";
         this.methodName     = "invokeJavaMethod";
         this.request        = request;

         /**
          * set the plugin name
          * @param  name
          **/
         /* void */
         this.setPlugin      = function(name)
         {
            this.plugin      = name;
         };

         /**
          * set the method name
          * @param  name
          **/
         /* void */
         this.setMethod      = function(name)
         {
            this.methodName  = name;
         };

         /* internal success callback for this request */
         this.success = function(result)
         {
            try
            {
               if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINER))
               {
                  adf.mf.log.Framework.logp(adf.mf.log.level.FINE, "device.integration", "success",
                        "Inside the success callback for request " + this.requestId);
               }

               for(var i = 0; i < this.scb.length; ++i)
               {
                  try
                  {
                     // console.log("START: device.integration.success - request = " + this.requestId + " success callback " + i + " with result = " + result);
                     // adf.mf.internal.perf.start("device.integration.success", "request = " + this.requestId + " success callback " + i);
                	 if (this.scb[i])
                     {
                	   this.scb[i](this.request, result);
                	 }

                     // adf.mf.internal.perf.stop("device.integration.success", "request = " + this.requestId + " success callback " + i);
                     // console.log("END: device.integration.success - request = " + this.requestId + " success callback " + i);
                  }
                  catch(sce)
                  {
                     adf.mf.log.Framework.logp(adf.mf.log.level.WARNING, "device.integration", "success",
                           ("Error executing Cordova request " + this.requestId +
                                 " success callback " + i + " or " + this.scb[i] +
                                 " request: " + adf.mf.util.stringify(this.request) +
                                 " result: " + adf.mf.util.stringify(result)) +
                                 " error: " + adf.mf.util.stringify(sce));
                     try
                     {
                        // adf.mf.internal.perf.start("device.integration.success", "request = " + this.requestId + " failure callback " + i);
                    	if(this.fcb[i])
                    	{
                    	  this.fcb[i](this.request, sce);
                    	}

                        // adf.mf.internal.perf.stop("device.integration.success", "request = " + this.requestId + " failure callback " + i);
                     }
                     catch(fatalError)
                     {
                        adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "device.integration", "success",
                              ("Error executing Cordova request " + this.requestId + " failed callback " + i + "   error = " + fatalError));
                     }
                  }
               }
            }
            catch(se)
            {
               adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "device.integration", "success",
                     ("Error executing cordovaSuccess callback - " + se));
            }
         }

         /* internal failure callback for this request */
         this.failure = function(result)
         {
            try
            {
               if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINE))
               {
                  adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "device.integration", "failure",
                        ("Inside the failure callback for request " + this.requestId));
               }

               for(var i = 0; i < this.fcb.length; ++i)
               {
                  try
                  {
                     // console.log("START: device.integration.failure - request = " + this.requestId + " failure callback " + i + " with result = " + result);
                     // adf.mf.internal.perf.start("device.integration.failure", "request = " + this.requestId + " failure callback " + i);
                     if(this.fcb[i])
                     {
                       this.fcb[i](this.request, result);
                     }

                     // adf.mf.internal.perf.stop("device.integration.failure", "request = " + this.requestId + " failure callback " + i);
                  }
                  catch(fce)
                  {
                     /* nothing we can do */
                     adf.mf.log.Framework.logp(adf.mf.log.level.WARNING, "device.integration", "failure",
                           ("Error executing Cordova request " + this.requestId +
                                 " failure callback " + i + " or " + this.scb[i] +
                                 " request: " + adf.mf.util.stringify(this.request) +
                                 " result: " + adf.mf.util.stringify(result)) +
                                 " error: " + adf.mf.util.stringify(fce));
                }
               }
            }
            catch(fe)
            {
               adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "device.integration", "failure",
                     ("Error executing cordovaFailure callback - " + fe));
            }
         }
         this.fail = this.failure;


         this.execute = function()
         {
            try
            {
               if(typeof(Cordova) == "undefined")
               {
                  adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "device.integration", "execute",
                     ("ERROR: Cordova has not been properly included. Aborting Cordova call to " + this.plugin + "." + this.methodName));
                  return;
               }

               adf.mf.internal.processingRequestQueue.insert(this);
               adf.mf.internal.processingRequestQueue.insert(this);

              Cordova.exec(containerInternalRoot().cordovaSuccessCallback(this.requestId),
                     containerInternalRoot().cordovaFailureCallback(this.requestId),
                     this.plugin, this.methodName, [this.request]);
            }
            catch(e)
            {
               // console.log("Cordova Request post-exec with exception for " + this.requestId);
               adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "device.integration", "execute",
                     ("Error executing Cordova request " + this.requestId + " with error = " + adf.mf.util.stringify(e)));

               for(var i = 0; i < this.fcb.length; ++i)
               {
                  try
                  {
                	 if(this.fcb[i])
                	 {
                	   this.fcb[i](this.request, e);
                	 }
                  }
                  catch(fe)
                  {
                     /* nothing we can do */
                     adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "device.integration", "execute",
                           ("Error executing Cordova request " + this.requestId + " failed callback " + i));
                  }
               }
            }
         }

         if(adf.mf.log.Framework.isLoggable(adf.mf.log.level.FINER))
         {
            adf.mf.log.Framework.logp(adf.mf.log.level.FINER, "device.integration", "constructor",
                  ("Cordova Request " + this.requestId + " has been created. " + adf.mf.util.stringify(this.request)));
         }
         return this;
      }
      catch(ex)
      {
         adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "device.integration", "execute",
               ("Error creating Cordova Request error = " + adf.mf.util.stringify(ex)));
      }
   };

   /**
    * Add the Features service to ADFMobile
    */
   containerInternalRoot().Features = new ADFMobileFeatures();
   adf.mf.Features                  = containerInternalRoot().Features;

   /**
    * Add the vmchannel (formerly Java) service to ADFMobile
    */
   containerInternalRoot().vmchannel = new ADFMobileJava();

   /**
    * Add the SlidingWindow service to ADFMobile
    */
   containerInternalRoot().SlidingWindow = new AdfmfSlidingWindowPlugIn();

   /**
    * Add the PushPlugin service to ADFMobile
    */
   containerInternalRoot().PushNotifications = new ADFMobilePushNotifications();

   /**
    * Add the Local Notification service to Maf
    */
   containerInternalRoot().LocalNotification = new MafLocalNotification();
   
}
catch(e)
{
   console.log("**************************************************");
   console.log("***** ERROR: adf.mf.device.integration.js buildout error: " + e);
}

adf.mf.internal.BUILD_INFO = {
PRODUCT_VERSION: "2.2.2.0.0",
JOB_NAME: "maf-v2.2.0.0.0-tip",
BUILD_NUMBER: "100",
};
