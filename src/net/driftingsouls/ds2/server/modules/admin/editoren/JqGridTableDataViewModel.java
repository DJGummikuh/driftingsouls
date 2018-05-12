package net.driftingsouls.ds2.server.modules.admin.editoren;

import java.util.ArrayList;
import java.util.List;

import net.driftingsouls.ds2.interfaces.annotations.ViewModel;

@ViewModel
public class JqGridTableDataViewModel
{
	public int page;
	public int total;
	public int records;
	public List<JqGridRowDataViewModel> rows = new ArrayList<>();
}
