package com.book.library.utill;

import java.awt.Color;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.OutputStream;  
import java.util.List;

import com.book.library.po.XtStudent;
import com.book.library.po.XtTeacher;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;  
import com.lowagie.text.DocumentException;  
import com.lowagie.text.Element;  
import com.lowagie.text.Font;  
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;  
import com.lowagie.text.PageSize;  
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;  
import com.lowagie.text.pdf.PdfWriter;  
import com.lowagie.text.rtf.RtfWriter2; 

/**
 * itext 操作类
 * @author liaopingzhu
 *
 */
public class ItextManager {
	private Font font;  
    private BaseFont bfChinese;  
  
    public ItextManager() throws Exception {  
        // 设置中文字体  
        bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        font = new Font(bfChinese);
        font.setSize(15);  
        font.setStyle(FontFactory.HELVETICA);  
//      font.setStyle(Font.BOLD);//加粗  
        font.setColor(new Color(0,0,0));  
    }  
  
    public static ItextManager getInstance() throws Exception {  
        return new ItextManager();  
    }  
    
    //导出学生表
    public void createRtfContextStu(List<XtStudent> students,OutputStream out,String type) throws FileNotFoundException, IOException{
    	Document doc = new Document(PageSize.A4,20,20,20,20);
    	try {
    		if("word".equals(type)){  
                RtfWriter2.getInstance(doc, out);  
            }else if("pdf".equals(type)){  
                PdfWriter.getInstance(doc, out);  
            }  
    		
    		//----------------------------itext设置表格---------------//
            HeaderFooter footer=new HeaderFooter(new Phrase(), true);
            footer.setBorder(0);
            footer.setAlignment(Element.ALIGN_CENTER);
            doc.setFooter(footer);
            //----------------------------itext设置表格---------------//
    		
            doc.open(); 
            //构建一个段落
            Paragraph par = new Paragraph("学生表",font);
            par.setAlignment(Element.ALIGN_CENTER);
            doc.add(par);
            Table table = new Table(6);
            table.setBorder(1);
            Cell cell1 = new Cell(new Phrase("姓名",font));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_CENTER);
            cell1.setHeader(true);
            cell1.setBackgroundColor(Color.GRAY);
            
            Cell cell2 = new Cell(new Phrase("学号",font));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_CENTER);
            cell2.setHeader(true);
            cell2.setBackgroundColor(Color.GRAY);
            
            Cell cell3 = new Cell(new Phrase("专业",font));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_CENTER);
            cell3.setHeader(true);
            cell3.setBackgroundColor(Color.GRAY);
            
            Cell cell4 = new Cell(new Phrase("班级",font));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setVerticalAlignment(Element.ALIGN_CENTER);
            cell4.setHeader(true);
            cell4.setBackgroundColor(Color.GRAY);
            
            Cell cell5 = new Cell(new Phrase("电话",font));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_CENTER);
            cell5.setHeader(true);
            cell5.setBackgroundColor(Color.GRAY);
            
            Cell cell6 = new Cell(new Phrase("邮箱",font));
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6.setVerticalAlignment(Element.ALIGN_CENTER);
            cell6.setHeader(true);
            cell6.setBackgroundColor(Color.GRAY);
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.endHeaders();
            
            XtStudent student = null;
            Paragraph title1 = null;  
            for(int i=0;i<students.size();i++){
            	student = students.get(i);
            	
            	Cell cell11 = new Cell(new Phrase(student.getName(),font));
            	Cell cell22 = new Cell(new Phrase(student.getCode(),font));
            	Cell cell33 = new Cell(new Phrase(student.getMajor().toString(),font));
            	Cell cell44 = new Cell(new Phrase(student.getSclass(),font));
            	Cell cell55 = new Cell(new Phrase(student.getTel(),font));
            	Cell cell66 = new Cell(new Phrase(student.getEmail(),font));
            	
                cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell11.setVerticalAlignment(Element.ALIGN_CENTER);
                cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell22.setVerticalAlignment(Element.ALIGN_CENTER);
                cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell33.setVerticalAlignment(Element.ALIGN_CENTER);
                cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell44.setVerticalAlignment(Element.ALIGN_CENTER);
                cell55.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell55.setVerticalAlignment(Element.ALIGN_CENTER);
                cell66.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell66.setVerticalAlignment(Element.ALIGN_CENTER);
            	
                table.addCell(cell11);
                table.addCell(cell22);
                table.addCell(cell33);
                table.addCell(cell44);
                table.addCell(cell55);
                table.addCell(cell66);
            }
            doc.add(table);
            doc.newPage();
            doc.close();
		} catch (DocumentException e) {  
            e.printStackTrace();  
        }
    }
    
    //导出导师表
    public void createRtfContextTeacher(List<XtTeacher> teachers,OutputStream out,String type) throws FileNotFoundException, IOException{
    	Document doc = new Document(PageSize.A4,20,20,20,20);
    	try {
    		if("word".equals(type)){  
                RtfWriter2.getInstance(doc, out);  
            }else if("pdf".equals(type)){  
                PdfWriter.getInstance(doc, out);  
            }
    		
    		//----------------------------itext设置表格---------------//
    		//构建页脚
            HeaderFooter footer=new HeaderFooter(new Phrase(), true);
            //设置页脚是否有边框
            //0表示无
            //1上边框
            //2下边框
            //3上下边框都有 默认都有
            //设置页脚是否有边框
            footer.setBorder(0);
            //footer.setBorder(1);
            //footer.setBorder(2);
            //footer.setBorder(3);
            //设置页脚的对齐方式
            footer.setAlignment(Element.ALIGN_CENTER);
            //将页脚添加到文档中
            doc.setFooter(footer);
            //----------------------------itext设置表格---------------//
            
            doc.open(); 
            //构建一个段落
            Paragraph par = new Paragraph("教师表",font);
            par.setAlignment(Element.ALIGN_CENTER);
            doc.add(par);
            
            //创建四列表格
            Table table = new Table(6);
            table.setBorder(1);
            //创建表头
            Cell cell1 = new Cell(new Phrase("姓名",font));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_CENTER);
            cell1.setHeader(true);
            cell1.setBackgroundColor(Color.GRAY);
            
            Cell cell2 = new Cell(new Phrase("教师编号",font));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_CENTER);
            cell2.setHeader(true);
            cell2.setBackgroundColor(Color.GRAY);
            
            Cell cell3 = new Cell(new Phrase("教研室",font));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_CENTER);
            cell3.setHeader(true);
            cell3.setBackgroundColor(Color.GRAY);
            
            Cell cell4 = new Cell(new Phrase("职称",font));
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setVerticalAlignment(Element.ALIGN_CENTER);
            cell4.setHeader(true);
            cell4.setBackgroundColor(Color.GRAY);
            
            Cell cell5 = new Cell(new Phrase("电话",font));
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setVerticalAlignment(Element.ALIGN_CENTER);
            cell5.setHeader(true);
            cell5.setBackgroundColor(Color.GRAY);
            
            Cell cell6 = new Cell(new Phrase("邮箱",font));
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6.setVerticalAlignment(Element.ALIGN_CENTER);
            cell6.setHeader(true);
            cell6.setBackgroundColor(Color.GRAY);
            
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            //添加此代码后每页都会显示表头
            table.endHeaders();
            
            XtTeacher teacher = null;
            Paragraph title1 = null;  
            for(int i=0;i<teachers.size();i++){

            	teacher = teachers.get(i);
            	
            	Cell cell11 = new Cell(new Phrase(teacher.getName(),font));
            	Cell cell22 = new Cell(new Phrase(teacher.getCode(),font));
            	Cell cell33 = new Cell(new Phrase(teacher.getClassroom(),font));
            	Cell cell44 = new Cell(new Phrase(teacher.getTitle(),font));
            	Cell cell55 = new Cell(new Phrase(teacher.getTel(),font));
            	Cell cell66 = new Cell(new Phrase(teacher.getEmail(),font));
            	
            	//单元格水平对齐方式
                cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
                //单元格垂直对齐方式
                cell11.setVerticalAlignment(Element.ALIGN_CENTER);
                cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell22.setVerticalAlignment(Element.ALIGN_CENTER);
                cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell33.setVerticalAlignment(Element.ALIGN_CENTER);
                cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell44.setVerticalAlignment(Element.ALIGN_CENTER);
                cell55.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell55.setVerticalAlignment(Element.ALIGN_CENTER);
                cell66.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell66.setVerticalAlignment(Element.ALIGN_CENTER);
            	
                table.addCell(cell11);
                table.addCell(cell22);
                table.addCell(cell33);
                table.addCell(cell44);
                table.addCell(cell55);
                table.addCell(cell66);
            }
            //将表格添加到新的文档
            doc.add(table);
            //创建新的一页
            doc.newPage();
            doc.close();
		} catch (DocumentException e) {
            e.printStackTrace();
        }
    }
  
//    public void createRtfContext(List<News> newsList, List<String> imgList, OutputStream out,String type) {  
//        Document doc = new Document(PageSize.A4, 20, 20, 20, 20);  
//        try {  
//            if("word".equals(type)){  
//                RtfWriter2.getInstance(doc, out);  
//            }else if("pdf".equals(type)){  
//                PdfWriter.getInstance(doc, out);  
//            }  
//            doc.open();  
//            News news = null;  
//            Paragraph title1 = null;  
//            for (int i = 0; i < newsList.size(); i++) {  
//                news = newsList.get(i);  
//                // 标题  
//                Paragraph title = new Paragraph(news.getTitle(), font);  
//                title.setAlignment(Element.ALIGN_LEFT);  
//                doc.add(title);  
//  
//                // 换行  
//                title1 = new Paragraph("\n");  
//                doc.add(title1);  
//  
//                // 正文  
//                Paragraph content = new Paragraph(news.getContent(), font);  
//                content.setAlignment(Element.ALIGN_LEFT);  
//                doc.add(content);  
//  
//                // 换行  
//                title1 = new Paragraph("\n");  
//                doc.add(title1);  
//  
//                // 站点  
//                Paragraph site = new Paragraph(news.getSite(), font);  
//                content.setAlignment(Element.ALIGN_LEFT);  
//                doc.add(site);  
//  
//                // 换行  
//                title1 = new Paragraph("\n");  
//                doc.add(title1);  
//  
//                // 发布时间  
//                Paragraph publishTime = new Paragraph(news.getPublishTime(), font);  
//                content.setAlignment(Element.ALIGN_LEFT);  
//                doc.add(publishTime);  
//  
//                // 换行  
//                title1 = new Paragraph("\n");  
//                doc.add(title1);  
//  
//            }  
//  
//            Image img = null;  
//            for (int j = 0; j < imgList.size(); j++) {  
//                // 图片  
//                img = Image.getInstance(imgList.get(j));  
//                float heigth = img.getHeight();  
//                float width = img.getWidth();  
//                int percent = getPercent2(heigth, width);  
//                img.setAlignment(Image.MIDDLE);  
//                img.scalePercent(percent + 3);// 表示是原来图像的比例;  
//                doc.add(img);  
//            }  
//  
//            doc.close();  
//        } catch (FileNotFoundException e) {  
//            e.printStackTrace();  
//        } catch (DocumentException e) {  
//            e.printStackTrace();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
//    }  
  
    /** 
     * 第一种解决方案 在不改变图片形状的同时，判断，如果h>w，则按h压缩，否则在w>h或w=h的情况下，按宽度压缩 
     *  
     * @param h 
     * @param w 
     * @return 
     */  
  
    public static int getPercent(float h, float w) {  
        int p = 0;  
        float p2 = 0.0f;  
        if (h > w) {  
            p2 = 297 / h * 100;  
        } else {  
            p2 = 210 / w * 100;  
        }  
        p = Math.round(p2);  
        return p;  
    }  
  
    /** 
     * 第二种解决方案，统一按照宽度压缩 这样来的效果是，所有图片的宽度是相等的，自我认为给客户的效果是最好的 
     *  
     * @param args 
     */  
    public static int getPercent2(float h, float w) {  
        int p = 0;  
        float p2 = 0.0f;  
        p2 = 530 / w * 100;  
        p = Math.round(p2);  
        return p;  
    }  
    
    //pdf文档中文字符处理
    public static Font ChineseFont()
    {
        BaseFont baseFont=null;
        try {
            baseFont=BaseFont.createFont("STSong-Light","UniGB-UCS2-H", true);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Font chineseFont=new Font(baseFont,8,Font.NORMAL,Color.BLUE);
        return chineseFont;
    }
}
