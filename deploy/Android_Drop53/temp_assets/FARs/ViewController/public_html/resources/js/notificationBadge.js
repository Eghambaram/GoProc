(function () {
    // ---------------------------------------------------------------------------------------------
    // This is the Javascript for the notification:badge custom component
    // ---------------------------------------------------------------------------------------------
    var badge = adf.mf.api.amx.TypeHandler.register("http://xmlns.ateam.com/notification", "badge");
    badge.prototype.render = function (amxNode, id) {
        var rootElement = document.createElement("div");
        try {
            var link = document.createElement("a");
            //tag's custom attribute values being accessed in the JavaScript function
            var userClass = amx.getTextValue(amxNode.getAttribute("class"));
            var unreadCount = parseInt(amx.getTextValue(amxNode.getAttribute("unread")));
 
            link.setAttribute("href", "#");
            link.setAttribute("class", userClass);
            rootElement.appendChild(link);
 
            var badgeOverlay = document.createElement("span");
            badgeOverlay.className = "notification-badge";
 
            // Display a badge only if there are more than 0 unread notifications
            if (unreadCount > 0) {
                badgeOverlay.appendChild(document.createTextNode(unreadCount));
            }
            else {
                badgeOverlay.className = "notification-badge-hidden";
            }
            link.appendChild(badgeOverlay);
 
        }
        catch (problem) {
            alert(problem);
            adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "notification", "render", 
                                       "Problem with custom component creation: " + problem);
        }
        return rootElement;
    };
 
})();