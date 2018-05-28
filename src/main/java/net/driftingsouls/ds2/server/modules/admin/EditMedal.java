package net.driftingsouls.ds2.server.modules.admin;

import javax.annotation.Nonnull;

import net.driftingsouls.ds2.interfaces.annotations.modules.AdminMenuEntry;
import net.driftingsouls.ds2.server.WellKnownAdminPermission;
import net.driftingsouls.ds2.server.config.Medal;
import net.driftingsouls.ds2.server.modules.admin.editoren.EditorForm8;
import net.driftingsouls.ds2.server.modules.admin.editoren.EntityEditor;

@AdminMenuEntry(category = "Spieler", name = "Orden", permission = WellKnownAdminPermission.EDIT_MEDAL)
public class EditMedal implements EntityEditor<Medal>
{
	@Override
	public Class<Medal> getEntityType()
	{
		return Medal.class;
	}

	@Override
	public void configureFor(@Nonnull EditorForm8<Medal> form)
	{
		form.allowAdd();
		form.field("Name", String.class, Medal::getName, Medal::setName);
		form.field("Nur Admins", Boolean.class, Medal::isAdminOnly, Medal::setAdminOnly);
		form.dynamicContentField("Grafik (normal)", Medal::getImage, Medal::setImage);
		form.dynamicContentField("Grafik (icon)", Medal::getImageSmall, Medal::setImageSmall);
	}
}
