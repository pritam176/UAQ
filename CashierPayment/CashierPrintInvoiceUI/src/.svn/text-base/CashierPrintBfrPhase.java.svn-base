import javax.faces.event.PhaseEvent;
import javax.faces.context.FacesContext;

import javax.faces.event.PhaseId;

import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.render.OutputMode;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;
public class CashierPrintBfrPhase {
    public CashierPrintBfrPhase() {
    }

    public void beforePhaseMethod(PhaseEvent phaseEvent) {
        if (phaseEvent.getPhaseId() == PhaseId.RENDER_RESPONSE){
                         FacesContext fctx = FacesContext.getCurrentInstance();
                         
                         AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
                           if(adfFacesContext.getOutputMode()== OutputMode.PRINTABLE){              
                              ExtendedRenderKitService erks = null;
                              erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
                              erks.addScript(fctx, "window.print();");                   
                            }
                         }   
    }
}
