

public class CarWashThread extends Thread{
    boolean isFree = true;

    public CarWashThread() {
    }



    @Override
    public void run() {
        while (!VehicleQueue.queue.isEmpty()){
            Vehicle vehicle = VehicleQueue.deQueue();
                wash(vehicle);
        }
        suspend();
        run();
    }

    public  void wash(Vehicle vehicle) {
        if(vehicle!=null){
            isFree = false;
            synchronized (vehicle){
                long timeWash = vehicle.getPrice();
                try {
                    Thread.sleep(400*timeWash);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                VehicleList.addData(vehicle);
        }


            }


        //System.err.println(Thread.currentThread().getName()+" đã rửa xong xe "+vehicle.getNumOfVehicle());


        isFree=true;
    }
}
