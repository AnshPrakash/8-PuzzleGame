import java.util.*;
import java.io.*;

public class Puzzle
{
	static HashMap<String,Vector<Node>> graph=new HashMap<String,Vector<Node>>();
	//Vector<String> Vertices;
	int cost;
	int steps;
	Tile[] d=new Tile[9];
//	String start;
//	String goal;
	Vector<String> vec=Permutation("12345678G");
	Puzzle()
	{
		CreateGraph("12345678G");
//		char[] ch=start.toCharArray();
//		for(int i=0;i<9;i++)
//		{
//			if(start.charAt(i)!='G')
//			{	
//				int til_no=Integer.parseInt(String.valueOf(start.charAt(i)));
//				d[til_no]=new Tile(start.charAt(i),co[til_no-1]);
//				//System.out.println(co[til_no-1]);
//				//d[til_no].cost=co[til_no-1];
//				//d[til_no].s=start.charAt(i);
//			}	
//		}
		
		//this.start=new Node();
//		this.start=start;
//		this.goal=goal;
		//vec=new Vector<Vector<Node>>();

	}
	public void updateTiles(int[] co)
	{
		String start="12345678G";
		for(int i=0;i<9;i++)
		{
			if(start.charAt(i)!='G')
			{	
				int til_no=Integer.parseInt(String.valueOf(start.charAt(i)));
				d[til_no]=new Tile(start.charAt(i),co[til_no-1]);
				//System.out.println(co[til_no-1]);
				//d[til_no].cost=co[til_no-1];
				//d[til_no].s=start.charAt(i);
			}
		}
		// for (int i=0; i<8 ;i++ ) 
		// {
		// 	System.out.println(co[i]);
			
		// }
	}
	public void CreateGraph(String s)
	{
		//Vector<String> vec=Permutation(s);
		Vector<String> neigh;
		HashMap<String,Node> filter=new HashMap<String,Node>();
		for(int i=0;i<vec.size();i++)
		{
			Vector<Node> nei=new Vector<Node>();
			neigh=nextconfigs(vec.get(i));
			
			for(int j=0;j<neigh.size();j++)
			{
			//	System.out.println(neigh.get(j));
				if(!filter.containsKey(neigh.get(j)))
				{
					Node t=new Node();
//					t.cost=Cost(vec.get(i),neigh.get(j));
//					t.Move=Move(vec.get(i),neigh.get(j));
					t.config=neigh.get(j);
					filter.put(neigh.get(j),t);
					nei.add(t);
				}
				else
				{
					
					//if(vec.get(i).equals("123456G78"))
					//	System.out.println(filter.get(neigh));
					nei.add(filter.get(neigh.get(j)));
				}
				
			}
			graph.put(vec.get(i),nei);
		}
	}
	public int Cost(String s1,String s2)//computes cost from going s1 to s2 for one step||s1 and s2 should be valid
	{
		int cos=0;
		int pos1=0;
		int pos2=0;
		for(int i=0;i<9;i++)
		{
			if(s1.charAt(i)=='G')
				pos1=i+1;
			if(s2.charAt(i)=='G')
				pos2=i+1;
		}
		//System.out.println(s1+"   "+ s2 );
		//System.out.println("pos "+ pos2 );
		int ind=Integer.parseInt(String.valueOf(s1.charAt(pos2-1)));
		//System.out.println(s1.charAt(ind) );
		cos=d[ind].cost;
		//System.out.print(cos+"cos");		
		return cos;
	}
	static public String Move(String s1,String s2)
	{
		int pos1=0;
		int pos2=0;
		String s="";
		String tile="";
		for(int i=0;i<9;i++)
		{
			if(s1.charAt(i)=='G')
				pos1=i+1;
			if(s2.charAt(i)=='G')
				pos2=i+1;
		}
		tile=s1.charAt(pos2-1)+"";
		if(pos1<pos2)//tile moved U or L
		{
			if((pos2-pos1)>1)
				s="U";
			else if((pos2-pos1)==1)
				s="L";
		}
		else if(pos1>pos2)//tile moved D or R
		{
			if((pos1-pos2)>1)
				s="D";
			else if((pos1-pos2)==1)
				s="R";
		}
		s=tile+s;
		return s;
	}
    static public Vector<String> Permutation(String s)
    {
    	String s_temp="";
    	Vector<String> per=new Vector<String>();
    	//char[] ch=s.toCharArray();
    	if(s.length()==2)
    	{
    		per.add(s);
    		per.add(s.charAt(1)+""+s.charAt(0));
    		return per;
    	}
    	for(int i=0;i<s.length();i++)
    	{	
    		s_temp=new String(s.substring(0,i)+s.substring(i+1));
    		Vector<String> temp=Permutation(s_temp);
    		for(int j=0;j<temp.size();j++)
    		{
    			per.add(s.charAt(i)+temp.get(j));
    		}
    		
    	}
    	return per;
    }
	static public Vector<String> nextconfigs(String s)
	{
		Vector<String> v=new Vector<String>();
		//char ch=s.toCharArray();
		int pos=0;
		for(int i=0;i<s.length();i++)
		{   
			if('G'==(s.charAt(i)))
			{
				pos=i;
				break;
			}
		}
		if(pos==0)
		{
			String str="";
			str=s.charAt(1)+"G"+s.substring(2,9);
			v.add(str);
			str=new String(s.charAt(3)+s.substring(1,3)+"G"+s.substring(4,9));
			v.add(str);
		}
		else if(pos==1)
		{
			String str="";
			str="G"+s.charAt(0)+s.substring(2,9);
			v.add(str);
			str=new String(s.charAt(0)+""+s.charAt(2)+"G"+s.substring(3,9));
			v.add(str);
			str=new String(s.charAt(0)+""+s.charAt(4)+s.substring(2,4)+"G"+s.substring(5,9));
			v.add(str);
		}
		else if(pos==2)
		{
			String str="";
			str=s.charAt(0)+"G"+s.charAt(1)+s.substring(3,9);
			v.add(str);
			str=new String(s.substring(0,2)+s.charAt(5)+s.substring(3,5)+"G"+s.substring(6,9));
			v.add(str);	
		}
		else if(pos==3)
		{
			String str="";
			str="G"+s.substring(1,3)+s.charAt(0)+s.substring(4,9);
			v.add(str);
			str=new String(s.substring(0,3)+s.charAt(6)+s.substring(4,6)+"G"+s.substring(7,9));
			v.add(str);
			str=new String(s.substring(0,3)+s.charAt(4)+"G"+s.substring(5,9));
			v.add(str);	
		}
		else if(pos==4)
		{
			String str="";
			str=s.charAt(0)+"G"+s.substring(2,4)+s.charAt(1)+s.substring(5,9);
			v.add(str);
			str=new String(s.substring(0,3)+"G"+s.charAt(3)+s.substring(5,9));
			v.add(str);
			str=new String(s.substring(0,4)+s.charAt(5)+"G"+s.substring(6,9));
			v.add(str);	
			str=new String(s.substring(0,4)+s.charAt(7)+s.substring(5,7)+"G"+s.charAt(8));
			v.add(str);	
		}
		else if(pos==5)
		{
			String str="";
			str=s.substring(0,4)+"G"+s.charAt(4)+s.substring(6,9);//left
			v.add(str);
			str=new String(s.substring(0,5)+s.charAt(8)+s.substring(6,8)+"G");//down
			v.add(str);
			str=new String(s.substring(0,2)+"G"+s.substring(3,5)+s.charAt(2)+s.substring(6,9));//up
			v.add(str);		
		}
		else if(pos==6)
		{
			String str="";
			str=s.substring(0,6)+s.charAt(7)+"G"+s.charAt(8);//right
			v.add(str);
			str=new String(s.substring(0,3)+"G"+s.substring(4,6)+s.charAt(3)+s.substring(7,9));//up
			v.add(str);	
		}
		else if(pos==7)
		{
			String str="";
			str=s.substring(0,6)+"G"+s.charAt(6)+s.charAt(8);
			v.add(str);
			str=new String(s.substring(0,7)+s.charAt(8)+"G");
			v.add(str);
			str=new String(s.substring(0,4)+"G"+s.substring(5,7)+s.charAt(4)+s.charAt(8));
			v.add(str);	
		}
		else if(pos==8)
		{
			String str="";
			str=s.substring(0,7)+"G"+s.charAt(7);//left
			v.add(str);
			str=new String(s.substring(0,5)+"G"+s.substring(6,8)+s.charAt(5));//up
			v.add(str);	
		}
		return v;
	}
	public String Dijkstra(String start,String goal,int[] co)
	{
		//int infinity=Integer.MAX_VALUE;
		if(start.equals(goal))
		{
			//System.out.println("0 0");
			String toreturn="0 0"+"\n";
			//System.out.println("");
			return toreturn;
		}
		updateTiles(co);
		cost=0;
		steps=0;
		Vector<Node> List=new Vector<Node>();
		String Path="";
		Heap h=new Heap();
		boolean path_exist=false;
		//HashMap<String,String> Inlist=new HashMap<String,String>();
		HashMap<String,String> visted=new HashMap<String,String>();
		//int i=0;
		Node startn=new Node();
		startn.config=start;
		startn.dist=0;
		startn.no_of_moves=0;
		//startn.prev=null;
		//visted.put(start, start);
		//Inlist.put(start, start);
		//h.insert(startn);
		setDistInf(start,List);
		Vector<Node> satr=graph.get(start);
		Vector<Node> starnei=graph.get(satr.get(0).config);
		for(int k=0;k<starnei.size();k++)
		{
			if(starnei.get(k).config.equals(start))
				{
					starnei.get(k).dist=0;
					starnei.get(k).no_of_moves=0;
					starnei.get(k).prev=null;
					h.insert(starnei.get(k));
					//System.out.println("I am here");
				}
		}
		
		//System.out.println(List.size());
		Node goaln=null;
		//Till now we have set the dist=infinity and visted=false for all the vertices in the graph
		Vector<Node> neigh=new Vector<Node>();
		//System.out.println(vec.size());
		//System.out.println(List.size());
		//int m=0;
		while(List.size()!=0)
		{
			//h.Print();
//			System.out.println(m);
//			m++;
			Node v=h.deleteMin();
			
//			System.out.println(v.dist);
			List.remove(List.size()-1);//just for satisfifying the condition
			//visted.put(v.config, v.config);
			//System.out.println(v.config);
			//System.out.println("I m out");
//			if(path_exist==true)
//			{
//				break;
//			}
			if(v!=null)
			{	//System.out.println("I m in");
				neigh=graph.get(v.config);
				visted.put(v.config, v.config);
				Node n=null;
//				System.out.println(List.size());
				//System.out.println(v.config);
				
			
				for (int index=0;index<neigh.size();index++)
				{
					
					n=neigh.get(index);
					//System.out.println(n.config);
					//System.out.println(h.Size());
					
					
					if(!visted.containsKey(n.config))//unvisted node
					{

//						System.out.println("dist "+n.dist);
//						
//						System.out.println(n.dist>v.dist+Cost(v.config,n.config));
						
						if(n.dist>v.dist+Cost(v.config,n.config))
						{
							//visted.put(n.config, n.config);
							//System.out.println("dist "+n.dist);
							//int did=n.dist;
							n.dist=v.dist+Cost(v.config,n.config);
//							if(n.dist==did)
//								System.out.println("OMG");
							
							n.no_of_moves=v.no_of_moves+1;	
							n.prev=v;
							h.insert(n);
//							System.out.println("I 'm stuck");
//							if(n.config.equals(start))
//							{
//								System.out.println("start "+"v: "+v.no_of_moves);
//							}
						//	get the goal
						//	System.out.println(n.config);
							if(n.config.equals(goal))
							{
								goaln=n;
								goaln.config=n.config;
								goaln.cost=n.cost;
								goaln.dist=n.dist;
								goaln.no_of_moves=n.no_of_moves;
								goaln.prev=n.prev;
								//System.out.println(goaln.prev.config);
								path_exist=true;
								//break;
							}
						}
						else if(n.dist==v.dist+Cost(v.config,n.config) )
						{
							if(n.no_of_moves>v.no_of_moves+1)
							{
								//System.out.println(n.no_of_moves+"<-n  v->"+v.no_of_moves);
								n.no_of_moves=v.no_of_moves+1;
								n.prev=v;
								//h.insert(n);///////////
							
								if(n.config.equals(goal))
								{
									goaln=n;
									goaln.config=n.config;
									goaln.cost=n.cost;
									goaln.dist=n.dist;
									goaln.no_of_moves=n.no_of_moves;
									goaln.prev=n.prev;
									//System.out.println(goaln.prev.config);
									path_exist=true;
									//break;
								}
							
							}
						}
					}	
				}	
				
			}
			
		 }
		if(path_exist==false)
		{
			//System.out.println("-1 -1");
			String st="-1 -1"+"\n" ;
			return st;
		}
		else
		{
			//Path=(steps +" "+cost);
			Path=getPath(start,goaln);
			Path=(steps +" "+cost)+"\n"+Path;
			//return Path;
		}
		
		return Path;
	}
	
	public void setDistInf(String s,Vector<Node> List)
	{
		Vector<Node> neigh=null;
		for(int i=0;i<vec.size();i++)
		{
			neigh=graph.get(vec.get(i));
			List.add(neigh.get(0));
			for(int j=0;j<neigh.size();j++)
			{
				//System.out.println(neigh.size());
//				if(neigh.get(j).equals(s))
//					neigh.get(j).dist=Integer.MAX_VALUE;
//				else
					neigh.get(j).dist=Integer.MAX_VALUE;
					//neigh.get(j).no_of_moves;
			}
		}
//		for(int i=0;i<vec.size();i++)
//		{
//			neigh=graph.get(vec.get(i));
//			for(int j=0;j<neigh.size();j++)
//			{
//				System.out.println(neigh.get(j).dist);
//			}
//		}
	}
	public String getPath(String start,Node goal)
	{
		String path="";
		Node v=goal;
		cost=0;
		steps=0;
		List<String> l=new LinkedList<String>();
		while(v!=null)
		{
			//System.out.println("Is this infinite loop? ");
			l.add(0, v.config);
//			path=v.config+"--> "+path;
			v=v.prev;
		}

		for(int i=0;i<l.size()-1;i++)
		{
			String mov="";
			String s1=l.get(i);
			String s2=l.get(i+1);
			cost+=Cost(s1,s2);
			//System.out.println(Cost(s1,s2));
			steps+=1;
			mov=Move(s1,s2);
			path=path+""+mov+" ";
		}
		//System.out.println(cost+" "+steps);
		return path;
	}
//	public void checkheap()
//	{
//		
//	}

	public static void main(String[] args) 
	{
		Vector<String> vec;
		int[] cost= {0,0,0,0,0,0,0,0};
		//String temp="12345678G";
		FileInputStream f;
		FileOutputStream o;
		long startTime = System.currentTimeMillis();
		Puzzle ob =new Puzzle();
		//ob.CreateGraph("12345678G");
//		System.out.println(ob.Dijkstra("12346875G", "12345678G",cost));
//		System.out.println(ob.Dijkstra("12346875G", "123456G78",cost));
//		System.out.println(ob.Dijkstra("12346875G", "12346875G",cost));
//		System.out.println(ob.Dijkstra("12346875G", "12346857G",cost));
		try
		{
			f=new FileInputStream(args[0]);
			o=new FileOutputStream(args[1]);
			Scanner r= new Scanner(f);
			int no_of_Inputs=Integer.parseInt(r.nextLine());
			long endTime   = System.currentTimeMillis();
			String tilStri="";
			String[] cosdis;
			String[] target;
			String towrite="";
			while(r.hasNextLine())
			{	

				target=r.nextLine().split(" ");
				tilStri=r.nextLine();
				cosdis=tilStri.split(" ");
				for(int i=0;i<cosdis.length;i++)
				{
					//System.out.println(cosdis[i]);
					cost[i]=Integer.parseInt(cosdis[i]);

					//System.out.println(cost[i]);
				}
				// for(int i=0;i<8;i++)
				// {
				// 	// System.out.println(Integer.parseInt(String.valueOf(cosdis[i])));
				// 	// System.out.println(cost[i]);
				// 	//System.out.println(cosdis.length);
				// }
				towrite=ob.Dijkstra(target[0], target[1],cost );
				towrite=towrite+"\n";
				byte[] strToBytes = towrite.getBytes();
				//System.out.println(towrite);
				o.write(strToBytes);
			}
			r.close();
			
			
			
			long totalTime = endTime - startTime;
			//System.out.println(totalTime);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found Exception");
		}
		catch(IOException e)
		{
			System.out.println("Something went wrong");
		}
	}
}
class Tile
{
	char s=' ';
	int cost=0;
	Tile(char s,int cost)
	{
		this.s=s;
		this.cost=cost;
	}
}
class Node 
{
	String config="";
	int cost;
	int no_of_moves=0;
	String Move="";
	int dist=0;
	Node prev=null;
	boolean visted=false;
	//Vector<String> neighbours=new Vector<String>();
	int[] neighbourcodes;
	/*Node(String config)
	{
		this.config=config;
	}*/
}
class Heap
{
	Vector<Node> heap=new Vector<Node>();
	int heapSize;//heap element starts from index =1
	public int Size()
	{
		return heap.size()-1;
	}
	public void insert(Node n)
	{
		//System.out.println("hi");
		if(heap.size()==0)
		{
			heap.add(null);//index 0
			heap.add(1, n);//index 1
			//System.out.println("hi");
			return;
		}
		int k=0;
		
		heap.add(n);
		int index=heap.size()-1;
		k=index/2;
		while(k>0)
		{
			//System.out.println("I 'm stuck  "+index);
			if(heap.get(k).dist>n.dist)
			{
				heap.set(index, heap.get(k));
				heap.set(k, n);
				index=k;
				k=k/2;
			}
			else if(heap.get(k).dist==n.dist)
			{
				if(heap.get(k).no_of_moves>n.no_of_moves)
				{
					heap.set(index, heap.get(k));
					heap.set(k, n);
					index=k;
					k=k/2;
				}
				else
				{
					return;
				}
			}
			else
			{
				return;
			}
		}
//		for(int i=1;i<heap.size();i++)
//			System.out.print(heap.get(i).config);
//		System.out.println("");
		
	}
	public Node deleteMin()
	{	if(heap.size()==1 ||heap.size()==0)
			return null;
	
		Node rm=heap.get(1);//minimum element
		//System.out.println("rm "+rm.config+" "+(heap.size()-1));
		heap.set(1, heap.get(heap.size()-1));//last element at the top
		heap.remove(heap.size()-1);//last element
		int index=1;
		while(index<heap.size())
		{
			//System.out.println(index);
			int left=2*index;
			int right=2*index+1;
			//System.out.println(index+"May be ");
			if(right<heap.size())//both left and right child exists
			{
				Node leftch=heap.get(left);
				Node rightch=heap.get(right);
				int min=index;
				if(leftch.dist<rightch.dist)
				{
					min=left;
				}
				else if(leftch.dist>rightch.dist)
				{
					min=right;
				}
				else //both are equal
				{
					if(leftch.no_of_moves<rightch.no_of_moves)
					{
						min=left;
					}
					else
					{
						min=right;
					}
					
				}
				if(heap.get(min).dist<heap.get(index).dist)
				{
					Node temp=heap.get(index);
					heap.set(index, heap.get(min));
					heap.set(min, temp);
					index=min;
				}
				else if(heap.get(min).dist==heap.get(index).dist)
				{
					if(heap.get(index).no_of_moves >heap.get(min).no_of_moves)
					{
						
						Node temp=heap.get(index);
						heap.set(index, heap.get(min));
						heap.set(min, temp);
						index=min;	
					}
					else
					{
						return rm;
					}
//					return rm;
				}
				else
				{
					return rm;
				}
			}
			else if(left<heap.size())//left child exists
			{
				Node leftch=heap.get(left);
				if(leftch.dist<heap.get(index).dist)
				{
					Node temp=heap.get(index);
					heap.set(index, leftch);
					heap.set(left, temp);
					index=left;
				}
				else if(heap.get(index).dist==leftch.dist)//left is bigger than heap.get(index)
				{
					if(heap.get(index).no_of_moves >heap.get(left).no_of_moves)
					{
						
						Node temp=heap.get(index);
						heap.set(index, heap.get(left));
						heap.set(left, temp);
						index=left;	
					}
					else
					{
						return rm;
					}
//					return rm;
				}
				else
				{
					return rm;
				}
			}
			else
			{
				index=2*index;
			}
		}
		return rm;
	}
	public void Print()
	{
//		int index=1;
//		Queue<Node> q=new LinkedList<Node>();
//		q.add(heap.get(1));
//		System.out.print(heap.get(1).config);
//		while(index>heap.size())
//		{
//						
//		}
		for(int i=1;i<heap.size();i++)
		{
			System.out.println(heap.get(i));
		}
			
	}
}
