package datasource.stub;

import datasource.TypeDataSource;
import storedentities.Type;

import java.util.Collection;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 25.01.2013
 * Time: 14:17:50
 */
public class StubTypeDataSource implements TypeDataSource{
    private final ArrayList<Type> arr = new ArrayList<Type>();

    public StubTypeDataSource(){
         Type t2 = new Type(0L,"Животные");
        arr.add(t2);
        Type t3 = new Type(1L,"Дети");
        arr.add(t3);
        Type t4 = new Type(2L,"Дома престарелых");
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
