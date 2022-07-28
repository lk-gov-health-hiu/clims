/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.bean.common;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
/**
 *
 * @author divudi_lk
 */
@Named
@SessionScoped
public class ViewController  implements Serializable {
    
    private String previousPage;

    /**
     * Creates a new instance of ViewController
     */
    public ViewController() {
    }

    public String getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(String previousPage) {
        this.previousPage = previousPage;
    }
    
    public void makeAdminFeesAsPreviousPage(){
        previousPage = "/admin_fees";
    }
    
    public void makeInvestigationAsPreviousPage(){
        previousPage = "/lab/investigation";
    }
    
    public void makeBulkFeesAsPreviousPage(){
        previousPage = "/dataAdmin/manage_item_fees_bulk";
    }
    
    public String backToPreviousPage(){
        if(previousPage==null||previousPage.trim().equals("")){
            previousPage = "/index";
        }
        return previousPage;
    }
    
    
}
