package Omok;

public class Pan {
   public int[][] pan=new int[21][21];
   public void Pan(int pan[][]){
      int i,j;
      System.out.print("\n");
      System.out.print("   ");
      for(i=1;i<=20;i++){
         if(i<=9){
            System.out.printf(" %d",i);
         }
         else{
            System.out.printf(" %d",i);
         }
      }
      System.out.print("\n");
       
      for(i=1;i<=20;i++){
         if(i<=9){
            System.out.printf("%d  ",i);
         }
         else{
            System.out.printf("%d ",i);
         }
         for(j=1;j<=20;j++){
        	 if(pan[i][j]==0){
        		 System.out.printf("┼");
        		 }
        	 else if(pan[i][j]>0){
               System.out.print("●");
            }
            else{
               System.out.print("○");
            }
         }
         System.out.print("\n");
      }
      System.out.print("\n");
   }

   
   public int win(int pan[][]){
      int i,j;
      int line;
      int garo=0;
      int sero=0;
      int rightdg=0;
      int leftdg=0;
       
      for(i=1;i<=20;i++){
         for(j=1;j<=16;j++){
            line=pan[i][j]+pan[i][j+1]+pan[i][j+2]+pan[i][j+3]+pan[i][j+4];
            if(line==5){
               garo=1;
            }
            else if(line==-5){
               garo=-1;
            }
         }
      }
       
      for(i=1;i<=16;i++){
         for(j=1;j<=20;j++)
         {
            line=pan[i][j]+pan[i+1][j]+pan[i+2][j]+pan[i+3][j]+pan[i+4][j];
            if(line==5){
               sero=1;
            }
            else if(line==-5){
               sero=-1;
            }
         }
      }
      for(i=1;i<=16;i++){
         for(j=1;j<=16;j++){
            line=pan[i][j]+pan[i+1][j+1]+pan[i+2][j+2]+pan[i+3][j+3]+pan[i+4][j+4];
            if(line==5){
               rightdg=1;
            }
            else if(line==-5){
               rightdg=-1;
            }
         }
      }
       
      for(i=1;i<=16;i++){
         for(j=5;j<=20;j++){
            line=pan[i][j]+pan[i+1][j-1]+pan[i+2][j-2]+pan[i+3][j-3]+pan[i+4][j+-4];
            if(line==5){
               leftdg=1;
            }
            else if(line==-5){
               leftdg=-1;
            }
         }
      }
       
      if(garo==1 || sero==1 || rightdg==1 || leftdg==1){
         return 1;
      }
       
      else if(garo==-1 || sero==-1 || rightdg==-1 || leftdg==-1){
         return -1;
      }
      else
         return 0;
      }
}