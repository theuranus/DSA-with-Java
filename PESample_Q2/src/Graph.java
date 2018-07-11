/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)============================================
import java.io.*;
import java.util.*;
//-------------------------------------------------------------------------------
public class Graph
 {int [][] a; int n;
  char v[];
  int deg[];
  Graph()
    {v = "ABCDEFGHIJKLMNOP".toCharArray();
     deg = new int[20];
     a = new int[20][20];
     n = 0;
    }

  void loadData(int k)  //do not edit this function
   {RandomAccessFile f;int i,j,x;
    String s;StringTokenizer t;
    a = new int[20][20];
    try {
     f = new RandomAccessFile("data.txt","r");
     for(i=0;i<k;i++) f.readLine();
     s = f.readLine();s = s.trim();
     n = Integer.parseInt(s);
     for(i=0;i<n;i++)
       {s = f.readLine();s = s.trim();
        t = new StringTokenizer(s);
        for(j=0;j<n;j++) 
          {x = Integer.parseInt(t.nextToken().trim());
           a[i][j] = x;
          }
       }
     f.close();
     }
    catch(Exception e) {}

   }

  void dispAdj()
   {int i,j;
    for(i=0;i<n;i++)
     {System.out.println();
      for(j=0;j<n;j++)
        System.out.printf("%4d",a[i][j]);
     }
   }

  void fvisit(int i, RandomAccessFile f) throws Exception
   {f.writeBytes(" "+v[i]);
   }

 void fdispAdj(RandomAccessFile f) throws Exception 
   {int i,j;
    f.writeBytes("n = "+n+"\r\n");
    for(i=0;i<n;i++)
     {f.writeBytes("\r\n");
      for(j=0;j<n;j++)  f.writeBytes("  " + a[i][j]);
     }
    f.writeBytes("\r\n");
   }

  void breadth(boolean [] en, int i, RandomAccessFile f) throws Exception
   {GQueue q = new GQueue();
    int r,j;
    q.enqueue(i); en[i]=true;
    while(!q.isEmpty())
     {r = q.dequeue();
      fvisit(r,f);
      for(j=0;j<n;j++)
       {if(!en[j] && a[r][j]>0) {q.enqueue(j);en[j]=true;}
       }
     }
   }

  void breadth(int  k, RandomAccessFile f) throws Exception
   {boolean [] en = new boolean[20];
    int i;
    for(i=0;i<n;i++) en[i]=false;
    breadth(en,k,f);
    for(i=0;i<n;i++) if(!en[i]) breadth(en,i,f);
   }

 void depth(boolean [] visited,int k, RandomAccessFile f) throws Exception
   {fvisit(k,f);visited[k]=true;
    for(int i=0;i<n;i++)
      {if(!visited[i] && a[k][i]>0) depth(visited,i,f);
      }
   }
  void depth(int k, RandomAccessFile f) throws Exception
   {boolean [] visited = new boolean[20];
    int i;
    for(i=0;i<n;i++) visited[i]=false;
    depth(visited,k,f);
    for(i=0;i<n;i++) 
       if(!visited[i]) depth(visited,i,f);
   }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
  void dijkstra(int vFrom, int vTo, RandomAccessFile f) throws Exception // vFrom: from vertex, vTo: to vertex
  { int [] S = new int[20]; // the set S
    int [] dist = new int[20]; // current distances
    int [] path = new int[20]; // current path 
    boolean [] selected = new boolean[20];
    int m,i,k, t, INFI;INFI = 999;// 99 is considered infinity value
    //Khoi tao
    for(i=0;i<n;i++) 
       {selected[i]=false;
        dist[i]=a[vFrom][i]; // At the first step distance is a direct distance  
        path[i]=vFrom;       // and the vertex before vertex i is the vertex  vFrom
       }

    k=vFrom;// k is selected to the set S
    selected[k]=true; S[0] = vFrom;m=1; // add the vertex k the the S set
    while(k != vTo) //Thuc hien vong lap cho den khi gap nut vTo
       {t=INFI;k=-1;
        //Tim dinh gan nhat de dua vao tap  S
        for(i=0;i<n;i++)
          {if(selected[i]) continue;
           if(dist[i]<t) {t=dist[i];k=i;}
          }
        if(t==INFI) {return;}
        //Dua  dinh k vao tap S da xet
        selected[k]=true;S[m++] = k;
        //Tinh lai khoang cach
        for(i=0;i<n;i++)
           {if(selected[i]) continue;
            if(dist[i]>dist[k]+a[k][i])  {dist[i]=dist[k]+a[k][i]; path[i]=k;  }
           }
       } // end of while(k != vTo) 

    GStack  u = new GStack();
    
    i=vTo;
    u.push(i);
    do
       {i=path[i];
        u.push(i);
       }
    while(i!=vFrom);

    int x, y;
    y = u.pop();
    while(!u.isEmpty())
     {x=y;
       y= u.pop();
       f.writeBytes("" + v[x]); 
       f.writeBytes(" -> (" + a[x][y] + ")");
     }
    f.writeBytes("" + v[y] + "\r\n"); 
  }
  
  void f5() throws Exception
   {loadData(13);
    String fname = "f5.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/


    //-------------------------------------------------------------------------------------
    f.writeBytes("\r\n");
    f.close();
   }

//=================================================================
/*
Algorithm for finding an Euler cycle from the vertex X using stack 
//Input: Connected graph G with all vertices having even degrees
//Output: Euler cycle
declare a stack S of characters
declare empty array E (which will contain Euler cycle)
push the vertex X to S
while(S is not empty)
 {r = top element of the stack S 
  if r is isolated then remove it from the stack and put it to E
   else
   select the first vertex Y (by alphabet order), which is adjacent
   to r, push  Y  to  S and remove the edge (r,Y) from the graph   
 }
 the last array E obtained is an Euler cycle of the graph
*/
  void f6() throws Exception
   {loadData(21);
    String fname = "f6.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    f.writeBytes("\r\n");
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
      // You can use the statement fvisit(i,f); i = 0, 1, 2,...,n-1 to display the vertex i to file f5.txt 
      //  and statement f.writeBytes(" " + k); to write  variable k to the file f5.txt  



    //-------------------------------------------------------------------------------------
    f.writeBytes("\r\n");
    f.close();
   }

}
//=================================================================
