package kaspersky.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Getter
public enum NavigationMenuTabs {
    Summary("Summary"),
    Devices("Devices"),
    Licenses("Licenses"),
    Downloads("Downloads"),
    Store("Store");

    private String tab;

}


