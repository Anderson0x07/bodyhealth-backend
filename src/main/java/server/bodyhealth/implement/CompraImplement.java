package server.bodyhealth.implement;

import server.bodyhealth.entity.Compra;
import server.bodyhealth.repository.CompraRepository;
import server.bodyhealth.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraImplement implements CompraService {
    @Autowired
    private CompraRepository compraRepository;
    @Override
    public List<Compra> listarCompras() {
        return (List<Compra>) compraRepository.findAll();
    }

    @Override
    public void guardar(Compra compra) {
        compraRepository.save(compra);
    }

    @Override
    public void eliminar(Compra compra) {
        compraRepository.delete(compra);
    }

    @Override
    public Compra encontrarCompra(int id_compra) {

        return compraRepository.findById(id_compra).orElse(null);
    }
}
