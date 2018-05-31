package net.driftingsouls.ds2.interfaces.framework.templates;

import net.driftingsouls.ds2.interfaces.framework.pipeline.Response;
import net.driftingsouls.ds2.interfaces.framework.pipeline.ViewResult;

import java.io.IOException;
import java.util.Map;

public interface ITemplateEngine extends ViewResult {
    boolean setFile(String handle, String filename);

    void setBlock(String parent, String handle, String replace);

    void setBlock(String parent, String handle);

    void setVar(String varname, Object value);

    void setVar(Object... list);

    String parse(String target, String handle, boolean append);

    String parse(String target, String handle);

    Map<String,Object> get_vars();

    String getBlockReplacementVar(String varname);

    boolean isVarTrue(String varname);

    String getVar(String varname);

    Number getNumberVar(String varname);

    void p(String varname) throws IOException;

    void registerBlockItrnl(String name, String filehandle, String parent);

    void start_record();

    void stop_record();

    void clear_record();

    @Override
    void writeToResponse(Response response) throws IOException;
}
