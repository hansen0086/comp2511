import java.io.Serializable;
import java.util.ArrayList;

public class Row implements Serializable{
        /**
         * 
         */
        private static final long serialVersionUID = -7176221193892365646L;
                /*
         * Attributes 
         */
        //public int rowNumber;
        public String rowLetter;
        public ArrayList<Seat> seat;
        
        /*
         * Constructor
         */
        public Row(String rowLetter) {
                this.rowLetter = rowLetter;
                seat = new ArrayList<Seat>();
        }
        
        public void AddSeat(int available ) {
                Seat temp = new Seat(available); 
                seat.add(temp);
        }
        
        
        
        public void PrintSeats() {
                System.out.print("Row "+ rowLetter+": ");
                for(Seat temp:seat) {
                        
                        System.out.print(temp.available +" ");
                }
                System.out.println();
        }
        
        
}