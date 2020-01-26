CREATE SCHEMA IF NOT EXISTS `stockcontrol` DEFAULT CHARACTER SET utf8 ;
USE `stockcontrol` ;

CREATE TABLE IF NOT EXISTS `stockcontrol`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `Login` VARCHAR(150) NOT NULL,
  `Senha` VARCHAR(150) NOT NULL,
  `Tipo_Usuario` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `idUsuario_UNIQUE` (`idUsuario` ASC) VISIBLE,
  UNIQUE INDEX `Login_UNIQUE` (`Login` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`Endereco` (
  `idEndereco` INT NOT NULL AUTO_INCREMENT,
  `Logradouro` VARCHAR(250) NOT NULL,
  `Numero` VARCHAR(20) NULL,
  `Complemento` VARCHAR(150) NULL,
  `Referencia` VARCHAR(150) NULL,
  `Bairro` VARCHAR(150) NOT NULL,
  `Cidade` VARCHAR(150) NOT NULL,
  `Estado` CHAR(5) NOT NULL,
  `CEP` CHAR(10) NOT NULL,
  PRIMARY KEY (`idEndereco`),
  UNIQUE INDEX `idEndereco_UNIQUE` (`idEndereco` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`DadosPessoais` (
  `idDados_Pessoais` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(200) NOT NULL,
  `Email` VARCHAR(200) NULL,
  `Fixo` VARCHAR(20) NULL,
  `Celular` VARCHAR(20) NULL,
  `IdEndereco` INT NOT NULL,
  PRIMARY KEY (`idDados_Pessoais`),
  INDEX `fk_Dados_Pessoais_Endereco_idx` (`IdEndereco` ASC) VISIBLE,
  UNIQUE INDEX `idDados_Pessoais_UNIQUE` (`idDados_Pessoais` ASC) VISIBLE,
  CONSTRAINT `fk_Dados_Pessoais_Endereco`
    FOREIGN KEY (`IdEndereco`)
    REFERENCES `stockcontrol`.`Endereco` (`idEndereco`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `IdDadosPessoais` INT NOT NULL,
  `Doc_Identificacao` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE INDEX `idCliente_UNIQUE` (`idCliente` ASC) VISIBLE,
  INDEX `fk_Cliente_DadosPessoais_idx` (`IdDadosPessoais` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_DadosPessoais`
    FOREIGN KEY (`IdDadosPessoais`)
    REFERENCES `stockcontrol`.`DadosPessoais` (`idDados_Pessoais`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`Fornecedor` (
  `idFornecedor` INT NOT NULL AUTO_INCREMENT,
  `IdDadosPessoais` INT NOT NULL,
  PRIMARY KEY (`idFornecedor`),
  UNIQUE INDEX `idFornecedor_UNIQUE` (`idFornecedor` ASC) VISIBLE,
  INDEX `fk_Fornecedor_DadosPessoais_idx` (`IdDadosPessoais` ASC) VISIBLE,
  CONSTRAINT `fk_Fornecedor_DadosPessoais`
    FOREIGN KEY (`IdDadosPessoais`)
    REFERENCES `stockcontrol`.`DadosPessoais` (`idDados_Pessoais`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`Servico` (
  `idServicos` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idServicos`),
  UNIQUE INDEX `idServicos_UNIQUE` (`idServicos` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`Funcionario` (
  `idFuncionario` INT NOT NULL AUTO_INCREMENT,
  `IdUsuario` INT NOT NULL,
  `IdDadosPessoais` INT NOT NULL,
  PRIMARY KEY (`idFuncionario`),
  UNIQUE INDEX `idFuncionario_UNIQUE` (`idFuncionario` ASC) VISIBLE,
  UNIQUE INDEX `IdUsuario_UNIQUE` (`IdUsuario` ASC) VISIBLE,
  INDEX `fk_Funcionario_DadosPessoais_idx` (`IdDadosPessoais` ASC) VISIBLE,
  CONSTRAINT `fk_Funcionario_Usuario`
    FOREIGN KEY (`IdUsuario`)
    REFERENCES `stockcontrol`.`Usuario` (`idUsuario`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Funcionario_DadosPessoais`
    FOREIGN KEY (`IdDadosPessoais`)
    REFERENCES `stockcontrol`.`DadosPessoais` (`idDados_Pessoais`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`Partes` (
  `idPartes` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(200) NOT NULL,
  `Preco` FLOAT NOT NULL,
  PRIMARY KEY (`idPartes`),
  UNIQUE INDEX `idPartes_UNIQUE` (`idPartes` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`Tipo` (
  `idTipo` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idTipo`),
  UNIQUE INDEX `idTipo_UNIQUE` (`idTipo` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`Produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(200) NOT NULL,
  `Preco` FLOAT NOT NULL,
  `Genero` CHAR(1) NOT NULL,
  `Customizavel` TINYINT(1) NOT NULL,
  `IdTipo` INT NOT NULL,
  PRIMARY KEY (`idProduto`),
  UNIQUE INDEX `idProduto_UNIQUE` (`idProduto` ASC) VISIBLE,
  INDEX `fk_Tipo_Produto_Tipo_idx` (`IdTipo` ASC) VISIBLE,
  CONSTRAINT `fk_Tipo_Produto_Tipo`
    FOREIGN KEY (`IdTipo`)
    REFERENCES `stockcontrol`.`Tipo` (`idTipo`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`PartesProduto` (
  `idPartes_Produto` INT NOT NULL AUTO_INCREMENT,
  `IdPartes` INT NOT NULL,
  `IdProduto` INT NOT NULL,
  PRIMARY KEY (`idPartes_Produto`),
  UNIQUE INDEX `idPartes_Produto_UNIQUE` (`idPartes_Produto` ASC) VISIBLE,
  INDEX `fk_Partes_Produtos_Partes_idx` (`IdPartes` ASC) VISIBLE,
  INDEX `fk_Partes_Produto_Produto_idx` (`IdProduto` ASC) VISIBLE,
  CONSTRAINT `fk_PartesProduto_Partes`
    FOREIGN KEY (`IdPartes`)
    REFERENCES `stockcontrol`.`Partes` (`idPartes`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_PartesProduto_Produto`
    FOREIGN KEY (`IdProduto`)
    REFERENCES `stockcontrol`.`Produto` (`idProduto`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`Status` (
  `idStatus` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idStatus`),
  UNIQUE INDEX `idStatus_UNIQUE` (`idStatus` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`Pedido` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `Data` DATETIME NOT NULL,
  `ValorTotal` FLOAT NOT NULL,
  `IdStatus` INT NOT NULL,
  `IdCliente` INT NOT NULL,
  PRIMARY KEY (`idPedido`),
  UNIQUE INDEX `idPedido_UNIQUE` (`idPedido` ASC) VISIBLE,
  INDEX `fk_Pedido_Cliente_Status_idx` (`IdStatus` ASC) VISIBLE,
  INDEX `fk_Pedido_Cliente_idx` (`IdCliente` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_Status`
    FOREIGN KEY (`IdStatus`)
    REFERENCES `stockcontrol`.`Status` (`idStatus`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Pedido_Cliente`
    FOREIGN KEY (`IdCliente`)
    REFERENCES `stockcontrol`.`Cliente` (`idCliente`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`ItensPedido` (
  `idItens_Pedido` INT NOT NULL AUTO_INCREMENT,
  `IdProduto` INT NOT NULL,
  `IdPedido` INT NOT NULL,
  `Quantidade` INT NOT NULL,
  PRIMARY KEY (`idItens_Pedido`),
  UNIQUE INDEX `idItens_Pedido_UNIQUE` (`idItens_Pedido` ASC) VISIBLE,
  INDEX `fk_Itens_Pedido_Pedido_idx` (`IdPedido` ASC) VISIBLE,
  INDEX `fk_Itens_Pedido_Produto_idx` (`IdProduto` ASC) VISIBLE,
  CONSTRAINT `fk_ItensPedido_Pedido`
    FOREIGN KEY (`IdPedido`)
    REFERENCES `stockcontrol`.`Pedido` (`idPedido`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ItensPedido_Produto`
    FOREIGN KEY (`IdProduto`)
    REFERENCES `stockcontrol`.`Produto` (`idProduto`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`Estoque` (
  `idEstoque` INT NOT NULL AUTO_INCREMENT,
  `IdEndereco` INT NOT NULL,
  PRIMARY KEY (`idEstoque`),
  INDEX `fk_Estoque_Endereco_idx` (`IdEndereco` ASC) VISIBLE,
  CONSTRAINT `fk_Estoque_Endereco`
    FOREIGN KEY (`IdEndereco`)
    REFERENCES `stockcontrol`.`Endereco` (`idEndereco`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`Orcamento` (
  `idOrcamento` INT NOT NULL AUTO_INCREMENT,
  `IdPedido` INT NOT NULL,
  `Data` DATETIME NOT NULL,
  PRIMARY KEY (`idOrcamento`),
  INDEX `fk_Orcamento_Pedido_idx` (`IdPedido` ASC) VISIBLE,
  UNIQUE INDEX `idOrcamento_UNIQUE` (`idOrcamento` ASC) VISIBLE,
  CONSTRAINT `fk_Orcamento_Pedido`
    FOREIGN KEY (`IdPedido`)
    REFERENCES `stockcontrol`.`Pedido` (`idPedido`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`Venda` (
  `idVenda` INT NOT NULL AUTO_INCREMENT,
  `IdOrcamento` INT NOT NULL,
  `Data` DATETIME NOT NULL,
  PRIMARY KEY (`idVenda`),
  UNIQUE INDEX `idVenda_UNIQUE` (`idVenda` ASC) VISIBLE,
  INDEX `fk_Venda_Orcamento_idx` (`IdOrcamento` ASC) VISIBLE,
  CONSTRAINT `fk_Venda_Orcamento`
    FOREIGN KEY (`IdOrcamento`)
    REFERENCES `stockcontrol`.`Orcamento` (`idOrcamento`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`ProdutosEstoque` (
  `idProdutos_Estoque` INT NOT NULL AUTO_INCREMENT,
  `IdEstoque` INT NOT NULL,
  `IdProduto` INT NOT NULL,
  `Quantidade` INT NOT NULL,
  PRIMARY KEY (`idProdutos_Estoque`),
  INDEX `fk_Produto_Estoque_Estoque_idx` (`IdEstoque` ASC) VISIBLE,
  INDEX `fk_Produto_Estoque_Produto_idx` (`IdProduto` ASC) VISIBLE,
  UNIQUE INDEX `idProdutos_Estoque_UNIQUE` (`idProdutos_Estoque` ASC) VISIBLE,
  CONSTRAINT `fk_ProdutoEstoque_Estoque`
    FOREIGN KEY (`IdEstoque`)
    REFERENCES `stockcontrol`.`Estoque` (`idEstoque`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ProdutoEstoque_Produto`
    FOREIGN KEY (`IdProduto`)
    REFERENCES `stockcontrol`.`Produto` (`idProduto`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `stockcontrol`.`ServicoFornecedor` (
  `IdServiçoFornecedor` INT NOT NULL AUTO_INCREMENT,
  `IdServico` INT NOT NULL,
  `IdFornecedor` INT NOT NULL,
  `Valor` FLOAT NOT NULL,
  INDEX `fk_Servico_has_Fornecedor_Fornecedor1_idx` (`IdFornecedor` ASC) VISIBLE,
  INDEX `fk_Servico_has_Fornecedor_Servico1_idx` (`IdServico` ASC) VISIBLE,
  PRIMARY KEY (`IdServiçoFornecedor`),
  UNIQUE INDEX `IdServiçoFornecedor_UNIQUE` (`IdServiçoFornecedor` ASC) VISIBLE,
  CONSTRAINT `fk_ServicoFornecedor_Servico`
    FOREIGN KEY (`IdServico`)
    REFERENCES `stockcontrol`.`Servico` (`idServicos`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ServicoFornecedor_Fornecedor`
    FOREIGN KEY (`IdFornecedor`)
    REFERENCES `stockcontrol`.`Fornecedor` (`idFornecedor`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;

INSERT INTO Usuario(Login, Senha, Tipo_Usuario) VALUES('admin', '21232F297A57A5A743894A0E4A801FC3', 'Administrador');
