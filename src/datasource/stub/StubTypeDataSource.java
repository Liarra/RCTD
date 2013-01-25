package datasource.stub;

import datasource.TypeDataSource;
import storedentities.Type;

import java.util.Collection;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 25.01.2013
 * Time: 14:17:50
 * To change this template use File | Settings | File Templates.
 */
public class StubTypeDataSource implements TypeDataSource{
    ArrayList<Type> arr = new ArrayList<Type>();

    public StubTypeDataSource(){
         Type t2 = new Type();
        t2.setName("Животные");
        t2.setId(0L);
        arr.add(t2);
        Type t3 = new Type();
        t3.setName("Дети");
        t3.setId(1L);
        arr.add(t3);
        Type t4 = new Type();
        t4.setId(2L);
        t4.setName("Дома престарелых");
        arr.add(t4);
    }

    public Collection<Type> getAllTypes() {
        return arr;
    }

    public Type getTypeById(Long typeId) {
        for(Type t:arr){
            if(t.getId().equals(typeId))
                return t;
        }
        //TODO: Обработать
        return arr.get(0);
    }
}
