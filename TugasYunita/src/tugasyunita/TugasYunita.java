/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasyunita;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TugasYunita {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in=new Scanner(System.in);
        Random r=new Random();
        ArrayList<karyawan> karyawans = new ArrayList<>();
		
        int menu=0;
        while(menu!=5){
                System.out.println("1. Insert Data Karyawan");
                System.out.println("2. View Data Karyawan");
                System.out.println("3. Update Data Karyawan");
                System.out.println("4. Delete Data Karyawan");
                System.out.println("5. Exit");
                System.out.print("Input pilihan: ");
                menu=Integer.parseInt(in.nextLine());
                if(menu==1){
                        // Generate kode karyawan
                        

                        tambahData(karyawans, 0);
                        int ctr=0;
                        String cur_jabatan=karyawans.get(karyawans.size()-1).getJabatan();
                        for(int i=0; i<karyawans.size()-1; i++){
                            if(karyawans.get(i).getJabatan().equals(cur_jabatan)){
                                ctr++;
                            }
                        }
                        if(ctr%3==0 && ctr != 0){
                                String jabatan=karyawans.get(karyawans.size() - 1).getJabatan();
                                int bonus=0;
                                String bns="";
                                if(jabatan.equals("Supervisor")){
                                        bonus=75;
                                        bns="7.5";
                                }else if(jabatan.equals("Manager")){
                                        bonus=100;
                                        bns="10";
                                }else{
                                        bonus=50;
                                        bns="5";
                                }

                                System.out.print("Bonus sebesar "+bns+"% telah diberikan kepada karyawan dengan id ");
                                for(int i=0; i<karyawans.size()-1; i++){
                                        if(karyawans.get(i).getJabatan().equals(cur_jabatan)){
                                                if(ctr>1) System.out.print(karyawans.get(i).getId()+",");
                                                else System.out.println(karyawans.get(i).getId());
                                                ctr--;
                                                int gaji=karyawans.get(i).getGaji();
                                                gaji=((gaji*bonus)/1000)+gaji;
                                                karyawans.get(i).setGaji(gaji);
                                        }
                                }
                        }

                        System.out.println("ENTER to return");
                        String ent=in.nextLine();
                }else if(menu==2){
                        printKaryawan(karyawans);
                }else if(menu==3){
                        printKaryawan(karyawans);
                        tambahData(karyawans, 1);

                        System.out.println("ENTER to return");
                        String ent=in.nextLine();
                }else if(menu==4){
                        printKaryawan(karyawans);
                        int nomor=-1;
                        System.out.print("Input nomor urutan yang ingin dihapus: ");
                        nomor=Integer.parseInt(in.nextLine());
                        String id_del=karyawans.get(nomor-1).getId();
                        System.out.println("Karyawan dengan kode "+id_del+" berhasil dihapus");
                        // remove at
                        karyawans.remove(nomor-1);

                        System.out.println("ENTER to return");
                        String ent=in.nextLine();
                }
        }
    }
    public static Boolean cekData(String data, int len){
		if(data.length()<len) return false;
		else return true;
	}
	
	public static void tambahData(ArrayList<karyawan> karyawans, int mode){
                Scanner in=new Scanner(System.in);
		int nomor = 0;
		if(mode==1){
			nomor=Integer.parseInt(in.nextLine());
			nomor-=1; //geser 1
		}
		String nama="", jk="", jabatan="";
		//cek nama
		while(!cekData(nama, 3) || (mode == 1 && nama.equals("0"))){
			System.out.print("Input nama karyawan [>=3]: ");
			nama=in.nextLine();
		}
		//cek jenis kelamin
		int cekJk=0;
		while(cekJk==0){
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
			jk=in.nextLine();
			if(jk.equals("Laki-laki")||jk.equals("Perempuan") || (mode == 1 && jk.equals("0"))) cekJk=1;
		}
		//cek jabatan
		int cekJabatan=0;
		while(cekJabatan==0){
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			jabatan=in.nextLine();
			if(jabatan.equals("Supervisor")||jabatan.equals("Manager")||jabatan.equals("Admin") || (mode == 1 && jabatan.equals("0"))) cekJabatan=1;
		}
		
		if(mode==0){
			//TAMBAH KARYAWAN
                        Random r = new Random();
                        String kode;
                        char id=(char) (r.nextInt(25)+'A');
                        char id2=(char) (r.nextInt(25)+'A');
                        int id3=r.nextInt(8999)+1000;
                        kode=Character.toString(id)+Character.toString(id2)+"-"+id3;
                        int gaji = 0;
                        if(jabatan.equals("Supervisor")){
				gaji = 6000000;
			}else if(jabatan.equals("Manager")){
				gaji = 8000000;
			}else{
				gaji = 4000000;
			}
                        karyawans.add(new karyawan(kode, nama, jk, jabatan, gaji));
                        
			System.out.println("Berhasil menambahkan karyawan dengan id "+kode);
			sortKaryawan(karyawans);
			//diurutkan lagi setelah ditambahkan
		}else{
			//EDIT KARYAWAN
                        if(!nama.equals("0"))
                        {
                            karyawans.get(nomor).setNama(nama);
                        }
                        if(!jk.equals("0"))
                        {
                            karyawans.get(nomor).setJk(jk);
                        }
                        if(!jabatan.equals("0"))
                        {
                            karyawans.get(nomor).setJabatan(jabatan);
                            if(jabatan.equals("Supervisor")){
				karyawans.get(nomor).setGaji(6000000);
                            }else if(jabatan.equals("Manager")){
                                    karyawans.get(nomor).setGaji(8000000);
                            }else{
                                    karyawans.get(nomor).setGaji(4000000);
                            }
                        }
			
			System.out.println("Berhasil mengupdtae karyawan dengan id "+karyawans.get(nomor).getId());
			//setelah edit
			sortKaryawan(karyawans);
		}
	}
	
	public static void printKaryawan(ArrayList<karyawan> karyawans){
		System.out.println("|-----|--------------------|------------------------------|---------------|---------------|");
		System.out.println("|No.  |Kode Karyawan       |Nama Karyawan                 |Jabatan        |Gaji Karyawan  |");
		System.out.println("|-----|--------------------|------------------------------|---------------|---------------|");
		for(int i=0; i<karyawans.size(); i++){
			// cetak nomor
			if (i<10) System.out.print("|    "+(i+1)+"|");
			else System.out.print("|   "+(i+1)+"|");
			
			//cetak kode
			System.out.print("             "+karyawans.get(i).getId()+"|");
			
			//cetak nama
			int space=30-karyawans.get(i).getNama().length();
			for(int j=0; j<space; j++) System.out.print(" ");
			System.out.print(karyawans.get(i).getNama()+"|");
			
			//cetak jabatan
			String jabatan=karyawans.get(i).getJabatan();
			if(jabatan.equals("Supervisor")){
				System.out.print("     Supervisor|");
			}else if(jabatan.equals("Manager")){
				System.out.print("        Manager|");
			}else{
				System.out.print("          Admin|");
			}
			
			//cetak gaji (15)
			int gaji=karyawans.get(i).getGaji();
			int n=gaji, ctr=0;
			while(n!=0){
				n/=10;
				ctr++;
			}
			for(int j=0; j<15-ctr; j++){
				System.out.print(" ");
			}
			System.out.print(gaji+"|");
                        System.out.println("");
		}
		System.out.println("|-----|--------------------|------------------------------|---------------|---------------|");
		
	}
	
	public static void sortKaryawan(ArrayList<karyawan> karyawans){
		for(int i=0; i<karyawans.size(); i++){
			for(int j=i+1; j<karyawans.size(); j++){
				if(karyawans.get(i).getNama().compareTo(karyawans.get(j).getNama())>1){
					 karyawan temp = new karyawan(karyawans.get(i).getId(), karyawans.get(i).getNama(),karyawans.get(i).getJk(), karyawans.get(i).getJabatan(), karyawans.get(i).getGaji());
                                        karyawans.get(i).setId(karyawans.get(j).getId());
                                        karyawans.get(i).setNama(karyawans.get(j).getNama());
                                        karyawans.get(i).setJk(karyawans.get(j).getJk());
                                        karyawans.get(i).setJabatan(karyawans.get(j).getJabatan());
                                        karyawans.get(i).setGaji(karyawans.get(j).getGaji());
                                        karyawans.get(j).setId(temp.getId());
                                        karyawans.get(j).setNama(temp.getNama());
                                        karyawans.get(j).setJk(temp.getJk());
                                        karyawans.get(j).setJabatan(temp.getJabatan());
                                        karyawans.get(j).setGaji(temp.getGaji());
				}
			}
		}
	}
}
