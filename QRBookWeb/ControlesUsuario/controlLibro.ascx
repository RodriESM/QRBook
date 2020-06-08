<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="controlLibro.ascx.cs" Inherits="QRBookWeb.ControlesUsuario.controlLibro" %>

<link rel="stylesheet" href="../assets/css/objsearch.css">



<table class="tableBus">
    <tr>
        <td class="tditem" style="width: 76px; padding: 0">
            <asp:Image ID="imglib" runat="server" ImageUrl="../img/book-img.png" Height="100" Width="70" style="border-radius: 5px"/>
        </td>
        <td class="tditem" style="width: 100%">
            <table style="margin: auto; width: 100%">
                <tr>
                    <td>
                        <asp:TextBox ID="titulo" runat="server" Text="Titulo libro" class="txtitem" ReadOnly="true" TextMode="MultiLine" Rows="2" style="overflow-x: hidden"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:TextBox ID="autor" runat="server" Text="Autor" class="txtitem" ReadOnly="true" style="height: 40px; border-style: none; padding: 0; white-space: nowrap; overflow-x: auto" TextMode="MultiLine" Rows="1"></asp:TextBox>
                    </td>
                </tr>
            </table>
        </td>
        <td class="tditem" style="width: 100%; border-style: none;">
            <table style="margin: auto; width: 100%; table-layout: fixed">
                <tr>
                    <td style="width: 20%">
                        <label class="lblitem">Editorial:</label>
                    </td>
                    <td>
                        <asp:Label ID="editorial" runat="server" Text="Editorial" class="lblitem"></asp:Label>
                    </td>
                </tr>
                <tr>
                    <td style="width: 20%">
                        <label class="lblitem">ISBN:</label>
                    </td>
                    <td>
                        <asp:Label ID="isbn" runat="server" Text="ISBN" class="lblitem"></asp:Label>
                    </td>
                </tr>
                <tr>
                    <td style="width: 20%">
                        <label class="lblitem">Genero:</label>
                    </td>
                    <td>
                        <asp:Label ID="genero" runat="server" Text="Genero" class="lblitem"></asp:Label>
                    </td>
                </tr>
            </table>
        </td>
        <td class="tditem" style="width: 85px; border-left: 2px solid #999999; border-right: none; padding-left: 7px" runat="server" id="tdModif">
         <button id="btnmodif" class="btnitem" runat="server" >
             <i class="fas fa-edit fa-lg blanco" style="margin: 7px 0"></i>
         </button>
        </td>
    </tr>
</table>