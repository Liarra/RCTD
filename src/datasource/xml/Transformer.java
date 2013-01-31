package datasource.xml;

import storedentities.Type;
import storedentities.Donate;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import datasource.xml.jaxbGenerated.Donates;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 31.01.2013
 * Time: 14:18:42
 * To change this template use File | Settings | File Templates.
 */
public class Transformer {

    public static Collection<Type> getAllTypes(Donates jaxbDonates){
        List<datasource.xml.jaxbGenerated.Type> jaxbTypes=jaxbDonates.getType();
        Collection<Type> types=new ArrayList<Type>();

        for(datasource.xml.jaxbGenerated.Type jaxbType:jaxbTypes){
            types.add(getTypeByJAXBType(jaxbType));
        }

        return types;

    }

    public static Type getTypeByJAXBType(datasource.xml.jaxbGenerated.Type t){
        Type ret=new Type();

        ret.setId(new Long(t.getId()));
        ret.setName(t.getName());

        Collection<Donate> donates=new ArrayList<Donate>();

        for(datasource.xml.jaxbGenerated.Donate d:t.getDonate()){
            Donate newDonate=getDonateByJAXBDonate(d);
            newDonate.setType(ret);
            donates.add(newDonate);
        }
        ret.setDonates(donates);
         return ret;
    }

    public static  Donate getDonateByJAXBDonate(datasource.xml.jaxbGenerated.Donate d){
        Donate ret=new Donate();
        ret.setId(new Long(d.getId()));
        ret.setAccountNumber(d.getAccountNumber());
        ret.setDescription(d.getDescription());
        ret.setName(d.getName());
        ret.setPicURL(d.getPicURL());
        return ret;
    }
}
