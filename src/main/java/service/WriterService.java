package service;

import lombok.RequiredArgsConstructor;
import model.Writer;
import repository.WriterRepository;
import repository.jdbc.JdbcWriterRepositoryImpl;

import java.util.List;

@RequiredArgsConstructor
public class WriterService {
    private final WriterRepository writerRepository;

    public WriterService() {
        this.writerRepository = new JdbcWriterRepositoryImpl();
    }

    public List<Writer> getAllWriter() {
        return writerRepository.showAll();
    }

    public Writer getWriterById(Long id) {
        return writerRepository.showById(id);
    }

    public Writer addNewWriter(Writer writer) {
        return writerRepository.add(writer);
    }

    public Writer updateWriter(Long id, Writer writer) {
        return writerRepository.update(id, writer);
    }

    public void deleteWriterById(Long id) {
        writerRepository.deleteById(id);
    }
}
