/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universitytransportsystem;

public class Data {

    // Time class
    public static class Time {
        private int hours, minutes;

        public Time(int hours, int minutes) {
            this.hours = hours;
            this.minutes = minutes;
        }

        public boolean isWithin(Time start, Time end) {
            int current = hours * 60 + minutes;
            return current >= start.hours * 60 + start.minutes && current <= end.hours * 60 + end.minutes;
        }
    }

    // Challan class
    public static class Challan {
        private boolean isPaid;

        public Challan(boolean isPaid) {
            this.isPaid = isPaid;
        }

        public boolean isPaid() {
            return isPaid;
        }
    }

    // Passenger class
    public static class Passenger {
        private String name;
        private Challan challan;
        private Time entryTime;

        public Passenger(String name, Challan challan, Time entryTime) {
            this.name = name;
            this.challan = challan;
            this.entryTime = entryTime;
        }

        public String getName() {
            return name;
        }

        public Challan getChallan() {
            return challan;
        }

        public Time getEntryTime() {
            return entryTime;
        }
    }
}


