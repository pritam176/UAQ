package com.mod.financial.view.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Hashtable;

import javax.faces.context.FacesContext;

import javax.jms.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.http.HttpServletRequest;

import javax.swing.JOptionPane;

public class QueueSend {
    public final static String JNDI_FACTORY =
        "weblogic.jndi.WLInitialContextFactory";
    public final static String JMS_FACTORY = "CF1";
    public final static String QUEUE = "Queue1";
    private QueueConnectionFactory qconFactory;
    private QueueConnection qcon;
    private QueueSession qsession;
    private QueueSender qsender;
    private Queue queue;
    private TextMessage msg;

    public void init(Context ctx, String queueName) throws NamingException,
                                                           JMSException {
        qconFactory = (QueueConnectionFactory)ctx.lookup(JMS_FACTORY);
        qcon = qconFactory.createQueueConnection();
        qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        queue = (Queue)ctx.lookup(queueName);
        qsender = qsession.createSender(queue);
        msg = qsession.createTextMessage();
        qcon.start();
    }

    public void send(String message) throws JMSException {
        msg.setText(message);
        msg.setObjectProperty("ip", ADFUtils.getReuqestObject().getRemoteAddr());
        qsender.send(msg);
    }

    public void close() throws JMSException {
        qsender.close();
        qsession.close();
        qcon.close();
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java examples.jms.queue.QueueSend WebLogicURL");
            return;
        }
        InitialContext ic = getInitialContext(args[0]);
        QueueSend qs = new QueueSend();
        qs.init(ic, QUEUE);
        readAndSend(qs);
        qs.close();
    }

    public static void readAndSend(QueueSend qs) throws IOException,
                                                        JMSException {
        BufferedReader msgStream =
            new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        boolean quitNow = false;
        //do {
        System.out.print("Enter message (\"quit\" to quit): \n");
        line = JOptionPane.showInputDialog("Enter Message");
        //line = msgStream.readLine();
        if (line != null && line.trim().length() != 0) {
            qs.send(line);
            System.out.println("JMS Message Sent: " + line + "\n");
            quitNow = line.equalsIgnoreCase("quit");
        }
        //} while (!quitNow);
    }

    public static InitialContext getInitialContext(String url) throws NamingException {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, url);
        return new InitialContext(env);
    }
}
