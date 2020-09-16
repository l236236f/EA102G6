package shopping;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sprod_detail.model.Sprod_detailVO;


public class ShoppingServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Vector<Sprod> buylist = (Vector<Sprod>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");

		if (!action.equals("CHECKOUT")) {

			// 刪除購物車中的書籍
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.removeElementAt(d);
			}
			// 新增書籍至購物車中
			else if (action.equals("ADD")) {
				boolean match = false;

				// 取得後來新增的書籍
				Sprod asprod = getSprod(req);
				//驗證數字不小於0
				if(asprod.getQuantity()>0) {
					// 新增第一本書籍至購物車時
					if (buylist == null) {
						buylist = new Vector<Sprod>();
						buylist.add(asprod);
					} else {
						for (int i = 0; i < buylist.size(); i++) {
							Sprod sprod = buylist.get(i);
							
								// 假若新增的書籍和購物車的書籍一樣時
								if (sprod.getName().equals(asprod.getName())) {
									
									
									
									sprod.setQuantity(sprod.getQuantity()
											+ asprod.getQuantity());
									buylist.setElementAt(sprod, i);
									match = true;
								} // end of if name matches
							} // end of for
		
							// 假若新增的書籍和購物車的書籍不一樣時
							if (!match)
								buylist.add(asprod);
					}
				}
			}else if (action.equals("change")) {
				HttpSession se = req.getSession();
				List<Sprod_detailVO> List = (List<Sprod_detailVO>)se.getAttribute("sprodDetail");
				String prodNo = req.getParameter("prodNo");
				Integer quen = new Integer(req.getParameter("quen"));
				for(Sprod_detailVO s :List) {
					if(s.getProdNo().equals(prodNo)) {
						s.setQuantity(quen);
					}
				}
				
			}

			session.setAttribute("shoppingcart", buylist);
			String url = "/front-end/shop/shopHome.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);

		}

		// 結帳，計算購物車書籍價錢總數
		else if (action.equals("CHECKOUT")) {
//			int total = 0;
//			for (int i = 0; i < buylist.size(); i++) {
//				Sprod order = buylist.get(i);
//				int price = order.getPrice();
//				int quantity = order.getQuantity();
//				total += (price * quantity);
//			}
			
//			利用java8寫法
			double total = buylist.stream()
					.mapToDouble(b -> b.getPrice() * b.getQuantity()).sum();
			
			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/front-end/shop/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}

	private Sprod getSprod(HttpServletRequest req) {

		String quantity = req.getParameter("quantity");
		String venNo = req.getParameter("venNo");
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String prodNo = req.getParameter("prodNo");

		Sprod bk = new Sprod();
		bk.setName(name);
		bk.setVenNo(venNo);
		bk.setPrice((new Integer(price)).intValue());
		bk.setQuantity((new Integer(quantity)).intValue());
		bk.setProdNo(prodNo);
		return bk;
	}
}
