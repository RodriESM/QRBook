<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="controlUsuario.ascx.cs" Inherits="QRBookWeb.ControlesUsuario.controlUsuario" %>

<link rel="stylesheet" href="../assets/css/objsearch.css">



<table class="tableBus">
    <tr>
        <td class="tditem" style="width: 56px; padding: 0">
            <asp:Image ID="imgusu" runat="server" ImageUrl="../img/user-img.png" Height="50" Width="50"/>
        </td>
        <td class="tditem" style="width: 40%">
            <table style="margin: auto; width: 100%">
                <tr>
                    <td>
                        <asp:Label ID="nomusu" runat="server" Text="Nombre y Apellidos" class="lblitem"></asp:Label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Label ID="usu" runat="server" Text="Nombre de Usuario" class="lblitem"></asp:Label>
                    </td>
                </tr>
            </table>
        </td>
        <td class="tditem" style="width: 40%">
            <asp:Label ID="email" runat="server" Text="Correo Electronico" class="lblitem"></asp:Label>
            <br />
        </td>
        <td class="tditem" style="width: 85px">
         <button id="btnmodif" class="btnitem" runat="server">
             <i class="fas fa-user-edit blanco"></i>
         </button>
         <asp:Label ID="lbldel" runat="server" Text="¿Eliminar?" class="lblitem"></asp:Label>
        </td>
        <td class="tditem" style="width: 60px; border-style: none">
         <button id="btnpredel" class="btnitemdel" runat="server"><i class="fas fa-user-minus blanco"></i></button>
         <button id="btndel" class="btnitemdel" runat="server"><i class="fas fa-user-minus blanco"></i></button>
         <button id="btncancel" class="btnitemdel" runat="server"><i class="fas fa-user-minus blanco"></i></button>
        </td>
    </tr>
</table>