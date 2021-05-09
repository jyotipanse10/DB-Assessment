package com.trade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TradeService {

	static SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
	static List<Trade> list = new CopyOnWriteArrayList<Trade>();

	public static List<Trade> validateNewTrade(Trade trade){//Validate new trade
		trade.setCreateDate(simpleDate.format(new Date()));
		expired(trade);
		validateMaturity(trade);
		if(list.size()>0) {
			int index = -1;
			for (Trade t : list) {
				if(t.getTradeId().equals(trade.getTradeId())) {
					if(t.getVersion() == trade.getVersion()) {// override the existing trade
						index++;
						t.setCounterPartyId(trade.getCounterPartyId());
						t.setBookId(trade.getBookId());
						t.setMaturityDate(trade.getMaturityDate());
						list.set(index, t);
						 break;
					}else if(t.getVersion() < trade.getVersion()) {// add trade bcoz version is greater
						list.add(trade);
					}
				}
			 
				expired(t);
			}
			if(index< 0)
			list.add(trade);
		}else list.add(trade);
		return list;

	}

	public static void validateMaturity(Trade trade){//not allow the trade which has less maturity date then today date
		String date=trade.getMaturityDate();
		try {
			//String maturityDate=simpleDate.format(date);
			Date mDate= simpleDate.parse(date);
			String currentDate=simpleDate.format(new Date());
			Date cDate=simpleDate.parse(currentDate);
			if(!(mDate.compareTo(cDate)<0)) {
				list.add(trade);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}  
	}
	public static void expired(Trade trade) {//Update expire flag if in a store the trade crosses the maturity date.
	
			String date=trade.getMaturityDate();
			try {
				//String maturityDate=simpleDate.format(date);
				Date mDate= simpleDate.parse(date); 
				String currentDate=simpleDate.format(new Date());
				Date cDate=simpleDate.parse(currentDate);
				if(mDate.compareTo(cDate)>=0) {
					trade.setExpired("Y");
				}else
					trade.setExpired("N");
			} catch (ParseException e) {
				e.printStackTrace();
			}  
		
	}


}
