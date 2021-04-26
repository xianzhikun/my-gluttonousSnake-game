/*    */ package testConnection;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.ServerSocket;
/*    */ 
/*    */ 
/*    */ public class Server
/*    */ {
/*    */   public static void main(String[] args) throws IOException {
/* 10 */     ServerSocket server = new ServerSocket(8088);
/* 11 */     server.accept();
/* 12 */     System.out.println("连接成功..");
/* 13 */     System.out.println("exit..");
/*    */   }
/*    */ }


/* Location:              C:\Users\xianzhikun\Desktop\mytemple\GluttonousSnake\GluttonousSnake.jar!\testConnection\Server.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */