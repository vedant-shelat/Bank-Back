package com.bank.dto;

import com.bank.model.History;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryDataTableDTO {

    private Long length;
    private List<HistoryDTO> histories = new ArrayList<>();

    public HistoryDataTableDTO() {}

    public HistoryDataTableDTO(Page<History> histories) {
        this.length = histories.getTotalElements();
        this.histories = histories.stream().map(h-> new HistoryDTO(h)).collect(Collectors.toList());
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public List<HistoryDTO> getHistories() {
        return histories;
    }

    public void setHistories(List<HistoryDTO> histories) {
        this.histories = histories;
    }
}
