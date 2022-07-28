/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.gov.health.bean.common;

//import ch.lambdaj.Lambda;
import lk.gov.health.entity.Item;
import lk.gov.health.entity.pharmacy.Amp;
import lk.gov.health.entity.pharmacy.Ampp;
import lk.gov.health.entity.pharmacy.PharmaceuticalItem;
import lk.gov.health.entity.pharmacy.Vmp;
import lk.gov.health.entity.pharmacy.Vmpp;
import lk.gov.health.facade.ItemFacade;
import lk.gov.health.facade.PharmaceuticalItemFacade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Buddhika
 */
@Named(value = "listsController")
@ApplicationScoped
public class ListsController {

    /**
     * Creates a new instance of ListsController
     */
    public ListsController() {
    }

    List<Item> items;
    List<PharmaceuticalItem> pharmaceuticalItems;
    @EJB
    ItemFacade itemFacade;
    @EJB
    PharmaceuticalItemFacade pharmaceuticalItemFacade;
    

    List<PharmaceuticalItem> ampsAndAmpp;

    public List<PharmaceuticalItem> getAmpsAndAmpp() {
        if (ampsAndAmpp == null) {
            String sql;
            sql = "select i from Item i where i.retired=false and (type(i)=:it1 or type(i)=:it2) order by i.name";
            Map m = new HashMap();
            m.put("it1", Amp.class);
            m.put("it2", Ampp.class);
            ampsAndAmpp = getPharmaceuticalItemFacade().findBySQL(sql, m);
        }
        return ampsAndAmpp;
    }

    public void setAmpsAndAmpp(List<PharmaceuticalItem> ampsAndAmpp) {
        this.ampsAndAmpp = ampsAndAmpp;
    }

    public List<Item> getItems() {
        if (items == null) {
            String sql;
            sql = "select i from Item i where i.retired=false order by i.name";
            items = getItemFacade().findBySQL(null);
        }
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    

    List<Item> pharmacyItems;
    
    public List<Item> getPharmacyItem() {
        if (pharmacyItems == null) {
            String sql;
            HashMap tmpMap = new HashMap();
            sql = "select c from Item c where c.retired=false and (type(c)= :amp or type(c)= :ampp or type(c)= :vmp or type(c)= :vmpp) order by c.name";
            //////System.out.println(sql);
            tmpMap.put("amp", Amp.class);
            tmpMap.put("ampp", Ampp.class);
            tmpMap.put("vmp", Vmp.class);
            tmpMap.put("vmpp", Vmpp.class);
            pharmacyItems = getItemFacade().findBySQL(sql, tmpMap);
        }
        return pharmacyItems;
    }

    
    public List<PharmaceuticalItem> getPharmaceuticalItems() {
        return pharmaceuticalItems;
    }

    public void setPharmaceuticalItems(List<PharmaceuticalItem> pharmaceuticalItems) {
        this.pharmaceuticalItems = pharmaceuticalItems;
    }

    public ItemFacade getItemFacade() {
        return itemFacade;
    }

    public void setItemFacade(ItemFacade itemFacade) {
        this.itemFacade = itemFacade;
    }

    public PharmaceuticalItemFacade getPharmaceuticalItemFacade() {
        return pharmaceuticalItemFacade;
    }

    public void setPharmaceuticalItemFacade(PharmaceuticalItemFacade pharmaceuticalItemFacade) {
        this.pharmaceuticalItemFacade = pharmaceuticalItemFacade;
    }

}
