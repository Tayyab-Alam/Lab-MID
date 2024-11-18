/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universitytransportsystem;

public class Business {

    // Filter interface
    public interface Filter {
        boolean validate(Data.Passenger passenger);
    }

    // Challan Validation Filter
    public static class ChallanFilter implements Filter {
        @Override
        public boolean validate(Data.Passenger passenger) {
            if (passenger.getChallan().isPaid()) {
                System.out.println("Challan validated for " + passenger.getName());
                return true;
            }
            System.out.println("Challan validation failed for " + passenger.getName());
            return false;
        }
    }

    // Time Validation Filter
    public static class TimeFilter implements Filter {
        private Data.Time startTime, endTime;

        public TimeFilter(Data.Time startTime, Data.Time endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public boolean validate(Data.Passenger passenger) {
            if (passenger.getEntryTime().isWithin(startTime, endTime)) {
                System.out.println("Time validated for " + passenger.getName());
                return true;
            }
            System.out.println("Time validation failed for " + passenger.getName());
            return false;
        }
    }

    // Observer Interface
    public interface Observer {
        void notify(Data.Passenger passenger);
    }

    // EntryValidator (Subject)
    public static class EntryValidator {
        private Observer observer;

        public void setObserver(Observer observer) {
            this.observer = observer;
        }

        public void validate(Data.Passenger passenger, Filter... filters) {
            for (Filter filter : filters) {
                if (!filter.validate(passenger)) {
                    System.out.println("Validation failed for " + passenger.getName());
                    return;
                }
            }
            System.out.println("Passenger " + passenger.getName() + " successfully validated!");
            if (observer != null) observer.notify(passenger);
        }
    }
}

