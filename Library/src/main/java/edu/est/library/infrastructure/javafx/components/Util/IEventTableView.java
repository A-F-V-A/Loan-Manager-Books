package edu.est.library.infrastructure.javafx.components.Util;

import edu.est.library.domain.dto.LoanBookDto;
import javafx.scene.control.TableView;

public interface IEventTableView <Entity>{
    void EventTableView(TableView<Entity> tableView);
}
