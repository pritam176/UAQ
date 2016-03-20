function preventBack() {
    window.history.forward(0);
};

function writeAdfFacesMessageToApplet(formPath) {
    document.applets[0].handleJavaScriptToAppletMessage(formPath);
};

  function clientMethodCall(event) {                   
                    component = event.getSource();
                    AdfCustomEvent.queue(component, "customEvent",null, true);                                 
                    event.cancel();                    
                }       ;