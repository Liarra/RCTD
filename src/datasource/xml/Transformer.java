package datasource.xml;

import datasource.storedentities.Type;
import datasource.storedentities.Donate;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import datasource.xml.jaxbGenerated.Donates;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: Buchina
 * Date: 31.01.2013
 * Time: 14:18:42
 */
class Transformer {

    public static Collection<Type> getAllTypes(Donates jaxbDonates){
        List<datasource.xml.jaxbGenerated.Type> jaxbTypes=jaxbDonates.getType();
        Collection<Type> types=new ArrayList<Type>();

        for(datasource.xml.jaxbGenerated.Type jaxbType:jaxbTypes){
            types.add(getTypeByJAXBType(jaxbType));
        }

        return types;
    }

    private static Type getTypeByJAXBType(datasource.xml.jaxbGenerated.Type t){
        Type ret=new Type(new Long(t.getId()),t.getName());

        Collection<Donate> donates=new ArrayList<Donate>();

        for(datasource.xml.jaxbGenerated.Donate d:t.getDonate()){
            Donate newDonate=getDonateByJAXBDonate(d);
            newDonate.setType(ret);
            donates.add(newDonate);
        }
        ret.setDonates(donates);
         return ret;
    }

    private static  Donate getDonateByJAXBDonate(datasource.xml.jaxbGenerated.Donate d){
        Donate ret=new Donate(new Long(d.getId()),d.getName());
        ret.setAccountNumber(d.getAccountNumber());
        ret.setDescription(d.getDescription());
        ret.setPicURL(d.getPicURL());
        ret.setSharePicId(d.getSharePicId());
        return ret;
    }
}
