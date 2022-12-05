<%@ attribute name="size" required="true" rtexprvalue="true" description="Size of the piece to show" %>
<%@ attribute name="piece" required="true" rtexprvalue="true" description="Piece to be rendered" %>
 
image = document.getElementById('${piece.type}-${piece.color}');
ctx.drawImage(image,${piece.getPositionXInPixels(size)},${piece.getPositionYInPixels(size)},${size},${size});
