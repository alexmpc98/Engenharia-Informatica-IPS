#pragma checksum "C:\Users\alexa\Desktop\Engenharia-Informatica-IPS\3º Ano\Anual\Programação Visual (PV)\Labs\Lab 3\CineSetúbal\Views\Filmes\Cartazes.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "e5d07d6e8571b5702b3c7da24833f5bc93a983c5"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Filmes_Cartazes), @"mvc.1.0.view", @"/Views/Filmes/Cartazes.cshtml")]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#nullable restore
#line 1 "C:\Users\alexa\Desktop\Engenharia-Informatica-IPS\3º Ano\Anual\Programação Visual (PV)\Labs\Lab 3\CineSetúbal\Views\_ViewImports.cshtml"
using CineSetúbal;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\alexa\Desktop\Engenharia-Informatica-IPS\3º Ano\Anual\Programação Visual (PV)\Labs\Lab 3\CineSetúbal\Views\_ViewImports.cshtml"
using CineSetúbal.Models;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"e5d07d6e8571b5702b3c7da24833f5bc93a983c5", @"/Views/Filmes/Cartazes.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"6bf9a5b25949ec6b88bbc3142df8b984d9deef34", @"/Views/_ViewImports.cshtml")]
    public class Views_Filmes_Cartazes : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<IEnumerable<Filme>>
    {
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_0 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("asp-action", "Index", global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        #line hidden
        #pragma warning disable 0649
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperExecutionContext __tagHelperExecutionContext;
        #pragma warning restore 0649
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner __tagHelperRunner = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner();
        #pragma warning disable 0169
        private string __tagHelperStringValueBuffer;
        #pragma warning restore 0169
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __backed__tagHelperScopeManager = null;
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __tagHelperScopeManager
        {
            get
            {
                if (__backed__tagHelperScopeManager == null)
                {
                    __backed__tagHelperScopeManager = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager(StartTagHelperWritingScope, EndTagHelperWritingScope);
                }
                return __backed__tagHelperScopeManager;
            }
        }
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.AnchorTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper;
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            WriteLiteral("\r\n<div>\r\n    <h4>Filme</h4>\r\n    <hr />");
#nullable restore
#line 5 "C:\Users\alexa\Desktop\Engenharia-Informatica-IPS\3º Ano\Anual\Programação Visual (PV)\Labs\Lab 3\CineSetúbal\Views\Filmes\Cartazes.cshtml"
           foreach (var item in Model)
    {

#line default
#line hidden
#nullable disable
            WriteLiteral("        <dl class=\"row\">\r\n\r\n            <dt class=\"col-sm-2\">\r\n                ");
#nullable restore
#line 10 "C:\Users\alexa\Desktop\Engenharia-Informatica-IPS\3º Ano\Anual\Programação Visual (PV)\Labs\Lab 3\CineSetúbal\Views\Filmes\Cartazes.cshtml"
           Write(Html.DisplayNameFor(model => model.Titulo));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n            </dt>\r\n            <dd class=\"col-sm-10\">\r\n                ");
#nullable restore
#line 13 "C:\Users\alexa\Desktop\Engenharia-Informatica-IPS\3º Ano\Anual\Programação Visual (PV)\Labs\Lab 3\CineSetúbal\Views\Filmes\Cartazes.cshtml"
           Write(Html.DisplayFor(modelItem => item.Titulo));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n            </dd>\r\n            <dt class=\"col-sm-2\">\r\n                ");
#nullable restore
#line 16 "C:\Users\alexa\Desktop\Engenharia-Informatica-IPS\3º Ano\Anual\Programação Visual (PV)\Labs\Lab 3\CineSetúbal\Views\Filmes\Cartazes.cshtml"
           Write(Html.DisplayNameFor(model => model.Diretor));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n            </dt>\r\n            <dd class=\"col-sm-10\">\r\n                ");
#nullable restore
#line 19 "C:\Users\alexa\Desktop\Engenharia-Informatica-IPS\3º Ano\Anual\Programação Visual (PV)\Labs\Lab 3\CineSetúbal\Views\Filmes\Cartazes.cshtml"
           Write(Html.DisplayFor(modelItel => item.Diretor));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n            </dd>\r\n\r\n            <dt class=\"col-sm-2\">\r\n                ");
#nullable restore
#line 23 "C:\Users\alexa\Desktop\Engenharia-Informatica-IPS\3º Ano\Anual\Programação Visual (PV)\Labs\Lab 3\CineSetúbal\Views\Filmes\Cartazes.cshtml"
           Write(Html.DisplayNameFor(model => model.Ano));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n            </dt>\r\n            <dd class=\"col-sm-10\">\r\n                ");
#nullable restore
#line 26 "C:\Users\alexa\Desktop\Engenharia-Informatica-IPS\3º Ano\Anual\Programação Visual (PV)\Labs\Lab 3\CineSetúbal\Views\Filmes\Cartazes.cshtml"
           Write(Html.DisplayFor(modelItem => item.Ano));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n            </dd>\r\n\r\n            \r\n        </dl>\r\n        <hr />\r\n");
#nullable restore
#line 32 "C:\Users\alexa\Desktop\Engenharia-Informatica-IPS\3º Ano\Anual\Programação Visual (PV)\Labs\Lab 3\CineSetúbal\Views\Filmes\Cartazes.cshtml"
    }

#line default
#line hidden
#nullable disable
            WriteLiteral("    </div>\r\n<div>\r\n    ");
#nullable restore
#line 35 "C:\Users\alexa\Desktop\Engenharia-Informatica-IPS\3º Ano\Anual\Programação Visual (PV)\Labs\Lab 3\CineSetúbal\Views\Filmes\Cartazes.cshtml"
Write(Html.ActionLink("Edit", "Edit", new { /* id = Model.PrimaryKey */ }));

#line default
#line hidden
#nullable disable
            WriteLiteral(" |\r\n    ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("a", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "e5d07d6e8571b5702b3c7da24833f5bc93a983c56982", async() => {
                WriteLiteral("Back to List");
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.AnchorTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_AnchorTagHelper.Action = (string)__tagHelperAttribute_0.Value;
            __tagHelperExecutionContext.AddTagHelperAttribute(__tagHelperAttribute_0);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("\r\n</div>\r\n");
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<IEnumerable<Filme>> Html { get; private set; }
    }
}
#pragma warning restore 1591
