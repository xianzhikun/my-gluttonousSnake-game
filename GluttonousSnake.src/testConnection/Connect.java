/*    */ package testConnection;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.InetSocketAddress;
/*    */ import java.net.Socket;
/*    */ 
/*    */ public class Connect
/*    */ {
/*    */   public static void main(String[] args) {
/* 10 */     Socket sco = new Socket();
/*    */     try {
/* 12 */       sco.connect(new InetSocketAddress("172.18.88.65", 8088));
/* 13 */     } catch (IOException e) {
/*    */       
/* 15 */       e.printStackTrace();
/*    */     } 
/* 17 */     System.out.println("exit..");
/*    */   }
/*    */ }


/* Location:              C:\Users\xianzhikun\Desktop\mytemple\GluttonousSnake\GluttonousSnake.jar!\testConnection\Connect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */