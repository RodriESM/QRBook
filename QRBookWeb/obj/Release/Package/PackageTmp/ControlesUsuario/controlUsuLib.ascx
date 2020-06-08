<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="controlUsuLib.ascx.cs" Inherits="QRBookWeb.ControlesUsuario.controlUsuLib" %>

<link rel="stylesheet" href="../assets/css/objsearch.css">



<table class="tableBus">
    <tr>
        <td class="tditem" style="width: 56px; padding: 0">
            <asp:Image ID="imglib" runat="server" ImageUrl="../img/user-img.png" Height="80" Width="50" style="border-radius: 5px"/>
        </td>
        <td class="tditem" style="width: 100%">
            <asp:TextBox ID="titulo" runat="server" Text="Titulo libro" class="txtitem" ReadOnly="true" TextMode="MultiLine" Rows="2" style="overflow-x: hidden; padding: 0; border: none"/>
        </td>
        <td class="tditem" style="width: 85px; border-left: 2px solid #999999; border: none; padding-left: 7px" runat="server" id="tdModif">
         <button id="btnmodif" class="btnitem" runat="server" >
             <i class="fas fa-edit fa-lg blanco" style="margin: 7px 0"></i>
         </button>
        </td>
    </tr>
</table>