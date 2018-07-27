import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Cloneable,Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 3144739992573719376L;
                /*
         * Attributes
         */
        public int cinemaId;
        public ArrayList<Row> row;
        
        /*
         * Constructor
         */
        public Cinema(int cinemaId) {
                this.cinemaId = cinemaId;
                row = new ArrayList<Row>();
                
        }
        
        public void AddRow(String rowLetter ) {
                Row temp = new Row(rowLetter); 
                row.add(temp);
        }
        
     
        public Object clone() throws CloneNotSupportedException {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream=null;
				try {
					objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                try {
					objectOutputStream.writeObject(this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                try {
					objectOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                ObjectInputStream objectInputStream = null;
				try {
					objectInputStream = new ObjectInputStream(byteArrayInputStream);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            Cinema cloned=null;
			try {
				cloned = (Cinema) objectInputStream.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				objectInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return cloned;
        }
        
        
        
        public void PrintCinema() {
                System.out.println("Printing Cinema: "+cinemaId);
                for(Row temp:row) {
                        temp.PrintSeats();
                }
                System.out.println();
        }
}