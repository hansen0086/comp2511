import java.io.Serializable;

public class Seat implements Serializable{
        /**
         * 
         */
        private static final long serialVersionUID = 4956727421401246588L;
                public int available;
        
        public Seat(int available) {
                this.available = available;
        }
}