package com.sightcorner.zuul.web;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;


@WebFilter(urlPatterns = "/*", filterName = "initialFilter")
public class InitialFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitialFilter.class);

    public InitialFilter() {
        LOGGER.info("InitialFilter 构造函数初始化");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("进入 InitialFilter 的 init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("进入 InitialFilter 的 doFilter");
        //检查证书
        this.validateCertificate(servletRequest);

        //如果证书合法，则可以执行下一步
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void validateCertificate(ServletRequest servletRequest) {
        X509Certificate[] certificates = (X509Certificate[]) servletRequest.getAttribute("javax.servlet.request.X509Certificate");
        if(null == certificates) {
            return;
        }
        for(X509Certificate certificate : certificates) {
            try {
                certificate.checkValidity();
                LOGGER.info(certificate.toString());

            } catch (CertificateExpiredException e) {
                e.printStackTrace();
                LOGGER.info("CertificateExpiredException 证书已过期");
            } catch (CertificateNotYetValidException e) {
                e.printStackTrace();
                LOGGER.info("CertificateNotYetValidException 证书已过期");
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.info("证书有问题");
            }
        }
    }

    @Override
    public void destroy() {
        LOGGER.info("进入 InitialFilter 的 destroy");

    }
}
