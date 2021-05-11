

public class CarWashThread extends Thread {
    boolean isFree = true;

    public CarWashThread() {
    }


    @Override
    public void run() {
        while (!VehicleQueue.queue.isEmpty()) {
            Vehicle vehicle = VehicleQueue.pollQueue();
            wash(vehicle);
        }
        suspend();
        run();
    }

    public void wash(Vehicle vehicle) {
        if (vehicle != null) {
            isFree = false;
            synchronized (vehicle) {
                System.err.println(Thread.currentThread().getName()+" bắt đầu rửa xe "+vehicle.getNumOfVehicle());
                long timeWash = vehicle.getPrice();
                try {
                    Thread.sleep(2000 * timeWash);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println(Thread.currentThread().getName()+" đã rửa xong xe "+vehicle.getNumOfVehicle());

                VehicleList.addData(vehicle);
            }


        }




        isFree = true;
    }
}
