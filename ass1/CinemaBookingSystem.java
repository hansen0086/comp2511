import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ListIterator;
import java.util.Scanner;
public class CinemaBookingSystem {
        public static void main(String[] args) throws CloneNotSupportedException {
               
        	ArrayList<Cinema> cinema = new ArrayList<Cinema>();
            ArrayList<Session> session = new ArrayList<Session>();
          //test();
        		try {
        			Scanner inputScanner = new Scanner(new File(args[0]));
        			
        			while (inputScanner.hasNext()) {
        				String s = inputScanner.nextLine();
        				String[] params = s.split(" ");
        				if (params[0].equalsIgnoreCase("Cinema")) {
        					//RoomInputs inputs = new RoomInputs(params);
        					//Room.createRoom(inputs);
        					Cinema(cinema,params);
        					
        				} else if (params[0].equalsIgnoreCase("Session")) {
        					Session(cinema,session,params);
        					
        				} else if (params[0].equalsIgnoreCase("Request")) {
        					Request(session,params);
        					
        					
        				} else if (params[0].equalsIgnoreCase("Change")) {
        					
        					Change(session,params);
        				} else if (params[0].equalsIgnoreCase("Cancel")) {
        					
        					Cancel(session,params);
        				}
        				
        				
        				else if (params[0].equalsIgnoreCase("Print")) {
        					Print(session,params);
        				}
        			}
        			inputScanner.close();
        		} catch (FileNotFoundException e) {
        			e.printStackTrace();
        		}
        		//System.out.println("printing cinema!!!!!!!!!!!!!!!!!!");
        		//cinema.get(0).PrintCinema();
        		
        		
        		
        		
        	}
        public static void Cinema(ArrayList<Cinema> cinema,String[] params) {
        	int cinemaId=Integer.parseInt(params[1]);
        	String rowLetter = params[2];
        	int seat_number = Integer.parseInt(params[3]);
        	//System.out.println("New request:\ncinemaId: "+cinemaId+"\nrowLetter: "+rowLetter+"\nseat_number: "+seat_number);
        	
        	
        	
        	if(!CinemaExist(cinema, cinemaId)) {
        		//System.out.println("adding Cinema"+ cinemaId);
        		cinema.add(new Cinema(cinemaId));
        	}
        	Cinema currCinema = cinema.get(GetCinemaNumber(cinema,cinemaId));
        	//add row
        	currCinema.AddRow(rowLetter);
        	//add seat
        	Row currRow = currCinema.row.get(GetRowNumber(currCinema,rowLetter));
        	//System.out.println("Adding "+seat_number+" seats");
        	for(int i=0;i<seat_number;i++) {
        		
        		//cinema.get(GetCinemaNumber(cinema, cinemaId)).row.get(GetRowNumber(cinema.get(GetCinemaNumber(cinema, cinemaId)).row,rowLetter)).AddSeat(0);
        		currRow.AddSeat(0);
        	}
        	
        	//for debugging
        	for(Cinema tempCinema : cinema) {
        		//tempCinema.PrintCinema();
        	}
        	
        	
        }
        public static void Session(ArrayList<Cinema> cinema,ArrayList<Session> session, String[] params) throws CloneNotSupportedException {
        	//eg. session.add(new Session(1, cinema.get(0), "14:00","Tom and Jerry"));
        	int cinemaId=Integer.parseInt(params[1]);
        	String time = params[2];
        	String movieName="";
        	for(int i=3; i< params.length;i++) {
        		movieName = movieName + params[i]+" ";
        	}
        	//System.out.println("\n\nSession: \ncinemaId: "+cinemaId+ "\ntime: "+time+"\nmovieName: "+movieName);
        	
        	
        	session.add(new Session(cinemaId,GetCinemaByCinemaId(cinema,cinemaId),time, movieName));
        	
        	//PrintAll(session);
        	
        	
        }
      
        public static void Request(ArrayList<Session> session, String[] params) {
        	
        	int requestId = Integer.parseInt(params[1]);
        	int cinemaId = Integer.parseInt(params[2]);
        	String time = params[3];
        	int tickets  = Integer.parseInt(params[4]);
        	Session tempSession = GetSessionByCinemaIdAndTime(session,cinemaId,time);
        	//System.out.println("--------Booking------ ");
        	//tempSession.PrintSession();
        	//PrintAll(session);
        	//System.out.println("##################### ");
        	String bookOrChange ="Booking ";
        	tempSession.Book(requestId, tickets,bookOrChange);
        }

        public static void Change(ArrayList <Session> session,String[] params) {
        	int requestId = Integer.parseInt(params[1]);
        	int cinemaId = Integer.parseInt(params[2]);
        	String time = params[3];
        	int tickets = Integer.parseInt(params[4]);
        	change(session,requestId, cinemaId, time, tickets);
        	
        }
        
        public static void Cancel(ArrayList <Session> session,String[] params) {
      
        	
        	int requestId = Integer.parseInt(params[1]);
        	Session tempSession = GetSessionByRequestId(session,requestId);
        	tempSession.requestCancel(requestId);
        	System.out.println("Cancel "+requestId);
        	
        }

        
        public static void Print() {
        	
        }
        
        public static void test() throws CloneNotSupportedException {
        	//Building cinema.get(0)
            ArrayList<Cinema> cinema = new ArrayList<Cinema>();
            ArrayList<Session> session = new ArrayList<Session>();
            
            cinema.add(new Cinema(1));
            
            
            
            cinema.get(0).AddRow("A");
            
            
            
            cinema.get(0).row.get(0).AddSeat(0);
            cinema.get(0).row.get(0).AddSeat(0);
            cinema.get(0).row.get(0).AddSeat(0);
            
            cinema.get(0).AddRow("B");
            cinema.get(0).row.get(1).AddSeat(0);
            cinema.get(0).row.get(1).AddSeat(0);
            cinema.get(0).row.get(1).AddSeat(0);
            cinema.get(0).row.get(1).AddSeat(0);
            cinema.get(0).row.get(1).AddSeat(0);
            cinema.get(0).row.get(1).AddSeat(0);
            
            cinema.get(0).PrintCinema();
            
            //Building cinema.get(1)
            cinema.add(new Cinema(2));

            cinema.get(1).AddRow("A");
            cinema.get(1).row.get(0).AddSeat(0);
            cinema.get(1).row.get(0).AddSeat(0);
            cinema.get(1).row.get(0).AddSeat(0);
            cinema.get(1).row.get(0).AddSeat(0);
            cinema.get(1).row.get(0).AddSeat(0);
            cinema.get(1).row.get(0).AddSeat(0);
            
            cinema.get(1).PrintCinema();
            
            //Set the Session for cinema.get(0)
            session.add(new Session(1, cinema.get(0), "14:00","Tom and Jerry"));
            
            session.get(0).PrintSession();
            
            //Set the Session for cinema.get(1)
            session.add(new Session(1, cinema.get(0),"19:00","Inception"));
            
            session.get(1).PrintSession();
            
            
            
            
            
            session.get(0).cinema.PrintCinema();
            //Book(request, number_of_request)
           // session.get(0).Book(1, 1);
            session.get(0).cinema.PrintCinema();
            
            //session.get(0).requestCancel(1);
            //session.get(0).cinema.PrintCinema();
            //session.get(0).Book(2, 4);
            //session.get(0).cinema.PrintCinema();
            //session.get(0).Book(3, 1);
            //session.get(0).cinema.PrintCinema();
            
            PrintSession(session,1,"14:00");
        }
        
        
        public static void  change(ArrayList <Session> session,int requestId, int cinemaId, String time, int tickets) {
                //looking for the new session he is going to book
                Session newSession = GetSessionByCinemaIdAndTime(session, cinemaId, time);
                Session oldSession = GetSessionByRequestId(session, requestId);
                
                
                //if  new Session book is legal
                if(newSession.RequestBook(requestId, tickets)==true) {
                        //cancel the old booking
                        oldSession.requestCancel(requestId);
                        String bookOrChange = "Change ";
                        newSession.Book(requestId, tickets,bookOrChange);
                         
                        //System.out.println("change succeed");
                }
                else {
                        System.out.println("rejected");
                }
                
        }
        
        public static void  Print(ArrayList<Session> session, String[] params) {
        	int cinemaId = Integer.parseInt(params[1]);
        	String time = params[2];
        	PrintSession(session,cinemaId,time);
        }
        
        
        public static int GetCinemaNumber(ArrayList<Cinema> cinema, int cinemaId) {
        	int i=0;
        	for(Cinema tempCinema: cinema) {
        		
        		if(tempCinema.cinemaId==cinemaId) {
        			//System.out.println("cinemaIndex is "+ i);
        			return i;
        		}
        		i++;
        	}
        	System.out.println("Warning: No such Cinema!!");
        	return-1;
        }
        
        
        
        public static int GetRowNumber(Cinema cinema, String rowLetter) {
        	int i=0;
        	for(Row tempRow: cinema.row) {
        		
        		if(tempRow.rowLetter.equals(rowLetter)) {
        			//System.out.println("rowindex is "+i);
        			return i;
        		}
        		i++;
        	}
        	System.out.println("Warning: No such row!!");
        	return-1;
        }
        
        /*
         * @param: the session list, cinemaId and time, return the session refers to 
         */
        public static Session GetSessionByCinemaIdAndTime(ArrayList <Session> session,int cinemaId,String time) {
                for( Session tempSession : session) {
                        if((tempSession.cinema.cinemaId== cinemaId) && (tempSession.time.equals(time))) {
                        		//System.out.println("getting session ");
                                return tempSession;
                        }
                        
                }
                System.out.println("Can not change since can not find the session");
                return null;
        }
        
        public static Session GetSessionByRequestId(ArrayList<Session>session,int requestId) {
                for( Session tempSession : session) {
                        for(Row tempRow : tempSession.cinema.row) {
                                for(Seat tempSeat : tempRow.seat) {
                                        if(tempSeat.available == requestId) {
                                                return tempSession;
                                        }
                                }
                }
                
                
        }
                System.out.println("can not get the old session.");
                return null;
}
        
        public static boolean CinemaExist(ArrayList<Cinema> cinema,int cinemaId) {
        	for(Cinema tempcinema: cinema) {
        		if(tempcinema.cinemaId==cinemaId) {
        			return true;
        		}
        	}
        	return false;
        }
        
        public static Cinema GetCinemaByCinemaId(ArrayList<Cinema> cinema,int CinemaId) {
        	for(Cinema tempcinema: cinema) {
        		if(tempcinema.cinemaId==CinemaId) {
        			return tempcinema;
        		}
        	}
        	System.out.println("Cinema not found!");
        	return null;
        }
        
        
        
        
        public static void PrintSession(ArrayList <Session> session,int cinemaId,String time) {
                Session tempSession = GetSessionByCinemaIdAndTime(session,cinemaId,time);
                System.out.println(tempSession.movieName);
                int start_seat=-1;
                int end_seat =-1;
                int start_seat_finder=0;
                int end_seat_finder=0;
                int roll_request=0;
                /*for (int currRow = 0; currRow < tempSession.cinema.row.size(); currRow++) {
                	for(int currSeat = 0; currSeat < tempSession.cinema.row.get(currRow).seat.size();currSeat++) {
                		if (tempSession.cinema.row.get(currRow).seat.get(currSeat).available==0)
                		{
                			
                		}
                	*/
                
                for(Row currRow : tempSession.cinema.row) {
                	System.out.print(currRow.rowLetter+": ");
                	start_seat=-1;
                	end_seat=-1;
                	roll_request=0;
                	for(start_seat_finder=0;start_seat_finder < currRow.seat.size();start_seat_finder++) {
                		
                		//looking for the start_seat
                		if(currRow.seat.get(start_seat_finder).available==0) {
                			continue;
                		}
                		else {//found the start_seat
                			start_seat=start_seat_finder;
                			
                			for(end_seat_finder=start_seat_finder;end_seat_finder<currRow.seat.size();end_seat_finder++) {
                				
                				//looking for the end_seat,set the end_seat after this loop
                				//System.out.println("New request, start_seat_finder is "+ start_seat_finder+"\nend_seat_finder is "+ end_seat_finder+"\n");
                				
                					if(end_seat_finder==currRow.seat.size()-1) {//if this is already the last item in the list
                						if(currRow.seat.get(end_seat_finder).available==currRow.seat.get(start_seat_finder).available) {
                							//check if the last item is good or not
                							end_seat = end_seat_finder;
                							
                						}
                						else {
                							end_seat=end_seat_finder-1;
                						}
                						break;
                					}
                					else {//there is a next item
                						if(currRow.seat.get(end_seat_finder).available==currRow.seat.get(start_seat_finder).available) {
                							//if the end_seat == start_seat;
                							continue;
                						}
                						else {
                							//meet a different
                							end_seat=end_seat_finder-1;
                							//System.out.println("break2:end_seat is "+end_seat);
                							break;
                						}
                					}
                				}
                				//Already set the start_seat and the end_seat
                				if(roll_request!=0) {
                					System.out.print(",");
                				}
                				if(start_seat==end_seat) {
                					//only 1 seat in a request
                					System.out.print(start_seat+1);
                				}
                				else {
                					System.out.print((start_seat+1)+"-"+(end_seat+1));
                				}
                				
                		        roll_request++;
                				//initialize for the next request loop
                		        if(end_seat==currRow.seat.size()-1) {
                		        	//already the last seat in the row
                		        	break;
                		        }
                		        else {
                		        	start_seat_finder=end_seat;
                		        	
                		        }
                			}
                	}
                	System.out.println();
                }
               // System.out.print("finished row"+currRow.seat.available);
                
        }

 

	public static void PrintAll(ArrayList<Session>session) {
		System.out.print("---------PRINTING ALL SESSOINS:");
		System.out.println("-------------------------");
		for(Session tempSession: session) {
			tempSession.PrintSession();
			tempSession.cinema.PrintCinema();
			System.out.println("**************************");
		}
	}
}






        