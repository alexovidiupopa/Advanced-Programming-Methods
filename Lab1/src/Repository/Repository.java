package Repository;

import Model.GenericVehicle;

public class Repository implements RepositoryInterface{
    private GenericVehicle[] list;
    private int length = 0;
    public Repository(){
        list = new GenericVehicle[10];
    }

    public void add(GenericVehicle vehicle){
        list[this.length++] = vehicle;
    }

    public void delete(int index){
        int count=0;
        for(int i=0;i<this.length;i++)
            if (i==index)
                count++;
            else
                list[i-1]=list[i];
        this.length--;
    }

    public int getSize(){
        return this.length;
    }

    public GenericVehicle[] getList(){return this.list;}

    public boolean isFull()
    {
        return this.length==10;
    }
}
