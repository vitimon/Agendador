/*     */ package com.thinkopen.skelton.bean;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Map;
/*     */ import java.util.ResourceBundle;

/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.FacesException;
/*     */ import javax.faces.application.FacesMessage;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.SessionScoped;
/*     */ import javax.faces.context.ExternalContext;
/*     */ import javax.faces.context.FacesContext;

/*     */ import org.springframework.security.core.Authentication;
/*     */ import org.springframework.security.core.context.SecurityContextHolder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean
/*     */ @SessionScoped
/*     */ public class SessionBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3835416126235456397L;
/*     */   public static final String MODE_INSERT = "ADD";
/*     */   public static final String MODE_VIEW = "SHOW";
/*     */   public static final String MODE_EDIT = "MODIFY";

/*  44 */   private BigDecimal lastId = null;
			private Authentication sessionAuthentication;
/*     */ 

/*     */   
/*     */   private String currentMode;
/*     */ 
/*     */   
/*     */   private String currentRowId;
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   public void init() {
/*  64 */     sessionAuthentication = SecurityContextHolder.getContext().getAuthentication();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCurrentMode() {
/*  71 */     return this.currentMode;
/*     */   }
/*     */   public void setCurrentMode(String currentMode) {
/*  74 */     this.currentMode = currentMode;
/*     */   }
/*     */   public String getCurrentRowId() {
/*  77 */     return this.currentRowId;
/*     */   }
/*     */   public void setCurrentRowId(String currentRowId) {
/*  80 */     this.currentRowId = currentRowId;
/*     */   }
/*     */   public String getModeInsert() {
/*  83 */     return SessionBean.MODE_INSERT;
/*     */   }
/*     */   public String getModeView() {
/*  86 */     return SessionBean.MODE_VIEW;
/*     */   }
/*     */   public String getModeEdit() {
/*  89 */     return SessionBean.MODE_EDIT;
/*     */   }

/*     */   public static void displayMessage(String tagLibParent, FacesMessage.Severity severity, String keyTitle, String keyMsg) {
/* 103 */     FacesContext context = FacesContext.getCurrentInstance();
/* 104 */     ResourceBundle bundleMsgs = context.getApplication().getResourceBundle(context, "messages");
/* 105 */     FacesContext.getCurrentInstance().addMessage(tagLibParent, new FacesMessage(severity, bundleMsgs.getString(keyTitle), bundleMsgs.getString(keyMsg)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void displayErrors(Map<String, String> errors) {
/* 112 */     FacesContext context = FacesContext.getCurrentInstance();
/* 113 */     ResourceBundle bundleExceptions = context.getApplication().getResourceBundle(context, "exceptions");
/* 114 */     ResourceBundle bundleLanguage = context.getApplication().getResourceBundle(context, "labels");
/*     */     
/* 116 */     for (Map.Entry<String, String> error : errors.entrySet()) {
/* 117 */       String itemError = error.getKey();
/* 118 */       String messageError = error.getValue();
/* 119 */       String[] convertedArgs = new String[1];
/* 120 */       convertedArgs[0] = bundleLanguage.getString(itemError);
/* 121 */       String errorDetails = MessageFormat.format(bundleExceptions.getString(messageError), (Object[])convertedArgs);
/* 122 */       context.addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_ERROR, bundleLanguage.getString(itemError).concat(":"), errorDetails));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void navigateToRoot() {
/*     */     try {
/* 128 */       ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
/* 129 */       externalContext.redirect(String.valueOf(getApplicationUri()) + "/logout");
/* 130 */     } catch (Exception ex) {
/* 131 */       displayMessage("messages", FacesMessage.SEVERITY_ERROR, "error", "failedToRedirect");
/*     */     } 
/*     */   }

/*     */   public String getApplicationUri() {
/*     */     try {
/* 140 */       FacesContext ctxt = FacesContext.getCurrentInstance();
/* 141 */       ExternalContext ext = ctxt.getExternalContext();
/* 142 */       URI uri = new URI(ext.getRequestScheme(), 
/* 143 */           null, ext.getRequestServerName(), ext.getRequestServerPort(), 
/* 144 */           ext.getRequestContextPath(), null, null);
/* 145 */       return uri.toASCIIString();
/* 146 */     } catch (URISyntaxException e) {
/* 147 */       throw new FacesException(e);
/*     */     } 
/*     */   }
/*     */   

/*     */   
/*     */   public BigDecimal getLastId() {
/* 159 */     return this.lastId;
/*     */   }
/*     */   
/*     */   public void setLastId(BigDecimal lastId) {
/* 163 */     this.lastId = lastId;
/*     */   }
/*     */   

/*     */ }
