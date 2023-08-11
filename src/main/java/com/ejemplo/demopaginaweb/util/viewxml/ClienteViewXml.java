package com.ejemplo.demopaginaweb.util.viewxml;

import com.ejemplo.demopaginaweb.entity.Cliente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.Map;

@Component("cliente/listar.xml")
public class ClienteViewXml extends MarshallingView {
    @Autowired
    public ClienteViewXml(Jaxb2Marshaller jaxb2Marshaller) {
        super(jaxb2Marshaller);
    }
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        model.remove("titulo");
        model.remove("page");
        Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");

        model.remove("clientes");

        model.put("clientesList",new ClienteList(clientes.getContent()));
        super.renderMergedOutputModel(model, request, response);
    }
}
