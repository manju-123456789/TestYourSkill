//package Instagram;
//
//	import java.awt.image.BufferedImage;
//	import java.io.File;
//	import java.net.URL;
//	import java.util.LinkedHashSet;
//	import java.util.List;
//
//	import javax.imageio.ImageIO;
//	import org.apache.poi.ss.usermodel.Row;
//	import org.apache.poi.ss.usermodel.Workbook;
//	import org.genriclib.BaseClass;
//	import org.openqa.selenium.WebElement;
//	import org.testng.annotations.Listeners;
//	import org.testng.annotations.Test;
//
//import com.Instagram.login.Baseclass;
//import com.instagramrepository.HomePage;
//	import com.instagramrepository.LoginPage;
//	import com.instagramrepository.ProfilePage;
//
//
//	public class GettingComments extends Baseclass {
//
//		@Test
//		public void gettingAllComments() throws Throwable {
//
//			driver.get("https://www.instagram.com/");
//
//			String username = eLib.getExcelData("Instagram", 1, 0);
//			String password = eLib.getExcelData("Instagram", 1, 1);
//			String nameToSearch = "java.world";
//			String id = "java.world";
//			String sheetName = "InstagramImageComments";
//			String pathToStorePic = "./images/";
//
//			//LoginPage lp = new LoginPage(driver);
//			//lp.Login(driver, username, password);
//
//			//HomePage hp = new HomePage(driver);
//			hp.clickNotNow(driver);
//			hp.clickNotNow(driver);
//			hp.getSearchBtn().click();
//			hp.enterValueSearch(nameToSearch);
//			Thread.sleep(3000);
//			hp.clickOnIdFromSearch(driver, id);
//
//			ProfilePage pp = new ProfilePage(driver);
//			pp.clickOnImage(driver);
//
//			Workbook wb = eLib.getWorkbook();
//
//			int r = 1;
//			int im = 1;
//
//			for(int k = 0; k< 30; k++) {
//				Thread.sleep(3000);
//
//				try {
//					LinkedHashSet<String> lh = new LinkedHashSet<String>();
//
//					while(true) {
//						List<WebElement> img = pp.getImg();
//
//						for(WebElement wb1 : img) {
//							String src = wb1.getAttribute("src");
//							lh.add(src);
//						}
//
//						try {
//							pp.clickOnNextInsideImage();
//						} catch (Exception e) {
//							break;
//						}
//					}
//					for (String url : lh) {
//						URL imgURL = new URL(url);
//						BufferedImage saveImage = ImageIO.read(imgURL);
//						ImageIO.write(saveImage, "jpg", new File(pathToStorePic+"image"+(im++)+".jpg"));
//					}
//				} catch (Exception e) {
//				}
//
//				Row ro = eLib.createRow(wb, sheetName, r);
//				Row ro2 = eLib.createRow(wb, sheetName, (r + 1));
//
//				try {
//					String postedDate = pp.postedDate();
//					String caption = pp.getCaption(driver, id);
//					String likes = pp.likes();
//
//					int intLikes = Integer.parseInt(likes);
//					int totalLikes = intLikes+1;
//
//					eLib.createCellAndSetCellValue(ro, 0, postedDate);
//					eLib.createCellAndSetCellValue(ro, 1, caption);
//					eLib.createCellAndSetCellValue(ro2, 1, ""+totalLikes+""+" likes");
//				} catch (Exception e) {
//					String postedDate = pp.postedDate();
//
//					try {
//						String likes = pp.likes();
//
//						try {
//							int intLikes = Integer.parseInt(likes);
//							int totalLikes = intLikes+1;
//							eLib.createCellAndSetCellValue(ro2, 1, ""+totalLikes+""+" likes");
//						}
//						catch(Exception e1) {
//							String like = likes.replace(",", "");
//							eLib.createCellAndSetCellValue(ro2, 1, like +" likes");
//						}
//					}
//					catch(Exception e1){
//						String likes = pp.alternativeLikes();
//						eLib.createCellAndSetCellValue(ro2, 1, ""+likes+""+" likes");
//					}
//
//					eLib.createCellAndSetCellValue(ro, 0, postedDate);
//					eLib.createCellAndSetCellValue(ro, 1, "No Caption");
//				}
//
//				List<WebElement> totalComments = pp.getTotalComents();
//
//				for (int i = 0; i < totalComments.size(); i++) {
//					String name = totalComments.get(i).getText();
//					Row ro1 = eLib.getRow(wb, sheetName, r);
//					eLib.createCellAndSetCellValue(ro1, (i + 2), name);
//					String comment = pp.getComment(driver, name);
//					Row ro3 = eLib.getRow(wb, sheetName, (r + 1));
//					eLib.createCellAndSetCellValue(ro3, (i + 2), comment);
//				}
//
//				try {
//					pp.clickOnNextBtn();
//				} catch (Exception e) {
//					break;
//				}
//				r += 3;
//			}
//			eLib.writeDataToExcel(wb);
//		}
//
//	}
//
//
