/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.data.dataStructure;

import lk.gov.health.entity.WebUser;
import java.util.List;

/**
 *
 * @author safrin
 */
public class WebUserBillsTotal {

    private WebUser webUser;
    private List<BillsTotals> billsTotals;

    public WebUser getWebUser() {
        return webUser;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }

    public List<BillsTotals> getBillsTotals() {
        return billsTotals;
    }

    public void setBillsTotals(List<BillsTotals> billsTotals) {
        this.billsTotals = billsTotals;
    }
}
