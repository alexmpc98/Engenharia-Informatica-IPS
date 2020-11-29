// ETAPA 3

// 1
db.produtos.find();

// 2
db.produtos.aggregate([{$match: {categoria:"Accessories"}} ])
    
// 3    
db.clientes.find( { "comentarios.email": "eugene10@adventure-works.com" }, {comentarios:1} )

db.clientes.aggregate([
   {
     $lookup:
       {
         from: "clientes",
         localField: "_id",
         foreignField: "comentarios.prod_id",
         as: ", {$group}"
       }
       $elemMatch: { "comentarios.email": "eugene10@adventure-works.com" }
       
  }, {$group:{
      _id: { x : "$x" },
      y: { $first : "$y" 
          
      }}}
])

//4
db.clientes.aggregate([
   {
     $lookup:
       {
         from: "clientes",
         localField: "_id",
         foreignField: "comentarios.prod_id",
         as: "comentarios"
       },
       $elemMatch: { "comentarios": { "$exists": false }}
       
  }
])

db.produtos.find( { "comentarios": { $size: 0 } } )


// 5
db.clientes.aggregate(
   [
      {
         $project: {
            email:1,
            numero_comentarios: { $size: "$comentarios" }
         }
      }
   ]
)

// 6
db.clientes.aggregate(
   [
      {
         $project: {
            email:1,
            numero_comentarios: { $size: "$comentarios" }
         }
      },
      {
          $group: {
              _id: email,
              avgQuantity: { $avg: "$comentarios.rate" }
          }
      }
   ]
)

// 7
db.produtos.insert({comentarios[{email:"alexandra10@adventure-works.com", texto:"Recomendo!", classificacao:4, data:"2018-10-22"}]})

// 8
db.produtos.update({"comentarios.email": "alexandra10@adventure-works.com"}, {"$set": {"comentarios": []}})

// 9



