public class Session {
        /*
         * attributes
         */
        private int sessionId;
        public Cinema cinema;
        public String time;
        public String movieName;
        
        private int start_row;
        private int start_seat;
        
        /*
         * Constructor
         */
        public Session(int sessionId,Cinema cinema, String time,String movieName) throws CloneNotSupportedException {
                this.sessionId = sessionId;
                this.cinema =  (Cinema) cinema.clone();
                this.time = time;
                this. movieName = movieName;
                this.start_row = 0;
                this.start_seat =0;
        }
        
        /*
         * Request <id> <cinema> <time> <tickets>
        # booking request <id> is for <cinema> at <time> for <tickets> tickets
        Check if the request is legal. if true, 
        set the start_seat and start_row that available.
         */
        public boolean RequestBook(int requestId, int tickets) {
                {
                        int max_available = 0;
                         start_row = 1;
                         start_seat = 1;
                         
                        for(Row tempRow : cinema.row) {
                                //System.out.println("doing "+tempRow.rowLetter +" row");
                                int i=1;//seat counter
                                max_available=0;
                                start_seat =1;
                                for(Seat tempSeat : tempRow.seat) {
                                        
                                        
                                        if(tempSeat.available==0) {
                                                //this is an available seat
                                                max_available++;
                                                if(max_available==tickets) {
                                                        //System.out.println("start-seat is "+ start_seat);
                                                        //System.out.println("start_row is " + start_row);
                                                        //this is a legal request,
                                                        //booking from (start_row,start_seat),
                                                        //quantity: tickets
                                                        //book(requestId,start_row, start_seat, tickets);
                                                        return true;
                                                }
                                        }
                                        else {
                                                //this is not an available seat
                                                max_available =0;
                                                
                                                start_seat = i+1;
                                                //System.out.println("start_seat is now " + start_seat);
                                                i++;
                                                continue;
                                        }
                                        
                                        
                                        i++; 
                                }
                                start_row++;
                                
                        }
                }
                
                start_seat=1;
                start_row=1;
                return false;
        }
        public void Book(int requestId,int tickets,String bookOrChange) {
                if((RequestBook(requestId, tickets))==true){
                       // System.out.println("Booking "+tickets+" tickets");
                        int i=0;
                        Row tempRow = cinema.row.get(start_row-1);
                
                        for(i=0; i < tickets;i++)//actually booking the seats
                        {
                                Seat tempSeat = tempRow.seat.get(start_seat+i-1);
                                tempSeat.available = requestId;
                        }
                        PrintBook(requestId, tickets,bookOrChange);
                        
                        
                                start_row =0;
                                start_seat =0;
                }
                else {
                        System.out.println("Booking rejected");
                }
                
        }
        
        
        public void requestCancel(int requestId) {
                for(Row tempRow : cinema.row) {
                        for(Seat tempSeat : tempRow.seat) {
                                if(tempSeat.available==requestId) {
                                        tempSeat.available = 0;
                                }
                        }
                }
                
                
        }
        
        
        
        
        public void PrintSession() {
                
                System.out.println("\nSessionId: " +sessionId);
                System.out.println("cinemaId: " + cinema.cinemaId);
                System.out.println("time: "+ time);
                System.out.println("movieName: "+ movieName+"\n");
        }
        
        public void PrintBook(int requestId, int tickets,String bookOrChange) {
        	String rowLetter = cinema.row.get(start_row-1).rowLetter;
        	System.out.print(bookOrChange+ requestId+" ");
        	if(tickets==1) {
        		//only 1 ticket
        		System.out.print(rowLetter+(start_seat));
        	}
        	else {
        		System.out.println(rowLetter+(start_seat)+"-"+rowLetter+(start_seat+tickets-1));
        	}
        	
        	
        	
        }
        
        
        
        
        
}