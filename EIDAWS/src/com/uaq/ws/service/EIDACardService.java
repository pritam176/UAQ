package com.uaq.ws.service;

import org.springframework.stereotype.Service;

import com.uaq.ws.pojo.EIDAVO;
import com.uaq.ws.util.PublicDataUtils;
import com.uaq.ws.util.UAQLogger;

import emiratesid.ae.exceptions.MiddlewareException;
import emiratesid.ae.publicdata.CardHolderPublicData;
import emiratesid.ae.publicdata.PublicDataFacade;
import emiratesid.ae.readersmgt.CardInfo;
import emiratesid.ae.readersmgt.PCSCReader;
import emiratesid.ae.readersmgt.ReaderManagement;
import emiratesid.ae.utils.Utils;

@Service(value = "eidaCardService")
public class EIDACardService {
	public static transient UAQLogger logger = new UAQLogger(EIDACardService.class);

	public EIDAVO readEIDA() {
		logger.enter("EIDA Service Start");
		EIDAVO eidaVO = new EIDAVO();
		ReaderManagement readerManagement = new ReaderManagement();
		PCSCReader reader = null;
		try {
			System.out.println("Establishing PCSC Context");
			readerManagement.establishContext();
			System.out.println("Discovering PCSC Readers");
			readerManagement.discoverReaders();
			if (readerManagement.getReaders() == null && readerManagement.getReaders().length == 0) {
				System.out.println("No PCSC Readers found");
			}
			System.out.println(readerManagement.getReaders().length + "PCSC readers discovered");
			for (int i = 0; i < readerManagement.getReaders().length; i++) {
				System.out.println(readerManagement.getReaders()[i].getReaderName());
			}
			reader = readerManagement.getReaders()[0];
			if (reader == null && !reader.isConnected()) {
				System.out.println("Reader is not connected");
			}
			try {
				CardInfo cardInfo = reader.getCardInfo();
				System.out.println(Utils.CharArrayToHex(cardInfo.getCardSerialNumber()));
				PublicDataFacade publicDataFacade = reader.getPublicDataFacade();
				CardHolderPublicData publicData = publicDataFacade.readPublicData(true, false, true, true, false);
				String fullName = PublicDataUtils.removeCommas(Utils.CharArrayToUTF8String(publicData.getFullName()));
				System.out.println(fullName);
				eidaVO.setFullName(fullName);
				String arabicfullName = PublicDataUtils.removeCommas(Utils.CharArrayToUTF8String(publicData.getArabicFullName()));
				System.out.println(arabicfullName);
				eidaVO.setArabicFullname(arabicfullName);
				String idNumber = PublicDataUtils.removeCommas(Utils.CharArrayToUTF8String(publicData.getIDNumber()));

				String firstDdigits = idNumber.substring(0, 3);
				String secondDigits = idNumber.substring(3, 7);
				String thirdDigits = idNumber.substring(7, 14);
				String lastDigits = idNumber.substring(14, idNumber.length());
				idNumber = firstDdigits + "-" + secondDigits + "-" + thirdDigits + "-" + lastDigits;
				//formated xxxx-xxx-xxx
				System.out.println(idNumber);
				eidaVO.setEmiratesID(idNumber);
				String expiryDate = Utils.CharArrayToStringDate(publicData.getExpiryDate());
				System.out.println(expiryDate);
				eidaVO.setEmiratesIDExpiryDate(expiryDate);
				String dob = Utils.CharArrayToStringDate(publicData.getDateOfBirth());
				System.out.println(dob);
				eidaVO.setDob(dob);
			} catch (MiddlewareException e) {
				e.printStackTrace();
			}
			readerManagement.disconnectAllReaders();
			readerManagement.closeContext();
			logger.exit("EIDA Service Exit");
		} catch (Exception e) {
			logger.error("EIDA Error " + e.getMessage());
			readerManagement.disconnectAllReaders();
		}
		return eidaVO;
	}
}
