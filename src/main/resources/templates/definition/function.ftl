-- Function: ${function.fullName}

-- @GENERATOR.NAME@
-- @GENERATOR.VERSION@
<#if dbversion?? && addDbVersion?? && addDbVersion == true >
-- ${dbversion}
</#if>

<#if function.content?? >
${function.content}
</#if>
