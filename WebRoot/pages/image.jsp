<%@ page contentType="image/jpeg" import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*" pageEncoding="GBK"%>
<%!
Color getRandColor(int fc,int bc){//������Χ��������ɫ
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
        }
%>
<%
//����ҳ�治����
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);

// ���ڴ��д���ͼ��
// ͨ����������޸�ͼƬ��С
int width=80, height=25;
BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

// ��ȡͼ��������
// g�൱�ڱ�
Graphics g = image.getGraphics();

//���������
Random random = new Random();

// �趨����ɫ
g.setColor(getRandColor(200,250));
// ��һ��ʵ�ĵĳ�������Ϊ����
g.fillRect(0, 0, width, height);

//�趨����
g.setFont(new Font("����",Font.PLAIN,18));

//���߿�
//g.setColor(new Color());
//g.drawRect(0,0,width-1,height-1);


// �������155�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
g.setColor(getRandColor(160,200));
for (int i=0;i<155;i++)
{
	int x = random.nextInt(width);
	int y = random.nextInt(height);
        int xl = random.nextInt(12);
        int yl = random.nextInt(12);
	g.drawLine(x,y,x+xl,y+yl);
}

// ȡ�����������֤��(4λ����)
//String rand = request.getParameter("rand");
//rand = rand.substring(0,rand.indexOf("."));
String sRand="";
// ���Ҫʹ�����ģ����붨���ֿ⣬����ʹ��������ж���
// ����ֱ��д���Ļ�����룬���뽫����ת��Ϊunicode����
String[] str = {"A","B","C","D","E","F","G","H","J","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","m","n","p","s","t","u","v","w","x","y","z","1","2","3","4","5","6","7","8","9"} ;


for (int i=0;i<4;i++){
    String rand=str[random.nextInt(str.length)];
    sRand+=rand;
    // ����֤����ʾ��ͼ����
    g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));//���ú�����������ɫ��ͬ����������Ϊ����̫�ӽ�������ֻ��ֱ������
    g.drawString(rand,16*i+6,19);
}

// ����֤�����SESSION
session.setAttribute("rand",sRand);


// ͼ����Ч
g.dispose();

// ���ͼ��ҳ��
ImageIO.write(image, "JPEG", response.getOutputStream());
out.clear();
out = pageContext.pushBody();
%> 