# -*- coding: utf-8 -*-
"""
Spyder Editor

Este é um arquivo de script temporário.
"""

import smtplib

print("Preparando email...")
smtp = smtplib.SMTP('smtp.cefetmg.br', 587)
smtp.starttls()

smtp.login('cesar@cefetmg.br', 'Noisebao32')

valor = 'texto'
assunto = 'Teste de email com Python'
de = 'cesar@cefetmg.br'
para = ['cesar@cefetmg.br']
msg = """From: %s
To: %s
Subject: %s

%s """ % (de, ', '.join(para), assunto, valor)

#print(msg)
smtp.sendmail(de, para, msg)

smtp.quit()
print("\nEmail enviado.")